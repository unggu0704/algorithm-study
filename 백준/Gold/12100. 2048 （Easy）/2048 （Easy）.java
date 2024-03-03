import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_12100_2048(easy)_김규형
 *
 * 완전 탐색
 *  N = 20 N ^ N
 *
 *  4방향으로 각자 회전시켜서 depth가 5까지 도달 할때 가장 높은 블록을 구하기
 *  배열의 복원화가 중요 할듯 함
 * @author 김규형
 */
public class Main{
    static int N;
    public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            N = Integer.parseInt(br.readLine());

            int[][] map = new int[N][N];

            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            simulate(map, 0);

            System.out.println(ans);
    }
    static int[] DX = {-1, 0, 1, 0};
    static int[] DY = {0, -1, 0, 1};


    static int ans = 0;
    public static void simulate(int[][] omap, int depth) {
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ans = Math.max(omap[i][j], ans);
                }
            }
            return;
        }



        for (int d = 0; d < 4; d++) {
            int[][] map = copyMap(omap);
            boolean[][] visited = new boolean[N][N];

          

            int starty = 0;     int startx = 0;
            int endy = 0;       int endX = 0;
            if (d == 0) {
                starty = 0;     startx = 1;
                endy = N;       endX = N;
            } else if (d == 1) {
                starty = 1;     startx = 0;
                endy = N;       endX = N;
            } else if (d == 2) {
                starty = 0;     startx = N - 2;
                endy = N;       endX = -1;
            } else {
                starty = N - 2;  startx = 0;
                endy = -1;       endX = N;
            }


            if (d == 0 || d == 1) {
                for (int i = starty; i < endy; i++) {
                    for (int j = startx; j < endX; j++) {
                        if (map[i][j] > 0) {
                            int dx = j;     int dy = i;

                            while (true) {
                                dy += DY[d];    dx += DX[d];
                                if (dy <= -1 || dx <= -1) {
                                    dy -= DY[d];    dx -= DX[d];
                                    break;
                                }

                                if (map[dy][dx] > 0)
                                    break;
                            }

                            if (map[dy][dx] == 0) {
                                map[dy][dx] = map[i][j];
                                visited[dy][dx] = visited[i][j];
                            } else if (visited[i][j] || visited[dy][dx] || map[i][j] != map[dy][dx]) { //합칠 수 없다면 이전에 저장한다.
                                dy -= DY[d];    dx -= DX[d];
                                map[dy][dx] = map[i][j];
                                visited[dy][dx] = visited[i][j];
                            } else {
                                map[dy][dx] += map[i][j];
                                visited[dy][dx] = true;
                            }

                            if (dy == i && dx == j)
                                continue;

                            visited[i][j] = false;
                            map[i][j] = 0;
                        }
                    }
                }
            }

            if (d == 2) {
                for (int i = starty; i < endy; i++) {
                    for (int j = startx; j > endX; j--) {
                        if (map[i][j] > 0) {
                            int dx = j;     int dy = i;

                            while (true) {
                                dy += DY[d];    dx += DX[d];

                                if (dy >= N || dx >= N) {
                                    dy -= DY[d];    dx -= DX[d];
                                    break;
                                }

                                if(map[dy][dx] > 0) {
                                    break;
                                }
                            }

                            if (map[dy][dx] == 0) {
                                map[dy][dx] = map[i][j];
                                visited[dy][dx] = visited[i][j];
                            }else if (visited[i][j] || visited[dy][dx] || map[i][j] != map[dy][dx]) { //합칠 수 없다면 이전에 저장한다.
                                dy -= DY[d];    dx -= DX[d];
                                map[dy][dx] = map[i][j];
                                visited[dy][dx] = visited[i][j];
                            } else {
                                map[dy][dx] += map[i][j];
                                visited[dy][dx] = true;
                            }

                            if (dy == i && dx == j)
                                continue;

                            visited[i][j] = false;
                            map[i][j] = 0;
                        }
                    }
                }
            }

            if (d == 3 ) {
                for (int i = starty; i > endy; i--) {
                    for (int j = startx; j < endX; j++) {
                        if (map[i][j] > 0) {
                            int dx = j;     int dy = i;

                            while (true) {
                                dy += DY[d];    dx += DX[d];

                                if (dy >= N || dx >= N) {
                                    dy -= DY[d];    dx -= DX[d];
                                    break;
                                }

                                if(map[dy][dx] > 0) {
                                    break;
                                }
                            }

                            if (map[dy][dx] == 0) {
                                map[dy][dx] = map[i][j];
                                visited[dy][dx] = visited[i][j];
                            }else if (visited[i][j] || visited[dy][dx] || map[i][j] != map[dy][dx]) { //합칠 수 없다면 이전에 저장한다.
                                dy -= DY[d];    dx -= DX[d];
                                map[dy][dx] = map[i][j];
                                visited[dy][dx] = visited[i][j];
                            } else {
                                map[dy][dx] += map[i][j];
                                visited[dy][dx] = true;
                            }

                            if (dy == i && dx == j)
                                continue;

                            visited[i][j] = false;
                            map[i][j] = 0;
                        }
                    }
                }
            }

       
            simulate(map, depth + 1);
        }
    }

    public static int[][] copyMap(int[][] originalMap) {
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = originalMap[i][j];
            }
        }

        return map;
    }

    public static boolean[][] copyvisitedMap(boolean[][] originalVisited) {
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = originalVisited[i][j];
            }
        }

        return visited;
    }

    public static void print(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("================");
    }

    public static void print(boolean[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("================");
    }
}


class Node {
    int y;
    int x;

    int num;
    boolean mixed;

}