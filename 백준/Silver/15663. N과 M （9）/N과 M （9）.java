

import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
        solution();
	}
    
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

       int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());
       int[] arr = new int[N];
       int[] combine = new int[M];       
       boolean[] visited = new boolean[N];
       st =new StringTokenizer(br.readLine());
       for(int i = 0; i < N; i++) {
    	   arr[i] = Integer.parseInt(st.nextToken());
       }
       Arrays.sort(arr);
       
       //입력 종료 
       backTracking(0,0,arr,combine,M, visited);
       ans.forEach(System.out::println);
    }
    
    static ArrayList<Integer> numList = new ArrayList<>();
    static Set<String> ans = new LinkedHashSet<>();
    public static void backTracking(int n, int m , int[] arr, int[]combine, int depth, boolean[] visited) {
    	if(m == depth) {   		
    		StringBuilder sb = new StringBuilder();
    		for(int i : combine) {
    			sb.append(i + " ");
    		}
    		ans.add(sb.toString());
    		return;
    	}
    	
    	
    	for(int i = n; i < arr.length; i++) {
    		if(visited[i]) continue; 
    		combine[m++] = arr[i];
    		visited[i] = true;
    		backTracking(0,m,arr,combine,depth,visited);
    		visited[i] = false;
    		m--;
    	}
    }
}

