import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};

    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map[][] map = new Map[N][M]; //지도 사이즈
        Boolean[][] visited;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = new Map(i,j);

                if (input.charAt(j) == 'W') { // 바다라면?
                    map[i][j].info = 0;
                }
                else {
                    map[i][j].info = 1; // 육지라면?
                }
            }
        }
        // 입력값 초기화


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].info == 1) { //육지라면?
                    visited = new Boolean[N][M];
                    Queue<Map> queue = new ArrayDeque<>(); //큐를 하나 만든다.

                    queue.offer(map[i][j]);
                    visited[i][j] = true;

                    int size = -1;
                    while(!queue.isEmpty()) {
                        int queueSize = queue.size();
                        size++;
                        //현재 담긴 큐 사이즈 만큼 반복한다.
                        for (int q = 0; q < queueSize; q++) {
                            Map node = queue.poll();

                            for (int r = 0; r < 4; r++) {
                                int dy = DY[r] + node.y;
                                int dx = DX[r] + node.x;

                                if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || map[dy][dx].info == 0 ||
                                        Boolean.TRUE.equals(visited[dy][dx]))
                                    continue; // 경계를 벗어나거나, 바다거나, 방문햇다면 큐에 넣지 않음

                                queue.offer(map[dy][dx]);
                                visited[dy][dx] = true;
                            }
                        }
                    }

                    ans = Math.max(ans, size);
                }
            }
        }

        System.out.println(ans);
    }
}

class Map {

    int y, x, info;

    Map(int y, int x) {
        this.y = y; this.x = x;
    }

}