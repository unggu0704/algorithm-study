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
class Node {
	int y; int x;
	boolean wall;
	
	Node(int y, int x, boolean wall) {
		this.y = y;
		this.x = x;
		this.wall = wall;
	}
}
public class Main {

	static int N;
	static int M;
	static int answer = 0;
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] input = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input, (int[] i1, int[] i2) -> i1[0] - i2[0]);
		
		st = new StringTokenizer(br.readLine());
		
		int totalDistance = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());
		//입력 종료 
		
		int range = 0;
		while (fuel < totalDistance) {
			if (answer > N) {
				answer = -1;
				break;
			}
			
			while (N > range) {
				if (input[range][0] > fuel)  //연료료 갈 수 없다면 우선 반복문을 중단한다.
					break;
				range++;
			}
			
			int maxIndex = 0;
			int max = 0;
			// 현재 갈 수 있는 범위내에 가장 큰 기름 충전 할 곳을 찾는다.
			for (int i = 0; i < range; i++) {
				if (input[i][1] >= max) {
					max = input[i][1];
					maxIndex= i;
				}
			}
			input[maxIndex][1] = -1;
			
			fuel += max; //가장 많이 충전할 수 잇는 주유소의 기름을 넣는다.
			answer++;
		}
		
		System.out.println(answer);
		
	}
	
}