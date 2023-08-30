import java.io.*;
import java.util.*;



public class Main {
	public static void main(String[] args) throws IOException{
        solution();
	}
	static int index = 1;
	static int N, Time;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static Queue<Node> q = new LinkedList<>();
	static int[] DX = {0,-1,1,0};
	static int[] DY = {-1,0,0,1};
	static boolean ate;
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
       
        Node[][] map = new Node[N][N];
        Node babyShark = null;
        boolean flag = false;
        
        for(int i = 0; i < N; i++) {
        	st =new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		int input =  Integer.parseInt(st.nextToken());
        		Node n = new Node(input,i,j);
        		if(input == 9) {
        			babyShark = n;
        			babyShark.changeShark(2, 0);
        			n.size = 0;
        		} // 아기 상어의 초기 위치 저장 
        		map[i][j] = n;
        	}
        }// 입력 종료     
        
     
        while(true) {
        	if(flag) break;
        	int sharkSize = babyShark.SharkSize; // 새로운 사이즈를 받아 드림    
        	int eat = babyShark.eatCount;
        	int distance = 0;
            q.add(babyShark);
            babyShark.visited = true;
        	loop:
        	while(true) {
        		if(q.isEmpty()) {
        			flag = true; // 더이상 먹지 못하고 큐가 바닥났다는 것 -> 더이상 먹을게 없어요! 
        			break;
        		}     		
        		int queueCount = q.size();
        		for(Node n : q) {
        			pq.add(n); // 우선 순위 정리 
        		}
        		q.clear();
        		for(int i = 0; i < queueCount; i++) {
        			Node thisNode = pq.poll();
        			
        			if(thisNode.ate == true) { // 해당 노드가 먹힌 노드라면 
        				babyShark.changeSpace(); // 이전에 객체는 원래대로 돌려놈 
        				babyShark = thisNode; // 상어 변수 재 할당 
        				babyShark.changeShark(sharkSize, eat + 1);
        				
        				Time += distance; // 시간 저장 
        				pq.clear();
        				q.clear();
        				resetVisited(map); // 방문 초기화 
        				break loop; //BFS 탈출 
        			}
        			
        			BFS(thisNode, map , sharkSize);
        		}
        		distance++; // 먹이를 못찾았다면 거리를 늘림 
        	} 		
        }
     
        System.out.println(Time);
    }
    
    public static void BFS(Node n, Node[][] map, int babySharkSize) {
    	
    	for(int i = 0; i < 4; i++) {
    		int mx = n.x + DX[i];	int my = n.y + DY[i];
    		if(mx <= -1 || my <= -1 || mx >= N || my >= N) continue; // 경계 값 체크 
    		Node space = map[my][mx];
    		if(space.visited || space.size > babySharkSize) continue;// 공간이 너무 커서 가지 못하거나, 이미 방문한 공간이면 생략 
    		
    		if(!space.Shark && space.size != 0 && space.size < babySharkSize) 	map[my][mx].ate = true;// 빈공간이 아니고 자기보다 사이즈가 작으면 먹는다    																				
    		map[my][mx].visited = true;
    		q.add(map[my][mx]);    		
    	}
    }
    
    public static void resetVisited(Node[][] map) {
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			map[i][j].visited = false;
    			map[i][j].ate = false;
    		}
    	}
    }
}

class Node implements Comparable<Node>{
	int size;
	boolean visited;
	int y,x;
	
	boolean Shark = false;
	int SharkSize = 0;
	int eatCount = 0;
	
	boolean ate = false;
	Node(int n,int y, int x){
		this.size = n;
		this.y = y;	
		this.x = x;
	}
	
	void changeShark(int s, int e) {
		Shark = true;
		size = 0;
		if(s <= e) {
			SharkSize = s + 1;
			eatCount = 0;
		}
		else{
			SharkSize = s;
			eatCount = e;
		}
	}
	
	void changeSpace() {
		size = 0;
		Shark = false;
		SharkSize = 0;
		eatCount = 0;
	}
	
	@Override
	public int compareTo(Node o) {
		if(this.y < o.y) return -1;
		else if(this.y == o.y) {
			if(this.x < o.x) return -1;
			else return 1;
		}
		else return 1;
	}
	
}
