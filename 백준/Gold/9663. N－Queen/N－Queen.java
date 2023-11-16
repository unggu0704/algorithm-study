
import java.io.*;
import java.util.*;

/**
 * visited 2차원으로 만들어서
 * 새로운 백트래킹 떄마다 기존의 boolean 2차원 배열을 복사해 
 * 나의 visited로 만든 뒤에 탐색이 종료되면 버린다.
 * 
 */

public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    static int N;
    static int answer = 0;
    static int[] arr;
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        backTracking(0);

        System.out.println(answer);

    }

    /**
     * 첫번째 열의 2행, 두번쨰열의 0행
     * [2, 0, 3 ,1]
     * arr[] -> 인덱스는 열 요소는 행
     * @param n -> 열
     */
    public static void backTracking(int n) {
        if (n == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[n] = i;
            if (queenAttack(n)) 
                backTracking(n + 1); // 다음 열로
        }
        
    }

    public static boolean queenAttack(int attack) {
        for (int i = 0; i < attack; i++) {
            if (arr[i] == arr[attack]) { // 기존에 같은 열이 있을 경우
                return false;
            }

            else if (Math.abs(i - attack) == Math.abs(arr[i] - arr[attack])) {
                return false;
            }
        }
        return true; // 공격이 가능 
    }

    // public static int[] DY = {-1, 1, 1, -1};
    // public static int[] DX = {-1, 1, -1, 1};
    // public static void queenAttack(boolean[][] visitedMap, int y, int x) {
    //     for (int i = 0; i < N; i++) {
    //         visitedMap[i][x] = true;
    //         visitedMap[y][i] = true;

    //         for (int j = 0; j < 4; j ++) {
    //             int dy = y + (DY[j] * i);  
    //             int dx = x + (DX[j] * i);

    //             if (dy >= N || dx >= N || dy <= -1 || dx <= -1) continue; // 경계를 넘어간다면 탐색하지 않음.

    //             visitedMap[dy][dx] = true;
    //         }
            
    //     }

    // }

    // public static void printMap(boolean[][] visitedMap) {
    //     for (int i = 0; i < N; i++) {
    //         System.out.println();
    //         for (int j = 0; j < N; j++) {
    //             System.out.print(visitedMap[i][j] + "|");
    //         }
    //     }
    // }

    // public static boolean[][] cloneVisited(boolean[][] visited) {
    //     boolean[][] newVisited = new boolean[N][N];
    //     for (int i = 0; i < N; i++) {
    //         for (int j = 0; j < N; j++) {
    //             newVisited[i][j] = visited[i][j];
    //         }
    //     }
    //     return newVisited;
    // }
}

