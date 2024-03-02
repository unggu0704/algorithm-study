import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[n];
        for(int i=0;i<n;i++) {
            start = i;
            solution(i,0,0);
        }
        System.out.println(min);
    }

    static int min = Integer.MAX_VALUE;
    static int start = 0;
    static boolean[] visited;
    private static void solution(int y, int cnt, int sum) {

        if ( cnt == n && start == y) {
            min = Math.min(min, sum);
            return;
        }
        // 도착한 y에서 이동
        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            // y->i로 갈 수 없는 경우 0값을 가지고있다.
            if ( arr[y][i] == 0)
                continue;
            if (sum + arr[y][i] > min)
                continue;
            
            visited[i] = true;
            solution(i,cnt+1,sum+arr[y][i]);
            visited[i] = false;
        }
    }
}