import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Main_2573_빙산_김규형
 *
 * 주말 스터디 과제
 *
 * 접근 방법
 * 임의의 바다에서부터 BFS 탐색을 통해 인접한 빙산을 녹인다.
 * 모든 바다가 BFS 탐색을 1회 하였다면 빙산에서 BFS 탐색을 통해 모든 빙산이 이어져있는지 확인한다.
 *
 * 1회의 BFS를 통해 도달하지 못한 빙산이 있다면 해당 빙산은 분리된 빙산이다.
 *
 */
class Main {

    static int N;
    static int M;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    static Node[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   M = Integer.parseInt(st.nextToken());

        map = new Node[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = new Node(i, j, Integer.parseInt(st.nextToken())); //지형에 대한 정보를 생성한다.
            }
        } //입력 종료

        int count = 0;
       // printMap();
        while(true) {
            count++;
            meltIceberg();

         //   printMap();
            int check = icebergCheck();
            if (check == 1) {//빙산이 분리 되었다면?
           //     System.out.println("빙산 분리");
                break;
            }

            if (check == 2) { //빙산이 전부 없어졌다면?
           //     System.out.println("빙산이 없음");
                count = 0;
                break;
            }

            //빙산은 아직 분리되지 않았다.
        }

        System.out.println(count);

    }

    //분리된 빙산이 있는지 체크 하는 BFS 탐색
    public static int icebergCheck() {
        Queue<Node> queue; //BFS 용 큐
        boolean[][] visted = new boolean[N][M]; // 방문 배열
        boolean isIceberg = false; //하나 이상의 빙산이 존재하는가?

        loop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visted[i][j] && map[i][j].iceberg > 0) { //빙산이면서 방문하지 않은 정점일시 탐색
                    isIceberg = true; //빙산은 아직 존재한다.
                    queue = new ArrayDeque<>();

                    queue.add(map[i][j]);
                    visted[i][j] = true;

                    while (!queue.isEmpty()) {
                        Node node = queue.poll();

                        for (int d = 0; d < 4; d++) {
                            int dy = node.y + DY[d];    int dx = node.x + DX[d];

                            if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || visted[dy][dx] || map[dy][dx].iceberg == 0) //바다면 담지 않는다.
                                continue;

                            visted[dy][dx] = true;

                            queue.add(map[dy][dx]);
                        }
                    }
                    break loop; //BFS 탐색은 1회만 진행한다.
                }
            }
        }

        if(!isIceberg) //빙산이 하나도 남지 않을때는 2이다.
            return 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].iceberg > 0 && !visted[i][j]) { //빙산이면서 BFS 탐색을 하지 않은 빙산이 있다면
                    return 1; //빙산이 분리되엇다!
                }
            }
        }

        return 0;
    }

    //빙산을 녹이기 위한 BFS 탐색
    public static void meltIceberg() {
        Queue<Node> queue; //BFS 용 큐
        boolean[][] visted = new boolean[N][M]; // 방문 배열

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visted[i][j] && map[i][j].iceberg == 0) { //바다이면서 방문하지 않은 정점일시 탐색
                    queue = new ArrayDeque<>();

                    queue.add(map[i][j]);
                    visted[i][j] = true;
                    while (!queue.isEmpty()) {
                        Node node = queue.poll();

                        for (int d = 0; d < 4; d++) {
                            int dy = node.y + DY[d];    int dx = node.x + DX[d];

                            if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || visted[dy][dx])
                                continue;

                            if (map[dy][dx].iceberg > 0) { //빙산이면 1회 줄인다. 단 큐에 넣지 않으므로 더이상 탐색은 하지 않는다.
                                if (--map[dy][dx].iceberg == 0) //빙산이 다 녹았다면 방문처리를 해준다.
                                    visted[dy][dx] = true;

                                continue;
                            }
                            visted[dy][dx] = true;
                            queue.add(map[dy][dx]);
                        }
                    }
                }
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j].iceberg + " ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

}

class Node {
    int iceberg; //빙산이면 1이상 이다.

    int y,x;
    Node(int y, int x, int iceberg) {
        this.y = y;
        this.x = x;
        this.iceberg = iceberg;
    }
}