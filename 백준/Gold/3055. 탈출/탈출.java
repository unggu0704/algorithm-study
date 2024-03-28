import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_2239_스도쿠_김규형
 * @author 김규형
 *
 */
public class Main {
    static int N;
    static int M;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        Node[][] map = new Node[N][M];
        Queue<Node> q= new ArrayDeque<>();
        Queue<Node> water = new ArrayDeque<>();
        
        for (int i = 0; i < N; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < M; j++) {
        		char info = str.charAt(j);
        		map[i][j] = new Node(i, j, info);
        		if (info == 'S') 
        			q.offer(map[i][j]);
        		else if (info == '*') {
        			water.offer(map[i][j]);
        		}
        	}
        }
        
        int time = 0;

        while (!q.isEmpty()) {
        	time++;
        	int waters = water.size();
        //	printMap(map);
        	
        	//물이 이동한다. 4방면으로
        	for (int i = 0; i < waters; i++) {
        		Node waterNode = water.poll();
        		
        		for (int d = 0; d< 4; d++) {
        			int dy = waterNode.y + DY[d];
        			int dx = waterNode.x + DX[d];
        			
        			//경계를 벗어나거나 바버의동굴, 같은 물, 돌에는 퍼지지 않는다.
        			if (dy >= N || dx >= M || dy <= -1 || dx <= -1 
        					|| map[dy][dx].info == 'X' || map[dy][dx].info == '*'
        					|| map[dy][dx].info == 'D') 
        				continue;
        			
        			map[dy][dx].info = '*';
        			
        			water.offer(map[dy][dx]);
        		}
        	}
        //	printMap(map);
        	// 고슴도치가 이동 
        	
        	int size = q.size();
        	
        	for (int i = 0; i < size; i++) {
	        	Node node = q.poll();
	        	
	        	for (int d = 0; d < 4; d++) {
	        		int dy = node.y + DY[d];
	    			int dx = node.x + DX[d];
	    			
	    			//경계를 벗어나거나 바버의동굴, 같은 물, 돌에는 퍼지지 않는다.
	    			if (dy >= N || dx >= M || dy <= -1 || dx <= -1 
	    					|| map[dy][dx].info == 'X' || map[dy][dx].info == '*'
	    					|| map[dy][dx].info == 'S')
	    				continue;
	    		
	    			
	    			if (map[dy][dx].info == 'D') {
	    				 System.out.println(time);
	    				 System.exit(0);
	    			}
	    			
	    			map[dy][dx].info = 'S';
	    			q.offer(map[dy][dx]);
	        	}
        	}
        }
        
        System.out.println("KAKTUS");
    }
    
    public static void printMap(Node[][] map) {
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < M; j++) {
        		System.out.print(map[i][j].info + " ");
        	}
        	System.out.println();
        }
        System.out.println("=================");
    }
}

class Node {
	int y;
	int x;
	char info;
	
	Node (int y, int x, char info) {
		this.y = y;
		this.x = x;
		this.info = info;
	}
}