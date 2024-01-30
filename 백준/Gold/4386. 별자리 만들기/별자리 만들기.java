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
		
		int N = Integer.parseInt(br.readLine());
		
		List<List<Edge>> graph = new ArrayList<>();
		List<Star> stars = new ArrayList<>(N);
		
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			double y = Double.parseDouble(st.nextToken());
			double x = Double.parseDouble(st.nextToken());
			
			stars.add(new Star(y, x));
		}
		// 입력 처리 종료 
		
		for (int i = 0; i < N; i++) {
			Star nowStar = stars.get(i);
			for (int j = i + 1; j < N; j++) {
				Star toStar = stars.get(j);
				double distance = getDistance(nowStar, toStar);
				graph.get(i).add(new Edge(j, distance));
				graph.get(j).add(new Edge(i, distance));
			}
		} //모든 간선을 계산한다. 
		
		System.out.printf("%.2f" , prim(graph, 0, N));
	}
	
	public static double prim(List<List<Edge>> graph, int start, int N) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		double answer = 0.0;
		
		pq.add(new Edge(0, 0)); //최초 시작할 0번 행성의 가중치는 0이다.
	//	visited[0] = true;
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int destinationStar = edge.star;
			double weight = edge.weight;
			
			if (visited[destinationStar]) 
				continue;
			
			visited[destinationStar] = true;
			answer += weight;
			
			for (Edge e : graph.get(destinationStar)) {
				if (visited[e.star])
					continue;
				
				pq.add(e);
			}
		}
		
		return answer;
	}
	
	public static double getDistance(Star s1, Star s2) {
		return Math.sqrt(Math.pow((s1.y - s2.y), 2) + Math.pow((s1.x - s2.x), 2));
	}
	
}

class Edge implements Comparable<Edge>{
	int star;
	double weight;
	
	Edge(int star, double weight) {
		this.star = star;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge e) {
		return (int) (this.weight - e.weight);
	}

}

class Star {
	double y;
	double x;
	
	Star(double y, double x) {
		this.y = y;
		this.x = x;
	}
}

