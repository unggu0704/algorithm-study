import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		if (N > 0)
			st = new StringTokenizer(br.readLine());
		
		int[] ranking = new int[P];
		Arrays.fill(ranking, 0);
		for (int i = 0; i < N; i++) {
			ranking[i] = Integer.parseInt(st.nextToken());
		}
		

		int rank = -1;
		
		if (N == P && ranking[P - 1] == S) {
			rank = -1;
		} else if (N == 0) {
			rank = 1;
		}
		else {
			for (int i = 0; i < ranking.length; i++) {
				if (ranking[i] <= S) {
					rank = i + 1;
					break;
				}
			}
		}
		
		System.out.println(rank);

		
	}

}