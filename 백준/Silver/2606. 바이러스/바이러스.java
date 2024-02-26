import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int [N + 1];
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
		
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int input1 = Integer.parseInt(st.nextToken());
			int input2 = Integer.parseInt(st.nextToken());
			
			if (find(input1) != find(input2)) {
				union(input1, input2);
			}
		}
		
		int ans = 0;
		for (int i : parents) {
			if (find(i) == 1) 
				ans++;
		}
		
		System.out.println(ans - 1);
		
	}
	
	public static int find(int node) {
		if (parents[node] == node) return node;
		
		return parents[node] = find(parents[node]);
	}
	
	public static void union(int n1, int n2) {
		int r1 = find(n1);
		int r2 = find(n2);
		
		if (r1 > r2)
			parents[r1] = r2;
		else
			parents[r2] = r1;
	}

}