import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];
        int[] operation = {1,2,3,4}; // 1은 더하기 2는 뺄셈 3은 곱하기 4는 나누기 
        int[] oper  = new int[N - 1];
        int[] combine  = new int[N - 1];
        boolean[] visited = new boolean[N-1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int index = 0;
        for (int i = 0; i <4; i++) {
            int operCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < operCount; j++) {
                oper[index++] = operation[i];
            }
        }


        backTracking( 0, numbers, combine, oper, visited, N);
        System.out.println(MAXSUM + "\n" + MINSUM);

    }
    static int count = 1;
    static int MAXSUM = Integer.MIN_VALUE;
    static int MINSUM = Integer.MAX_VALUE;
    public static void backTracking(int operIndex, int[] numbers, int[] combine, int[] oper, boolean[] visited, int SUM) {
        if (operIndex >= oper.length) { // 숫자가 끝나간다.
            int num = numbers[0];
         
            
            for (int i = 1; i < numbers.length; i++) {
                num = isCalculate(num, numbers[i], combine[i - 1]);
            }
/* 
            for (int i : combine) {
                System.out.print(i + " | ");
            }
            */
           // System.out.println(" = " + count++);
            MAXSUM = Math.max(num, MAXSUM);
            MINSUM = Math.min(num, MINSUM);
            return;
        }

        for (int i = 0; i < oper.length; i++) {
            if (visited[i]) continue;

            combine[operIndex] = oper[i]; // operIndex가 n 일때 n -1 번 반복 해야한다.
            visited[i] = true; // 방문 처리 같은 경우는 해당 oper[n] 이 앞에 있다는것을 표시 함
            backTracking( ++operIndex, numbers, combine, oper, visited, SUM); // operIndex가 n일때 브루트 포스
            visited[i] = false; // 더이상 앞에 없음 
            operIndex--;
        }
        return ;
    }

    public static int isCalculate(int num1, int num2, int oper) {
    
        switch (oper) {
            case 1:
                return num1 + num2;
            case 2:
                return num1 - num2;
            case 3:
                return num1 * num2;
            case 4:
                return num1 / num2;
        }

        return Integer.MAX_VALUE;
    }

}