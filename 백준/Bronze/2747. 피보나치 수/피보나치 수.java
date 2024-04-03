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
 * Main_2747_피보나치수_김규형
 *   메모리       시간
	14096	124	
 * 
 */
public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	    int N = Integer.parseInt(br.readLine());
	    
	    int[] dp = new int[N + 1];
	    
	    dp[0] = 0;
	    dp[1] = 1;
	    
	    for (int i = 2; i <= N; i++) {
	    	dp[i] = dp[i - 1] + dp[i -2];
	    }
	    
	    System.out.println(dp[N]);
    }
}