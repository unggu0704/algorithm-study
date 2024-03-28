import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_2239_스도쿠_김규형
 * @author 김규형
 *
 */
public class Main {
    static int N;
    static int M;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int[] input = new int[N];
        int[] dp = new int[M + 2];
        for (int i = 0; i < N; i++) {
        	input[i] = Integer.parseInt(br.readLine());
        }
        dp[0] = 1;
        //점화식 : dp[i] = dp[i] + dp[i - coin]
        for (int i = 0; i < N; i++) {
        	for (int j = input[i]; j <= M + 1; j++) {
        		dp[j] += dp[j - input[i]];
        	}

        }
        
        System.out.println(dp[M]);
    }
    

}