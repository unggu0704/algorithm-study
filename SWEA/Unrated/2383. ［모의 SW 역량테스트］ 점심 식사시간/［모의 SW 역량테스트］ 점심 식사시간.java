import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
 
 /**
  * D4_3289_서로소집합_김규형

  * @author 김규형
  *
  */
public class Solution {
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int index = Integer.parseInt(br.readLine());
        for (int i = 1; i <= index; i++) {
        	sb.append("#").append(i).append(" ");
            solution(br);
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    static int N;
    static int max;
    public static void solution(BufferedReader br) throws IOException {
    	max = Integer.MAX_VALUE;
    	StringTokenizer st;
    	List<Node> inputs = new ArrayList<>();
    	Node[] staris = new Node[2];	int sIndex = 0;	int index = 0;
    	
    	N = Integer.parseInt(br.readLine());
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < N; j++) {
    			int input = Integer.parseInt(st.nextToken());
    			if (input > 1)
    				staris[sIndex++] = new Node(i,j,input, -1);
    			else if (input == 1)
    				inputs.add(new Node(i,j,input, index++));
    			
    		}
    	}
    	
    	Node[] humans = new Node[inputs.size()];
    	for (int i = 0; i < inputs.size(); i++) {
    		humans[i] = inputs.get(i);
    	}
    	
    	//각 계단마다 걸리는 시간 
    	for (Node human : humans) {
    		human.timeA = Math.abs(human.y - staris[0].y) + Math.abs(human.x - staris[0].x);
    		human.timeB = Math.abs(human.y - staris[1].y) + Math.abs(human.x - staris[1].x);
    	}
    	
    	for (int i = 0; i < humans.length; i++) {
    		Node[] combines = new Node[i];
    		boolean[] visited = new boolean[humans.length];
    		divide(humans, staris, combines, visited, 0, 0, i);
    	}
    	
    	sb.append(max);
    }
    
    public static void divide(Node[] humans, Node[] staris, Node[] combines, boolean[] visited, int index, int depth, int size) {
    	if (depth == size) {
    		if (size == 4) {
    		//	System.out.println("a");
    		}
    			
    		max = Math.min(simulate(combines, humans, staris, humans.length), max);
    		max = Math.min(simulate(humans, combines, staris, humans.length), max);
    		return;
    	}
    	
    	for (int i = index; i < humans.length; i++) {
    		if (visited[i])
    			continue;
    		
    		combines[depth] = humans[i];
    		visited[i] = true;
    		divide(humans, staris, combines, visited, i + 1, depth + 1, size);
    		visited[i] = false;
    	}
    }
    
    public static int simulate(Node[] objectA, Node[] objectB, Node[] staris, int size) {
    	boolean[] visited = new boolean[size];
    	List<Node> aList = new ArrayList<>();
    	List<Node> bList = new ArrayList<>();
    	for (Node node : objectA) {
    		visited[node.index] = true;
    		
    		Node newNode = new Node(node.y, node.x, node.info, node.index);
    		newNode.timeA = node.timeA;
    		aList.add(newNode);
    	}
    	
    	for (Node node : objectB) {
    		if (visited[node.index])
    			continue;
    		
    		Node newNode = new Node(node.y, node.x, node.info, node.index);
    		newNode.timeB = node.timeB;
    		bList.add(newNode);
    	}
    	
    	List<Node> adown = new ArrayList<>();
    	List<Node> bdown = new ArrayList<>();
    	
    	int time = 0;
    	int count = size;
    	while(true) {
    		if (count == 0) 
    			return time;
    		
    		time++;
    		for (Node node : aList) {
    			if (node.timeA == 0) {
    				node.downtime = staris[0].info;
    				adown.add(node);
    			}
    			node.timeA--;
    		}
    		
    		for (Node node : bList) {
    			if (node.timeB == 0)  {
    				node.downtime = staris[1].info;
    				bdown.add(node);
    			}
    			node.timeB--;
    		}
    		
    		//꼐단 내려가기 
    		int asize = adown.size();
    		int bsize = bdown.size();
    		
    		Iterator<Node> aiter = adown.iterator();
    		Iterator<Node> biter = bdown.iterator();
    		int outCount = 0;
    		for (int i = 0; i < asize; i++) {
    			if (i == 3 + outCount)
    				break;
    			Node node = aiter.next();
    			if (node.downtime <= 0) {
    				aiter.remove();
    				count--;
    				outCount++;
    			}
    			node.downtime--;
    		}
    		
    		outCount = 0;
    		for (int i = 0; i < bsize; i++) {
    			if (i == 3 + outCount)
    				break;
    			Node node = biter.next();
    			if (node.downtime <= 0) {
    				biter.remove();
    				count--;
    				outCount++;
    			}
    			node.downtime--;
    		}
    		
    	}
    }

}

class Node {
	int timeA; // A 계단 까지 걸리는 시간 
	int timeB; // B 계단 까지 걸리는 시간 
	int y, x;
	int info;
	int index;
	int downtime;
	
	Node (int y, int x, int info, int index) {
		this.info = info;
		this.y = y;
		this.x = x;
		this.index = index;
	}
}