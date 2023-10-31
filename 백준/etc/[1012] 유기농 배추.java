import java.util.*;

class field{
	int x, y;
	field(int x, int y){
		this.x = x;
		this.y = y;
	}
}


class makeOrgarnic{
	int farm[][]; boolean farm_visited[][];
	int cabbage, farm_x, farm_y, worm_count = 0;
	Queue<int []> queue = new LinkedList<>();
	
	makeOrgarnic(int f[][],int c, int tmp_X, int tmp_Y){
		this.farm = f;
		this.cabbage = c;
		this.farm_x = tmp_X; this.farm_y = tmp_Y;
		farm_visited = new boolean[farm_x][farm_y];
		spread_Worm();
	}
	
	void spread_Worm() {
		int location[] = new int[2];
		for(int i = 0; i<farm_x; i++) {
			for(int j =0; j<farm_y; j++) {
				if(farm[i][j] == 1 && !farm_visited[i][j]) {
					location[0] = i; location[1] = j;
					queue.offer(location);
					farm_visited[i][j] = true;
					while(!queue.isEmpty()) {
						
						bfs(queue.poll());
					}
					worm_count++;
				}
			}
		}
		System.out.println(worm_count);
	}
	
	void bfs(int loc[]) {
		int x = loc[0]; int y = loc[1];
		int tmp1[], tmp2[], tmp3[], tmp4[];
		if(x != 0 && !farm_visited[x-1][y] && farm[x-1][y] == 1) {
			tmp1 = new int[2];
			tmp1[0] = x-1; tmp1[1] = y;
			queue.offer(tmp1);
			farm_visited[x-1][y] = true;
		}
		if(y != 0 && !farm_visited[x][y-1] && farm[x][y-1] == 1) {
			farm_visited[x][y-1] = true;
			tmp2 = new int[2];
			tmp2[0] = x; tmp2[1] = y-1;
			queue.offer(tmp2);
		}
		if(x != farm_x-1 && !farm_visited[x+1][y] && farm[x+1][y] == 1) {
			tmp3 = new int[2];
			tmp3[0] = x+1; tmp3[1] = y;
			queue.offer(tmp3);
			farm_visited[x+1][y] = true;
		}
		if(y != farm_y-1 && !farm_visited[x][y+1] && farm[x][y+1] == 1) {
			tmp4 = new int[2];
			tmp4[0] = x; tmp4[1] = y+1;
			queue.offer(tmp4);
			farm_visited[x][y+1] = true;
		}
	}
	
	void setting_farm() {
		for(int i = 0; i<farm_x; i++) {
			for(int j =0; j<farm_y; j++) {
				farm_visited[i][j] = false;
			}
		}
			
	}
}

public class Main {
	static Scanner s = new Scanner(System.in);
	static int farm_count, x, y, cabbage;
	static int farm[][];
	public static void main(String args[]) {
		long beforeTime = System.currentTimeMillis();
		
		farm_count = s.nextInt();
		for(int i = 0; i<farm_count; i++) {
			x= s.nextInt(); y = s.nextInt(); cabbage = s.nextInt();
			farm = new int[x][y];
			init_farm();
			for(int j = 0; j<cabbage; j++) {
				farm[s.nextInt()][s.nextInt()] = 1;
			}
			makeOrgarnic o = new makeOrgarnic(farm,cabbage,x,y);
		}
		
		//time(beforeTime);
	}
	
	public static void init_farm() {
		for(int i = 0; i<x; i++) {
			for(int j =0; j<y; j++) {
				farm[i][j] = 0;
			}
		}
	}
	public static void time(long beforeTime) {
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("\n" + secDiffTime + "ms");

	}
}	

