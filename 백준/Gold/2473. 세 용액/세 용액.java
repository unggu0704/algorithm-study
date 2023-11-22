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
        
        long[] liquor = new long[N];

        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            liquor[i] = Integer.parseInt(st.nextToken());
        } // 100.000
        Arrays.sort(liquor);
        //투포인터
        /*
         * 1. 투 포인터로 가장 작은 값을 찾고 그 안에서 다시 한번 3번째 가장 작은 용액을 찾기 
         *  용액을 찾는 시간 복잡도 o(N)
         *  그 안에서 다시한번 최소값을 찾기 o(N - 1) 
         *  -> o (n / 2 ^ 2)
         *  2개의 최소값이 다른 최소값을 보장하는가...?
         * 
         * 2. 백트래킹으로 모든 조합의 갯수 찾기
         *  5000C3 - > 약 10억 이상... 시간초과
         * 
         * 3. 투 포인터로 찾고 
         * 값에 따라 0보다 작으면 가장 작은 수부터 한번더 포인터 
         * 0보다 크다면 큰 수부터 한번더 포인터 기존의 값보다 값이 더 커지면 탐색 종료 
         * -> 실패 
         * 
         * -97 -6 -2 6 98
         * 
         * 4. start를 정해놓고 투 포인터  
         * -> N^2
         */

        int leftIndex = 0;
        int rightIndex = N - 1;
        int midIndex = 0;
        long MIN = Long.MAX_VALUE;
        int leftSave = 0, rightSave = 0, midSave = 0;
        
        for (int i = 0; i < N - 2; i++) {
            leftIndex = i;
            midIndex = i + 1;
            rightIndex = N - 1;

            while (rightIndex > midIndex) {
                long diff = liquor[leftIndex] + liquor[midIndex] + liquor[rightIndex];

                if (MIN > Math.abs(diff)) {
                    MIN = Math.abs(diff); // 최솟값 갱신

                    leftSave = leftIndex;
                    midSave = midIndex;
                    rightSave = rightIndex;
                }

                if (diff < 0) 
                    midIndex++;
                else
                    rightIndex--;
            }
        }


        long[] answer = {liquor[leftSave], liquor[rightSave], liquor[midSave]};

        Arrays.sort(answer);

        for (long i : answer) {
            System.out.print(i + " ");
        }

        
  
    }

  
}
