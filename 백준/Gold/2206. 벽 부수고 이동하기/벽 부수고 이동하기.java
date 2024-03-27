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
    static Node[][] map;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) ;
        M = Integer.parseInt(st.nextToken());

        if (N == 1 && M == 1) {
        	System.out.println(1);
        	System.exit(0);
        }
        map = new Node[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if ('0' == input.charAt(j))
                    map[i][j] = new Node(i, j, false);
                else
                    map[i][j] = new Node(i, j, true);
            }
        }
        // 입력 종료

        BFS(map[0][0], 1, true);

        if (ans == Integer.MAX_VALUE)
            ans = -1;

        System.out.println(ans);
    }

    public static void BFS(Node firstNode, int distance, boolean broken) {
        Queue<NodeInfo> q = new ArrayDeque<>();
        firstNode.distance = 1;
        q.add(new NodeInfo(firstNode, false));
        boolean[][][] visited = new boolean[N][M][2];
        // 0은 벽을 하나도 안부순 방문배열
        // 1은 벽을 부순 방문배열

        while (!q.isEmpty()) {
            int size = q.size();
            
        //    printMap();
            for (int s = 0; s < size; s++) {
                NodeInfo nodeinfo = q.poll();
                Node node = nodeinfo.node;
                
                for (int i = 0; i < 4; i++) {
                    int dy = node.y + DY[i];
                    int dx = node.x + DX[i];

                    if (dy <= -1 || dx <= -1 || dy >= N || dx >= M)
                        continue;

                    if (map[dy][dx].y == N - 1 && map[dy][dx].x == M - 1) {
                        ans = Math.min(ans, node.distance + 1);
                    }

                    //벽이면
                    if (map[dy][dx].wall) {
                    	//벽을 하나도 안부셨다면 
                    	if (!nodeinfo.broken) {
                    		visited[dy][dx][1] = true;
                    		map[dy][dx].distance = node.distance + 1;
                    		q.offer(new NodeInfo(map[dy][dx], true));
                    	}
                    } else { //일반 길이면 벽을 부순지 안부순지로 나눈다.
                    	if (!visited[dy][dx][1] && nodeinfo.broken) {
                    		visited[dy][dx][1] = true;
                    		map[dy][dx].distance = node.distance + 1;
                    		q.offer(new NodeInfo(map[dy][dx], true));
                    	} else if (!visited[dy][dx][0] && !nodeinfo.broken){
                    		visited[dy][dx][0] = true;
                    		map[dy][dx].distance = node.distance + 1;
                    		q.offer(new NodeInfo(map[dy][dx], false));
                    	}
                    }

                }
            }
        }
    }
}


class Node {
    int y; int x;
    boolean wall;

    int distance;

    Node (int y, int x ,boolean wall) {
        this.y = y;
        this.x = x;
        this.distance = Integer.MAX_VALUE;
        this.wall = wall;
    }
}

class NodeInfo {
	Node node;
	boolean broken;
	
	NodeInfo(Node node, boolean broken) {
		this.node = node;
		this.broken = broken;
	}
}