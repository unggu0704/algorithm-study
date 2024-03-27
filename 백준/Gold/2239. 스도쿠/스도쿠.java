import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_2239_스도쿠_김규형
 * @author 김규형
 *
 */
public class Main {
    static int N;
    static int M;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    static int[][] sdoku;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        //스도쿠 2차원 배열
        sdoku = new int[9][9];
        
        for (int i = 0; i < 9; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < 9; j++) {
        		sdoku[i][j] = str.charAt(j) - '0';
        	}
        }
        
        putSdoku(0, 0);
  
    }
    
    public static void putSdoku(int y, int x) {
    	
    	if (x >= 9) {
    		putSdoku(y + 1, 0);
    		return;
    	}
    	
    	if (y >= 9) {
    		for (int i = 0; i < 9; i++) {
            	for (int j = 0; j < 9; j++) {
            		System.out.print(sdoku[i][j]);
            	}
            	System.out.println();
        	}
        	System.exit(0);
    	}
    	
    	if (sdoku[y][x] == 0) {
			for (int n = 1; n < 10; n++) {
				if (check(n, y, x)) {
					sdoku[y][x] = n;
					putSdoku(y, x + 1);
				}
			}
			
			sdoku[y][x] = 0;
			return;
    	} else {
    		putSdoku(y, x + 1);
    	}
    	
    	
    	
    }
    
    /**
     * 해당 숫자가 들어가도 스도쿠 규칙에 어긋나지 않음을 판단한다.
     * @param num
     * @param y
     * @param x
     * @return
     */
    public static boolean check(int num, int y, int x) {
    	

    	for (int i = 0; i < 9; i++) {
    		// 스도쿠에 y축에 숫자가 있는지 확인 
    		if (num == sdoku[y][i]) {
    			return false;
    		}
    		
    		// 스도쿠에 y축에 숫자가 있는지 확인
    		if (num == sdoku[i][x]) 
    			return false;
    	}
    	

    	
    	int partionY = (y / 3) * 3; // value가 속한 3x3의 행의 첫 위치
		int partionX = (x / 3) * 3; // value가 속한 3x3의 열의 첫 위치
    	
    	for (int i = partionY; i < partionY + 3; i++) {
    		for (int  j = partionX; j < partionX + 3; j++) {
    			if (num == sdoku[i][j]) 
    				return false;
    		}
    	} 
    	 
    	return true;
    }

}