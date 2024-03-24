import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	  static boolean visited[];
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

    	int r = Integer.parseInt(st.nextToken());
    	Node root = new Node(r);
    	
    	while(true) {

    		
    		String s = br.readLine();
    		if(s == null) break;
    		
    		if(s.length() == 0) break;
    		
    		int N = Integer.parseInt(s);
    		
    		Node n = new Node(N);
    		
    		root.isLink(n);
    	}
    	
    	backSearch(root);
    	System.out.println(sb);
    }
    static StringBuilder sb = new StringBuilder();
    
    public static void backSearch(Node n) {
    	
    	if(n.leftNode != null)	backSearch(n.leftNode);

    	if(n.rightNode != null)	backSearch(n.rightNode);
    	
    	sb.append(n.nodeName + "\n");
    	return;
    }
    
    
}


class Node{
	int nodeName;
	Node leftNode;
	Node rightNode;
	Node(int n){
		this.nodeName = n;
		leftNode = null;
		rightNode = null;
	}
	
	void isLink(Node inputNode){
		if(this.nodeName > inputNode.nodeName) {
			if(leftNode == null) {
				leftNode = inputNode;
			}
			else leftNode.isLink(inputNode);
		}
		else {
			if(rightNode == null) {
				rightNode = inputNode;
			}
			else rightNode.isLink(inputNode);
		}		
	}
	
}



