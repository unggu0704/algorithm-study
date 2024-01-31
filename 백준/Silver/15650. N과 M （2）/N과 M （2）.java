import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	 static int[] list;
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	list = new int [M];
    	dfs(1,0);
    	
    	System.out.println(sb);
    	
    	
    }

    public static void dfs(int at, int depth) {
    	if(depth == M) {
    	//	sb.append(at + " ");
    		for(int i : list) {
    			sb.append(i + " ");
    		}
    		sb.append("\n");
    		return;
    	}
    	
    	
    	
    	for(int i = at; i <= N; i++) {
    		list[depth] = i;
    		dfs(i+1, depth+1);
    	}
    	
    }
    

}

