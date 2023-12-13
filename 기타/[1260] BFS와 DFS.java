import java.util.*;


public class Main {
	static List<Integer> resultDFS = new ArrayList<>();
	static List<Integer> resultBFS = new ArrayList<>();
	public static void main(String[] args) {

		isExecute();
		
	}
	
	static void isExecute() {
		Scanner s = new Scanner(System.in);
		int	nodeCount, edgeCount, startNode;
		List<Node> Graph = new ArrayList<>();
		int nodeA, nodeB;
	
		nodeCount = s.nextInt();	edgeCount = s.nextInt();	startNode = s.nextInt();
		
		for(int i = 1; i< nodeCount+1; i++) {
			Graph.add(new Node(i));
		}
		
		for(int i = 0; i < edgeCount; i++) {
			nodeA = s.nextInt()-1;	nodeB = s.nextInt()-1;
			Graph.get(nodeA).nearNode.add(Graph.get(nodeB));
			Graph.get(nodeB).nearNode.add(Graph.get(nodeA));
		}
		
		for(int i = 0; i < nodeCount; i++) {
			Collections.sort(Graph.get(i).nearNode);
		}
		
		is_DFS(Graph.get(startNode-1));
	//	B_Queue.add(Graph.get(startNode-1));
		Graph.get(startNode-1).BFSvisited = true;
		is_BFS(Graph.get(startNode-1));
		
		for(Integer e : resultDFS) {
			System.out.print(e+ " ");
		}
		System.out.print("\n");
		for(Integer e : resultBFS) {
			System.out.print(e+ " ");
		}
	}

	
	public static void is_DFS(Node n) {
		n.DFSvisited = true;
		resultDFS.add(n.nodeNumber);
		for( Node e : n.nearNode) {
			if(!e.DFSvisited)	{ is_DFS(e); }
		}
	}
	
	static Queue<Node> B_Queue = new LinkedList<Node>();
	public static int is_BFS(Node n) {
//		n.BFSvisited=true;
		resultBFS.add(n.nodeNumber);
	//	if(B_Queue.isEmpty()) {	return 0;	}
		
		for(Node e : n.nearNode) {
			if(!e.BFSvisited)	{ B_Queue.add(e);	e.BFSvisited = true;	}
		}
		if(B_Queue.isEmpty()) {	return 0;	}
		is_BFS(B_Queue.poll());
		return 0;
	}
}
	
class Node implements Comparable<Node>{
	int nodeNumber;
	List<Node> nearNode = new ArrayList<>();
	boolean DFSvisited, BFSvisited = false;
	
	Node(int n){
		this.nodeNumber = n;
	}
	
	@Override
	public int compareTo(Node n) {
		if(this.nodeNumber > n.nodeNumber) {
			return 1;
		}
		else {
			return -1;
		}
	}
}

