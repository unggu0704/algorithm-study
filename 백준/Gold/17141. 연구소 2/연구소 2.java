import java.util.*;

class lab2{
	Scanner s = new Scanner(System.in);
	room[][] labo_map, backup_map;
	boolean[][] bfs_flag;
	ArrayList<room>[] arr;
	int N, M;
	boolean check_virus = false , tmp_check = true;
	Queue<room> Q = new LinkedList<>();
	Stack<room> memory_Q = new Stack<>();
	int recur_count = 0, time = 0, nMin = 9999999;;
	long beforeTIme;
	int f_x, f_y, l_x, l_y;
	lab2(){
		N = s.nextInt();  M= s.nextInt();
		boolean flag = false;
		labo_map = new room[N][N]; backup_map = new room[N][N]; 
		arr = new ArrayList[M];
		for(int i = 0; i<M; i++) {
			arr[i] = new ArrayList<room>();
		}
		bfs_flag = new boolean[N][N];
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < N; j++) {
				int input =s.nextInt();
				backup_map[i][j] = new room(input, i , j);
				if(!flag && input == 2) {
					flag = true;
					f_x = i; f_y = j;
				}
				else if(input == 2) {
					l_x = i; l_y = j;
				}
				
			}
		}
		beforeTIme = System.currentTimeMillis(); 
		init_Copy(labo_map);
		simulate();
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTIme); //두 시간에 차 계산
	//	System.out.println("시간차이(m) : "+secDiffTime + "ms");
	}
	
	void simulate() {		
		init_flag(bfs_flag);
		virus_infect(f_x,f_y);
		if(!check_virus) {
			System.out.println(-1);
		}
		else {
			System.out.println(nMin);
		}
	}
	
	
	 int virus_infect(int prev_x, int prev_y) {
		 		int i = prev_x ,j = prev_y;
		//		System.out.println("현재 재귀 번호: " + recur_count);
		//		System.out.println("다음  감염 계산할 지점 i: " + i + " j: " + j);
				if(recur_count == M) {
					Iterator iter = memory_Q.iterator();
					make_wall();
					while(iter.hasNext()) {
						room a = (room) iter.next();
						labo_map[a.x][a.y].time = 0;
					//	print_map(bfs_flag);
						bfs_flag[a.x][a.y] =false;
					//	print_map(bfs_flag);
						Q.add(a);
					//	System.out.print(a.toString() + " ");
					}
			//		System.out.println();
				//	print_map(bfs_flag);
					infect_simulate();
			//		print_map(labo_map);
					init_Copy(labo_map);
			//		print_map(labo_map);
					recur_count--;
					
					memory_Q.pop();
					if(i != l_x || j != l_y) virus_infect(i,j);
					return 0;
				}
				
				while(i != N) {
					while(j != N) {
						if(labo_map[i][j].infor == 2) {
					//		System.out.println("다음 감염: " + i + "," + j + "재귀 번호: " + recur_count);
							memory_Q.push(labo_map[i][j]);
							recur_count++;
							
							if(i == l_x && j == l_y) { // 마지막 감염 지점이
								virus_infect(i,j);
								if(!memory_Q.isEmpty()) {
									memory_Q.pop();
								}
								recur_count --;
								return 0;
							}
							
							if( j != N-1 ) { j = j+1; }
							else if(i != N-1 && j == N-1){  i= i + 1; j =0;}	
							
							if(recur_count == M) {
								virus_infect(i,j); return 0;	
							}
							virus_infect(i,j); // 0,1 1, 6 
						}	
						j++;
					}
					j =0;
					i++;
				}
				return 0;
	}
	
	int infect_simulate() {
		if(Q.isEmpty()) {
			calculate_time(time);
			time = 0;
			return 0;
		}
		int x = 0 , y = 0 , cnt = Q.size();
		while(true) {
			if(cnt == 0) {
				if(!Q.isEmpty()) {
					time++;
				}
			/*	if(time > nMin) {
					Q.clear();
					time = 0;
					return 0;
				}*/
				break;
			}
			room a = Q.poll();
			x = a.x; y = a.y;
			labo_map[x][y].time = time;
		//	bfs_flag[x][y] = false;
		//	System.out.println("a: " + a.toString() +"의 시간을 " + labo_map[x][y].time + "으로 바꿈 ");
		//	print_map(bfs_flag);
			bfs_flag[x][y] = false;
			if(x > 0 && bfs_flag[x-1][y] ) {
				Q.add( labo_map[x-1][y] );
				labo_map[x-1][y].time = time;
				bfs_flag[x-1][y] = false;
			}
			
			if(x != N-1 && bfs_flag[x+1][y] ) {
				Q.add( labo_map[x+1][y] );
				labo_map[x+1][y].time = time;
				bfs_flag[x+1][y] = false;
			}
			
			if(y > 0 && bfs_flag[x][y-1] ) {
				Q.add( labo_map[x][y-1] );
				labo_map[x][y-1].time = time;
				bfs_flag[x][y-1] = false;
			}
			
			if( y != N-1 && bfs_flag[x][y+1]) {
				Q.add( labo_map[x][y+1] );
				labo_map[x][y+1].time = time;
				bfs_flag[x][y+1] = false;
			}
			cnt--; 
		}
		infect_simulate();
		return 0;
	}
	
	void init_flag(boolean[][] m) {
		for(int i =0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				m[i][j] = true;
			}
		}
	}
	
	void calculate_time(int t) {
		
		loop:
		for(int i =0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(bfs_flag[i][j]) {
			//		System.out.println("퍼지지 못한 곳의 위치는 " + i + ", " + j);
					tmp_check = false; // 최저 계산 하지 마!바이러스가 퍼지지 않은 곳이 있을
					break loop;
				}
			}
		}
		
		if(tmp_check) {
			check_virus =true;
			nMin = Math.min(t, nMin);
	//		System.out.println("최솟값: " + nMin);
		}
		tmp_check = true;
	}
	
	void init_Copy(room[][] m) {
		for(int i = 0; i<N; i++) {
			for(int j =0; j<N; j++) {
				room l = (room) backup_map[i][j].clone();
				m[i][j] = l;
			}
		}	
	}
	
	void print_map(room m[][]) {
		for(int i =0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(m[i][j].time + " ");
			}	
			System.out.println();
		}
		System.out.println();
	}
	
	void print_map(boolean m[][]) {
		for(int i =0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(m[i][j] + " ");
			}	
			System.out.println();
		}
		System.out.println();
	}
	
	void make_wall() {
		for(int i =0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(labo_map[i][j].infor == 1 ) {
					bfs_flag[i][j] = false;
				}
				else {
					bfs_flag[i][j] = true;
				}
			}
		}
	}
}

class room implements Cloneable {
	int infor, x ,y, time = 0;
	
	room(int s, int x, int y){
		infor = s;
		this.x = x; this.y = y;
	}
	public Object clone()  {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return "x = " + x + " y = " + y + " | ";
	}
}

public class Main {

	public static void main(String[] args) {
		lab2 l = new lab2();

	}

}
