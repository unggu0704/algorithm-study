import java.io.*;
import java.util.*;

/**
 * ep
 * BOJ_2916_센서
 *
 *
 * @author 김규형
 */

public class Main {

    static StringTokenizer st;
    static int N;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(10, N);

        StringBuilder sb = new StringBuilder();
        for (int i = N / 10; i < N; i++) {
            // 1의 자리수부터 탐색

            loop:
            for (int j = N / 10; j != 0; j /= 10) {
                int num = i / j; //앞자리 수

                if (num < 2) break loop;

                if (N == 10 && num == 2) 
                    sb.append(2).append("\n");
                
                if (num == 2) continue;

                for (int w = 2; w <= Math.sqrt(num); w++) {
                    if (num % w == 0) {
                        break loop;
                    }
                }
                if (j < 10)
                    sb.append(i).append("\n");
            }

        }
        System.out.println(sb.toString());
    }
}