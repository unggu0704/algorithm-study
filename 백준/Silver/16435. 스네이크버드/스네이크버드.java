import java.io.*;
import java.util.*;

/**
// * Main_10972_다음순열_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static long answer = 0;
	static boolean flag;
	
	static int[] DX = {-1, 0, 1, 0};
	static int[] DY = {0, 1, 0, -1};
	static int[] arr, inputs, combine;
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int length = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for (int i = 0; i < N; i++) {
			if (arr[i] <= length) {
				length++;
				continue;
			} 
			break;
		}
		
		System.out.println(length);
	}		
}