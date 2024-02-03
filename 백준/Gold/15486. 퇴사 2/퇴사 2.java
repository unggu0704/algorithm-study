import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int maxPay = - 1;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        int[][] input = new int[N + 1][2]; //입력 정보를 담을 배열

        //  dp.add(0);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N + 1; i++) {
            if (maxPay < dp[i]) //현재 i일차의 최댓값
                maxPay = dp[i];

            int endDay = input[i][0] + i; // 상담이 끝나는 날
            int endPay = input[i][1] + maxPay; // 상담이 끝나는 날 얻게 되는 최댓값

            if (endDay > N) //endDay가  N 보다 크다면 탐색하지 않음
                continue;

            dp[endDay] = Math.max(dp[endDay], endPay); //기존의 dp값보다 큰가?
      //      System.out.print(dp[i] + " ");
        }

        System.out.println(dp[N]);
    }

}

