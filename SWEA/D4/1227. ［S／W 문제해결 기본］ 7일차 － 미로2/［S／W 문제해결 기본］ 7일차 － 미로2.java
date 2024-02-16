import java.util.*;


/**
 * D4_1227_미로2_김규형
 * 
 * @author 김규형
 */
public class Solution {
    static Scanner scanner = new Scanner(System.in);
    static String[] map = new String[100];
    static boolean[][] visited = new boolean[100][100];
    static int chk = 0;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static int sr, sc;


    static void solution() {
        for (int i = 1; i <= 10; i++) {
            scanner.nextInt();
            makeMap();
            
            dfs(sr, sc);
            
            System.out.println("#" + i + " " + chk);
            chk = 0;
            initVistted();
        }
    }

    public static void main(String[] args) {
        solution();
    }
    static void initVistted() {//방문 여부 배열 초기화
        
        for(int i=0;i<100;i++) {
            for(int j=0;j<100;j++)
                visited[i][j] =false;
        }
    }

    static void makeMap() {
        for (int i = 0; i < 100; i++) {
            map[i] = scanner.next();
            for(int j=0;j<100;j++) {
                
                if(map[i].charAt(j) == '2') {//시작점 찾기
                    sr = i;
                    sc = j;
                }
            }
        }
    }

    static boolean is_valid(int r, int c) {//유효성 검사
        return (r >= 0 && r < 100) && (c >= 0 && c < 100) && map[r].charAt(c) != '1';
    }

    static void dfs(int r, int c) {
        if (map[r].charAt(c) == '3') {
            chk = 1;
            return;
        }
        for (int i = 0; i < 4; i++) {//상하좌우 체크
            if (is_valid(r + dr[i], c + dc[i]) && !visited[r + dr[i]][c + dc[i]]) {
                visited[r + dr[i]][c + dc[i]] = true;
                dfs(r + dr[i], c + dc[i]);
                visited[r + dr[i]][c + dc[i]] = false;
            }

        }
    }
    
}