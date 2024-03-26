import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	static int[] DX = {-1, 0, 1, 0};
	static int[] DY = {0, 1, 0, -1};
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 1;
		sb = new StringBuilder();
		while (true) {
			int N = Integer.parseInt(br.readLine());
			
			if (N == 0)
				break;
			
			sb.append("Problem ").append(count++).append(": ");
			solution(br, N);
		}
		System.out.println(sb.toString());
	}
	
	public static void solution(BufferedReader br, int N) throws IOException {
		Node[][] map = new Node[N][N];
		StringTokenizer st; 
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = new Node(i, j, Integer.parseInt(st.nextToken()));
			}
		}
		
		Queue<Node> q = new ArrayDeque<>();
	
		map[N - 1][N - 1].value = map[N - 1][N - 1].info;
		q.offer(map[N - 1][N - 1]);
		
		while (!q.isEmpty()) {
			
			int size = q.size();
			
			for (int t = 0; t < size; t++) {
				Node node = q.poll();
				
//				if (node.visited)
//					continue;
//				
//				node.visited = true;
			//	printMap(map);
				
				for (int i = 0; i < 4; i++) {
					int dy = node.y + DY[i];	int dx = node.x + DX[i];
					
					if (dy >= N || dx >= N || dy <= -1 || dx <= -1)
						continue;
					
					
					if (map[dy][dx].value > map[dy][dx].info + node.value) {
						map[dy][dx].value = map[dy][dx].info + node.value;
						q.add(map[dy][dx]);
					}
				}
			}
		}
		
		sb.append(map[0][0].value).append("\n");
	}
	
	public static void printMap(Node[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j].value + " ");
			}
			System.out.println();
		}
		System.out.println("==========================");
	}

}


class Node {
	int y, x, value, info;
	boolean visited;
	
	Node (int y, int x, int info) {
		this.y = y;
		this.x = x;
		this.info = info;
		this.value = Integer.MAX_VALUE;
	}
}