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
	static int count;
	static int answer = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] sight;
	static List<CCTV> cctvs;
	static List<CCTV> combines;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		sight = new int[N][M];
		cctvs = new ArrayList<CCTV>(8);
		combines = new ArrayList<CCTV>(8);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int input = Integer.parseInt(st.nextToken());

				if (input != 0 && input != 6) {
					cctvs.add(new CCTV(i, j, input));
				}
				map[i][j] = input;
				// sight[i][j] = input;
			}
		}
		// 입력 종료

		backTracking(0);

		System.out.println(answer);

	}

	public static void backTracking(int depth) {
		int size = cctvs.size();

		if (size == depth) {
			int diff = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
			//		System.out.print(sight[i][j]);
					if (sight[i][j] == 0 && map[i][j] == 0)
						diff++;
				}
				//System.out.println();
			}
			
		//	System.out.println("================");

			answer = Math.min(diff, answer);
			return;
		}

		CCTV cctv = cctvs.get(depth);
		int num = cctv.num; // cctv의 종류

		switch (num) {
			case 1:
				cctv1(cctv, depth);
				break;
			case 2:
				cctv2(cctv, depth);
				break;
			case 3:
				cctv3(cctv, depth);
				break;
			case 4:
				cctv4(cctv, depth);
				break;
			case 5:
				cctv5(cctv, depth);
				break;
		}

	
	}

	public static void search(int y, int x, int moveY, int moveX, int flag) {
		while (true) {
			int dy = y + moveY;
			int dx = x + moveX;

			if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || map[dy][dx] == 6) // 경계에 도달하거나 벽을 만나면 탐색을 중지 한다.
				return;

			sight[dy][dx] += flag;
			y = dy;
			x = dx;
		}
	}
	
	public static void cctv1(CCTV cctv, int depth) {
		int[] DX = { -1, 0, 1, 0 };
		int[] DY = { 0, 1, 0, -1 };

		for (int i = 0; i < 4; i++) {
			search(cctv.y, cctv.x, DY[i], DX[i], 1); // 방향 하나를 결정
			backTracking(depth + 1);
			search(cctv.y, cctv.x, DY[i], DX[i], -1); // 방향을 돌리기 전에 다시 원래대로 변경
		}
	}

	public static void cctv2(CCTV cctv, int depth) {
		int[] D = { 1, 0, -1, 0, 0, 1, 0, -1 };

		for (int i = 0; i < 8; i += 4) {
			search(cctv.y, cctv.x, D[i], D[i + 1], 1);
			search(cctv.y, cctv.x, D[i + 2], D[i + 3], 1);
			backTracking(depth + 1);
			search(cctv.y, cctv.x, D[i], D[i + 1], -1);
			search(cctv.y, cctv.x, D[i + 2], D[i + 3], -1);
		}
	}

	public static void cctv3(CCTV cctv, int depth) {
		int[] D = { -1, 0, 0, 1,  1, 0, 0, 1,  1, 0, 0, -1,  -1, 0, 0, -1  };


		for (int i = 0; i < 16; i += 4) {
			search(cctv.y, cctv.x, D[i], D[i + 1], 1);
			search(cctv.y, cctv.x, D[i + 2], D[i + 3], 1);
			backTracking(depth + 1);
			search(cctv.y, cctv.x, D[i], D[i + 1], -1);
			search(cctv.y, cctv.x, D[i + 2], D[i + 3], -1);
		}
	}

	public static void cctv4(CCTV cctv, int depth) {
		int[] DX = { -1, 0, 1, 0 };
		int[] DY = { 0, 1, 0, -1 };
		
		int[] indexList = new int[3];
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				index = index % 4;
				search(cctv.y, cctv.x, DY[index], DX[index], 1);
				indexList[j] = index;
				index++;
			}
			
			backTracking(depth + 1);
			for (int j = 0; j < 3; j++) {
				search(cctv.y, cctv.x, DY[indexList[j]], DX[indexList[j]], -1);
			}
		}

//		index = 0;
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 3; j++) {
//				index = index % 4;
//				search(cctv.y, cctv.x, DY[index], DX[index], -1);
//				index++;
//			}
//		}
	}

	public static void cctv5(CCTV cctv, int depth) {
		int[] DX = { -1, 0, 1, 0 };
		int[] DY = { 0, 1, 0, -1 };

		for (int i = 0; i < 4; i++) {
			search(cctv.y, cctv.x, DY[i], DX[i], 1);
		}

		backTracking(depth + 1);

		for (int i = 0; i < 4; i++) {
			search(cctv.y, cctv.x, DY[i], DX[i], -1);
		}
	}

}

class CCTV {
	int y;
	int x;
	int num;

	public CCTV(int y, int x, int num) {
		this.y = y;
		this.x = x;
		this.num = num;
	}
}