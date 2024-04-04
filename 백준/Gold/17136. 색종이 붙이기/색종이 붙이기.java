import java.io.*;
import java.util.*;
/**
 * Main_17144_미세먼지안녕!_김규형
 *   메모리       시간
	108664	396
 * 
 * 알고리즘 없는 구현 문제
 */
public class Main {
	
	static int N, M, T;
	static int[] DY = {-1,  0,  1, 0};
	static int[] DX = { 0,  1,  0, -1};
	static int[][] map;
	static  int[] confettis  = {0, 5, 5, 5, 5, 5};
	static List<Point> pointList;
	static int ans = Integer.MAX_VALUE;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        pointList = new ArrayList<Point>();
        
        map = new int[10][10];
        
        for (int i = 0; i <10; i ++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < 10; j++) {
        		int input = Integer.parseInt(st.nextToken());
        		if (input == 1) 
        			pointList.add(new Point(i, j));
        		
        		map[i][j] = input;
        	}
        }
       
        backTracking(0, 0, 0);
        
        if(ans == Integer.MAX_VALUE)
        	ans = -1;
    	
    	System.out.println(ans);
    }
    
    public static void backTracking(int index,  int confettisCount, int depth) {
    	if (depth > pointList.size() - 1) {
    		ans = Math.min(confettisCount, ans);
    		return;
    	}
    	
    	int i = pointList.get(index).y;		int j = pointList.get(index).x;		
		
		if (map[i][j] == 1) {
			//크기 5인 색종이부터 붙힌다.
			for (int p = 5; p > 0; p--) {
				//남은 색종이가 있는가? && 색종이를 붙일수 있는지?
				if (confettis[p] <= 0 || checkConfetti(i, j, p))
					continue;
				
				confettis[p]--;
				attachConfetti(i, j, p, 0);
				backTracking(index + 1, confettisCount + 1, depth + 1);
				attachConfetti(i, j, p, 1);
				confettis[p]++;
			}
		} else {
			backTracking(index + 1, confettisCount, depth + 1);
		}
    }
    
    public static boolean checkConfetti(int y, int x, int size) {
    	if (y + size > 10 || x + size > 10)
    		return true;
    	
        for (int i = y; i < y + size; i++) {
        	for (int j = x; j < x + size; j++) {  		
        		if (map[i][j] == 0)
        			return true;
        	}
        }
        return false;
    }
    
    public static void attachConfetti(int y, int x, int size, int state) {
        for (int i = y; i < y + size; i++) {
        	for (int j = x; j < x + size; j++) {  		
        		map[i][j] = state;
        	}
        }
        
    }
    
    public static void printMap() {
    	
    	for (int i = 0; i < 10; i++) {
          	for (int j = 0; j < 10; j++) {
          		System.out.print(map[i][j] + " ");
          	}
          	System.out.println();
    	}
    	System.out.println("=================");
    }

}

class Point {
	int y; 
	int x;
	
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}