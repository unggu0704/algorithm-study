import java.io.*;
import java.util.*;

/**
 * 데일리 실습
 * BOJ_2916_센서
 * 
 * 
 * @author 김규형 
 */

public class Main {
	
	static StringTokenizer st;
    static int N;
    static int M;
    static int answer;
    static int[] passwordCheck = new int[4];
    static char[] original;
    
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		original = new char[N];

		String dna = br.readLine();

		for (int i = 0; i < original.length; i++) {
			original[i] = dna.charAt(i);
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 4; i++) {
			passwordCheck[i] = Integer.parseInt(st.nextToken());
		}
		//입력 체크 
		
		
		int[] dnaCount = new int[4]; //0이 A, 1이 G, 2가 C, 3이 T
		
		for (int i = 0; i < M; i++) {
			char ch =  original[i];
			
			if (ch == 'A')
				dnaCount[0]++;
			if (ch == 'C')
				dnaCount[1]++;
			if (ch == 'G')
				dnaCount[2]++;
			if (ch == 'T')
				dnaCount[3]++;
		}
		
		for (int i = 0; i < 4; i++) {
			if (passwordCheck[i] != 0 && dnaCount[i] < passwordCheck[i]) 
				break;
			
			if (i == 3)
				answer++;
		}
		
		int first = 0;
		int end = M;

		while (end < N) {
			
			char deleteCh = original[first++];
			char addCh = original[end++];
			
			if (deleteCh == 'A')
				dnaCount[0]--;
			if (deleteCh == 'C')
				dnaCount[1]--;
			if (deleteCh == 'G')
				dnaCount[2]--;
			if (deleteCh == 'T')
				dnaCount[3]--;
			
			if (addCh == 'A')
				dnaCount[0]++;
			if (addCh == 'C')
				dnaCount[1]++;
			if (addCh == 'G')
				dnaCount[2]++;
			if (addCh == 'T')
				dnaCount[3]++;
			
			for (int i = 0; i < 4; i++) {
				if (passwordCheck[i] != 0 && dnaCount[i] < passwordCheck[i]) 
					break;
				
				if (i == 3)
					answer++;
			}
		}
		
		System.out.println(answer);
	}
}

