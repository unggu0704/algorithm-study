import java.io.*;
import java.util.*;

/**
 * 1일 N알골 BOJ_2212_센서
 * 
 * 입력 N = 10.000 K = 1.000
 * 
 * @author 김규형
 *
 */

public class Main {

	static int N;
	static int M;
	static int answer = 0;
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int rotateCount = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] DX = {0, 1, 0, -1};
		int[] DY = {1, 0, -1, 0};

		
		for (int w = 0; w < rotateCount; w++) {
			boolean[][] visited = new boolean[N][M];
			int y = 0;	int x = 0;
			int direct = 0;
			int depth = 0;
			int temp1 = map[y][x], temp2 = 0;
			int count = 0;
			while (count < N * M) {
				
				int dy = y + DY[direct];		int dx = x + DX[direct];

				
				if (dy >= N - depth|| dx >= M - depth|| dy <= -1 + depth|| dx <= -1 + depth) { //경계면까지 도달했다면 
					direct++; //방향을 전환한다.
					continue;
				}
				
				
			//	System.out.println(dy +" , " + dx + " | " + count);
				temp2 = map[dy][dx];
				map[dy][dx] = temp1; //요소 옮기기
				temp1 = temp2;
				count++;
				
				if (visited[dy][dx]) { //한바퀴를 돌았다면 
					y = dy + 1;		x = dx + 1; // 한층 더 깊은 배열로 이동 
				//	System.out.println("깊은 곳으로 이동 ");
					direct = 0;
					depth++;
					temp1 = map[y][x];
					continue;
				}
				
				visited[y][x] = true;
				y = dy;		x = dx; //현재 위치 갱신 
				
			}

		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
}