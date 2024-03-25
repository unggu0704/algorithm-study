import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	static int ans = 0;
	static int N;
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	
    	int stairs[] = new int[N+1];
    	stairs[0] = 0; 
    	for(int i = 1; i < N+1; i++) {
    		st = new StringTokenizer(br.readLine());
    		stairs[i] =  Integer.parseInt(st.nextToken());
    	}
    	// 입력 처리 
    	
    	int dp[][] = new int[N+1][2];
    	dp[0][0] = 0;	dp[0][0] = 0;
    	if(N >= 1) {
    		dp[1][0] = stairs[1];
    		dp[1][1] = stairs[1];
    	}
    	if(N >= 2) {
    		dp[2][0] = stairs[2];
    		dp[2][1] = stairs[2] + dp[1][1];
    	}
    	
    	for(int i = 3; i < N+1; i++) {
    		dp[i][0] = stairs[i] + Math.max(dp[i-2][0], dp[i-2][1]);  //두번 뛴다면 어디든지 뛰어도 됨 
    		dp[i][1] = stairs[i] + dp[i-1][0]; // 이전에 두번 뛴 칸과 이어서 뜀 
    	}
    	
    	System.out.println(Math.max(dp[N][0], dp[N][1]));
    	
    }
}
