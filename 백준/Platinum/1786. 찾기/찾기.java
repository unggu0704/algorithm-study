import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int N; // 봉우리 개수
	
	static String pattern;
	static int patternSize;
	static int ans, count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String str = br.readLine();
		pattern = br.readLine();
		patternSize = pattern.length();
		
		int[] pi = makeFaliureTable();
		
		solution(str, pi);
		
		System.out.println(count);
		System.out.println(sb.toString());
	}
	
	public static void solution(String str, int[] pi) {
		int size = str.length();
		
		int idx = 0;
		for (int i = 0; i < size; i++) {
			while (idx > 0 && (pattern.charAt(idx) != str.charAt(i))) 
				idx = pi[idx - 1];
			
			
			if (str.charAt(i) == pattern.charAt(idx)) {
				// 패턴의 끝만큼 도달했다면
				if (idx == patternSize - 1) { 
					count++;
					//기존에 존재하던 길이에 현재 패턴이 맞는 시작 위치
					sb.append(ans + i - idx + 1).append(" ");
					idx = pi[idx];
				} else
					idx++;
			}
				
		}
		
	}
	
	public static int[] makeFaliureTable() {
		//실패 테이블 
		int[] pi = new int[pattern.length()];
		
		int idx = 0;
		for (int i = 1; i < pattern.length(); i++) {
			//접두사와 접미사가 일치하지 않으면 idx를 이전 단계로 되돌린다.
			while (idx > 0 && (pattern.charAt(idx) != pattern.charAt(i))) {
				idx = pi[idx - 1];
			}
			
			//접두사와 접미사가 일치하면 idx를 늘리면서 테이블에 기록한다.
			if (pattern.charAt(idx) == pattern.charAt(i)) {
				pi[i] += ++idx;
			}
		}
		return pi;
	}

	

}