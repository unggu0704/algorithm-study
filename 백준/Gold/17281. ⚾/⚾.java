import java.io.*;
import java.util.*;


/**
 * Main_17281_야구_김규형
 * 
 * 각 이닝에서의 출전할 선수를 구해서 총 게임 결과 가장 많은 점수를 기록 할 수 있도록 타자를 갱신할것 
 * 
 * 선수의 출진 순서는 인덱스 고정 즉 모든 이닝에서 인덱스의 변화는 같다.
 * 1번 선수는 무조건 4번으로 고정되어야한다.
 * 타수는 한명이 아웃되더라도 고정되여야한다.
 * 
 * 풀이
 * 백트래킹으로 선수 번호와 타수 번호의 조합을 만들어서 시뮬레이션 돌린다.
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static int K;
	static StringBuilder sb = new StringBuilder();

	static int[] DY = {-1, 0, 1, 0};
	static int[] DX = {0, 1, 0, -1};
	static int answer = Integer.MIN_VALUE;
	static int[][] inputs;
	static boolean[] visited;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		inputs = new int[N][9];
		visited = new boolean[9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				inputs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] batterNumber = new int[9]; //타자 번호 
		int[] combine = new int[9]; //타자들의 조합 번호 
		
		for (int i = 0; i < 9; i++) 
			batterNumber[i] = i;
		
		combine[3] = 0;
		backTracking(batterNumber, combine , 0);
		
		System.out.println(answer);
	}
	
	public static void backTracking(int[] batterNumber, int[] combine, int depth) {
		if (depth == 9) { //9명의 조합이 완성됬다면?
			int score = 0;
			for (int i = 0; i < N; i++) {
				int[] batters = new int[9]; // 순서를 저장할 배열 
				
				for (int c = 0; c < 9; c++) {
					batters[c] = inputs[i][combine[c]];
				} //순서에 따른 타자 배치 
				
				score += playBaseball(batters);
			}
			
			answer = Math.max(answer, score);
			battingNum = -1;
			return;
		}
		
		
		for (int i = 1; i < 9; i++) {
			if (visited[i])
				continue;
			
			if (depth == 3) 
				depth++;
			
			combine[depth] = batterNumber[i];
				
			visited[i] = true;
			backTracking(batterNumber, combine, depth + 1);
			visited[i] = false;
		}
	}
	
	static BaseBall baseball;
	static int battingNum = 0;
	public static int playBaseball(int[] batters) {
		//N번 게임을 시작한다.
		baseball = new BaseBall();
		int score = 0;
		int outCount = 0;
		
		loop:
		while (true) {
			if (outCount == 3) {
				break loop;
			}
			battingNum++;
			battingNum %= 9;
			switch (batters[battingNum]) {
			case 0:
				outCount++;
				break;
			case 1:
				score += baseball.move1();
				break;
			case 2:
				score += baseball.move2();
				break;
			case 3:
				score += baseball.move3();
				break;
			case 4:
				score += baseball.move4();
				break;
			}
			
		}
		
		return score;
	}
}

class BaseBall {
	int r1;
	int r2;
	int r3;
	int r4;
	
	BaseBall() {
		r1 = 0;	r2 = 0;	r3 = 0;	r4 = 0;
	}
	
	int move1() {
		r4 = r3;
		r3 = 0;
		r3 = r2;
		r2 = 0;
		r2 = r1;
		r1 = 1;
		return check();
	}
	
	int move2() {
		r4 = r3 + r2;
		r3 = r1;
		r2 = 1;
		r1 = 0;
		return check();
	}
	
	int move3() {
		r4 = r3 + r2 + r1;
		r2 = r1 = 0;
		r3 = 1;
		return check();
	}
	
	int move4() {
		r4 = r1 + r2 + r3 + 1;
		r3 = r2 = r1 = 0;
		return check();
	}
	
	int check() {
		int r = r4;
		r4 = 0;
		return r;
	}
	
}