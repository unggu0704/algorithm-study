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
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			System.out.println("#" + i);
			solution(br);
		}
		
	}
	
	static int[] DY = {0, 0, -1}; 
	static int[] DX = {+1, -1, 0};
	
    private static void solution(BufferedReader br) throws NumberFormatException, IOException {
    	
    	int n = Integer.parseInt(br.readLine());
       	
        int[] DX = {1, 0, -1, 0};
        int[] DY = {0, 1, 0, -1};
        
       	boolean[][] visited = new boolean[n][n];
       	int[][] answer = new int[n][n];
       	int y = 0, x = 0, i = 0;
       	visited[y][x] = true;
       	
       	int num = 1;
      	answer[y][x] = num++;
       	int loopCount = 0;
       	while (loopCount++ <= 4) {
   			int dy = y + DY[i];	int dx = x + DX[i];
       
   			if (dy >= n || dx >= n || dy <= -1 || dx <= -1 || visited[dy][dx]) { // 조건에 걸리면
   				if (++i >= 4) // 핸들 꺽기 
   					i = 0;
   				continue;
   			}
   			
   			loopCount = 0;
   			visited[dy][dx] = true;
   			answer[dy][dx] = num++;
   			y = dy;	x= dx;
   			
       	}
       	
       	for (int i1 = 0; i1 < n; i1++) {
       		for (int j = 0; j < n; j++) {
       			System.out.print(answer[i1][j] + " ");
       		}
       		System.out.println();
       	}
           
       }
       
}

