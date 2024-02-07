import java.io.*;
import java.util.*;

/**
 * Main_16935_배열돌리기3_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static long answer = 0;

	static int[] DX = {0, 1, 0, -1};
	static int[] DY = {1, 0, -1, 0};
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		List<Pool> poolList = new ArrayList<Pool>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			poolList.add(new Pool(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		//입력 종료 
		
		Collections.sort(poolList);
		
		int endPivot = 0; //널판지의 끝
		long blank = 0;
		
		int start = 0;
		int end = 0;
		for (int i = 0; i < N; i++) {
			Pool pool = poolList.get(i); //다음 웅덩이를 찾는다. ;
			
			if (pool.start < endPivot) { //널판지의 시작지점에 이미 널판지가 있다면?
				start = endPivot;
			} else {
				start = pool.start; //널판지가 없다면 여기서부터 출발 한다.
			}
			
			if (pool.end <= endPivot) { // 끝포인트까지 널판지가 이미 있다면?
				continue;
			} else {
				end = pool.end;
			}
			
			
			int poolSize = end - start;
			
			while (poolSize > 0) {
				poolSize -= L;
				blank +=1; //널판지를 하나씩 놓는다.
			}
			
			endPivot = end + (-1 * poolSize); //현재 널판지의 길이는 end에 초과된 널판지의 길이 
		}
		

		System.out.println(blank);
	}
	
}

class Pool implements Comparable<Pool>{
	int start;
	int end;
	
	Pool(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Pool o) {
		if (this.start == o.start) 
			return this.end - o.end;
		
		return this.start - o.start;
	}
}