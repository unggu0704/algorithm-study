import java.io.*;
import java.util.*;

/**
 * 데일리 실습
 * BOJ_2916_센서
 * 
 * 
 * @author 김규형 
 */

public class Main {
	
	static StringTokenizer st;
    static int N;
    static int[][] combine;
    static int[][] ingredients;
    static boolean[] visited;
    static int combineSum = 0;
    static int answer = Integer.MAX_VALUE;
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		ingredients = new int[N][2]; //재료들의 2차원 배열 
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());
		}
		//입력 종료 
		
		for (int i = 1; i < N + 1; i++) { // 조합의 사이즈를 1, 2, ... n만큼 찾는다. -> 멱집합
			combine = new int[i][2]; // 조합의 사이즈 만큼 배열을 생성 해논다.
			backTracking(0, 0, i); // 백트래킹 
		}
		
		System.out.println(answer);
	}
	
	/**
	 * 백트래킹 함수 
	 * @param depth 조합 인덱스의 결정과 재귀의 깊이를 표시한다.
	 * @param index 이전 탐색했던 재료보다 뒤에서 항상 찾는다.
	 * @param count 조합의 사이즈와 계산해야할 타이밍을 정한다.
	 */
	public static void backTracking(int depth, int index, int count) {
		if (depth >= count) { //조합이 다 찾다면?
			int sour = 1;
			int bitter = 0;
			
			for (int i = 0; i < depth; i++) {
				sour *= combine[i][0];
				bitter += combine[i][1];
			}
			answer = Math.min(answer, Math.abs(sour - bitter));
			return;
		}
		
		for (int i = index ; i < N; i++) {		
			combine[depth][0] = ingredients[i][0];
			combine[depth][1] = ingredients[i][1];
			
			backTracking(depth + 1, i + 1, count);
		}
	}
}
