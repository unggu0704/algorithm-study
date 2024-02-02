import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	static int ans = 0;

    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	Queue<Integer> q = new LinkedList<Integer>();
    	
    	for(int i = 1; i <= N; i++) {
    		q.add(i);
    	}
    	
    	boolean flag = false;
    	int item;
    	while(q.size() != 1) {
    		item = q.poll();
    		if(flag) {
    			q.add(item);
    			flag = false;
    		}
    		else {
    			//System.out.print(item + " ");
    			flag = true;
    		}
    			
    	}
    	System.out.println(q.poll());
    }
    
    
    
    
    
    static public void print(int a[]) {
    	StringBuilder sb = new StringBuilder();
    	for(int i : a) {
    		if(i == 0) break;
    		sb.append(i + " ");
    	}
    	System.out.print(sb);
    }
}



