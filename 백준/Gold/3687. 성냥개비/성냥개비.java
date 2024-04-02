import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 */
public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M, K;
	
	static int[] DY = {-1, 0, 1, 0, 0};
	static int[] DX = {0, 1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		int[] inputs = new int[N];
		
		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(br.readLine());
		}
		
		//최초로 시작할 배열 
		String[] numbers = {"0", "0", "1", "7", "4", "2", "0", "8", "10"};
		
		long[] dp = new long[101]; //dp 배열
		
		Arrays.fill(dp, Long.MAX_VALUE);
		
		for (int i = 0; i < 9; i++) {
			dp[i] = Long.parseLong(numbers[i]);
		}
		
		
		for (int i = 9; i < 101; i++) {
			for (int j = i - 2; j > i - 9; j--) {
				String number = dp[j] + numbers[i - j];
				if (number.charAt(0) == '0') {
					number = '6' + number.substring(1);
				}
				
				dp[i] = Math.min(Long.parseLong(number), dp[i]);
			}
		}
		//최솟 값 구했다.
		
		if (dp[6] == 0) 
			dp[6] = 6;
		
		
		StringBuilder max;
		for (int i = 0; i < N; i++) {
			int num = inputs[i];
			max = new StringBuilder();
			//짝수라면?
			if (num % 2 == 0) {
				for (int j = 0; j < num / 2; j++) 
					max.append("1");
				
			} else { //홀수라면? 
				max.append("7");
				for (int j = 0; j < num / 2 - 1; j++) 
					max.append("1");
				
			}
			sb.append(dp[num]).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
}