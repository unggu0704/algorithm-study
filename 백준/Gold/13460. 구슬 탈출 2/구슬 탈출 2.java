import java.io.*;

import java.util.*;


/**
 * Main_13460_구슬탈출2_김규형
 *
 * 구슬은 무조건 끝까지 움직인다.
 * ### 구슬이 있을때 해당 구슬 앞에 멈추는가? -> O
 * ### 구슬은 움직이면서 빠질수 있는가? -> O
 *
 * DFS로 배열을 깊은 복사하면서 depth가 10 미만으로 탐색 하기
 *
 *
 */
public class Main {
    public static void main(String[] args) throws IOException{
        solution();
    }
    static int N,M;
    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, -1, 0, 1};
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Node[][] map = new Node[N][M];

        Node red = null, blue = null;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == '.')
                    map[i][j] = new Node(i, j, 1, false, false);
                else if (input.charAt(j) == 'O')
                    map[i][j] = new Node(i, j, 2, false, false);
                else if (input.charAt(j) == 'R')
                    red = map[i][j] = new Node(i, j, 1, true, false);
                else if (input.charAt(j) ==  'B')
                    blue = map[i][j] = new Node(i, j, 1, false, true);
                else
                    map[i][j] = new Node(i, j, 0, false, false);
            }
        }

        int ans = moveBall(map, red, blue, 1);
        if (ans == Integer.MAX_VALUE)
            System.out.println(-1);

        else
            System.out.println(ans);
    }

    public static int moveBall(Node[][] originalmap, Node red, Node blue, int depth) {
        if (depth > 10)
            return Integer.MAX_VALUE;

//        System.out.println("============ depth : "  + depth + " ==========");
//        printMap(originalmap);

        int ans = Integer.MAX_VALUE;


        for (int i = 0; i < 4; i++) {
            boolean redGoal  = false , blueGoal = false;
            boolean ballColision = false;

            int ry = red.y;     int rx = red.x;
            int by = blue.y;    int bx = blue.x;


            Node[][] map = copyMap(originalmap);
            red = map[red.y][red.x];
            blue = map[blue.y][blue.x];


            while(true) {
                ry += DY[i];    rx += DX[i];

                if (map[ry][rx].info == 1 && !map[ry][rx].blue) //통로라면 계속 가 !
                    continue;

                if (map[ry][rx].info == 2) {//구멍을 만났다면?
                    redGoal = true;
                    continue;
                }

                if (map[ry][rx].blue) //가는길에 구르기전 파랑 골을 만났다면?
                    ballColision = true;

                ry -= DY[i];    rx -= DX[i];
                red.red = false;
                map[ry][rx].red = true;
                break;
            }

            while(true) {
                by += DY[i];    bx += DX[i];

                if (map[by][bx].info == 1 && !map[by][bx].red) //통로라면 계속 가 !
                    continue;

                if (map[by][bx].info == 2) { //구멍을 만났다면?
                    blueGoal = true;
                    continue;
                }

                by -= DY[i];    bx -= DX[i];
                blue.blue = false;
                map[by][bx].blue = true;
                break;
            }

            //볼과 충돌했다면 한번 더 굴린다.
            while(ballColision) {
                map[ry][rx].red = false;
                ry += DY[i];    rx += DX[i];

                if (map[ry][rx].info == 1  && !map[ry][rx].blue) //통로라면 계속 가 !
                    continue;

                if (map[ry][rx].info == 2) { //구멍을 만났다면?
                    redGoal = true;
                    continue;
                }

                ry -= DY[i];    rx -= DX[i];
                map[ry][rx].red = true;
                break;
            }
            //공의 이동이 종료됨

            if (ry == red.y && rx == red.x && by == blue.y && bx == blue.x)
                continue;

            if (blueGoal) {
                //map[ry][rx].red = false;    map[by][bx].blue = false;
                continue;
            }

            if (redGoal) {
              //  map[ry][rx].red = false;    map[by][bx].blue = false;
                ans = Math.min(ans, depth);
                continue;
            }

            ans = Math.min(moveBall(map, map[ry][rx], map[by][bx], depth + 1), ans);
            map[ry][rx].red = false;    map[by][bx].blue = false;
        }


        return ans;
    }

    public static Node[][] copyMap(Node[][] map) {
        Node[][] copyMap = new Node[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                copyMap[i][j] = new Node(map[i][j]);
        }

        return copyMap;
    }

    public static void printMap(Node[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].red)
                    System.out.print("R ");
                else if (map[i][j].blue)
                    System.out.print("B ");
                else
                    System.out.print(map[i][j].info + " ");
            }
            System.out.println();
        }
    }
}

class Node {
    int y,  x;
    int info;
    boolean red, blue;

    Node (int y, int x, int info, boolean red, boolean blue) {
        this.y =y;
        this.x =x;
        this.info = info;
        this.red = red;
        this.blue =blue;
    }

    Node (Node node) {
        this.y = node.y;
        this.x = node.x;
        this.info = node.info;
        this.red = node.red;
        this.blue = node.blue;
    }
}