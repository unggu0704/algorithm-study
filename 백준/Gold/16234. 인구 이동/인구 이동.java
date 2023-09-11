import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[] DY = {0, 1, 0, -1};
    static int[] DX = {-1, 0, 1, 0};
    static Set<Contry> list = new HashSet<>();
    static boolean flag;

    public static void BFS(Contry[][] map, Contry contry, int N, int L, int R, Queue<Contry> queue) {
        int dy = contry.getY();
        int dx = contry.getX();
        for (int i = 0; i < 4; i++) {
            dy = contry.getY() + DY[i];
            dx = contry.getX() + DX[i];

            if (dy >= N || dx >= N || dy <= -1 || dx <= -1 || map[dy][dx].getBoard() ||  // 경계값 및 국경 여부 확인 
                (Math.abs(contry.getPop() - map[dy][dx].getPop()) < L) || (Math.abs(contry.getPop() - map[dy][dx].getPop()) > R)) continue; //L 이상이거나 R 이하인 경우 큐에 담지 않음.

            flag = true; // 한번이라도 열릴 국경이 있음을 알림.
            if (!contry.getBoard()) contry.isBoard();
            map[dy][dx].isBoard();
            list.add(map[dy][dx]);
            queue.add(map[dy][dx]);
        }
    }

    public static int movePop() {
        int totalPOP = 0;
        int dividePOP = 0;
        for (Contry c : list) {
            totalPOP += c.getPop();
        }
        dividePOP = totalPOP / list.size();
            for (Contry c : list) {
            c.setPop(dividePOP);
        }

        return totalPOP;
    }

    public static void initMap(Contry[][] map, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].getBoard()) {
                    map[i][j].isBoard();
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int days = 0;

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        Contry[][] map = new Contry[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int pop = Integer.parseInt(st.nextToken());
                Contry contry = new Contry(pop, i, j);
                map[i][j] = contry;
            }
        }
     // 입력 종료

        while (true) {
            Queue<Contry> queue = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j].getBoard()) continue; // 이미 국경이 열려 있으면 검사하지 않음.
                    queue.add(map[i][j]);
                    list.add(map[i][j]);
                    while (!queue.isEmpty()) {
                        Contry contry = queue.poll();
                        list.add(contry);
                        BFS(map, contry, N, L, R, queue);
                    }
                    movePop();
                    list.clear();

                }
            }
            // 국경열린 곳을 전부 찾음 


            if (!flag) { // 국경이 열린곳이 아무도 없다면 loop 종료.
                break;
            }

            days++;

            // 인구 이동 실시 

            initMap(map, N); // 국경 다시 닫힘 
            flag = false;

        }

        System.out.println(days);
        
    }



}


class Contry {
    private int pop;
    private boolean board;
    private int y;
    private int x;

    Contry(int p, int y, int x) {
        this.pop = p;
        this.y = y;
        this.x = x;
        board = false; // 국경은 기본적으로 닫혀있음.
    }

    public void setPop(int p) {
        this.pop = p;
    }

    public int getPop() {
        return this.pop;
    }

    public boolean getBoard() {
        return board;
    }

    public void isBoard() {
        board = !board;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

}
