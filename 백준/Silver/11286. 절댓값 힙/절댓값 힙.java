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
    	StringBuilder sb= new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken());
    	
    	PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
    		
    		@Override
    		public int compare(Integer o1, Integer o2) {
    			if(Math.abs(o1) > Math.abs(o2)) { // 절댓값이 낮으면 우선 순위가 높다 
    				return 1;
    			}
    			else if(Math.abs(o1) == Math.abs(o2)){ // 절댓값이 같으면 
    				if(o1 > o2) return 1; // 새로 들어온게 더 작으면 우선순위가 높음   
    				else return -1; // 새로 들어온게 더 크면  우선순위가 낮음  
    			}
    			else {
    				return -1; // 절댓값이 크면 우선순우가 많이 낮음 
    			}
    		}
    	});
    	
    	
    	int input;
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		input = Integer.parseInt(st.nextToken());
    		if(input == 0) {
    			if(pq.isEmpty()) {
    				sb.append(0 + "\n");
    			}
    			else {
    				sb.append(pq.poll() + "\n");
    			}
    		}
    		else {
    			pq.add(input);
    		}
    	}
    	
    	System.out.println(sb);
    	
    }
    
}



