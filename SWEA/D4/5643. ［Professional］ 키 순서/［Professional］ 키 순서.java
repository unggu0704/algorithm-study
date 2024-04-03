import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
        // 테스트 케이스 수를 받고
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
             sb.append("#" + tc +" ");
             solution();
        }

        System.out.println(sb);
    }
	
	public static void solution() throws IOException{
	    int N = Integer.parseInt(br.readLine());
	    int M = Integer.parseInt(br.readLine());
	    
	    Node[] nodes = new Node[N + 1];
	    int ans = 0;
	    
	    for (int i = 1; i <= N; i++) {
	    	nodes[i] = new Node(i);
	    }
	    
	    for (int i = 0; i < M; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	
	    	int inDgree = Integer.parseInt(st.nextToken());
	    	int outDgree = Integer.parseInt(st.nextToken());
	    	
	    	//나가는 배열을 올리고 하위 노드 리스트에 추가하여 기록 한다.
	    	nodes[outDgree].inDgrees.add(inDgree);
	    	nodes[inDgree].outDgrees.add(outDgree);
	    }
	    
	    Queue<Node> q = new ArrayDeque<>();
	    
	    //inDegree의 BFS 와 outDegree의 BFS를 탐색한다.
	    //q를 공유하며  진행한다.
	    for (int i = 1; i <= N; i++) {
	    	int count = 1;
	    	Node thisNode = nodes[i];
	    	q.offer(thisNode);
	    	
	    	boolean[] visited = new boolean[N + 1];
	    	visited[i] = true;
	    	
	    	//들어오는 간선 탐색
	    	while (!q.isEmpty()) {
	    		Node node = q.poll();
	    		for (Integer index : node.inDgrees) {
	    			if (visited[index])
	    				continue;
	    			
	    			visited[index] = true;
		    		count++;
	    			q.offer(nodes[index]);
	    		}
	    	}

	    	q.offer(thisNode);
	    	//나가는 간선 탐색
	    	while (!q.isEmpty()) {
	    		Node node = q.poll();
	    		for (Integer index : node.outDgrees) {
	    			if (visited[index])
	    				continue;
	    			
	    			visited[index] = true;
		    		count++;
	    			q.offer(nodes[index]);
	    		}
	    	}

	    	if (N <= count)
	    		ans++;
	    }
	    
	    sb.append(ans).append("\n");
    }

}

class Node {
	int num;
	List<Integer> inDgrees = new ArrayList<>();
	List<Integer> outDgrees = new ArrayList<>();

	public Node(int num) {
		this.num = num;
	}	
}