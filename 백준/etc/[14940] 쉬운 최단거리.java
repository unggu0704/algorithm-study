import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{

		isExecute();
		
	}
	static Node[][] map;
	static int n, m;
	static Queue<Node> queue = new LinkedList<>();
	static void isExecute() throws IOException {
		//Scanner s = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int input;
		int starti = 0 , startj = 0;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new Node[n][m];
		for(int i = 0 ; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < m; j++) {
				input = Integer.parseInt(st.nextToken());
				Node n = new Node(input,i,j);
				map[i][j] = n;	
				if(input == 2) { starti = i;	startj = j;  }
				else if(input == 0 ) {	map[i][j].BFSvisited = true; }
				else { map[i][j].BFSvisited = false; }
			}
		}
		
		init_BFS(starti,startj);
		
		
		
		for(int i = 0 ; i < n; i++) {
			for(int j = 0 ; j < m; j++) {
				if(!map[i][j].BFSvisited) { map[i][j].nodeNumber = -1; }
				System.out.print(map[i][j].nodeNumber + " ");
			}
			System.out.println();
		}
		
		
	}
	static int deep = 1;
	
	static void init_BFS(int i1,int i2) {
		
		queue.add(map[i1][i2]);
		map[i1][i2].nodeNumber = 0;	map[i1][i2].BFSvisited = true;
		
		while(true) {
			if(queue.isEmpty()) {  // 큐가 비었으면 탈출 
				break;
			}
			int queueSize = queue.size();
			for(int i = 0; i<queueSize; i++) {
				BFS(queue.poll());
			} // 인접한 큐를 찾고
			deep++;
		}
		
	}
	
	static int[] DX = {1,0,-1,0};
	static int[] DY = {0,-1,0,1};
	
	
 	static void BFS(Node node) {
 		int x = node.x;	int y = node.y;
		/*
		for(int i = 0; i < 4; i++) {
			int x = node.x;	int y = node.y;
			x += DX[i];
			y += DY[i];
			
			if(x <= -1 || y <= -1 || x >= n || y>= m) continue;
			if(map[x][y].BFSvisited) continue;
			map[x][y].nodeNumber = deep;	
			map[x][y].BFSvisited = true;
			queue.add(map[x][y]); 
		}
		*/
		
		
		
		if(x -1 > -1 && !map[x-1][y].BFSvisited) {
			map[x-1][y].nodeNumber = deep;	
			map[x-1][y].BFSvisited =true;
			queue.add(map[x-1][y]); 
		}
		if(x+1 < n && !map[x+1][y].BFSvisited) {
			map[x+1][y].nodeNumber = deep;	
			map[x+1][y].BFSvisited =true;
			queue.add(map[x+1][y]); 
		}
		if(y - 1 > -1 && !map[x][y-1].BFSvisited) {
			map[x][y-1].nodeNumber = deep;	
			map[x][y-1].BFSvisited =true;
			queue.add(map[x][y-1]); 
		}
		if(y+1 < m && !map[x][y+1].BFSvisited ) {
			map[x][y+1].nodeNumber = deep;	
			map[x][y+1].BFSvisited =true;
			queue.add(map[x][y+1]); 
		}
		
	
	}
	
}
	
class Node {
	int nodeNumber,x ,y;
	boolean BFSvisited =  false;
	
	Node(int n, int i, int j){
		this.nodeNumber = n;	x = i;	y = j;
	}

}

