import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 데일리 실습
 * 
 * @author 김규형
 *
 */
public class Solution  {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//int tc = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("#" + i + " " + solution(br));
		}
		
	}
	
	public static int solution(BufferedReader br) throws IOException {
		int dump = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] container = new int[100];
		
		for (int i = 0; i < 100; i++) {
			container[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 종료 

		for (int i = 0; i < dump; i++) {
			Arrays.sort(container);
			if (container[99] == container[0])
				continue;

			container[99]--;
			container[0]++;
		}
		
		Arrays.sort(container);
		return container[99] - container[0];// 정답을 return 
	}
}

