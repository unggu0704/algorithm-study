import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st =new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	
    	int list[][] = new int[N][2];
    	
    	for(int i = 0; i < N; i++) {
    		st =new StringTokenizer(br.readLine());
    		
    		list[i][0] = Integer.parseInt(st.nextToken());
    		list[i][1] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 입력 종료 
    	
    	Arrays.sort(list, new Comparator<int[]>() {
    		
    		@Override
    		public int compare(int[] o1, int[] o2) {
    			return o2[0] - o1[0];
    		}
    	});
    	
    	int dp[][] = new int[N+1][K+1];
    	
    	for(int i = 1; i < N+1 ; i++) { // 무게 값의 dp 배열 추가 	
    		int weight = list[i-1][0];
    		for(int j = 0; j < K+1; j++) {
    			if(weight >  j ) continue;
    			dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - weight] + list[i-1][1]);	
    		}
    	}
    	
    	
    	System.out.println(dp[N][K]);
    }
   
}




