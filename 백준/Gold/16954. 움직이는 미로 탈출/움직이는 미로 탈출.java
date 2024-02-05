import java.io.*;
import java.util.*;

/**
 * 1일 N알골 BOJ_2212_센서
 * 
 * 입력 N = 10.000 K = 1.000
 * 
 * @author 김규형
 *
 */
class Node {
	int y; int x;
	boolean wall;
	
	Node(int y, int x, boolean wall) {
		this.y = y;
		this.x = x;
		this.wall = wall;
	}
}
public class Main {

	static int N;
	static int M;
	static Node[][] map;
	static int[] DX = {1, 0, -1, 0, 1, 1, -1, -1, 0};
	static int[] DY = {0, 1, 0, -1, 1, -1, 1, -1, 0};
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new Node[8][8];
		
		for (int i = 0; i < 8; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {
				if (input.charAt(j) == '#') {
					map[i][j] = new Node(i,j, true);
				} else {
					map[i][j] = new Node(i,j, false);
				}
			}
		}
		//입력 종료 
		
		Queue<Node> queue = new LinkedList<>();

		queue.add(map[7][0]); //캐릭터의 처음 시작 위치 
		
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			
			for (int i = 0; i < queueSize; i++) {
				Node pollNode = queue.poll();
				//System.out.println(pollNode.y + ", " + pollNode.x + "에 있음 ");
				if (map[pollNode.y][pollNode.x].wall) //벽이 내려왔으면 사망
					continue;
				
				for (int j = 0; j < 9; j++) {
					int y = DY[j] + pollNode.y;		int x = DX[j] + pollNode.x;
					
					if (y >= 8 || x>= 8 | x <= -1 || y <= -1 || map[y][x].wall)
						continue;
					
					if (y == 0 && x == 7) {
						System.out.println(1);
						System.exit(0);
					}
					
					queue.add(map[y][x]);
				}
			}
			
			moveWall();
		//	print();
		}

		System.out.println(0);
	}
	
	public static void print() {
		System.out.println("====================");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(map[i][j].wall + " ");
			}
			System.out.println();
		}
	}
	
	public static void moveWall() {
		for (int i = 7; i >= 1; i--) {
			for (int j = 0; j < 7; j++) {
				map[i][j].wall = map[i -1][j].wall;
			}
		} //벽이 내려간다.
		
		for (int i = 0; i < 8; i++) {
			map[0][i].wall = false;
		} //맨위층은 벽이 아닌걸로 계속 생산
	}
	
}