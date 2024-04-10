import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    static char[][] map;
    static int height, weight;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        height = (int) Math.pow(2, N) - 1;

        weight = 1;
        int add = 4;
        for (int i = 1; i < N; i++) {
            weight += add;
            add += add;
        }

        map = new char[height + 1][weight];

        for (int i = 0; i< height; i++) {
            for (int j = 0; j < weight; j++) {
                map[i][j] = ' ';
            }
        }

        makeDuctilityCircle(N, 0, 0, height - 1);

        StringBuilder sb = new StringBuilder();

        List<Integer> list = new ArrayList<>();


        for (int i = 0; i < height; i++)
            list.add(i);


        if (N % 2 == 1)
            list.sort(Collections.reverseOrder());

        for (int i = 0; i< height; i++) {
            int end = list.get(i);
            for (int j = 0; j < weight - end; j++) {
                sb.append(map[i][j]);

            }
            if (N != 1)
                sb.append("\n");
        }

        System.out.println(sb);
    }


    public static void makeDuctilityCircle(int n, int startX, int startY, int endY) {
        if (n == 0)
            return;

        if (startY + 1 == endY)
            startY = endY;

        int endX = startX;
        int add = 4;
        for (int i = 1; i < n; i++) {
            endX += add;
            add += add;
        }


//        System.out.println("startY : " + startY + ", endY : " + endY);
//        System.out.println("startX : " + startX + ", endX : " + endX);

        //홀수라면?
        if (n % 2 == 1) {
            for (int i = startX; i <= endX; i++) {
                map[endY][i] = '*';
            }

            for (int i = endY - 1; i >= startY; i--) {
                for (int j = startX; j < endX ; j++) {
                 //   System.out.println((endY - i) + " | " + (endX - i - endY));
                    if (j == startX + endY - i || j ==  endX - (endY - i))
                        map[i][j] = '*';
                }
            }

            makeDuctilityCircle(n - 1,  startX +  (int) Math.pow(2, n - 1), startY + (endY - startY) / 2, endY - 1);

        } else { //짝수라면?
            for (int i = startX; i <= endX; i++) {
                map[startY][i] = '*';
            }

            for (int i = startY + 1; i <= endY; i++) {
                for (int j = startX; j < endX; j++) {

                    if (j == startX + i - startY|| j == endX - (i - startY) )
                        map[i][j] = '*';
                }
            }


            makeDuctilityCircle(n - 1,  startX +  (int) Math.pow(2, n - 1), startY + 1, startY + (endY - startY) / 2);
        }

    }


}

