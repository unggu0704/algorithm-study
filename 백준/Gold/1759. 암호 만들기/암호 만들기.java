import java.io.*;
import java.util.*;


/**
 * Main_1759_암호만들기_김규형
 *
 * 암호 규칙
 * 최소 한개의 모음
 * 최소 두개의 자음
 * 문자열은 정렬
 *
 * C개의 문자열을 조작
 *
 * @author 김규형
 */

public class Main {

    static int N;
    static int M;
    static int K;
    static StringBuilder sb = new StringBuilder();

    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    static char[] chars;
    static char[] combine;
    static boolean[] visited;
    static char[] verbs = {'a', 'e', 'i', 'o', 'u'};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 암호 문자열 갯수
        M = Integer.parseInt(st.nextToken()); // 조합될 문자들

        st = new StringTokenizer(br.readLine());

        chars = new char[M]; //가능한 문자열 모음 원 배열
        combine = new char[N];
        visited = new boolean[M];

        for (int i = 0; i < M; i++) {
            chars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(chars);

        backTracking(0, 0);

        System.out.println(sb.toString());
    }

    public static void backTracking(int index, int depth) {
        if (depth == N) {
            int verbCount = 0;
            int consonantCount = 0;

            for (char c : combine) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                    verbCount++;
                else
                    consonantCount++;


            }

            if (verbCount < 1 || consonantCount < 2) //모음이 없거나 자음이 한개 미만이면 탐색하지 않는다.
                return;

            for (char c : combine) {
                sb.append(c);
                //		System.out.print(c + "|");
            }
            //	System.out.println();

            sb.append("\n");

            return;
        }


        for (int i = index; i < M; i++) {
            if (visited[i])
                continue;

            combine[depth] = chars[i];
            visited[i] = true;
            backTracking(i + 1, depth + 1);
            visited[i] = false;
        }
    }

}