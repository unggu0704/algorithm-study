import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M, K;
	static Node[][] map;
	static int ans = Integer.MAX_VALUE;
	static boolean[][][] visited;
	
	static int[] DY = {-1, 0, 1, 0};
	static int[] DX = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		

		map = new Node[N][M];
		visited = new boolean[N][M][K + 1];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = new Node(i, j, input.charAt(j) - '0');
			}
		} 
		//입력 종료 
		
		if (N == 1 && M == 1) {
        	System.out.println(1);
        	System.exit(0);
        }
		  
		BFS();
		
		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}
	
	public static void BFS() {
		Queue<NodeInfo> q = new ArrayDeque<NodeInfo>();
		q.add(new NodeInfo(map[0][0], 0));
		visited[0][0][0] = true;
		int dist = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			dist++;
			
			for (int i = 0; i < size; i++) {
				NodeInfo nodeinfo = q.poll();
				Node node = nodeinfo.node;
				int depth = nodeinfo.depth;
				
				for (int d = 0; d < 4; d++) {
					int dy = node.y + DY[d];	int dx = node.x + DX[d];
					
					if (dy <= -1 || dx <= -1 || dy >= N || dx >= M || visited[dy][dx][depth]) 
						continue;
					
					
					// 적정 거리에 도달했다면 최솟값을 비교한다.
					if (dy == N - 1 && dx == M - 1) {
						ans = dist + 1;
						return;
					}
					
					//빈공간이라면 우선 큐에 담고 다음 탐색으로 떠난다.
					if (map[dy][dx].info == 0) {
						visited[dy][dx][depth] = true;
						q.offer(new NodeInfo(map[dy][dx], depth));
						continue;
					}
					
					// 만약 벽을 만났다면? n번째 벽을 부순 지점을 체크하고 탐색
					if (map[dy][dx].info == 1) {
						if (depth + 1 <= K && !visited[dy][dx][depth + 1]) {
							visited[dy][dx][depth + 1] = true;
							q.offer(new NodeInfo(map[dy][dx], depth + 1));
						}
					}
				}
			}
		}
	}
	
	
}

class NodeInfo {
	Node node;
	
	int depth;

	
	NodeInfo(Node node, int depth) {
		this.node = node;
		this.depth = depth;
	}
} 

class Node {
	int y, x;
	
	int info; // 0은 빈공간 1은 벽 이다.

	Node(int y, int x, int info) {
		this.y = y;
		this.x = x;
		this.info = info;
	}
}