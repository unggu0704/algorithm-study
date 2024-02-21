import java.io.*;
import java.util.*;

/**
 * Main_2636_치즈_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static int K;
	static StringBuilder sb = new StringBuilder();

	static int[] DY = {-1, 0, 1, 0};
	static int[] DX = {0, 1, 0, -1};
	static int[] parents;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Human> humanList = new LinkedList<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			humanList.add(new Human(i));
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			Human h1 = humanList.get(Integer.parseInt(st.nextToken()));
			Human h2 = humanList.get(Integer.parseInt(st.nextToken()));
			
			h1.friends.add(h2);		h2.friends.add(h1);
		}
		
		for (int i = 0; i < N; i++)
			DFS(humanList.get(i), 0);
		
		System.out.println(0);
	}
	
	public static void DFS(Human human, int depth) {
		if (depth == 4) {
			System.out.println(1);
			System.exit(0);
		}
		
		human.visited = true;
		List<Human> friends = human.friends;
		
		for (Human friendOfHuman : friends) {
			if (friendOfHuman.visited) 
				continue;
			
			friendOfHuman.visited = true;	
			DFS(friendOfHuman, depth + 1);
			friendOfHuman.visited = false;	
		}
		human.visited = false;
	}
}

class Human {
	int num;
	boolean visited;
	List<Human> friends = new ArrayList<Human>();
	
	Human(int num) {
		this.num = num;
	}
}