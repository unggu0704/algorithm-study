import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * Main_17144_미세먼지안녕!_김규형
 *   메모리       시간
 *  
 * 
 */
public class Main {
	
	static int N, M, T;
	static int[] DY = {-1,  0,  1, 0};
	static int[] DX = { 0,  1,  0, -1};
	
	static int[] RY = {1,  0, -1, 0};
	static int[] RX = {0, 1,  0, -1};
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    T = Integer.parseInt(st.nextToken());
	    
	    int[][] map = new int[N][M];
	    
	    Airclean airclean = null;
	    for (int i = 0; i < N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	for (int j = 0; j < M; j++) {
	    		int input = Integer.parseInt(st.nextToken());
	    		
	    		if (input == -1) {
	    			input = 0;
	    			if (airclean == null)
	    				airclean = new Airclean(i, j, i + 1, j);
	    		}
	    		
	    		map[i][j] = input;
	    	}
	    }
	    
	    for (int t = 0; t < T; t++) {
	    	spread(map, airclean);
	    	wind(map, airclean);
	    }
	    
	    System.out.println(sumMap(map));
    }
    
    public static void spread(int[][] map, Airclean airclean) {
    	List<Point> list = new ArrayList<>();
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < M; j++) {
    			//먼지가 있었다면?
    			if (map[i][j] > 0) 
    				list.add(new Point(i, j, map[i][j]));
    		}
    	}
    	//khim98
    	for (Point point : list) {
    		int value = point.value / 5; //확산될 미세먼지양
    		int totalValue = 0;
    		for (int i = 0; i < 4; i++) {
    			int dy = point.y + DY[i];	int dx = point.x + DX[i];
    			
    			if (dy >= N || dx >= M || dy <= -1 || dx <= -1 || (dy == airclean.y1 && dx == airclean.x1) || (dy == airclean.y2 && dx == airclean.x2))
    				continue;
    			//미세먼지 추가
    			map[dy][dx] += value;
    			totalValue += value;
    		}
    		
    		map[point.y][point.x] -= totalValue; //확산된 만큼 뺀다.
    	}
    }
    
    public static void wind(int[][] map, Airclean airclean) {
    	
		int dy = airclean.y1;		int dx = airclean.x1;
		
    	for (int i = 0; i < 4; i++) {
    		while (true) {
    			dy += DY[i];	dx += DX[i];
    			
    			if (dy >= airclean.y2 || dx >= M || dy <= -1 || dx <= -1) {
    				dy -= DY[i];	dx -= DX[i];
    				break;
    			}
    			
    			//dy, dx로 옮기기
    			map[dy - DY[i]][dx - DX[i]] = map[dy][dx];
    		//	printMap(map);
    		}
    		map[airclean.y1][airclean.x1] = 0;
    	}
    	
    	dy = airclean.y2;	dx = airclean.x2;
    	for (int i = 0; i < 4; i++) {
    		while (true) {
    			dy += RY[i];	dx += RX[i];
    			
    			if (dy >= N || dx >= M || dy <= airclean.y1 || dx <= -1) {
    				dy -= RY[i];	dx -= RX[i];
    				break;
    			}
    			
    			//dy, dx로 옮기기
    			map[dy - RY[i]][dx - RX[i]] = map[dy][dx];
    		//	printMap(map);
    		}
        	map[airclean.y2][airclean.x2] = 0;
    	}

    }
    
    public static int sumMap(int[][] map) {
    	int sum = 0;
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < M; j++) {
    			sum += map[i][j];
    		}
    	}
    	
    	return sum;
    }

}

class Point {
	int y, x, value;

	public Point(int y, int x, int value) {
		super();
		this.y = y;
		this.x = x;
		this.value = value;
	}
}

class Airclean {
	int y1,y2,x1,x2;

	public Airclean(int y1, int x1, int y2, int x2) {
		super();
		this.y1 = y1;
		this.y2 = y2;
		this.x1 = x1;
		this.x2 = x2;
	}
	
}