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
    	
    	int deep = 1;
    	int six = 6;
    	int i = 1;
    	int index = 1;
    	while(true) {
    		if( index >= N) {
    			System.out.println(deep);
    			System.exit(0);
    		}
    		index += six * i++;
    		deep++;
    		
    	}
    	
    }
    
    
}




