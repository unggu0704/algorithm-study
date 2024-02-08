import java.io.*;
import java.util.*;

/**
 * Main_16935_배열돌리기3_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static long answer = 0;

	static int[] DX = { 1, 1,  1};
	static int[] DY = {-1, 0,  1};
	static Node[][] map;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Node[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = new Node(i,j);
				if(str.charAt(j) == 'x')
					map[i][j].visited = true;
			}
		}
		// 입력 종료 
		
	
		for (int i = 0; i < N; i++) {
			map[i][0].visited = true;
			DFS(map[i][0]);
		}
		System.out.println(answer);
	}
	
	public static boolean DFS(Node node) {
		for (int i = 0; i < 3; i++) {
		//	booleanPrint();
			int y = node.y + DY[i];
			int x = node.x + DX[i];
			
			if (y >= N || x >= M || y <= -1 || x <= -1 || map[y][x].visited )
				continue;
			
			if (x == M - 1) {
				answer++;
				map[y][x].visited = true;
			//	booleanPrint();
				return true;
			}
			
		
			
			map[y][x].visited = true;
			if (DFS(map[y][x]))
				return true;

		}
		
		return false;
	}
	
	public static void booleanPrint() {
		System.out.println("========================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j].visited + " ");
			}
			System.out.println();
		}
	}
	

}

class Node {
	int y; int x;
	boolean visited;
	
	Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
}