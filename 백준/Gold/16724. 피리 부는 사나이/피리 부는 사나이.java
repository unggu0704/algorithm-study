import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}

    /**
     * 특정 i, j
     * i, j-1 or i+1, j 중에 MIN 값을 가져온다.
     * 
     * k = 1 :  1, 2 |  2, 3 |  3 ,4
     * k = 2 :  1, 3 2 .4 3 ,5
     * k = 3 :  1, 4 2, 5 3, 6
     * @throws IOException
     */
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;
        Node[][] map = new Node[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < M; j++) {
                char directInput = line.charAt(j);

                Node node = new Node(directInput, i, j);
                map[i][j] = node;
            }
        }
        // 입력 종료

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char direct = map[i][j].direct;
                int y = map[i][j].y;
                int x = map[i][j].x;
                switch (direct) {
                    case 'D':
                        y += 1;
                        break;
                    case 'R':
                        x += 1;
                        break;
                    case 'U':
                        y -= 1;
                        break;
                    case 'L':
                        x -= 1;
                        break;
                    default:
                        break;
                }

                map[i][j].root = findRoot(map[y][x]); // 부모 노드로 추가 
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].root == map[i][j]) { 
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static Node findRoot(Node n) {
        if (n == n.root) {
            return n;
        } else return findRoot(n.root);
    }

    /* 

    public static int startDFS(Node[][] map, Node node, int N, int M) {
        int safezoneCount = 0;
        node.visited = true; // 최초의 시작점 true
        DFS(map, node, N, M);
        safezoneCount++;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].visited) continue;
                safezoneCount++;
                DFS(map, map[i][j], N, M);
            }
        }
        return safezoneCount;
    }

    public static void DFS(Node[][] map, Node node, int N, int M) {
        char direct = node.direct; // 해당 노드의 방향
        int y = node.y; int x = node.x;
        switch (direct) {
            case 'D':
                y += 1;
                break;
            case 'R':
                x += 1;
                break;
            case 'U':
                y -= 1;
                break;
            case 'L':
                x -= 1;
                break;
            default:
                break;
        }

        if (map[y][x].visited) return;

        map[y][x].visited = true;
        DFS(map, map[y][x], N, M);
    }
    */



    public static void initMap(Node[][] map, int N, int M) {
        Node[][] copymap = new Node[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j].visited = false;
            }
        }
    }

}

class Node {
    int y, x;
    boolean visited;
    char direct;
    Node root;

    Node(char d, int y, int x) {
        this.direct = d;
        this.y = y;
        this.x = x;
        this.root = this;
    }
}






