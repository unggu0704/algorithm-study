import java.io.*;
import java.util.*;

/**
 * Main_16926_배열돌리기1_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M, k;
	static int answer = 0;
	static int min = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] original;
	static Input[] inputs;
	static Input[] combine;
	static boolean[] visited;
	static int[] DX = {1, 0, -1, 0};
	static int[] DY = {0, 1, 0, -1};
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		original = new int[N][M];
		inputs = new Input[k];
		combine = new Input[k];
		visited = new boolean[k];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				original[i][j] = num;
			}
		}
		
		for (int i = 0; i < k; i++) {
			int[] input = new int[3];
			st = new StringTokenizer(br.readLine());
			input[0] = Integer.parseInt(st.nextToken());
			input[1] = Integer.parseInt(st.nextToken());
			input[2] = Integer.parseInt(st.nextToken());
			inputs[i] = new Input(input);
		}
		
		backTracking(0, 0);
		
		System.out.println(min);

	}
	
	
	public static void backTracking(int index, int depth) {
		if (depth == k) {
			rotate(combine);
			return;
		}
		
		for (int i = 0; i < k; i++) {
			if (visited[i])
				continue;
			
			combine[depth] = inputs[i];
			visited[i] = true;
			backTracking(i + 1, depth + 1);
			visited[i] = false;
		}
		
	}
	public static void rotate(Input[] z) {
		
		for (Input inputObject : z) {
			int[] input = inputObject.input;
			
			int y = input[0] - input[2] - 1; 
			int x = input[1] - input[2] - 1;

			int startY = y - 1;
			int startX = x - 1;
			int endy = input[0] + input[2]; //끝 경계
			int endx = input[1] + input[2]; //끝 경계
			
			boolean[][] visited = new boolean[N][M];
			int direct = 0; //방향 
			int depth = 0; //깊이 
			int temp1 = map[y][x], temp2 = 0; // 변경을 위한 임시 변수 
			int count = 0; //바꾼 갯수
			while (count < (endy - (startY + 1)) * (endx - (startX + 1))) {
				//print(map);
				if (direct == 4) {
					count++;
					continue;
				}
				int dy = y + DY[direct];		int dx = x + DX[direct];

				
				if (dy <= startY + depth || dx <= startX + depth || dy >= endy - depth || dx >= endx - depth) { //경계면까지 도달했다면 
					direct++; //방향을 전환한다.
					continue;
				}
				
				/**
				 * 요소 옮기는 로직 
				 */
				temp2 = map[dy][dx];
				map[dy][dx] = temp1; 
				temp1 = temp2;
				count++;
				
				if (visited[dy][dx]) { //한바퀴를 돌았다면 
					y = dy + 1;		x = dx + 1; // 한층 더 깊은 배열로 이동 
					direct = 0;
					depth++;
					temp1 = map[y][x];
					continue;
				}
				
				visited[y][x] = true;
				y = dy;		x = dx; //현재 위치 갱신 
				
			}
		}
		
		///print();
		//각 행의 최댓값 계산
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += map[i][j];
				map[i][j] = original[i][j];
			}
			min = Math.min(min, sum);
		}
		
	}
	
	public static void print() {
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

class Input {
	int[] input;
	
	Input(int[] input) {
		this.input = input;
	}
}