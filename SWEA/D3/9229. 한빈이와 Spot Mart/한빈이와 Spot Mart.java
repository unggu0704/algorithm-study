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
  * SWEA_1225_암호생성기_김규형
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
     
    public static void solution(BufferedReader br) throws IOException {
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	List<Integer> list = new ArrayList<>(N);
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < N; i++) {
    		list.add(Integer.parseInt(st.nextToken()));
    	}
    	
    	int max = -1;
    	for (int i = 0; i < list.size(); i++) {
    		for (int j = i +1; j < list.size(); j++) {
    			int totalSnack = list.get(i) + list.get(j);
    			if (totalSnack <= M)
    				max = Math.max(max, list.get(i) + list.get(j));
    		}
    	}
    	
    	System.out.println(max);
    }
}