import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int ans = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		parents = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
		
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			if (find(edge.v1) != find(edge.v2)) {
				union(edge.v1, edge.v2);
				ans += edge.weight;
			}
		}
		
		System.out.println(ans);
	}
	
	public static int find(int node) {
		if (parents[node] == node) return node;
		
		return parents[node] = find(parents[node]); 
	}
	
	public static void union(int n1, int n2) {
		int root1 = find(n1);
		int root2 = find(n2);
		
		if (root1 > root2) {
			parents[root1] = root2;
		} else {
			parents[root2] = root1;
		}
	}
}

class Edge implements Comparable<Edge> {
	int v1;
	int v2;
	int weight;
	
	Edge(int v1, int v2, int w) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = w;
	}

	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}
}