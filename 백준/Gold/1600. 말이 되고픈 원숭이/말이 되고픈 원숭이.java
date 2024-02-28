import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_1753_최소경로_김규형
 * @author 김규형
 */

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N, M, K;
	static int[] DY = {-1, 0, 1, 0};
	static int[] DX = {0, 1, 0, -1};
	static int[] JY = {2,  2, 1, -1, -2, -2, 1, -1};
	static int[] JX = {1, -1, 2, 2, 1, -1, -2, -2}; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		if (M == 1 && N == 1) {
			System.out.println(0);
			System.exit(0);
		}
			
		
		int[][] map = new int[N][M];
	
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(0, 0, 0));
		
		boolean[][][] visited = new boolean[N][M][K + 1];
		visited[0][0][0] = true;
		
		int ans = 0;
		
		loop:
		while (true) {
			if (q.isEmpty()) {
				ans = -1;
				break;
			}
			
			int size = q.size();
			ans++;
			for (int c = 0; c < size; c++) {
				Node node = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int dy = node.y + DY[i];	int dx = node.x + DX[i];
					
					if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || map[dy][dx] == 1 || visited[dy][dx][node.k])
						continue;
					
					if (dy == N - 1 && dx == M - 1) {
						break loop;
					}
						
					
					visited[dy][dx][node.k] = true;
					q.add(new Node(dy, dx, node.k));
				}
				
				if (node.k < K) {
					for (int i = 0; i < 8; i++) {
						
						int dy = node.y + JY[i];	int dx = node.x + JX[i];
						
						if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || map[dy][dx] == 1 || visited[dy][dx][node.k + 1])
							continue;
						
						if (dy == N - 1 && dx == M - 1) {
							break loop;
						}
						
						visited[dy][dx][node.k + 1] = true;
						q.add(new Node(dy, dx, node.k + 1));
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}

class Node {
	int y;
	int x;
	int k;
	
	Node (int y, int x, int k) {
		this.y = y;
		this.x = x;
		this.k = k;
	}
}