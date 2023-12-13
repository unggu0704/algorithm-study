import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}

	static ArrayList<Node> virus = new ArrayList<>();
	static Node[] v;
	static Node map[][];
	static int N,M;
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	map = new Node[N][N];
    	v = new Node[M];
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < N; j++) {
    			int input =  Integer.parseInt(st.nextToken());
    			Node n = new Node(input, i,j);
    			
    			if(input ==1) {
    				n.flag = true; 
    				n.infact = 1; // 갈 수 없으니 영구적인 방문 표시 또한 벽도 감염 처리 
    			}
    			else if(input == 2) {
    				virus.add(n); 
    				n.infact = 1;// 바이러스 리스트 저장 및 감염 처리 
    			}
    			
    			map[i][j] = n;
    		}
    	}// 입력 종료 
    	
    
    	dfs(0,0,M);
    	
    	if(ans == 1000000000) System.out.println(-1);
    	else System.out.println(ans);
    }
    static int time;
    static int ans = 1000000000;
    static Queue<Node> virusQueue = new LinkedList<>();
    static int qsize = 0;
    public static void dfs(int at, int depth, int M) {
    	
    	if(depth == M) {
    		time = 0;
    		virusQueue.clear();
    		for(Node n : v) {
    			n.flag = true;
    			virusQueue.add(n);
 //   			System.out.print(n.y + "," + n.x + "|");
    		}
    //		System.out.println();
    		while(!virusCheck() && !virusQueue.isEmpty()) { // 아직 감염되지 않은 곳이 있으면서 큐가 아직 있을때만 		
    			qsize = virusQueue.size();
    			time++;
    			for(int i = 0; i < qsize; i++) {
	    			Node n = virusQueue.poll();
	    			bfs(n);
    			}
    		}
    		// bfs 종료 
    		
    		if(virusCheck()) { //n
    			ans = Math.min(ans, time);
    		}
    		initMap(); // 맵을 초기화함 n
    		return;
    	}
    	
    	for(int i = at; i < virus.size(); i++) {
    		v[depth] = virus.get(i);
    		
    		dfs(i+1, depth+1, M);
    	}
    }
    
    static int[] DX = {-1,0,1,0};
    static int[] DY = {0,1,0,-1};
    
    public static void bfs(Node n) {
    	
    	int x,y;
    	for(int i = 0; i < 4; i++) {
    		x = n.x + DX[i];	y= n.y + DY[i];
    		
    		if(x <= -1 || y <= -1 || x >= N || y >= N || map[y][x].flag) continue;
    	
    		map[y][x].flag = true;
    		map[y][x].infact = 1;
    		virusQueue.add(map[y][x]);	
    	}
    }
    
    public static boolean virusCheck() {
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(map[i][j].infact == 0) {
    				return false; // 아직 감염이 되지 않은 곳이 있네요 . 
    			}
    		}
    	}
    	return true; // 전부 감연 됨 
    }
    
    public static void initMap() {
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(map[i][j].state != 1) {
    				map[i][j].flag = false; //감염 초기화 
    			}
    			if(map[i][j].state == 0) { // 일반 공간이면 
    				map[i][j].infact = 0; //감염 초기화 
    			}
    		}
    	}
    	
    }
    
    public static void printMap() {
    	for(int i = 0; i < N; i++) {
    		 System.out.print("\n");
    		for(int j = 0; j < N; j++) {
    			System.out.print(map[i][j].infact);
    		}
    	}
    	 System.out.print("\n");
   
    }

}

class Node{
	
	int y,x;
	boolean flag;
	int infact; // 빈공간에는 항상 0이 있어야함 
	int state; // 0이면 빈공간, 1이면 벽 2이면 비 활성 바이러스 
	Node(int s, int y, int x){
		infact = s;
		state = s;
		this.y = y;
		this.x = x;
	}
	
}

