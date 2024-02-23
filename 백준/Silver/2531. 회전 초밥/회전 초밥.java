
import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	
	static int index =0;
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	int N,d,k,c;
    	int belt[];
    	st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	d = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	
    	belt = new int[N+k];
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		belt[i] = Integer.parseInt(st.nextToken());
    	}
    	for(int i = 0 ; i <k; i++) {
    		belt[N+i] = belt[i];
    	} // 원형큐를 흉내 
    	
    	// 입력 처리 종료 
    	
    	int eatMax = k+1; // 총 먹을 수 있는 가지의 최댓값 
    	int Max = 0, size;
    	Set<Integer> set;

    	for(int i = 0; i<= N; i++) {
    	
    	
    		set = new HashSet<Integer>();
    		for(int j = i; j < i+k; j++) {
    			set.add(belt[j]);
    		}
    		
    		size = set.size();
    		// 중복 제거 
    		if(!set.contains(c)) size++;
    		// 쿠폰을 안먹었으면 추가 
    		if(size > Max) {
    			Max = size;
    			if(eatMax == Max) break; // 최대 최댓값 발견시 종료 
    		}
    		
    	}
    	
    	System.out.println(Max);
    	
    	
    	
    }
    
    
}




