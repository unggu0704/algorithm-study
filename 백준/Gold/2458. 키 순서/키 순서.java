import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * Main_2458_키순서_김규형
 *   메모리       시간
 * 
 * 위상정렬로.. 해볼려했지만 최초 노드들의 우선 순위가 파악되지 않음
 * 
 */
public class Main {
	
	static int N, M;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    
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
	    
	    System.out.println(ans);
    }
}

class Node {
	int num;
	List<Integer> inDgrees = new ArrayList<>();
	List<Integer> outDgrees = new ArrayList<>();
	boolean visited;

	public Node(int num) {
		super();
		this.num = num;
		visited = false;
	}	
}