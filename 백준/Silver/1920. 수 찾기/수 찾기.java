import java.io.*;
import java.util.*;

/**
 * Main_10973_이전순열_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static long answer = 0;
	
	static int[] arr;
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		int[] find = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) 
			find[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		for (int i = 0; i < M; i++) {
			System.out.println(Arrays.binarySearch(arr, find[i]) < 0 ? 0 : 1);
		}
	}
	
}