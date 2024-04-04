import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
/**
 * SWEA_5643_키순서_김규형
 *   메모리       시간
 * 

 * 
 */
class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
	static int[] DX = {-1, 0, 1, 0};
	static int[] DY = {0, 1, 0, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
        // 테스트 케이스 수를 받고
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
             sb.append("#" + tc +" ");
             solution();
        }

        System.out.println(sb);
    }
	
	public static void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		Node[][] map = new Node[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = new Node(i, j, str.charAt(j) - '0');
			}
		}
		//입력 종료 
		
		Queue<Node> q = new ArrayDeque<>();
	
		map[N - 1][N - 1].value = map[N - 1][N - 1].info;
		q.offer(map[N - 1][N - 1]);
		
		while (!q.isEmpty()) {
			
			int size = q.size();
			
			for (int t = 0; t < size; t++) {
				Node node = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int dy = node.y + DY[i];	int dx = node.x + DX[i];
					
					if (dy >= N || dx >= N || dy <= -1 || dx <= -1)
						continue;
					
					
					if (map[dy][dx].value > map[dy][dx].info + node.value) {
						map[dy][dx].value = map[dy][dx].info + node.value;
						q.add(map[dy][dx]);
					}
				}
			}
		}
		
		sb.append(map[0][0].value).append("\n");
	}

}

class Node {
	int y, x, value, info;
	boolean visited;
	
	Node (int y, int x, int info) {
		this.y = y;
		this.x = x;
		this.info = info;
		this.value = Integer.MAX_VALUE;
	}
}