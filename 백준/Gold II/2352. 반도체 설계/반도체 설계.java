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
		int[] input = new int[N];
		int[] binaryArr = new int[N];
		
 		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 종료 
		
		Arrays.fill(binaryArr, Integer.MAX_VALUE);
		
		binaryArr[0] = input[0];
		
		for (int i = 0; i < N; i++) {
			int index = Arrays.binarySearch(binaryArr, input[i]);
			if (index < 0) { //배열에 이게 없다면 
				index = (index + 1) * -1;
				binaryArr[index] = input[i];
			}
			
			
		}
		
		for (int i : binaryArr) {
			if (i == Integer.MAX_VALUE)
				break;
			answer++;
		}
		
		System.out.println(answer);
	}
	
}