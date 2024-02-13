import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	static int ans = 0;
	static int N,M;
	static int r,c;
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	int size = 1;	int checkNumber = 1;
    	for(int i = 0; i < N; i++) {
    		size *= 2;
    		checkNumber *= 4;
    	}
    	checkNumber /= 4;
    	r = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	int check = size/2;
    	
   
    	//2^1 -> 4^0 1
    	//2^2 3 -> 4^1 4
    	//2^3 6 -> 4^2 16
    	//2^4 16 -> 4^3 64
    	
    	if( r < check && c < check) {
    		recur(0,0,check, checkNumber/4);
    	}
    	else if(r < check && c >= check) {
    		ans = checkNumber;
    		recur(0, check, check, checkNumber/4);
    	}
    	else if (r >= check && c < check) {
    		ans = 2 * checkNumber;
    		recur(check, 0, check, checkNumber/4);
    	}
    	else if(r >= check && c >= check) {
    		ans = 3 * checkNumber;
    		recur(check,check,check, checkNumber/4);
    	}
 		
    	
    	System.out.println(ans);
    }
    
    public static int recur(int y, int x, int n, int ch) {
    //	System.out.println("y: " + y + ", x: " + x + " n: " + n);
    
    	if(y == r && x == c) {
    		System.out.println(ans);
    		System.exit(0);
    	}
    	
    	if(n == 1) {
        	ans++;
        	return 0;
        }
    	
    	if(n == 2) {
    		recur(y,x,1,1);
    		recur(y,x+1,1,1);
    		recur(y+1,x,1,1);
    		recur(y+1,x+1,1,1);
    		return 0;
    	}
        int y_half = y + n/2;
        int x_half = x + n/2;
    	
    	if( r < y_half && c < x_half) {
    		recur(y, x, n/2, ch/4);
    	}
    	else if(r < y_half && c >= x_half) {
    		ans += ch;
    		recur(y, x + n/2, n/2, ch/4);
    	}
    	else if (r >= y_half && c <  x_half) {
    		ans += 2 * ch;
    		recur(y + n/2, x , n/2, ch/4);
    	}
    	else if(r >= y_half && c >=  x_half) {
    		ans += 3 * ch;
    		recur(y + n/2, x + n/2 , n/2, ch/4);
    	}
    	
  
    	
    	return 0;
    }
    
    
}
