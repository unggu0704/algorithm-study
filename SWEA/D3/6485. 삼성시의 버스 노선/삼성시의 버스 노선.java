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
	
	static int N;
	public static void main(String args[]) throws Exception
		{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {

	        StringTokenizer st = new StringTokenizer(br.readLine()); 
			N = Integer.parseInt(st.nextToken()); // 버스의 갯수
			
			HashMap<Integer, Integer> busMap = new HashMap<>();
			
			for (int i = 1; i <= 5000; i++) {
				busMap.put(i, 0);
			}
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()); // 한줄 입력
				int startBus = Integer.parseInt(st.nextToken());
				int endBus = Integer.parseInt(st.nextToken());
				
				for (int j = startBus; j <= endBus; j++) {
					busMap.put(j, busMap.get(j) + 1); // 정류장에 도착하는 버스 value를 1씩 추가 
				}
			}
			
			ArrayList<Integer> arr = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int printCount = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < printCount; i++) {
				st = new StringTokenizer(br.readLine());
				int key = Integer.parseInt(st.nextToken());
				arr.add(busMap.get(key));
			}
			
			System.out.print("#" + test_case);
			for (Integer i : arr) {
				System.out.print(" " + i);
				
			}
			System.out.println();
				
		}

	}
	

}