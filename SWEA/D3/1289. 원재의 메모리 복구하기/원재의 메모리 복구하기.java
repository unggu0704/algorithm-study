import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < N + 1; i++) {
			System.out.println("#" + i + " " + solution(br.readLine()));
		}
			
	}
	
	public static int solution(String original) {
		sb = new StringBuilder();
		int answer = 0;
		
		for (int i =0; i < original.length(); i++) {
			sb.append("0");
		}
		//초기 비트 값 0으로 세팅
		
		String bit = sb.toString(); //초기 비트 
		
		for (int i = 0; i < original.length(); i++) {
			if (original.charAt(i) == bit.charAt(i)) 
				continue; // 이미 서로 비트가 같다면 다음 비트로 넘어감 
			
			bit = changeBit(bit, i, original.charAt(i) - 48);
			answer++;
		}
		
		return answer;
	}
	
	public static String changeBit(String bit, int index, int bitNumber) {
		sb = new StringBuilder();
		
		for (int i = 0; i < index; i++) {
			sb.append(bit.charAt(i));
		} // 기존의 인덱스까지 이어 적기 
		
		for (int i = index; i < bit.length(); i++) {
			sb.append(bitNumber);
		} // 새로 변환된 인덱스까지 이어적기
		
		return sb.toString();
	}
}
