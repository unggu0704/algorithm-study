import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	static int ans = 0;
	static int N,M;
	static Tomato map[][];
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	M= Integer.parseInt(st.nextToken());
    	N = Integer.parseInt(st.nextToken());
    	
    	Queue<Tomato> q = new LinkedList<>();
    	map = new Tomato[N][M];
    	boolean flag = false;
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < M; j++) {
    			int input = Integer.parseInt(st.nextToken());
    			Tomato t = new Tomato(input,i,j);
    			map[i][j] = t;
    		}
    	}// 입력 처리 끝 
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(map[i][j].state == 1) {
    				q.add(map[i][j]);
    				qSize++;
    			}
    			if(map[i][j].state == 0 && !flag) {
    				flag = true;
    			}
    		}
    	}
    	
    	if(!flag) {
    		System.out.println(0); 
    		System.exit(0);
    	} // 전부 익어 있으면 0 출력 
    	
    	while(!q.isEmpty()) {
			ans++;
			int size = qSize;
			qSize = 0;
			for(int i = 0; i <size; i++) {
				BFS(q);
			}
		} //BFS 실행 
    	
    	
    	if(check()) System.out.println(-1);
    	else System.out.println(--ans);
    	
    }
    
    static int qSize = 0;
    static int DX[] = {-1, 0, 1, 0};
    static int DY[] = {0, 1, 0, -1};
    public static void BFS(Queue<Tomato> q) {
    	Tomato t = q.poll();
    	
    	for(int i = 0; i < 4; i++) {
    		int X = t.x + DX[i];	int Y = t.y + DY[i];
    		
    		if(X <= -1 || Y <= -1 || X >= M || Y >= N || map[Y][X].state != 0) continue;
    		map[Y][X].state = 1; // 익는다 
    		q.add(map[Y][X]);
    		qSize++;
    	}
    	
    }
    
    public static boolean check() {
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(map[i][j].state == 0) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
   
}


class Tomato{
	int state; // -1 이면 벽 , 0 이면 익지 않은 토마토 , 1이면 익은 토마토 
	int y,x;
	boolean visited = false;
	Tomato(int n,int y, int x){
		state = n;
		this.y = y;
		this.x = x;
	}
}
