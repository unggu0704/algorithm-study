import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    /**
     *
     * BOJ_15787_기차가 어둠을 헤치고 은하수를
     * @authors 김규형
     */
    static StringBuilder sb = new StringBuilder();
    static   int[][] trains;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trains = new int[N][20];
        int[][] commands = new int[M][3];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            while (st.hasMoreTokens())
                commands[i][j++] = Integer.parseInt(st.nextToken()) - 1;
        }
        //입력 종료

        /**
         * 입력된 명령들을 수행한다.
         */
        for (int c = 0; c < M; c++) {
            int command = commands[c][0];
            switch (command) {
                case 0:
                    command1(commands[c][1], commands[c][2]);
                    break;
                case 1:
                    command2(commands[c][1], commands[c][2]);
                    break;
                case 2:
                    command3(commands[c][1]);
                    break;
                case 3:
                    command4(commands[c][1]);
                    break;
            }
        }

        /**
         * 은하수를 건넌다.
         */

        List<int[]>  milkyWay = new LinkedList<>();
        milkyWay.add(trains[0]);
        for (int i = 1; i < N; i++) {
            int[] train = trains[i];

            loop:
            for (int l = 0; l < milkyWay.size(); l++) {
                int[] overTrain = milkyWay.get(l);
                for (int j = 0; j < 20; j++) {
                    if (overTrain[j] != train[j]) {
                        break; // i 번재 기차는 다르기때문에 다른거 보러간다.
                    }

                    if (j == 19)
                        break loop;
                }

                if (l == milkyWay.size() - 1)
                    milkyWay.add(train);
            }

        }

        System.out.println(milkyWay.size());
    }

    public static void command1 (int trainNum, int seatNum) { //1번 명령
        trains[trainNum][seatNum] = 1;
    }

    public static void command2 (int trainNum, int seatNum) { //2번 명령
        trains[trainNum][seatNum] = 0;
    }

    public static void command3 (int trainNum) { //3번 명령
        for (int i = 18; i >= 0; i--) {
            trains[trainNum][i + 1] = trains[trainNum][i];
        }
        trains[trainNum][0] = 0;
    }

    public static void command4 (int trainNum) { //4번 명령
        for (int i = 0; i < 19; i++) {
            trains[trainNum][i] = trains[trainNum][i + 1];
        }
        trains[trainNum][19] = 0;
    }

}

