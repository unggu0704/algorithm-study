import java.io.*;
import java.util.*;

/**

 Main_16935_배열돌리기3_김규형
 @author 김규형 */
public class Main {

    static int N;
    static int M;
    static long answer = 0;

    static int[] DX = {0, -1, -1,  0,  1, 1, 1, 0, -1};
    static int[] DY = {0,  0, -1, -1, -1, 0, 1, 1,  1};
    static int[] edge = {1, 1, -1, 1, 1, -1, -1, -1};

    static int[][] map;
    static int[][] input;
    static List<Coordinate> cloudList;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        input = new int[M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }
        // 입력 종료

        //M 번 반복한다.
        cloudList = new ArrayList<Coordinate>(); //구름 좌표 리스트
        cloudList.add(new Coordinate(N - 1, 0));    cloudList.add(new Coordinate(N - 1, 1));
        cloudList.add(new Coordinate(N - 2, 0));    cloudList.add(new Coordinate(N - 2, 1));

        for (int i = 0; i < M; i++) {
            moveCloud(input[i][0], input[i][1]); //구름 이동
            waterCopyMagic(); //물 복사
            generateCloud(); //새 구름 생성
        }

        System.out.println(waterCount());
    }

    /**
     * 구름이 움직이는 로직
     * @param directNum 어느 방향으로 움직일지?
     * @param moveCount 몇번 움직일지?
     */
    public static void moveCloud(int directNum, int moveCount) {
        for (Coordinate cloud : cloudList) { //하나의 구름을 뽑아서
            int y = cloud.y;        int x = cloud.x;
            for (int i = 0; i < moveCount; i++) { //moveCount만큼 움직인다!
                y += DY[directNum];        x += DX[directNum];

                //마이너스로 넘어갈때 처리
                if (y < 0)
                    y = N - 1;
                if (x < 0)
                    x = N - 1;

                y %= N;
                x %= N;
            }
     //       System.out.println("원래 " + cloud.y + ", " + cloud.x + "에 있던 구름이 " + y + ", " + x + "로 이동함 ");
            map[y][x]++; // 옮긴 구름에 비가 내려서 물이 1씩 올라간다.
            cloud.y = y;    cloud.x = x;
        }
     //   printMap();
    }

    /**
     * 주변의 물을 복사하는 로직
     */
    public static void waterCopyMagic() {

        for (Coordinate cloud : cloudList) {

            int waterCount = 0;

            for (int i = 0; i < 8; i+=2) {
                int y = cloud.y + edge[i];      int x = cloud.x + edge[i + 1];

                if (y >= N || x >= N || y <= -1 || x <= -1)
                    continue;

                if (map[y][x] > 0) {
                    waterCount++;
                }
            }
            map[cloud.y][cloud.x] += waterCount;
        }

     //   printMap();
    }

    /**
     * 구름이 생성되는 로직
     */
    public static void generateCloud() {
        List<Coordinate> newCloudList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                if (map[i][j] >= 2 && !cloudList.contains(coordinate)) {
                    map[i][j] -= 2; //물을 2만큼 줄어들게 한다.
                    newCloudList.add(new Coordinate(i, j)); //구름을 만든다.
                }
            }
        }

        cloudList = newCloudList;

     //   System.out.println("구름이 생성됨");
    //    printMap();
    }

    /**
     * 모든 물의 양을 계산
     */
    public static int waterCount() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += map[i][j];
            }
        }

        return answer;
    }

    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=========================");
    }

}

class Coordinate {
    int y;    int x;

    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        Coordinate coordinate = (Coordinate) o;

        return coordinate.x == this.x && coordinate.y == this.y;
    }
}