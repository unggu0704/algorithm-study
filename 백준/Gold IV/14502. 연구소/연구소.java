import java.util.*;

class Circle_Queue{  //원형큐 클래스 
	public int top =0, tail = 0; // 원형큐의 머리와 꼬리 
	private node queue[] = new node[1000000]; //원형큐의 사이즈 
	public int q_size = 0;
	Circle_Queue() {
	}
	
	void enQueue(node input_node) {
		if(is_full()) {
			System.out.println("큐가 가득 찾습니다.");
			throw new IllegalStateException();
		}q_size++;
		queue[top] = input_node;
		top++;
		top =top % 1000000;
	}
	
	node deQueue() {
		node tmp;
		if(is_empty()) {
			System.out.println("큐가 비었습니다.");
			throw new IllegalStateException();
		}q_size--;
		tmp = queue[tail];
		tail++;
		tail =tail % 1000000;
		return tmp;
	}
	
	boolean is_full() {
		if((top+1)%1000000 == tail) {	return true;	}
		else{	return false;	}
	}
	
	boolean is_empty() {
		if( (tail%1000000)  == top) { return true; }
		else { return false; }
	}
}

class node implements Cloneable {
	private int node_information =0;
	public int x, y;
	public boolean visited1, visited2, visited3, visited_virus; //bfs 갯수가 총 4개이기 때문에 방문표시도 4개로 설정
	
	node(int s,int x ,int y){ // 생성자 
		node_information = s; this.x = x; this.y = y;
		visited1 = false;
		visited2 = false;
		visited3 = false;
		visited_virus = false;
	}
	int get_information(){	return node_information;	}
	void set_information(int i) { this.node_information = i ; }
	
	@Override //배열의 깊은 복사를 위한 클론 메소드 오버라이드 
	public Object clone()  {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
}

class map{	
	Scanner s = new Scanner(System.in);
	node[][] labo_map, tmp_map;
	Circle_Queue Q1, Q2, Q3, Q;
	int m ,n, Max_count = 0;
	ArrayList<Integer> virus_array = new ArrayList<>();   

	map(){
		Q1 = new Circle_Queue();
		Q2 = new Circle_Queue();
		Q3 = new Circle_Queue();
		Q = new Circle_Queue(); // 큐 생성 
		m = s.nextInt(); n = s.nextInt();
		labo_map = new node[m][n];
		tmp_map = new node[m][n];
		int input = 0;
		for(int i = 0; i<m; i++) { //연구소 맵 입력 
			for(int j =0; j<n; j++) {
				input = s.nextInt();
				node new_node = new node(input,i,j);
				node bckup_node = new node(input,i,j);
				labo_map[i][j] = new_node; // 무결성을 유지할 맵 
				tmp_map[i][j] = bckup_node; // 시뮬레이션을 돌릴 맵 
				if(input == 2) {
					virus_array.add(i); virus_array.add(j); //바이러스가 어디 있는지 기록 
				}
			}
		}
		run();
		//run_BFS(0);
		System.out.print(Max_count);
	}
	void run() {
		Q1.enQueue(labo_map[0][0]);
		while(!Q1.is_empty()) {
			node tmp_node = Q1.deQueue();
			tmp_node.visited1 = true;
			bfs1(tmp_node);
		}
	}
	
	/*
	void run_BFS(int key){
		loop:
		for(int i = 0; i<m; i++) {
			for(int j =0; j<n; j++) {
				int state = labo_map[i][j].get_information(); //해당 위치의 상태를 불러
				if(key == 0 && state == key) {
					Q1.enQueue(labo_map[i][j]);
					while(!Q1.is_empty()) {
						node tmp_node = Q1.deQueue();
						bfs1(tmp_node);
						tmp_node.visited1 = true;
					}
					break loop;

				}
			}
		}
		
	}
	*/
	
	void bfs1(node bfs1_node) {
		int x1 = bfs1_node.x; int y1 = bfs1_node.y;
		if(x1 != 0 && !labo_map[x1-1][y1].visited1 ) {
			Q1.enQueue(labo_map[x1-1][y1]);
			Q2.enQueue(labo_map[x1-1][y1]);
			while(!Q2.is_empty()) {
				bfs2(bfs1_node, Q2.deQueue());
			}reset_Flag(2);
			
		}
		if(y1 != 0 && !labo_map[x1][y1-1].visited1) {
			Q1.enQueue(labo_map[x1][y1-1]);
			Q2.enQueue(labo_map[x1][y1-1]);
			
			while(!Q2.is_empty()) {
				bfs2(bfs1_node, Q2.deQueue());
			}reset_Flag(2);
		}
		if(x1 != m-1 && !labo_map[x1+1][y1].visited1) {
			Q1.enQueue(labo_map[x1+1][y1]);
			Q2.enQueue(labo_map[x1+1][y1]);	
			while(!Q2.is_empty()) {
				bfs2(bfs1_node, Q2.deQueue());
			}reset_Flag(2);
			
		}
		if(y1 != n-1 && !labo_map[x1][y1+1].visited1) {
			Q1.enQueue(labo_map[x1][y1+1]);
			Q2.enQueue(labo_map[x1][y1+1]);		
			while(!Q2.is_empty()) {
				bfs2(bfs1_node, Q2.deQueue());
			}reset_Flag(2);
		
		}


		
	}
	
	void bfs2(node bfs1_node, node bfs2_node) {
		int x2 = bfs2_node.x; int y2 = bfs2_node.y;
		bfs2_node.visited2 = true;
		if(x2 != 0 && !labo_map[x2-1][y2].visited1 && !labo_map[x2-1][y2].visited2 ) {
			Q2.enQueue(labo_map[x2-1][y2]);
			Q3.enQueue(labo_map[x2-1][y2]);	
			
			while(!Q3.is_empty()) {
				bfs3(bfs1_node, bfs2_node, Q3.deQueue());
			}reset_Flag(3);
		} 
		if(y2 != 0 && !labo_map[x2][y2-1].visited1 && !labo_map[x2][y2-1].visited2) {
			Q2.enQueue(labo_map[x2][y2-1]);
			Q3.enQueue(labo_map[x2][y2-1]);	
			
			while(!Q3.is_empty()) {
				bfs3(bfs1_node, bfs2_node, Q3.deQueue());
			}reset_Flag(3);
		} 
		if(x2 != m-1 && !labo_map[x2+1][y2].visited1 && !labo_map[x2+1][y2].visited2 ) {
			Q2.enQueue(labo_map[x2+1][y2]);
			Q3.enQueue(labo_map[x2+1][y2]);	
			
			while(!Q3.is_empty()) {
				bfs3(bfs1_node, bfs2_node, Q3.deQueue());
			}reset_Flag(3);
		} 
		if(y2 != n-1 && !labo_map[x2][y2+1].visited1 && !labo_map[x2][y2+1].visited2  ) {
			Q2.enQueue(labo_map[x2][y2+1]);
			Q3.enQueue(labo_map[x2][y2+1]);	
			
			while(!Q3.is_empty()) {
				bfs3(bfs1_node, bfs2_node, Q3.deQueue());
			}reset_Flag(3);
		} 
	

		 
	}
	
	void bfs3(node bfs1_node, node bfs2_node, node bfs3_node) {
		int x3= bfs3_node.x; int y3 = bfs3_node.y; 
		
		if(x3 != 0 && !labo_map[x3-1][y3].visited1 && !labo_map[x3-1][y3].visited2 && !labo_map[x3-1][y3].visited3  )  {
			Q3.enQueue(labo_map[x3-1][y3]);
			labo_map[x3-1][y3].visited3 = true;
		}  
		if(y3 != 0 && !labo_map[x3][y3-1].visited1 && !labo_map[x3][y3-1].visited2  && !labo_map[x3][y3-1].visited3 ) {
			Q3.enQueue(labo_map[x3][y3-1]);
			labo_map[x3][y3-1].visited3 = true;
		} 
		if(x3 != m-1 && !labo_map[x3+1][y3].visited1 && !labo_map[x3+1][y3].visited2 && !labo_map[x3+1][y3].visited3 ) {
			Q3.enQueue(labo_map[x3+1][y3]);
			labo_map[x3+1][y3].visited3 = true;
		} 
		if(y3 != n-1 && !labo_map[x3][y3+1].visited1 && !labo_map[x3][y3+1].visited2 && !labo_map[x3][y3+1].visited3  ) {
			Q3.enQueue(labo_map[x3][y3+1]);
			labo_map[x3][y3+1].visited3 = true;
		} 
		
		int x1 = bfs1_node.x;  int x2 = bfs2_node.x; int y1 = bfs1_node.y; int y2 = bfs2_node.y;
		if((bfs1_node.get_information() == 0 && bfs2_node.get_information() ==0 && bfs3_node.get_information() == 0)
				&& !( (x1 == x2 && y1 == y2) || (x2 == x3 && y2 == y3) ||(x1 == x3 && y1 == y3) ) ) {
				is_Infection(bfs1_node, bfs2_node,bfs3_node); //벽 3개를 세울 노드를 찾았다
		}
	}
	
	void is_Infection(node node1, node node2, node node3) {
		int count = 0; int x1, x2, x3, y1, y2, y3; 
		x1 = node1.x; x2 = node2.x; x3 = node3.x; y1 = node1.y; y2 = node2.y; y3 = node3.y;
		init_Copy(labo_map); // 임시맵으로 복사 
		tmp_map[x1][y1].set_information(1); tmp_map[x2][y2].set_information(1); tmp_map[x3][y3].set_information(1);		//System.out.println(x1 + "," +y1 + " | " + x2 + "," + y2 + " | " + x3 +"," +y3 + " 에 벽을 세움 ");
		count_infection();
		for(int i = 0; i<m; i++) {
			for(int j =0; j<n; j++) {
				if(tmp_map[i][j].get_information() == 0) {
					count++;
				}
			}
		}
		if(Max_count < count) {		
	//	System.out.println("max" + x1 + ", " + y1 + " | " + x2 + ", " +y2 + " | " + x3 + ", " + y3);	print_map(tmp_map);
			Max_count = count; 
		}
	}
	
	void count_infection() {
		int i, j;
		for(int index = 0; index<virus_array.size(); index++) {
			i = virus_array.get(index);  
			index++;
			j = virus_array.get(index);
			Q.enQueue(tmp_map[i][j]); // 2가 있는 곳을 불러와서 하나하나 감염 시작 
			while(!Q.is_empty()) {
				bfs_infection(Q.deQueue());
			}
		}
	}
	
	void bfs_infection(node virus_node) {
		int x = virus_node.x; int y = virus_node.y;
		if(x != 0  && tmp_map[x-1][y].get_information() == 0) {
			Q.enQueue(tmp_map[x-1][y]); 
			tmp_map[x-1][y].set_information(2); //감염됨 
		//	tmp_map[x-1][y].visited_virus = true;
		} 
		if(y != 0 && tmp_map[x][y-1].get_information() == 0 ) {
			Q.enQueue(tmp_map[x][y-1]); 
			tmp_map[x][y-1].set_information(2); //감염됨
		//	tmp_map[x][y-1].visited_virus = true;
		} 
		if(x != m-1 && tmp_map[x+1][y].get_information() == 0 ) {
			Q.enQueue(tmp_map[x+1][y]); 
			tmp_map[x+1][y].set_information(2); //감염됨
//			tmp_map[x+1][y].visited_virus = true;
		} 
		if(y != n-1  && tmp_map[x][y+1].get_information() == 0 ) {
			Q.enQueue(tmp_map[x][y+1]); 
			tmp_map[x][y+1].set_information(2); //감염됨
		//	tmp_map[x][y+1].visited_virus = true;
		} 
	}
	
	void init_Copy(node[][] origin) {
		for(int i = 0; i<m; i++) {
			for(int j =0; j<n; j++) {
				node tmp_node = (node) labo_map[i][j].clone();
				tmp_map[i][j] = tmp_node;
			}
		}	
	}
	
	void print_map(node[][] map) {
		for(int i = 0; i<m; i++) {
			for(int j =0; j<n; j++) {
				System.out.print(map[i][j].get_information() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	void reset_Flag(int key) {
		for(int i = 0; i<m; i++) {
			for(int j =0; j<n; j++) {
				if(key == 2) { labo_map[i][j].visited2 = false; 	} //bfs2 가 끝나면 모든 방문 마크를 초기화 시node
				else if(key == 3) labo_map[i][j].visited3 = false;	
			}
		}
	}
	
}

public class Main {
	public static void main(String args[]) {
		long beforeTime = System.currentTimeMillis();
		map m = new map();
	//	time(beforeTime);
	}
	
	public static void time(long beforeTime) {
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("\n" + secDiffTime + "ms");

	}
}	

