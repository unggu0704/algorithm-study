import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    /**
     * BOJ_21318_피아노 체조
     * @authors 김규형
     */
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int[] muscis = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            muscis[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        int[][] questions = new int[M][2];
        for (int i = 0; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            questions[i][0] = Integer.parseInt(st.nextToken());
            questions[i][1] = Integer.parseInt(st.nextToken());
        }
        // 입력 종료

        int[] mistakeMusic = new int[N + 1];

        for (int i = 1; i < N; i++) {
            //뒤에 있는 악보보다 현재 어려운게 있다면
            if (muscis[i] >  muscis[i + 1]) {
                mistakeMusic[i]++; // i 번재 악보에서는 반드시 틀린다.
            }
            mistakeMusic[i] += mistakeMusic[i -1];
        }
        //틀릴 악보 설정 -> 누적합 계산

        for (int i = 0; i < M; i++) {
            int start = questions[i][0] - 1; //이전까지의 누적합
            int end = questions[i][1] - 1;

            int mistakeCount = mistakeMusic[end] - mistakeMusic[start];
            sb.append(mistakeCount).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}

