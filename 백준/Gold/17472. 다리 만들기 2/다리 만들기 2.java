import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 데일리 과제 
 * 1244 - 스위치 켜고 끄기 S4
 * @author 김규형 
 *
 */
public class Main {
	
	static List<Edge>[][] graph;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int answer = 0;
		
		Node[][] map = new Node[N][M]; 
		graph = new ArrayList[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = new Node(i, j,  Integer.parseInt(st.nextToken()));
				graph[i][j] = new ArrayList<>();
			}
		}
		// 입력 종료 
		
		int islandCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j].island) {
					if (map[i][j].num == -1) { //섬 번호 세팅 안되어 있으면 설정 
						BFS(map, map[i][j], islandCount++, -1);
					}
					
					for (int w = 0; w < 4; w++) { // 간선 정의
						DFS(map, i, j, i, j, DY[w], DX[w], 0);
					}
				}
			}
		}
		// 간선 정의 작업 완료 

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (graph[i][j].size() >= 1) {
					prim(map, map[i][j], islandCount);

				}
			}
		}
		
		System.out.println(-1);
	}
	
	public static void prim(Node[][] map, Node firstNode, int islandCount) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		List<Integer> visited = new ArrayList<>();
		int answer = 0;
		
		pq.add(new Edge(firstNode, 0));
		
		while (!pq.isEmpty()) {
			
			Edge edge = pq.poll(); //가장 낮은 가중치의 연결 받아옴 
			int islandNum = edge.end.num;
			if (visited.contains(islandNum))
				continue;
			
			answer += edge.weight;
			visited.add(islandNum);
			
		
			List<Node> linkedNode = BFS(map, edge.end, edge.end.num, edge.end.num);
			
			for (Node n : linkedNode) {
				for (Edge e : graph[n.y][n.x]) {
					if (e == null || visited.contains(e.end.num))
						continue;
					
					pq.add(e);
				}
			}
			
			
		}
		if (visited.size() != islandCount) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
		
		System.exit(0);
	}
	
	
	static int[] DX = {0, -1, 0, 1};
	static int[] DY = {1, 0, -1, 0};
	
	public static List<Node> BFS(Node[][] map, Node fistNode, int islandNum, int flagNumber) {
		List<Node> nodeList = new ArrayList<>();
		Queue<Node> q = new LinkedList<>();
		q.add(fistNode);
		nodeList.add(fistNode);
		fistNode.num = islandNum; 

		
		while (!q.isEmpty()) {
			Node node = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int dy = node.y + DY[i];	int dx = node.x + DX[i];
				
				if (dy <= -1 || dx <= -1 || dy >= N || dx >= M || !map[dy][dx].island || map[dy][dx].num != flagNumber || nodeList.contains(map[dy][dx]))
					continue;
				
				//큐에서 poll 해서 섬 번호 설정 
				Node findNode = map[dy][dx];
				q.add(findNode);
				nodeList.add(findNode);
				findNode.num = islandNum;
			}
		}
		
		return nodeList;
	}
	
	public static void DFS(Node[][] map, int initY, int initX, int y, int x, int moveY, int moveX, int depth) {

		int dy = y + moveY;		int dx = x + moveX;
		
		if (dy <= -1 || dx <= -1 || dy >= N || dx >= M) 
			return;
		
		if (map[dy][dx].island && map[dy][dx].num == map[initY][initX].num) {
			DFS(map, initY, initX, dy, dx, moveY, moveX, 0);
			return;
		}
		
		if ((depth <= 1 && map[dy][dx].island)) {
			return;
		}
			
		if (map[dy][dx].island) { //다른 섬에 도착했다면? 간선을 정의한다!
			graph[initY][initX].add(new Edge(map[dy][dx], depth));
			graph[dy][dx].add(new Edge(map[initY][initX], depth));
			return;
		}
		
		DFS(map, initY, initX, dy, dx, moveY, moveX, depth + 1);
	}
}


class Node {
	int y;
	int x;
	boolean island; //true면 섬이다.
	int num = -1;
	
	Node(int y, int x, int island) {
		this.y = y;
		this.x = x;
		if (island == 1)
			this.island = true;
	}
}


class Edge implements Comparable<Edge>{
	Node end;
	int weight;
	
	Edge(Node end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}
	
}