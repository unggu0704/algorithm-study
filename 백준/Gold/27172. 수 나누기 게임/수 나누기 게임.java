import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int [] input = new int[N];
        int [] answer = new int[1000001];
        boolean[] flag = new boolean[1000001];
        st =new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int intinput = Integer.parseInt(st.nextToken());
            input[i] = intinput;
            flag[intinput] = true;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= Math.sqrt(input[i]); j++) {
                if (input[i] % j == 0) { // 약수인가? && j가 있는가?
                    if (flag[j] && Math.pow(j, 2) == input[i]) {  // j 가 제곱근인가?
                        answer[input[i]]--;
                        answer[j]++;
                    } else { 
                        if (flag[j]) {
                        answer[input[i]]--; // 우선 j는 추가!
                        answer[j]++; 
                        }
                        if (flag[input[i]/ j]) { // 이 수도 있으면 대결!
                            answer[input[i]]--;
                            answer[input[i] / j]++;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(answer[input[i]] + " ");
        }

        System.out.println(sb);
        
        

    }

  /**
   * -4 1 1 1 1
   * -4 -2 2 2 2
   * -4 -2 0 3 3
   * -4 -2 0 2 4
   */
}
