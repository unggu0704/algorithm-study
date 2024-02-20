import java.io.*;
import java.util.*;

/**
 * Main_16235_나무재테크_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static int K;
	static StringBuilder sb = new StringBuilder();

	static int[] DY = {-1, 0, 1, 0};
	static int[] DX = {0, 1, 0, -1};
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Node[][] map = new Node[N][M];
		boolean[][] visited;
		int answer = -1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = new Node (Integer.parseInt(st.nextToken()), i, j); //맵의 정보를 입력 
			}
		}
		//입력 종료
	
		boolean isCheese = true;
		int cheeseCount = 0;
		List<Integer> cheeseList = new ArrayList<Integer>();
		
		while (true) {
			Queue<Node> q = new ArrayDeque<Node>();
			visited = new boolean[N][M];
			answer++;
		//	print(map);
			if (!isCheese) 
				break;
			
			cheeseCount = 0;
			q.add(map[0][0]); //공기의 첫 시작
			visited[0][0] = true;
			isCheese = false;
			
			while (!q.isEmpty()) {
				Node node = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int dy = node.y + DY[i];	int dx = node.x + DX[i];
					
					if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || visited[dy][dx])
						continue;
					
					if (map[dy][dx].cheese == 1)  {//해당 칸이 치즈라면...
						map[dy][dx].cheese = 0; //녹인다.
						isCheese = true;
						cheeseCount++;
					//	System.out.println(dy + ","  + dx +"의 치즈를 녹임");
					} else //그렇지 않다면
						q.add(map[dy][dx]);
					
					visited[dy][dx] = true;
				}
			}
			cheeseList.add(cheeseCount);
		}
		
		System.out.println(answer - 1);
		System.out.println(cheeseList.get(cheeseList.size() - 2));
	}
	
	public static void print(Node[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j].cheese);
			}
			System.out.println();
		}
		System.out.println("========================");
	}
	
}

class Node {
	int cheese; // 치즈인지 알아보는 배열 
	int y,x;
	
	Node(int c, int y, int x) {
		this.cheese =c;
		this.y = y;
		this.x = x;
	}
}