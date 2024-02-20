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
	static int answer = 1;
	static boolean[] visited;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Character[][] map = new Character[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		//입력 종료 
		
		
		//DFS 탐색
		visited = new boolean[26];
		visited[map[0][0] - 'A'] = true; //아스키 코드  A = 0 방문 배열 
		DFS(map, 0, 0, 1);
		
		System.out.println(answer);
	}
	
	public static void DFS(Character[][] map, int y, int x, int depth) {
		
		for (int i = 0; i < 4; i++) {
			int dy = y + DY[i];		int dx = x + DX[i];
			
			if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || visited[map[dy][dx] - 'A'])
				continue;
			
			int asc = map[dy][dx] - 'A';
			visited[asc] = true;
			answer = Math.max(answer, depth + 1);
			DFS(map, dy, dx, depth + 1); //다음 탐색을 하러 떠난다.
			visited[asc] = false;
		}
	}
}