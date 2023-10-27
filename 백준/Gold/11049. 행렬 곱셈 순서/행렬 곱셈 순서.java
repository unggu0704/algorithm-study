import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}

    /**
     * 특정 i, j
     * i, j-1 or i+1, j 중에 MIN 값을 가져온다.
     * 
     * k = 1 :  1, 2 |  2, 3 |  3 ,4
     * k = 2 :  1, 3 2 .4 3 ,5
     * k = 3 :  1, 4 2, 5 3, 6
     * @throws IOException
     */
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N;
        int[][] dp;
        int[][] input;

        N = Integer.parseInt(st.nextToken());
        input = new int[N][2];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
           st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    dp[i][j] = 0;
           
                }
            }
        }

        for (int k = 1; k < N ; k++) {
            for (int i = 0; i + k < N; i++) {
                dp[i][i+k] = Integer.MAX_VALUE;
				for(int j=i; j<i+k; j++){
					dp[i][i+k] = Math.min(dp[i][i+k], dp[i][j]+dp[j+1][i+k] + input[i][0]*input[j][1]*input[i+k][1]);
                                        // k = 3
                                        // j = 0 dp[0][3] = min(dp[0][3], dp[0][0] + dp[1][3])
                                        // j = 1                          dp[0][1] + dp[2][3]
                                        // j = 2                          dp[0][2] + dp[3][3]
                }
			}
                
                // k = 2 ,  dp[0][2] = dp[0][1], dp[1][2] + input[0][0] *  
                //dp[i][i + k] = Math.min( (dp[i][i + k - 1] + (input[i][0] * input[i][1] * input[i + 1][1])), (dp[i + 1][i + k] + (input[i + 1][0] * input[i + 1][1] * input[i + k][1])) );
                // k = 1 i  = 0 |    dp[0][1] = dp[0][0] + input[0][0] * [0][1] * [1][1]    ,  dp[1][1]  +  input [1][0] * [2][1] * [1][1]
                // k = 2 ,  dp[0][2] = dp[0][1] + input[0][0] * [0][1] * [2][1]    ,   dp[1][2] +  input [1][0] * [1][1] * [2][1]
            }
        

        /* 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        */

        System.out.println(dp[0][N-1]);



    }

}






