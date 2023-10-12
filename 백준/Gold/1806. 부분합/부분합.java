import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] input = new int[N];
        st =new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
  

        int startIndex = 0;
        int endIndex = 1;
        int length = 2000000000;
        long sum = input[0];

        while (true) {
        
            
            if (sum >= S) {
                length = Math.min(endIndex - startIndex , length);
                sum -= input[startIndex++];
            } else if (endIndex >= N) {
                break;
            } else if (sum < S) {
                sum += input[endIndex++];
            }
        }
       
        if (length == 2000000000) {
            System.out.println(0);
        }
        else System.out.println(length);
    }

  
}
