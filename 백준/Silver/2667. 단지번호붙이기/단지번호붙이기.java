
import java.io.*;
import java.util.*;


/**
 * visited 2차원으로 만들어서
 * 새로운 백트래킹 떄마다 기존의 boolean 2차원 배열을 복사해 
 * 나의 visited로 만든 뒤에 탐색이 종료되면 버린다.
 * 
 */

public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    static int N;
    static int answer = 0;
    static Node[][] map;
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        map = new Node[N][N];



        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                Node node;
                if (str.charAt(j) == '0')
                    node = new Node(i,j, true); // 단지가 없으면 true
                else {
                    node = new Node(i,j, false);
                }
             
                map[i][j] = node;
            }
        }

        Queue<Node> queue = new LinkedList<>();
        ArrayList<Integer> danji = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].visited || map[i][j].isdanji) continue; //이미 방문한 노드거나 단지가 없는 경우면 탐색x

                map[i][j].visited = true;
                queue.add(map[i][j]);
                danji.add(DFS(queue));
            }
        }

        Collections.sort(danji);

        StringBuilder sb = new StringBuilder();
        sb.append(danji.size() + "\n");

        for (Integer i : danji) {
            sb.append(i + "\n");
        }

        System.out.println(sb.toString());

    }
    static int[] DX = {0, -1, 0, 1};
    static int[] DY = {-1, 0, 1 ,0};

    public static int DFS(Queue<Node> queue) {
        int danjiCount = 0;
        while (!queue.isEmpty()){
            danjiCount++; // 단지 갯수 카운트 
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int dy = node.y + DY[i];
                int dx = node.x + DX[i];

                if (dy <= -1 || dx <= -1 || dy >= N || dx >= N || map[dy][dx].visited || map[dy][dx].isdanji) continue;
                
                map[dy][dx].visited = true;
                queue.add(map[dy][dx]); // 인전합 단지는 큐에 넣는다.
            }
        }
        return danjiCount;
    }
}

class Node {
    int y;
    int x;
    boolean isdanji;
    boolean visited;

    Node(int y, int x,boolean v) {
        this.y = y;
        this.x = x;
        this.isdanji = v;
        visited = false;
    }
}

