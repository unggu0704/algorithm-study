import java.io.*;
import java.util.*;

public class Main {

static int N;
static int M;
static int name = 0;
static  Node[][] mapNodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
        mapNodes = new Node[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                boolean wall = false;
                if (Character.getNumericValue(input.charAt(j)) == 1) 
                    wall = true;
                
                mapNodes[i][j] = new Node(i, j, wall);
            }
        }

       // 입력 종료 

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mapNodes[i][j].wall || mapNodes[i][j].visited) continue; // 벽인 곳을 계산할 필요가 없음
                BFS(mapNodes[i][j]);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!mapNodes[i][j].wall) continue;
            
                mapNodes[i][j].wallresult = 1;
                for (int w = 0; w < 4; w++) {
                    int y = i + DY[w];  int x = j + DX[w];
                    if (y <= -1 || x <= -1 || y >= N || x >= M || list.contains(mapNodes[y][x].areaName) || mapNodes[y][x].wall) continue;
                    
                    list.add(mapNodes[y][x].areaName);
                    mapNodes[i][j].wallresult += mapNodes[y][x].result;

                    mapNodes[i][j].wallresult %= 10;
                }
                list.clear();
            }
        }
        // System.out.println();
        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < M; j++) {
        //         System.out.print(mapNodes[i][j].result);
        //     }
        //     System.out.println();
        // }

        // System.out.println();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(mapNodes[i][j].wallresult);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int[] DX = {-1, 0, 1, 0};
    static int[] DY = {0, 1, 0, -1};
    static int count;
    static ArrayList<Node> list = new ArrayList<>();

    public static void BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        list.add(node);
        node.visited = true;
        count = 0;
        while(!queue.isEmpty()) {
            node = queue.poll();
            count++;
            for (int j = 0; j < 4; j++) {
                int y = DY[j] + node.y;     int x = DX[j] + node.x;

                if (y <= -1 || x <= -1 || y >= N || x >= M || mapNodes[y][x].wall || mapNodes[y][x].visited) continue;

                mapNodes[y][x].visited = true;
                queue.add(mapNodes[y][x]);
                list.add(mapNodes[y][x]);
            }
        }

        for (Node n : list) {
            n.result = count;
            n.areaName = name;
        }

        name++;
        list.clear();
    }
}

class Node{
    int y;
    int x;
    boolean wall;
    boolean visited;
    int areaName;
    int result;
    int wallresult;

    Node(int y, int x, boolean wall) {
        this.y = y;
        this.x = x;
        this.wall = wall;
    }
}

