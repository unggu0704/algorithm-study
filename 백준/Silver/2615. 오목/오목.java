import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 문제 05 오목판
 * @author 김규형
 */
public class Main {
	static final int WINSCORE = 5; // 이기는 돌의 개수 
	static final int PLAYERONE = 1; // 플레이어 1
	static final int PLAYERTWO = 2; // 플레이어 2
	
	/*
	 * 방향
	 */
	static int[] DX = {0, 0, 1, -1, 1, -1, -1, 1};
	static int[] DY = {1, -1, 0, 0, 1, -1, 1, -1};
	
	static int[][] 오목판;
	static PriorityQueue<Coordinate> pq = new PriorityQueue<>();
	
	/**
	 * Main 함수 
	 * 모든 오목판을 돌며 돌이 있다면 search() 함수를 호출해 
	 * 조건에 따라 결과를 출력한다.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
	//	System.setIn(new FileInputStream("Test5.txt"));
		//여기에 코드를 작성하세요.
		
		오목판 = new int[19][19];
		Scanner s = new Scanner(System.in);
		StringTokenizer st;
		for (int i = 0; i < 19; i++) {
			String input = s.nextLine();
			st = new StringTokenizer(input);
			for (int j = 0; j < 19; j++) {
				오목판[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		s.close();
		
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (오목판[i][j] == PLAYERONE) {
					Coordinate c = search(i, j, PLAYERONE);
					if (c != null) {
						System.out.println(1);
						System.out.println((c.y + 1) + " " + (c.x + 1));
						System.exit(0);
					}
				} else if (오목판[i][j] == PLAYERTWO) {
					Coordinate c = search(i, j, PLAYERTWO);
					if (c != null) {
						System.out.println(2);
						System.out.println((c.y + 1) + " " + (c.x + 1));
						System.exit(0);
					}
				}
					
			}
		}
		
		System.out.println(0);
	}

	/**
	 * 돌을 기준으로 8방향 탐색을 시도하는 메서드
	 * 
	 * @param inity 첫 돌의 y 위치 
	 * @param initx 첫 돌의 x 위치 
	 * @param num 흑돌인지 백돌인지 비교 
	 * @return 정답이라면 가장 왼쪽 상단의 돌의 위치 정답이 아니라면 null
	 */
	public static Coordinate search(int inity, int initx, int num) {
		if (inity == 2 && initx == 5) {
		//	System.out.println("s");
		}
		Coordinate initCoordinate = new Coordinate(inity, initx);
		for (int i = 0; i < 7; i += 2) {
			int depth = 1;
			pq.clear();
			pq.add(initCoordinate);
			
			int dy = inity + DY[i];		int dx = initx + DX[i];
			
			if (dy <= 18 && dx <= 18 && dy >= 0 && dx >= 0 && 오목판[dy][dx] == num) {
				pq.add(new Coordinate(dy, dx));
				depth++;
				depth = serchOfDirect(DY[i], DX[i], new Coordinate(dy, dx), num, depth);
			}

			dy = inity + DY[i + 1];		dx = initx + DX[i + 1];
			
			if (dy <= 18 && dx <= 18 && dy >= 0 && dx >= 0 && 오목판[dy][dx] == num) {
				pq.add(new Coordinate(dy, dx));
				depth++;
				depth = serchOfDirect(DY[i + 1], DX[i + 1], new Coordinate(dy, dx), num, depth);
			}

			
			if (depth == WINSCORE) {
				return pq.poll();
			} 
		}

		return null;
	}
	
	/**
	 * 돌과 방향을 기준으로 이어진 돌의 개수를 세는 메서드  
	 * @param yDirect y 방향 
	 * @param xDircet x 방향 
	 * @param num 돌의 종류 
	 * @param coordinate 이전 돌의 좌표 
	 * @param depth 현재 맞은 돌의 개수
	 * @return 총 맞은 돌의 개수를 return 한다.
	 */
	public static int serchOfDirect( int yDirect, int xDircet, Coordinate coordinate, int num, int depth ) {	
		
		int dy = coordinate.y + yDirect;	int dx = coordinate.x + xDircet;
		
		if (dy >= 19 || dx >= 19 || dy <= -1 || dx <= -1 || 오목판[dy][dx] != num)
			return depth;
		
		
		Coordinate newCoordinate = new Coordinate(dy, dx);
		pq.add(newCoordinate);
		depth++;
		
		return serchOfDirect(yDirect, xDircet, newCoordinate, num, depth);
		
	}
	
	
}

/**
 * 좌표를 담고잇는 ADT
 * 
 * Comparable 기준은 왼쪽 상단의 돌을 우선적으로 오름차순 정렬한다.
 */
class Coordinate implements Comparable<Coordinate>{
	int y,x;
	
	Coordinate(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public int compareTo(Coordinate o) {
		
		if (this.x == o.x) {
			return this.y - o.y;
		} else {
			return this.x - o.x;
		}
	}
	
	
}

