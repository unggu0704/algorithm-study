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

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < N + 1; i++) {
			list.add(i);
		}
		
		int index = M - 1;
		StringBuilder sb = new StringBuilder("<");
		sb.append(list.get(index));
		list.set(index, 0);
		
		for (int i = 0; i < N -1 ; i++) {
			int visitCount = 1;
			while(true) {
				index = index % list.size();
				if (list.get(index) == 0) {
					index++;
					continue;
				}
				
				if (visitCount == M) {
					sb.append(", ").append(list.get(index));
					list.set(index, 0);
					break;
				}
				visitCount++;
				index++;
			}
		//	System.out.println(sb.toString());
		}
		sb.append(">");
		
		System.out.println(sb.toString());
	}
	
}