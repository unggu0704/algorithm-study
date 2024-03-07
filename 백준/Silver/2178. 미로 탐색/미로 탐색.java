import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	static int ans = 0;
	static Queue<node> queue = new LinkedList<node>();
	static int N, M;
	static node map[][];
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken()); // 세로 길이 
    	M = Integer.parseInt(st.nextToken()); // 가로 길이 
    	
    	map = new node[N][M]; 
    	int y,x;
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		String str = st.nextToken();
    		for(int j = 0 ; j < M; j++) {
    			int road = (int) str.charAt(j) - '0';
    			node n = new node(i,j);
    			map[i][j] = n;
    			if(road == 1) map[i][j].visited = true;
    		}
    	}
    	// 입력 처리 
    	
    	queue.add(map[0][0]);
		map[0][0].visited = false; // 방문 처리 
		start_BFS();
    	
    //	System.out.println(ans);
    	
    }
    
    static int[] DX = {1,0,-1,0};
    static int[] DY = {0,-1,0,1};
    static boolean flag = false;
    static int qDeep = 1;
    public static void start_BFS() {
    	int result = 1;
    	while(!queue.isEmpty()) {	
    		BFS();
    		qDeep--;
    		if(qDeep == 0) {
    			qDeep = queue.size(); 	
    			result++;// 깊이를 설정 
    			if(flag) break;
    		}
    		
    	}
    	System.out.println(result);
    }
    
    public static void BFS() {
    	node n = queue.poll();
    	
    	for(int i = 0; i <4; i++) {
    		int newY = n.y + DY[i];	int newX = n.x + DX[i];
    		
    		if(newY <= -1 || newX <= -1 || newY >= N || newX >= M || !map[newY][newX].visited) continue;
    		if(newY == N-1 && newX == M-1) flag =true;
    		queue.add(map[newY][newX]);
    		map[newY][newX].visited = false;
    	}
    }
    	
}

class node{
	int y,x;
	boolean visited = false;
	
	node(int y, int x){
		this.y = y;
		this.x = x;
	}
}



