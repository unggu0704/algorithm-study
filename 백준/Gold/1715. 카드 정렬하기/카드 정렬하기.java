import java.io.*;
import java.util.*;

/**
 * 1일 N알골 BOJ_2212_센서
 * 
 * 입력 N = 10.000 K = 1.000
 * 
 * @author 김규형
 *
 */

public class Main {

	static int N;
	static int M;
	static int answer = 0;
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		if (N == 1) {
			System.out.println(0);
			System.exit(0);
		}
		
		while (pq.size() >= 2) {
			int min = pq.poll() + pq.poll();
			pq.add(min);
			answer += min;
		}
		System.out.println(answer);
	}
	
}