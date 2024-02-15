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
	static long answer = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		List<Long> positive = new ArrayList<Long>();
		List<Long> negative = new ArrayList<Long>();
		boolean zero = false;
		boolean one = false;
		
		for (int i = 0; i < N; i++) {
			Long input = Long.parseLong(br.readLine());
			if (input == 1) {
				answer++;
			} else if (input > 1) {
				positive.add(input);
			} else if (input < 0) {
				negative.add(input);
			} else if (input == 0) {
				zero = true;
			}
		}
		
		Collections.sort(positive, Collections.reverseOrder());
		Collections.sort(negative);
		
		for (int i = 0; i < positive.size() - 1; i += 2) {
			answer += positive.get(i) * positive.get(i + 1);
		}
		
		for (int i = 0; i < negative.size() - 1; i += 2) {
			answer += negative.get(i) * negative.get(i + 1);
		}
		
		if (positive.size() % 2 == 1) {
			answer += positive.get(positive.size() - 1);
		}

		if (negative.size() % 2 == 1) {
			long n = negative.get(negative.size() - 1);
			if (!zero) {
				answer += n;
			}
		}
		
		System.out.println(answer);
	}
}