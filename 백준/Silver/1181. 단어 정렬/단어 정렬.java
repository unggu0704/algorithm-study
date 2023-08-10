import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   //	StringTokenizer st =new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
   // 	ArrayList<String> str = new ArrayList<>();
    	Set<String> tmpStr = new HashSet<>();
    	int N = Integer.parseInt(br.readLine());
    	
    	for(int i = 0; i < N; i++) {
    		tmpStr.add(br.readLine());
    	}
      	ArrayList<String> str = new ArrayList<>(tmpStr);
    	
    
    	
    	Collections.sort(str, new Comparator<String>(){
    		@Override
    		public int compare(String o1, String o2) {
    			if(o1.length() > o2.length()) {
    				return 1;
    			}
    			else if(o1.length() == o2.length()) {
    				return o1.compareTo(o2);
    			}
    			else return -1;
    			
    		}
    		
    	});
    	
    	for(String s : str) {
    		sb.append(s + "\n");
    	}
    	
    	
    	System.out.println(sb);
    }
    
    
}




