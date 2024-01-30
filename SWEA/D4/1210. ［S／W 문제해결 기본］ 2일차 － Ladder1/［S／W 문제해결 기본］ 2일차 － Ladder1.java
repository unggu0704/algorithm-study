import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 데일리 실습
 * SWEA-1208 1일차 - Ladder
 *
 * ===========
	147 ms
	실행시간
	2,248
	코드길이
 * ==========
 * @author 김규형
 *
 */
public class Solution  {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for (int i = 1; i <= 10; i++) {
			System.out.println("#" + i + " " + solution(br));
		}
		
	}
	
	static int[] DY = {0, 0, -1}; 
	static int[] DX = {+1, -1, 0};
	
	public static int solution(BufferedReader br) throws IOException {
		br.readLine(); // 한줄 버림 1,2,3...
		
		StringTokenizer st;
		int[][] ladder = new int[100][100]; //사다리 2차원 배열 
		int y = 0, x = 0; //이동할 좌표 
		boolean isRight = false;
		boolean isLeft = false;

		
		for (int i = 0; i < 100; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 100; j++) {
				int input =  Integer.parseInt(st.nextToken());
				ladder[i][j] = input;

				if (input == 2) {
					y = i;	x = j;
				}
			}
		}
		
		while (true) {
			for (int i = 0; i < 3; i++) { // 오른쪽 -> 왼쪽 -> 위 탐색 
				int dy = y + DY[i];		int dx = x + DX[i];
				
				if (dy == 0) {
					return dx;
				}
					
				if (dy <= -1 || dx <= -1 || dy >= 100 || dx >= 100) // 경계를 넘어가면 탐색하지 않음 
					continue;
				
				if ((i == 1 && isRight) || (i == 0) && isLeft)  // 사이드로 가는 중이면  반대는 탐색하지 않음
					continue;
				
				
				if (ladder[dy][dx] == 1) { 
					y = dy;
					x = dx;
					
					if (i == 1) { //왼쪽 탐색 
						isLeft = true;
					} else if (i == 0) { //오른쪽 탐색 
						isRight = true;
					} else {
						isLeft = false;
						isRight = false;
					}
					
					break;
				}
			}
		}
	}
}

