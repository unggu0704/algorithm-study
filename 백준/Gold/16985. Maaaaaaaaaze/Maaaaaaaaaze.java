import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Main_16985_Maaaaaaaaaze_김규형
 * 
 * @author 김규형
 */
public class Main {
    static Node[][][] input = new Node[5][5][5];
    static Node[][][] cube = new Node[5][5][5]; // 조합된 큐브
    static boolean[][][] visited;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer  st;

        for (int  z = 0; z < 5; z++) {
            for (int y = 0; y < 5; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < 5; x++) {
                    input[z][y][x] = new Node (Integer.parseInt(st.nextToken()));
                }
            }
        }
        // 입력 종료

        combineCube( 0);
        if (MIN == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(MIN);
    }

    /**
     * 큐브를 조립
     */
    static boolean[] cubeVisited = new boolean[5];
    public static void combineCube(int depth) {
        if (depth == 5) {
            Node[][][] combinedCube = copyCube(cube); //오리지널의 불변성 유지를 위한 새로운 3차원 배열 전달
            combineMap(combinedCube, 0);
            return;
        }

        for (int i = 0 ; i < 5; i++) {
            if (cubeVisited[i])
                continue;

            cube[depth] = input[i];

            cubeVisited[i] = true;
            combineCube(depth + 1);
            cubeVisited[i] = false;
        }
    }

    /**
     * 원 배열의 주소를 가져오기 때문에 새로운 배열로 돌린다.
     * 원 배열의 값의 불변성을 지킬것
     * 맵을 돌리는 조합
     */
    public static void combineMap(Node[][][] combinedCube, int index) {
        if (index == 5) { //드디어 3차원 cube가 안성됬다...
            if (combinedCube[0][0][0].walkable != 1 || combinedCube[4][4][4].walkable != 1) //입구와 출구가 이동가능한 통로인지 체크한다.
                return;

          MIN = Math.min(MIN, startMaze(combinedCube));
            return;
        }

        for (int i = index ; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                combinedCube[i] = rotateMap(combinedCube[i]); //새로운 주소의 돌려진 배열을 리턴한다
                combineMap(combinedCube, i + 1);
            }
        }
    }

    static int[] DY = {-1, 0, 1, 0, 0, 0};
    static int[] DX = {0, 1, 0, -1, 0, 0};
    static int[] DZ = {0, 0, 0, 0, 1, -1};
    public static int startMaze(Node[][][] cubeMap) {
        visited = new boolean[5][5][5]; //이번 탐색에서 사용할 방문 3차원 배멸
        Queue<Cooridnate> q = new ArrayDeque<>();
        int count = 0;

        q.add(new Cooridnate(0, 0 , 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            count++;

            for (int t = 0; t < size; t++) {
                Cooridnate cooridnate = q.poll();

                for (int i = 0; i < 6; i++) {
                    int dy = cooridnate.y + DY[i];
                    int dx = cooridnate.x + DX[i];
                    int dz = cooridnate.z + DZ[i];

                    if (dy <= -1 || dx <= -1 || dy >= 5 || dx >= 5|| dz <= -1 || dz >= 5
                            || cubeMap[dz][dy][dx].walkable == 0 || visited[dz][dy][dx])
                        continue;

                    if (dz == 4 && dy == 4 && dx == 4) // 미로를 탈출 할 수 있다.
                        return count;

                    q.add(new Cooridnate(dz, dy, dx));
                    visited[dz][dy][dx] = true;

                }
            }
        }
        return Integer.MAX_VALUE;
    }
    /**
     * 2차원 배열 하나를 돌린다.
     * 원 배열의 불변성은 지킨채 새로운 배열을 return
     * @param map
     * @return rotateMap이라는 새로운 배열을  return 한다.
     */
    public static Node[][] rotateMap(Node[][] map) {
        Node[][] rotateMap = new Node[5][5];

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                rotateMap[x][4 - y] = map[y][x];
            }
        }

        return rotateMap;
    }

    public static Node[][][] copyCube(Node[][][] originalCube) {
        Node[][][] copyCube = new Node[5][5][5];

        for (int  z = 0; z < 5; z++) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    copyCube[z][y][x] = originalCube[z][y][x];
                }
            }
        }

        return copyCube;
    }

    public static void printMap(Node[][] map) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                System.out.print(map[y][x].walkable);
            }
            System.out.println();
        }

        System.out.println("=============");
    }
}

class Node {
    int walkable;

    Node (int walkable) {
        this.walkable = walkable;
    }
}

class Cooridnate {
    int y; int x; int z;

    Cooridnate(int z, int y, int x) {
        this.z = z;
        this.y = y;
        this.x = x;
    }
}