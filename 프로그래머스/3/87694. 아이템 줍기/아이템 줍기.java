import java.util.*;
import java.math.*;

class Solution {
    boolean[][] map = new boolean[101][101];
    int answer = Integer.MAX_VALUE;
    int destY, destX = 0;
    int[] DX = {0, 1,  0, -1};
    int[] DY = {1, 0, -1, 0};
    
    public void findDFS (int y, int x, int dist, boolean[][] visted) {
        // 가지치기
        if (dist >= answer)
            return;
        
        if (y == destY && x == destX) {
            answer = Math.min(answer, dist);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int dy = y + DY[d];     int dx = x + DX[d];

            // 갈 수 없는 곳 
            if (dy > 100 || dx >100 || dy < 0 || dx < 0 || !map[dy][dx] || visted[dy][dx])
                continue;
            
            visted[dy][dx] = true;
            findDFS(dy, dx, dist + 1, visted);
            visted[dy][dx] = false;
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        destY = itemY * 2;  destX = itemX * 2;
        // 직사각형 꺼내고 둘레 그리기
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2; int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2; int y2 = rectangle[i][3] * 2;
      
            // 내부를 체운다.
            for (int y = y1; y <= y2; y ++) {
                for (int x = x1; x <= x2; x++) {
                    map[y][x] = true;
                }
            }
        }
        
         for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2; int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2; int y2 = rectangle[i][3] * 2;
      
            // 내부를 파낸다.
            for (int y = y1 + 1; y <= y2 - 1; y ++) {
                for (int x = x1 + 1; x <= x2 - 1; x++) {
                    map[y][x] = false;
                }
            }
        }
        
        
        for (int i = 0; i < 4; i++) {
            int dy = characterY * 2 + DY[i];    int dx = characterX * 2 + DX[i];
            
            if (dy > 100 || dx >100 || dy < 0 || dx < 0 || !map[dy][dx])
                continue;
            
            findDFS(dy, dx, 1, new boolean[101][101]);
        }
        
        return answer / 2;
    }
}