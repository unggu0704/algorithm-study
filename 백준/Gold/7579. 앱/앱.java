import java.io.*;
import java.util.*;

public class Main {

static int N;
static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

         int[] app = new int[N];
         int[] cost = new int[N];
         int answer = Integer.MAX_VALUE;
         int allCost = 0;

         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < N; i++) {
            app[i] = Integer.parseInt(st.nextToken());
         }

         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            allCost += cost[i];
         }

        int[][] dp = new int[N][100001];
         for (int i = 0; i < N; i++) { // 행의 반복문 
            int mem = app[i]; // 해당 앱의 메모리 
            int c = cost[i]; // 해당 앱의 철거 비용

            for (int j = 0; j < 100000; j++) { //열의 반복문 
                if (i == 0) { // 위의 값이 없으면 우선 셋팅
                    if (j >= c)
                        dp[i][j] = mem;
                }
                else {
                    if (j >= c) { //비용이 음수가 되는 경우 
                        dp[i][j] = Math.max(dp[i - 1][j - c] + mem, dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            
                if (dp[i][j] >= M) {
                    answer = Math.min(answer, j);
                }
            }
         }
         System.out.println(answer);
    }
}