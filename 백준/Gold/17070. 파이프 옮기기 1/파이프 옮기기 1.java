import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	static Node[][] map;
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

    	N = Integer.parseInt(st.nextToken());
    	int ans = 0;
    	
    	map = new Node[N][N];
    	int input;
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < N; j++) {
    			input = Integer.parseInt(st.nextToken());
    			Node n = new Node(i,j,input); // xy 입력 
    			
    			map[i][j] = n;
    		}
    	}
    	
    	if(map[N-1][N-1].count == 1 || ( map[N-2][N-1].count == 1 && map[N-1][N-2].count == 1 )  ) {
    		System.out.println(0);
    		System.exit(0);
    	}
    		
    	
    	
    	map[0][1].state = 0; // 누운 상태로 시작한다. 
    	
    	Queue<Node> q = new LinkedList<>();
    	Queue<Integer> Dq = new LinkedList<>();
    	
    	q.add(map[0][1]);
    	Dq.add(0);
    	
    	int state;
    	while(!q.isEmpty()) {
    		Node n = q.poll();
    		state = Dq.poll();
    		if(n.y == N-1 && n.x == N-1) { // 도달하면 종료 
    			ans++;
    			continue;
    		}
   
    		
    		// 갈수 있는 방향에 따라 벽이 있는지 체크, 경계 면인지 체크 
    		
    		if(n.x + 1 < N && !map[n.y][n.x + 1].flag && state != 1 ) { 
				Dq.add(0); //  가로 
				q.add(map[n.y][n.x + 1]);
			}
			if(n.y + 1 < N && !map[n.y + 1][n.x].flag && state != 0 ) {
				Dq.add(1); // 세로 
				q.add(map[n.y+1][n.x]);
			}
    		
    		if(n.y + 1 < N && n.x + 1 < N && !map[n.y+ 1][n.x].flag  && !map[n.y][n.x+1].flag
					&& !map[n.y+1][n.x+1].flag) { // 경계선을  넘지 않고 벽이 모든 면에 없으면 
				q.add(map[n.y + 1][n.x + 1]);
				Dq.add(2);
			}
    		
    	}
    	
    	System.out.println(ans);
    
    }
    

}

class Node{
	int y;	int x;
	boolean flag = false;
	int count;
	int state; // 0이면 누운 상태 , 1이면 세로 
	Node(int y, int x, int state ){
		this.y = y;
		this.x = x;
		this.count = state;
		if(state == 1) {
			flag = true; // 벽 세우기 
		}
	}
}
