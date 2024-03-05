import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 3번
 * @author 김규형
 *
 */
public class Main {
    static int N;
    static int M;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Node[][] map = new Node[N][M];
        int flag= 0;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == 'L') {
                    map[i][j] = new Node(2 ,i ,j);
                    if (flag++ == 0)
                        map[i][j].visited1 = true;
                    else
                        map[i][j].visited2 = true;
                    visitedQ.add(map[i][j]);
                    prevMelt.add(map[i][j]);
                } else if (input.charAt(j) == 'X') {
                    map[i][j] = new Node(1, i, j);
                } else {
                    map[i][j] = new Node(0, i , j);
                    map[i][j].melted = true;
                    prevMelt.add(map[i][j]);
                }
            }
        }

        int count = 0;
        while (!findHuman(map)) {
            count++;
            meltIceburg(map);
       //     printMap(map);
        }

        System.out.println(count);
    }

    static Queue<Node> visitedQ = new ArrayDeque<>();
    static Queue<Node> prevMelt = new ArrayDeque<>();

    public static boolean findHuman(Node[][] map) {
        Queue<Node> q;

        int size = visitedQ.size();

        for (int i = 0; i < size; i++) {
            q = new ArrayDeque<>();
            q.add(visitedQ.poll());

            while (!q.isEmpty()) {
                Node node = q.poll();


                for (int d = 0; d < 4; d++) {
                    int dy = node.y + DY[d];
                    int dx = node.x + DX[d];

                    if (dy >= N || dx >= M || dy <= -1 || dx <= -1)
                        continue;

                    if (map[dy][dx].info == 1) {
                        visitedQ.add(node);  //빙하를 만난다면 다음에 탐색하기 위해 큐에 넣어놓는다.
                        continue;
                    }

                    if (node.visited1) { // 1번 백조의 움직임
                        if (map[dy][dx].visited2)
                            return true;

                        if (map[dy][dx].visited1)
                            continue;

                        map[dy][dx].visited1 = true;
                    }

                    if (node.visited2) { // 2번 백조의 움직임
                        if (map[dy][dx].visited1)
                            return true;

                        if (map[dy][dx].visited2)
                            continue;

                        map[dy][dx].visited2 = true;
                    }

                    if (map[dy][dx].info == 2)
                        return true;

                    q.add(map[dy][dx]);
                }
            }
        }

        return false;
    }

    public static void meltIceburg(Node[][] map) {
        Queue<Node> q;

        int size = prevMelt.size();

        for (int i = 0; i < size; i++) {
            q = new ArrayDeque<>();
            q.add(prevMelt.poll());

            while (!q.isEmpty()) {
                Node node = q.poll();

                for (int d = 0; d < 4; d++) {
                    int dy = node.y + DY[d];	int dx = node.x + DX[d];

                    if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || map[dy][dx].melted)
                        continue;

                    map[dy][dx].melted = true;

                    if (map[dy][dx].info == 1) { //방금 녹이 땅만 melt 큐에 넣는다.
                        map[dy][dx].info = 0;
                        prevMelt.add(map[dy][dx]);
                        continue;
                    }

                    q.add(map[dy][dx]);
                }
            }


        }
    }

    public static void printMap(Node[][] map) {
        for (int i = 0 ; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j].info + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

}

class Node {
    int info; // 0은 땅 1은 빙하 2는 사람
    int y; int x;

    boolean melted = false;
    boolean visited1 = false;
    boolean visited2 = false;
    Node(int ice, int y, int x) {
        this.info =ice;
        this.y =y;
        this.x= x;
    }
}