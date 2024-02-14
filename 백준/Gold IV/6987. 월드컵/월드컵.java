import java.io.*;
import java.util.*;

/**
 * Main_6987_월드컵 _김규형
 * 
 * A B C D E F
 * 
 * A B 
 * A C
 * A D
 * A E
 * A F
 * .
 * .
 * .
 * 
 * 이김 | 비김 | 패배 
 * 
 * @author 김규형
 */

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static long answer = 0;
	static final int MATCHCOUNT = 30; //정해진 경기 횟수
	static int count = 0;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<int[][]> inputs = new ArrayList<>();
		boolean[] flags = new boolean[4];

		for (int i = 0; i < 4; i++) {
			int[][] socres = new int[6][3];
			st = new StringTokenizer(br.readLine());
			int total = 0;
			for (int j = 0; j < 6; j++) {
				for (int w = 0; w < 3; w++) {
					int input = Integer.parseInt(st.nextToken());
					socres[j][w] = input;
					total += input;
				}
			}
			
			//총 경기수가 30이 맞는가?
			if (total != 30) 
				flags[i] = true;
			
			inputs.add(socres);
		}
		//입력 종료 

		
		
		
		for (int i = 0; i < 4; i++) {
			if (backTracking(inputs.get(i), 0, 1, 0) && !flags[i]) {
				sb.append(1).append(" ");
				continue;
			}
			sb.append(0).append(" ");
		}
		
		System.out.println(sb.toString());
	}
	
	
	public static boolean backTracking(int[][] bracket, int home, int away, int count) {

		if (count >= 15) {
			return true;
		}
		if (home >= 6) {
			return false;
		}
		
		if (away >= 6) {
			return backTracking(bracket, home + 1, home + 2, count);
		}
		
		if (bracket[home][0] > 0 && bracket[away][2] > 0) { //home팀이 이겼다면?
			bracket[home][0]--;
			bracket[away][2]--;
			if (backTracking(bracket, home, away + 1, count + 1)) return true;
			bracket[home][0]++;
			bracket[away][2]++;
		}
		
		if (bracket[away][0] > 0 && bracket[home][2] > 0) { //away팀이 이겼다면?
			bracket[home][2]--;
			bracket[away][0]--;
			if (backTracking(bracket, home, away + 1, count + 1)) return true;
			bracket[home][2]++;
			bracket[away][0]++;
		}
		
		if (bracket[home][1] > 0 && bracket[away][1] > 0) { // home 과 away가 비겼다면?
			bracket[home][1]--;
			bracket[away][1]--;
			if (backTracking(bracket, home, away + 1, count + 1)) return true;
			bracket[home][1]++;
			bracket[away][1]++;
		}
		
		return false;
	}
	
}