import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * SWEA_1767_프로세서연결하기_김규형
 * @author 김규형
 *
 */
public class Solution {
	
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	
	static int N;
	static int[][] map;
	static ArrayList<int[]> coreList;
	static int coreCount;
	static int wireCount;
	
	public static void bfs(int num, int cc, int wc) {
		
		if(num == coreList.size()) { // 모든 코어를 다 검사해본 경우
			
			if(coreCount == cc) { // 연결할 수 있는 코어의 수가 같다면
				wireCount = Math.min(wireCount, wc); // 최소 전선 길이 저장
			}
			else if(coreCount < cc) { // 연결할 수 있는 코어 수가 더 많다면
				coreCount = cc;
				wireCount = wc;
			}
			
			return;
		}
		
		// 현재 검사할 코어의 좌표
		int x = coreList.get(num)[0];
		int y = coreList.get(num)[1];
		
		// 현재 코어에 사방 중 한 방향으로 전선을 놓는 경우
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 현재 방향으로 전선을 놓을 수 있는지 검사
			int count = 0;
			
			while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if(map[nx][ny] == 0) {
					map[nx][ny] = 1;
					nx += dx[i];
					ny += dy[i];
					
					count++; //count 1 증가
				}
				else 
					break;
			}
			
			if(nx != -1 && nx != N && ny != -1 && ny != N) { // 전원을 연결하지 못하면
				nx -= dx[i];
				ny -= dy[i];
				
				while(nx != x || ny != y) { // 연결했던 전선들 회수
					map[nx][ny] = 0;
					nx -= dx[i];
					ny -= dy[i];
				}
				
				if(coreCount > coreList.size() - (num + 1) + cc) 
					continue;
				
				bfs(num + 1, cc, wc); // 전원을 연결하지 못한 채로 다음 코어로
			}
			else { // 전원을 연결했다면
				bfs(num + 1, cc + 1, wc + count); // 다음 코어로
				
				// 이후 연결했던 전선 회수
				nx -= dx[i];
				ny -= dy[i];
				while(nx != x || ny != y) { // 연결했던 전선들 회수
					map[nx][ny] = 0;
					nx -= dx[i];
					ny -= dy[i];
				}
			}
		}
		
		// 현재 코어에 전선을 놓지 않는 경우
		if(cc != coreList.size()) 
			bfs(num + 1, cc, wc);
	}

	//메인 로직
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			coreList = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j]= Integer.parseInt(st.nextToken());
					
					if(map[i][j] == 1) { // core라면
						// 이미 전원에 연결된 것은 넘어감
						if(i == 0 || i== N - 1 || j == 0 || j == N - 1) 
							continue;
						coreList.add(new int[] {i, j});
					}
				}
			}
			
			coreCount = 0;
			wireCount = 0;
			
			bfs(0, 0, 0);
			
			sb.append("#" + tc + " " + wireCount + "\n");
		}
		
		System.out.println(sb.toString());
	}

}