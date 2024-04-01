import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        long factorial[] = new long[1000001];
        factorial[0]=1;
        for(int i=1;i<1000001;i++) {
            factorial[i] = (factorial[i-1]* i) % 1234567891;
        }
        
        for(int test_case=1;test_case<=t;test_case++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            
            
            long answer = factorial[N] * pow((factorial[R]* factorial[N-R])%1234567891,1234567891-2) % 1234567891;
            
            System.out.println("#"+test_case+" "+answer);
            
        }
    }

    private static long pow(long a, long b) {
        if (b == 0) 
            return 1;
        if (b == 0)
        	return a;
        
        long next = pow(a,b/2);
        long ans = (next*next) % 1234567891;
        if(b % 2 == 0 ) {
            return ans;
        }else {
            return (ans*a) % 1234567891;
        }

    }

}