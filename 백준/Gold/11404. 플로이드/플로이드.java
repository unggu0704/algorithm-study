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
       st =new StringTokenizer(br.readLine());
       int M = Integer.parseInt(st.nextToken());
 
       int[][] map = new int [N][N]; // 거리 정보를 받은 2차원 배열 
       
       for(int i = 0 ; i < N; i++) {
    	   for(int j = 0; j < N; j++) {
    		   if(i == j) { // 출발과 목적지가 같은 정점은 0 
    			   map[i][j] = 0;
    		   }
    		   else map[i][j] = 1000000000; // 매우 큰 값 으로 셋팅 
    	   }
       }
       int start, end, distance;
      for(int i  = 0; i < M; i++) {
    	  st =new StringTokenizer(br.readLine());
    	  start = Integer.parseInt(st.nextToken()) -1;
    	  end = Integer.parseInt(st.nextToken()) - 1;
    	  distance = Integer.parseInt(st.nextToken());
    	  map[start][end] = Math.min(distance, map[start][end]);
      }
       // 입력 셋팅 
      
      // 플로이드 워샬 알고리즘 
     for(int k = 0; k < N; k++) {
    	 for(int i = 0; i < N; i++) {
    		 for(int j = 0; j < N; j++) {
    			 map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
    		 }
    	 }
     }
     
     for(int i = 0 ; i < N; i++) {
  	   for(int j = 0; j < N; j++) {
  		   if(map[i][j] == 1000000000) map[i][j] = 0;
  	   }
     }
  	   
     StringBuilder sb = new StringBuilder();
     for(int i = 0; i < N; i++) {
		 for(int j = 0; j < N; j++) {
			 sb.append(map[i][j] + " ");
		 }
		 sb.append("\n");
	 }
     
     System.out.print(sb);
    }
}