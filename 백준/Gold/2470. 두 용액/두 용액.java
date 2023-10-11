import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        int[] liquor = new int[N];

        st =new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            liquor[i] = Integer.parseInt(st.nextToken());
        } // 100.000

        Arrays.sort(liquor);
        int leftIndex = 0;
        int rightIndex = N - 1;
        int MIN = 2000000000;
        int leftSave = 0, rightSve = 0;
        while(true) {
            if (leftIndex >= rightIndex) break;

            int result = liquor[leftIndex] + liquor[rightIndex];

            if (MIN > Math.abs(result)) {
                MIN = Math.abs(result);
                leftSave = liquor[leftIndex];
                rightSve =liquor[rightIndex];
                if (MIN == 0) {
                    break;
                }
            }

            if (result < 0) { 
                leftIndex++;
            } else {
                rightIndex--;
            }
        }

        System.out.println(leftSave + " " + rightSve);
  
    }

  
}
