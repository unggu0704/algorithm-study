import java.io.*;
import java.util.*;



public class Main {
	public static void main(String[] args) throws IOException{
        solution();
	}

	static int N, M;
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
       
        int[] arr = new int[N+1];
        int[][] input = new int[N][2];
        
        st =new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i =0; i < M; i++) {
        	st =new StringTokenizer(br.readLine());
        	input[i][0] = Integer.parseInt(st.nextToken());
        	input[i][1] = Integer.parseInt(st.nextToken());
        }
        
        // 입력 종료 
        
        for(int i = 1; i < N+1; i++) {
        	arr[i] += arr[i-1];
        } // 누적합
        
        StringBuilder sb = new StringBuilder();
       
        for(int i =0; i < M; i++) {
        	int a = input[i][0];	int b = input[i][1];
        	
        	sb.append(arr[b] - arr[a-1] + "\n");
        }
        
        System.out.println(sb);
    }

}
