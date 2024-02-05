import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
         
        for (int i = 1; i <11; i++) {
        //    int index = Integer.parseInt(br.readLine());
            System.out.print("#" + i + " ");
            solution(br);
            System.out.println();
        }
 
 
    }
     
    public static void solution(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> password = new ArrayList<>();
        int N = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
        	password.add(Integer.parseInt(st.nextToken()));
    	}
        
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
        	String x = st.nextToken();
        	int y = Integer.parseInt(st.nextToken());
        	int z = Integer.parseInt(st.nextToken());
        	List<Integer> insertList = new ArrayList<>();
        	for (int j = 0; j < z; j++) {
        		insertList.add(Integer.parseInt(st.nextToken()));
        	}
        	
        	for (int j = insertList.size() - 1; j >= 0; j--) {
        		password.add(y, insertList.get(j));
        	}
        }
     
        for (int i = 0; i < 10; i++) {
        	System.out.print(password.get(i) + " ");
        }
    }
}