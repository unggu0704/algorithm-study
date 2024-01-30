import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 데일리 실습
 * 15649 N과 M(1)
 * 
 * @author SSAFY
 *
 */
public class Main {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] numbers = new int[N];
		int[] combine = new int[M]; // 조합을 담을 배열
		boolean[] visited = new boolean[N];
		
		//초기 N까지의 값을 배열에 할당 
		for (int i = 0; i < N; i++) {
			numbers[i] = i + 1;
		}
		
		backTracking(N, M, 0, numbers, combine, visited, 0);
		System.out.println(sb.toString());
	}
	
	/**
	 * 백트래킹으로 조합을 만들어내는 메서드
	 * @param N numbers의 갯수 
	 * @param M combine의 갯수 
	 * @param index
	 * @param numbers
	 * @param combine
	 * @param depth
	 */
	public static void backTracking(int N, int M, int index, int[] numbers, int[] combine, boolean[] visited, int depth) {
		if (depth == M) {
			for (int i : combine) 
				sb.append(i + " ");
			
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) 
				continue;
			
			combine[depth] = numbers[i];
			
			depth++;
			visited[i] = true;
			backTracking(N, M, i + 1, numbers, combine, visited, depth);
			visited[i] = false;
			depth--;
		}
		
	}
	

}

