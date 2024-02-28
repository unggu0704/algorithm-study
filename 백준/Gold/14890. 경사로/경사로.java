import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_1753_최소경로_김규형
 * @author 김규형
 */
public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N, M, L;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		int ans = 0;
		Node[][] map = new Node[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = new Node(i,j,Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (isRoad(map[i]))
				ans++;
		}
		
		for (int i = 0; i < N; i++) {
			Node[] vertex = new Node[N];
			
			for (int j = 0; j < N; j++) {
				vertex[j] = map[j][i]; 
			}
			
			if (isRoad(vertex))
				ans++;
		}
		
		System.out.println(ans);
	}
	
	public static boolean isRoad(Node[] load) {
		int l = 1; //같은 높이의 땅
		int h = load[0].h; //현재 높이 
		boolean[] stair = new boolean[N];
		
		for (int i = 1; i < N; i++) {
			if (h + 1 < load[i].h || h - 1 > load[i].h)  //높이가 2이상 차이 난다면 이건 불가능하다.
				return false;
			
			if (h + 1 == load[i].h) { // 높이가 하나 차이면?
				if (l < L || stair[i]) //널판지 놓을 공간이 충분하지 않다면? || 이미 널판지가 놓여져 있다면?
					return false;
				
				for (int j = i - L; j < i; j++) {
					stair[j] = true;
				}
				
				l = 0; // 널판지 넣기 위한 공간
				h++; //현재 높이를 올린다.
			} 
			
			if (h - 1 == load[i].h) { //높이가 하나 낮다면?
				l = 0;
				for (int j = i; j < N; j++) { // 이 앞의 낮은 높이의 개수를 센다.
					if (load[j].h != load[i].h || stair[j])
						break;
					l++;
				}
				
				if (l < L)
					return false;
				
				for (int j = i; j < i + L; j++) { //널판지를 놓는다.
					stair[j] = true;
				}
				
				l = 0;
				h--;
			}
						
			if (h == load[i].h && !stair[i]) //높이가 갖고 경사판도 없다면
				l++;
		}
		
		return true;
	}
}

class Node {
	int y;
	int x;
	int h;
	boolean stair;
	
	Node(int y, int x, int h) {
		this.y =y;
		this.x = x;
		this.h = h;
		this.stair = false;
	}
}