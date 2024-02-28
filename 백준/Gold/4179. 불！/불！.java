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
	static int N, M, L;
	static int[] DY = {-1, 0, 1, 0};
	static int[] DX = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Node[][] map = new Node[N][M];
	
		Queue<Node> fq = new ArrayDeque<>();
		Queue<Node> jq = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				if (str.charAt(j) == '.')
					map[i][j] = new Node(i, j, 0);
				if (str.charAt(j) == '#')
					map[i][j] = new Node(i, j, 1);
				if (str.charAt(j) == 'J') {
					map[i][j] = new Node(i, j, 2);
					jq.offer(map[i][j]);
				}
				if (str.charAt(j) == 'F') {
					map[i][j] = new Node(i, j, 3);
					fq.offer(map[i][j]);
				}
			}
		}
		
		int ans = 0;
		while (true) {
			if (jq.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				System.exit(0);
			}
			
			ans++;
			int fsize = fq.size();
			int jsize = jq.size();
			
			for (int i = 0; i < fsize; i++) {
				Node node = fq.poll();
				
				for (int d = 0; d < 4; d++) {
					int dy = node.y + DY[d];	int dx = node.x + DX[d];
					
					if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || map[dy][dx].info == 1 || map[dy][dx].info == 3)
						continue;
					
					map[dy][dx].info = 3; // 빈공간을 불로 만든다.
					fq.offer(map[dy][dx]);
				}
			}
			
			for (int i = 0; i < jsize; i++) {
				Node node = jq.poll();
				
				for (int d = 0; d < 4; d++) {
					int dy = node.y + DY[d];	int dx = node.x + DX[d];
					
					if (dy >= N || dx >= M || dy <= -1 || dx <= -1) {
						System.out.println(ans);
						System.exit(0);
					}
						
					
					if (map[dy][dx].info == 1 || map[dy][dx].info == 3 || map[dy][dx].info == 2)
						continue;
					
					map[dy][dx].info = 2;
					jq.offer(map[dy][dx]);
				}
			}
		}
		
	}
}

class Node {
	int y;
	int x;
	int info; //0은 빈공간  1은 벽은  2는 재윤이 3은 불!
	
	Node(int y, int x, int i) {
		this.y =y;
		this.x = x;
		this.info = i;
	}
}