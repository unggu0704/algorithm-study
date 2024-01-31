import java.io.*;
import java.util.*;

/**
 * 데일리 실습 
 * BOJ_2212_센서
 * 
 * 입력 
 * N = 10.000
 * K = 1.000
 * @author 김규형 
 *
 */

public class Main {
   
   
	static int[][] arr;
	static int[] dx;
	static int[] dy;
	static int size;

	public static void main(String args[]) throws Exception {

	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());

	    int N = Integer.parseInt(st.nextToken());
	    int K = Integer.parseInt(br.readLine());
	    
	    int[] highway = new int[N];
	    int[] diff = new int[N - 1];
	    
	    st = new StringTokenizer(br.readLine());
	    for (int i = 0; i < N; i++) {
	    	highway[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    Arrays.sort(highway); // 고속도로 센서 정렬
	    
	    for (int i = 0; i < N - 1; i++) {
	    	diff[i] = highway[i + 1] - highway[i];
	    }

	    Arrays.sort(diff); // 차이 정렬 
	    
	    int answer = 0;
	    
	    for (int i = 0; i < N - K; i++) {
	    	answer += diff[i];
	    }
	    
	    System.out.println(answer);
	
	}
}
