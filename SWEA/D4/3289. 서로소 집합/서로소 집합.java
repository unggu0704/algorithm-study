import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int index = Integer.parseInt(br.readLine());
        for (int i = 1; i <= index; i++) {
        	sb.append("#").append(i).append(" ");
            solution(br);
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    static int N;
    static int M;
    
    static int[] parents;
    public static void solution(BufferedReader br) throws IOException {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		} //초기에는 자기 자신이 부모이다.
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int command = Integer.parseInt(st.nextToken());
			int friendRoot1 = find(Integer.parseInt(st.nextToken()));
			int friendRoot2 = find(Integer.parseInt(st.nextToken()));
			
			switch (command) {
			case 0:
				if (friendRoot1 != friendRoot2)
					if (friendRoot1 > friendRoot2)
						parents[friendRoot1] = parents[friendRoot2]; //합친다
					else if (friendRoot1 < friendRoot2)
						parents[friendRoot2] = parents[friendRoot1]; 
				break;
				
			case 1:
				int ans = 0;
				if (friendRoot1 == friendRoot2)
					ans = 1;
				
				sb.append(ans);
				break;
			}
			
//			for (int j = 1; j < N + 1; j++) {
//				System.out.print(parents[j] + " ");
//			}
//			System.out.println();
		}
    }
    
	public static int find(int node) {
		if (parents[node] == node) return node; //부모를 찾았다!
		
		return parents[node] = find(parents[node]); //경로 압축
	}
}