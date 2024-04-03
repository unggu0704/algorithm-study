import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * Main_9461_파도반수열_김규형
 *   메모리       시간
 * 	16160kb	168ms	
 * 
 * DP와 그리디를 적절히 섞은 문제
 */
public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	    int N = Integer.parseInt(br.readLine());
	    
	    long[] dp = new long[101];
	
	    dp[1] = dp[2] = dp[3] = 1;
	    dp[4] = dp[5] = 2;
	
	    for (int i = 6; i <= 100; i++)
	        dp[i] = dp[i - 1] + dp[i - 5];
	
	    for (int tc = 1; tc <= N; tc++){
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        System.out.println(dp[Integer.parseInt(st.nextToken())]);
	    }
}
}