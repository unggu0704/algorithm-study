import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
 
 /**
  * SWEA_1233_사칙연산_유효성검사_김규형
  * 메모리
  * 21,996 kb
  * 시간
  * 135 ms
  * 
  * @author 김규형
  *
  */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int index = Integer.parseInt(br.readLine());
        for (int i = 1; i <= index; i++) {
        	System.out.print("#" + i + " ");
            solution(br);
        }
    }
    
    static int[][] input;
    static int N;
    static int Cal;
    static int max;
    public static void solution(BufferedReader br) throws IOException {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	max = 0;
    	N = Integer.parseInt(st.nextToken());
    	Cal = Integer.parseInt(st.nextToken());
    	input = new int[N][2];
    	
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		input[i][0] = Integer.parseInt(st.nextToken());
    		input[i][1] = Integer.parseInt(st.nextToken());
    	}
    	
    	for (int i = 1; i <= N; i++) {
    		backTracking(0, 0, i, 0, 0);
    	}
    	
    	System.out.println(max);
    }
    
    public static void backTracking(int index, int depth, int size, int val, int cal) {
    	if (cal > Cal) 
    		return;
    	
    	
    	if (depth == size) {
    		max = Math.max(val, max);
    		return;
    	}
    	
    	for (int i = index; i < N; i++) {
    		backTracking(i + 1, depth + 1, size, val + input[i][0], cal + input[i][1]);
    	}
    }
}