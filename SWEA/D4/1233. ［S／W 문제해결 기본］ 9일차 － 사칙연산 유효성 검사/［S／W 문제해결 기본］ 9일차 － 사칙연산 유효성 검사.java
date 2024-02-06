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
import java.util.StringTokenizer;
 
 /**
  * SWEA_9229_한빈이와 Spot_Mart_김규형
  * 메모리
  * 31,996 kb
  * 시간
  * 235 ms
  * 
  * @author 김규형
  *
  */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
  //      int index = Integer.parseInt(br.readLine());
        for (int i = 1; i <= 10; i++) {
        	System.out.print("#" + i + " ");
            solution(br);
        }
 
 
    }
     
    public static void solution(BufferedReader br) throws IOException {
    	String operators ="+-/*";
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int answer =1 ;
    	int N = Integer.parseInt(st.nextToken());
    	
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int root = Integer.parseInt(st.nextToken()); //루트 노드 입력 
    		String oper = st.nextToken();
    		
    		int leaf = N / 2 + 1;
    		
    		if (root >= leaf) {
    			if (operators.contains(oper)) {
    				answer = 0;
    				continue;
    			}
    		}
    	}
    	
    	System.out.println(answer);
    }
}