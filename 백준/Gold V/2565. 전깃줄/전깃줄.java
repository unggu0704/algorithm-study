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
	static int answer = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //수빈이의 위치 

		int[][] arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		//입력 종료 
		
		Arrays.sort(arr, (a1, a2) -> a1[0] - a2[0]);
		
		int[] length = new int[N]; //길이를 담을 배열 
		
		for (int k = 0; k < N; k++) {
			length[k] = 1; //초기 수열의 길이는 자기 자신인 1이다.
			for (int i = 0; i < k; i++) {
				if (arr[k][1] > arr[i][1]) { //여기 수열이 나보다 작다면?
					length[k] = Math.max(length[k], length[i] + 1); //나보다 작은 수열이 가지고 있는 수열 길이의 + 1 vs 내가 가지고 있는 최장 수열 길이 
				}
			}
		}
		
		int max = 0;
		for (int i : length) {
			max = Math.max(max, i);
		}
		
		System.out.println(N - max);
		
		
	}
	
}