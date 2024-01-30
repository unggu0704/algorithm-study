import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //집의 수 
			int M = Integer.parseInt(st.nextToken()); //도로의 수
			long totalCost = 0;
			
			if (N == 0 && M == 0)
				break;
			
			List<Edge>[] graphs = new ArrayList[M];
			
			for (int i = 0; i < M; i++) {
				graphs[i] = new ArrayList<>(); //각 집이 가지고 있는 노드의 수
			}
			
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()); // x 라는 집 
				int y = Integer.parseInt(st.nextToken()); // y 라는 집 
			
				int cost = Integer.parseInt(st.nextToken()); // x와 y를 연결하기 위한 비용 
				
				totalCost += cost;
				graphs[x].add(new Edge(cost, y));
				graphs[y].add(new Edge(cost, x));
			}
			
			System.out.println(totalCost - prim(graphs, 0, N));
		}

	}
	
	public static long prim(List<Edge>[] graphs, int start, int N) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		pq.offer(new Edge(0, start));
		long totalCost = 0;
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll(); // 가장 저렴한 길을 가져온다. 
			if (visited[edge.destination]) // 방문한 노드면 생략
				continue;
			
			int destination = edge.destination; // 목적지 
			totalCost += edge.cost;
			visited[destination] = true;
			
			for (Edge e : graphs[destination]) { //도착한 목적지가 가지고 있는 모든 연결된 경로를 우선순위큐에 담는다. 
				if (!visited[e.destination])
					pq.add(e);
			}
		}
		return totalCost;
	}
}

class Edge implements Comparable<Edge>{
	int cost;
	int destination;
	
	Edge(int cost, int destination) {
		this.cost = cost;
		this.destination = destination;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

