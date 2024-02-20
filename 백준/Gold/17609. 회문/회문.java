import java.io.*;
import java.util.*;

/**
 * Main_2636_치즈_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static int K;
	static StringBuilder sb = new StringBuilder();

	static int[] DY = {-1, 0, 1, 0};
	static int[] DX = {0, 1, 0, -1};
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		String[] input = new String[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = br.readLine();
		}

		for (int i = 0; i < N; i++) {
			Character[] palindrome = new Character[input[i].length()];
			for (int j = 0; j < input[i].length(); j++) {
				palindrome[j] = input[i].charAt(j);
			}
			
			sb.append(twoPointer(palindrome, 0, palindrome.length - 1, 0)).append(" ");
		}

		System.out.println(sb.toString());
	}
	
	public static int twoPointer(Character[] palindrome, int first, int end, int diff) {
		
		if (first >= end)
			return diff;
		
		int result = -1;
		
		if (palindrome[first] == palindrome[end]) { //양 쪽이 같다면!
			result = twoPointer(palindrome, first + 1, end - 1, diff); //다음 찾으러 간다.
		} else if (diff != 1 && palindrome[first] != palindrome[end]) { //유사 팰린드룸으로 한번 더 기회를 준다...
			result = twoPointer(palindrome, first + 1, end, 1);
			
			if (result == 2)
				result = twoPointer(palindrome, first, end - 1, 1);
		} else {
			result = 2;
		}
		
		return result;
	}
}