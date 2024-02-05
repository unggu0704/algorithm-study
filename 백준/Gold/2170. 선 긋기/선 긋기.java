import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	static int N;
	static StringBuilder sb = new StringBuilder();

    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	
    	int arr[][] = new int[N][2];
    	
    	int input1;	int input2;
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		
    		input1 = Integer.parseInt(st.nextToken());
    		input2 = Integer.parseInt(st.nextToken());
    		
    		Math.abs(input1 - input2);
    		
    		if(input1 > input2) {
    			arr[i][1] = input1;
        		arr[i][0] = input2;
    		}
    		else {
    			arr[i][0] = input1;
        		arr[i][1] = input2;
    		}
    	}
    	
    	Arrays.sort(arr, (p1,p2) ->{
    		return p1[0] - p2[0];
    	});
    	
    	int pivot = arr[0][1];
    	int ans = Math.abs(arr[0][1] - arr[0][0]);
    	for(int i = 1; i < N; i++) {
    		
    		if(pivot >= arr[i][1]) continue;
    		
    		if(pivot < arr[i][0]) {
    			ans += Math.abs(arr[i][1] - arr[i][0]);
    			pivot = arr[i][1];
    			continue;
    		}//  새로운  pivot이 출발 
    		
    		ans += Math.abs(arr[i][1] - pivot);
    		pivot = arr[i][1]; // 피봇 갱신 
    	
    	}
    	System.out.println(ans);
    	
    	
    	
    	
    }
    
    
    
    
}
