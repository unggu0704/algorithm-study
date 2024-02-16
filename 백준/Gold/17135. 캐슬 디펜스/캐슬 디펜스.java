import java.io.*;
import java.util.*;

/**

 Main_17135_캐슬디펜스_김규형
 궁수 3명을 배치해 가장 많은 적을 사살
 궁수 3명을 각 배치할 로직 -> 백래킹으로 조합 생성
 화살을 쏠 로직 가장 가까운 적 , 가장 왼쪽 적 거리 : 4방 1, 대각선 2 - > BFS로 범위를 탐색... 우선 순위 가장 왼쪽
 적이 내려올 로직 -> 배열 내리기

 @author 김규형 */
public class Main {

    static int N;
    static int M;
    static int D;
    static int answer = 0;
    static StringBuilder sb = new StringBuilder();
    static int[][] map;

    static int[][] originalMap;
    static int[] castle, combine;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M];
        originalMap = new int[N + 1][M];
        castle = new int[M];
        combine = new int[M];

        int input = 0;
        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                originalMap[i][j] = input;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                for (int w = j + 1; w < M; w++) {
                    answer = Math.max(simulate(i, j, w), answer);
                }
            }
        }

        System.out.println(answer);
    }

    static int[] DX = {-1, 0, 1};
    static int[] DY = {0, -1, 0};

    public static int simulate(int a1, int a2, int a3) {
        int kill = 0;

        while (checkEnd()) {
            Coordinate t1 = attack(new Coordinate(N, a1));
            Coordinate t2 = attack(new Coordinate(N, a2));
            Coordinate t3 = attack(new Coordinate(N, a3));

            if (t1 != null && map[t1.y][t1.x] == 1) {
                map[t1.y][t1.x] = 0;
                kill++;
            }

            if (t2 != null && map[t2.y][t2.x] == 1) {
                map[t2.y][t2.x] = 0;
                kill++;
            }

            if (t3 != null && map[t3.y][t3.x] == 1) {
                map[t3.y][t3.x] = 0;
                kill++;
            }

            moveEnemy(); //적이 다가온다.

        }

        initMap();

        return kill;
    }

    public static Coordinate attack(Coordinate archerCoordinate) {
        Queue<Coordinate> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.add(archerCoordinate);

        for (int c = 0; c < D; c++) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Coordinate coordinate = q.poll();

                for (int j = 0; j < 3; j++) {
                    int dy = coordinate.y + DY[j];        int dx = coordinate.x + DX[j];

                    if (dy >= N || dy <= -1 || dx >= M || dx <= -1 || visited[dy][dx])
                        continue;

                    if (map[dy][dx] == 1)  // 만약 여기에 적이 있다면?
                        return new Coordinate(dy, dx);


                    visited[dy][dx] = true;
                    q.add(new Coordinate(dy, dx));
                }
            }
        }

        return null;
    }

    /**
     * 남은 적이 있는지?
     * @return
     */
    public static boolean checkEnd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void moveEnemy() {
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < M; j++) {
                map[i][j] = map[i - 1][j];
            }
        }

        for (int j  = 0; j < M; j++)
            map[0][j] = 0;
    }

    public static void initMap() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                map[i][j] = originalMap[i][j];
            }
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("====================");
    }
}

class Coordinate {
    int y;     int x;

    Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }
}