import java.io.*;
import java.util.*;



/////////////////////////////////////////////////////////////////////////////////////////////
//기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
//아래 표준 입출력 예제 필요시 참고하세요.
//표준 입력 예제
//int a;
//double b;
//char g;
//String var;
//long AB;
//a = sc.nextInt();                           // int 변수 1개 입력받는 예제
//b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
//g = sc.nextByte();                          // char 변수 1개 입력받는 예제
//var = sc.next();                            // 문자열 1개 입력받는 예제
//AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
//표준 출력 예제
//int a = 0;                            
//double b = 1.0;               
//char g = 'b';
//String var = "ABCDEFG";
//long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {
	
	static int N, M;
	public static void main(String args[]) throws Exception
	{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int[][] parisMap = new int[N + 1][N + 1];
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {

					parisMap[i][j] =  Integer.parseInt(st.nextToken()) + parisMap[i - 1][j] + parisMap[i][j - 1] - parisMap[i - 1][j - 1]; 
									// 누적 합  : 자기 값 + 이전 왼쪽 값 + 이전 위 값 - 이전 왼쪽 위 대각선
				}
			}
			
			int MAX = Integer.MIN_VALUE;
			for (int i = M; i <= N; i++) {
				for (int j = M; j <= N ; j++) {
					int prexSum = parisMap[i][j] - parisMap[i - M][j] - parisMap[i][j - M] + parisMap[i - M][j - M];
					MAX = Math.max(MAX, prexSum);
				}
			}
			
			System.out.println("#" + test_case + " " + MAX);
		}

	}
	

}