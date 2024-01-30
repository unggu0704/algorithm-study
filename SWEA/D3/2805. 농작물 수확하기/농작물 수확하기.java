import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 데일리 실습
 * 15649 N과 M(1) S3
 * 
 * 메모리 : 37964kb
 * 시간 : 488ms
 * @author SSAFY
 *
 */
public class Solution {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < tc + 1; i++) {
			System.out.println("#" + i + " " + solution(br));
		}
		
	}
	
	public static int solution(BufferedReader br) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int[][] farm = new int[N][N];
		
		int midIndex = N / 2; // 중간 인덱스
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				farm[i][j] = input.charAt(j) - 48;
			}
		}
		// 입력 종료 
		
		//다이아몬드 형식으로 탐색 
		for (int i = 0; i < N; i++) {
			
			//범위가 커질 경우 
			if (i <= midIndex) {
				for (int j = midIndex - i; j < N - midIndex + i; j++) {
					answer += farm[i][j];
				}	
			} //범위가 좁아질 경우  
			else {
				for (int j = i - midIndex; j < N + midIndex - i; j++) {
					answer += farm[i][j];
				}
			}
		}

		return answer;
	}
	

}

