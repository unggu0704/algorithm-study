import java.io.*;
import java.util.*;

/**
 * Main_16235_나무재테크_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static int K;
	static int answer = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {
		long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //수빈이의 위치 
		K = Integer.parseInt(st.nextToken()); //동생의 위치 

		Queue<Integer> q = new ArrayDeque<Integer>(); //BFS 용 큐 
		boolean[][] possible = new boolean[2][500010];
		int[] move = new int[3];
		
		if (N == K) {
			System.out.println(0);
			System.exit(0);
		}
		
		q.add(N);
		possible[0][N] = true;
		
		loop:
		while (true) {
			answer++; //시간이 흐른다.
			int mod = answer % 2; //짝수 초인가? 홀수 초인가?
			
			//동생이 이동한다.
			K += answer;
			
			if (q.isEmpty() || K >= 500001) {
				answer = -1;
				break;
			}
			
			//수빈이가 이동한다.
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int poll = q.poll();
				
				move[0] = poll - 1;
				move[1] = poll + 1;
				move[2] = poll * 2;
				
				for (int j = 0; j < 3; j++) {
					if (move[j] <= -1 || move[j] >= 500001 || possible[mod][move[j]])
						continue;
					
				//	System.out.println(move[j]  + "로 이동 동생 위치 : " + K);

					possible[mod][move[j]] = true;
					q.offer(move[j]);
				}
			}
			
			if (possible[mod][K])
				break;
		}
		
		System.out.println(answer);
		
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = afterTime - beforeTime; //두 시간에 차 계산
	//	System.out.println("시간차이(m) : "+secDiffTime);
	}

}