import java.io.*;
import java.util.*;

/**
 * Main_21608_상어초등학교_김규형
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
		arr = new int[N];
		inputs = new int[N];
		combine = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			inputs[i] = input;
			arr[i] = input;
		}
		
//		Arrays.sort(arr);
//	
//		do {
//			
//		} while(np(arr));
		if (np(arr)) {
			for (int i : arr) {
				System.out.print(i + " ");
			}
		} else {
			System.out.println(-1);
		}

		
	}
	
	static boolean np(int numbers[]) {
//		1. 교차점 찾기 
		int N = numbers.length;
		int i = N - 1;
//		뒤쪽 부터 탐색하여 꼭대기(i) 찾기 
		while(i > 0 && numbers[i - 1] <= numbers[i]) {
			i--;
		}
		
//		i = 0이면 순열 생성 못함 
		if(i == 0) return false;
			
//		2. 교차할 데이터의 위치 찾기 
		int j = N - 1;
//		뒤쪽 부터 탐색하여 교환할 큰값(j) 찾기 
		while(numbers[i - 1] <= numbers[j])	{
			j--;
		}
			
//		3. i-1 번째 데이터와 j번째 데이터 교환 (swap)
		swap(numbers,i - 1, j);
			
//		4. 다음 순열을 위해서 교환후 꼭지점 이후는 (index)를 내림차순으로 
		int k = N - 1;
		while(i < k) {
				swap(numbers, i++, k--);			
		}
		return true;
	}
	static void swap(int numbers[], int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
}