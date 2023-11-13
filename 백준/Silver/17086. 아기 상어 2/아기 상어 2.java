
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    static int N, M;
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    
        Node[][] nodeMap = new Node[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                Node node = new Node(i,j);
                if (Integer.parseInt(st.nextToken()) == 1) {
                    node.babyShark = true;
                }
                nodeMap[i][j] = node;
            }
        }
        // 입력 처리 종료 

        Queue<Node> queue = new LinkedList<>();
        int safeZone = 0;
        int MIN = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!nodeMap[i][j].babyShark) {
                    queue.add(nodeMap[i][j]);
                    nodeMap[i][j].visited = true;
                    loop:
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int q = 0; q < size; q++) {
                            Node nowNode = queue.poll();
                            if(BFS(nowNode, queue, nodeMap)) {}
                            else {
                                break loop;
                            }
                        }
                        safeZone++;
                    }
                }

                MIN = Math.max(safeZone, MIN);
                safeZone = 0;
                queue.clear();
                initVisited(nodeMap);
            }
        }

        System.out.println(MIN + 1);
    }
    static int[] DX = {-1, 0, 1, 0, -1, +1, -1, 1};
    static int[] DY = {0, 1, 0, -1, -1, +1, 1, -1};

    public static boolean BFS(Node node, Queue<Node> queue, Node[][] nodeMap) {
        int y = node.y; int x = node.x;

        for (int i = 0; i < 8; i++) {
            int moveY = y + DY[i];  int moveX = x + DX[i];

            if(moveY <= -1 || moveX <= -1 || moveY >= N || moveX >= M || nodeMap[moveY][moveX].visited) continue;

            nodeMap[moveY][moveX].visited = true;
            if (nodeMap[moveY][moveX].babyShark) {
                return false;
            }
            queue.add(nodeMap[moveY][moveX]);
        }
        return true;
    }

    public static void initVisited(Node[][] mapNodes) {
        for (int i = 0; i < mapNodes.length; i++) {
            for (int j = 0; j < mapNodes[i].length; j++) {
                mapNodes[i][j].visited = false;
            }
        }
    }
}

class Node {
    int y;
    int x;
    boolean visited;
    boolean babyShark; // 아기 상어가 잇는 노드

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
