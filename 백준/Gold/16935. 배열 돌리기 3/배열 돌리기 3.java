import java.io.*;
import java.util.*;

/**
 * Main_16926_배열돌리기1_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static int answer = 0;
	static int[][] map;
	static int[][] rmap;
	static int[] DX = {0, 1, 0, -1};
	static int[] DY = {1, 0, -1, 0};
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Integer> input = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());

		while (st.hasMoreTokens()) {
			input.add(Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < input.size(); i++) {
			
			switch(input.get(i)) {
			case 1:
				upDown();
				break;
			case 2:
				reversal();
				break;
			case 3:
				rightAngle();
				break;
			case 4:
				leftAngle();
				break;
			case 5:
				division5();
				break;
			case 6:
				division6();
				break;
			}
			copy();
		}
		
		print();
	}
	
	public static void upDown() {
		rmap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				rmap[(N-1) - i][j] = map[i][j];
			}
		}
	}
	
	public static void reversal() {
		rmap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				rmap[i][(M-1) - j] = map[i][j];
			}
		}
	}
	
	public static void rightAngle() {
		rmap = new int[M][N];

		for (int j = 0; j < M; j++) {
			for (int i = N - 1; i >= 0; i--) {
				rmap[j][(N - 1) - i] = map[i][j];
			}
		}
	}
	
	public static void leftAngle() {
		rmap = new int[M][N];
		
		for (int j = M - 1; j >= 0; j--) {
			for (int i = 0; i < N; i++) {
				rmap[(M - 1) - j][i] = map[i][j];
			}
		}
	}
	
	public static void division5() {
		rmap = new int[N][M];
		
		rightMove(0);
		downMove(M / 2);
		leftMove(N / 2);
		upMove(0);
	}
	
	public static void division6() {
		rmap = new int[N][M];
		
		downMove(0);
		rightMove(N / 2);
		upMove(M / 2);
		leftMove(0);
	}
	
	public static void rightMove(int start) {
		for (int i = start; i < start + N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				rmap[i][j + M / 2] = map[i][j]; 
			}
		}
	}
	
	public static void leftMove(int start) {
		for (int i = start; i < start + N / 2; i++) {
			for (int j = M / 2; j < M ; j++) {
				rmap[i][j - M / 2] = map[i][j]; 
			}
		}
	}
	
	public static void downMove(int start) {
		for (int i = 0; i < N / 2; i++) {
			for (int j = start; j < start + M / 2 ; j++) {
				rmap[i + N / 2][j] = map[i][j]; 
			}
		}
	}
	
	public static void upMove(int start) {
		for (int i = N / 2; i < N; i++) {
			for (int j = start; j < start + M / 2 ; j++) {
				rmap[i - N / 2][j] = map[i][j]; 
			}
		}
	}
	
	public static void copy() {
		map = new int[rmap.length][rmap[0].length];
		N =  rmap.length;
		M = rmap[0].length;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < rmap[0].length; j++) {
				map[i][j] = rmap[i][j];
			}
		}
	}
	
	
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}