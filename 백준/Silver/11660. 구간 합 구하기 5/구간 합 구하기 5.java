import java.io.*;
import java.util.*;


public class Main {
   
   
   public static void main(String[] args) throws IOException{
      solution();
   }

    public static void solution() throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       StringBuilder sb = new StringBuilder();
   
        int N = Integer.parseInt(st.nextToken()); // 정사각형의 크기 1024
        int M = Integer.parseInt(st.nextToken()); // 연산의 횟수 100,000
        int[][] matrix = new int[N+1][N+1];
        int[] input = new int[M*4];
        
        for(int i = 1 ; i < N+1; i++ ) {
           st = new StringTokenizer(br.readLine());
           for(int j = 1 ; j < N+1; j++ ) {
              matrix[i][j] = Integer.parseInt(st.nextToken());
           }
        }
        
        for(int i = 0; i < M * 4; i += 4) {
           st = new StringTokenizer(br.readLine());
           for(int j = i ; j < i + 4; j++ ) {
              input[j] =  Integer.parseInt(st.nextToken());
           }
        }
        // 입력 종료 
        
        for(int i = 1 ; i < N+1; i++ ) {
            for(int j = 1 ; j < N+1; j++ ) {
            	matrix[i][j] = matrix[i-1][j] + matrix[i][j-1] + matrix[i][j] - matrix[i-1][j-1]; // 누적합 계산 
            }
        }
        
        int y1,x1,y2,x2;
        int count = 0;
        for(int i = 0; i < M * 4; i+=4) {
           y1 = input[i];   x1 = input[i+1]; y2 = input[i+2]; x2 = input[i+3];
           
           count = matrix[y2][x2] - matrix[y2][x1-1] - matrix[y1-1][x2] + matrix[y1-1][x1-1];
           
           sb.append(count + "\n");
           count = 0; //값들  초기화 
        }   
        
        System.out.println(sb);
    }
}
