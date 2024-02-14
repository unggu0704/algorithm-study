import java.io.*;
import java.util.*;

/**

 Main_3109_빵집_김규형
 @author 김규형 */
public class Main {

    static int N;
    static int M;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        find(0, N, 0, N, N);

        System.out.println(sb.toString());
    }

    public static void find(int startY, int endY, int startX, int endX, int size) {
        char init = '_';

        //사분면 체크
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                if (init == '_') {
                    init = map[i][j];
                    continue;
                }

                if (map[i][j] != init) {
                    quadTree(startY, endY, startX, endX, size);
                    return;
                }
            }
        }
        sb.append(init);
    }

    public static void quadTree(int startY, int endY, int startX, int endX, int size) {

        sb.append("(");
        find (startY, (startY + endY) / 2, startX, (startX + endX) / 2, size / 2); //1사분면 탐색
        find (startY, (startY + endY) / 2, (startX + endX) / 2, endX, size / 2); //2사분면 탐색
        find ((startY + endY) / 2, endY, startX, (startX + endX) / 2, size / 2); //3사분면 탐색
        find ((startY + endY) / 2, endY, (startX + endX) / 2, endX, size / 2); //4사분면 탐색
        sb.append(")");
    }
}