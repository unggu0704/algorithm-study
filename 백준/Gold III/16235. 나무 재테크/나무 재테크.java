import java.io.*;
import java.util.*;

/**
 * Main_3109_빵집_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static int K;
	static long answer = 0;
	static StringBuilder sb = new StringBuilder();
	
	static int[][] S2D2;
	static int[][] treeInput; //심을 나무 갯수 
	static Field[][] map;
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // NxN 크기의 땅 
		M = Integer.parseInt(st.nextToken()); // M개의 나무 갯수 
		K = Integer.parseInt(st.nextToken()); // K년 지난 후의 결과 
		
		S2D2 = new int[N][N]; // 겨울에 뿌릴 양분
		treeInput = new int[M][3]; //심을 나무 갯수 
		map = new Field[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S2D2[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			treeInput[i][0] = Integer.parseInt(st.nextToken()) - 1;
			treeInput[i][1] = Integer.parseInt(st.nextToken()) - 1;
			treeInput[i][2] = Integer.parseInt(st.nextToken());
		}
		//입력 종료 
		
		//초기 밭의 영양분을 세팅 한다.
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Field(5); //초기 밭의 영양분은 5로 고정된다.
			}
		}
		
		//input으로 주어진 나무를 심는다.
		for (int i = 0; i < M; i++) {
			map[treeInput[i][0]][treeInput[i][1]].treeList.add(new Tree(treeInput[i][2]));
		}
		
		//K년간 계절이 흐른다...
		for (int i = 0; i < K; i++) {
			Spring();
			//print();
			Summer();
		//	print();
			Auttum();
			Winter();
		//	print();
		}
		
		for (Field[] fields : map) {
			for (Field field : fields) {
				for (Tree tree : field.treeList) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static void Spring() {
		
		for (Field[] fields : map) {
			for (Field field : fields) {
				Collections.sort(field.treeList); //트리의 나이순으로 정렬 
				for (Tree tree : field.treeList) {
					int age = tree.age; //해당 트리의 나이를 가져온다.
					
					if (field.nourishment < age) {//땅의 영양분이 부족하다면...
						tree.die = true; //나무가 죽는다.
				//		System.out.println("나무가 죽음");
					} else {
						field.nourishment -= age; //나이만큼 영양분을 먹는다.
						tree.age++; //나이를 먹는다.
					}
				}
			}
		}
	}
	
	public static void Summer() {
		
		for (Field[] fields : map) {
			for (Field field : fields) {
				Iterator<Tree> iter = field.treeList.iterator();
				while (iter.hasNext()) {
					Tree tree = iter.next();
					if (tree.die) { //죽은 나무가 있는지?
						int nourishment = tree.age / 2;
						field.nourishment += nourishment; //죽은 나무의 나이만큼 영양분이 된다.
						iter.remove();
					}
				}
			}
		}
	}
	
	public static void Auttum() {
		int[] DY = {0, 1, 0, -1, 1, 1, -1, -1};
		int[] DX = {1, 0, -1, 0, -1, 1, 1, -1};
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (Tree tree : map[i][j].treeList) {
					if (tree.age % 5 == 0) {
						for (int d = 0; d < 8; d++) {
							int dy = i + DY[d];		int dx = j + DX[d];
							
							if (dy <= -1 || dx <= -1 || dy >= N || dx >= N) 
								continue;
							
							map[dy][dx].treeList.add(new Tree(1));
						}
					}
				}
			}
		}
	}
	
	public static void Winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j].nourishment += S2D2[i][j]; //S2D2가 각 밭에 양분을 추가한다.
			}
		}
	}
	
	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j].nourishment + " ");
			}
			System.out.println();
		}
		System.out.println("=========================");
	}
}

class Tree implements Comparable<Tree>{
	int age;
	boolean die;
	
	Tree (int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Tree o) {
		return this.age - o.age;
	}
	
}

class Field {
	List<Tree> treeList = new ArrayList<Tree>(); //해당 필드에 있는 트리들
	int nourishment;
	
	Field(int nourishment) {
		this.nourishment = nourishment;
	}
}