import java.io.*;
import java.util.*;

public class Main {
	static int N; // 봉우리 개수
	static Pair[] lst;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		List<Pair> lst = new ArrayList<>();
		
		int startX = Integer.MAX_VALUE;
		int startY = Integer.MAX_VALUE;
		int startIndex = 0;
		// 꼭짓점 입력 받기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			lst.add(new Pair(y, x));
			//가장 왼쪽에 있는 꼭지점 찾기 
			//y를 마이너스로 
			if (x < startX && y < 0) {
				startX = x;
				startY = y;
				startIndex = i;
			}
			
		}
		startIndex++;
		boolean isPeak = true;
		List<Peak> peakList = new ArrayList<Peak>();
		int number = 0;
		int prevX = startX;
		
		for (int i = 0; i < N - 1; i++) {
			int index = (startIndex + i) % N;
			
			Pair pair = lst.get(index);
			
			//이전에 바닥이였는데 위로 올라간 경우 봉우리가 시작되었다.
			if (isPeak && pair.y > 0) {
				prevX = pair.x;
				isPeak = false;
			}
			
			//다시 바닥으로 들어가면 하나의 봉우리를 생성
			if (!isPeak && pair.y < 0) {
				if (prevX < pair.x) {
					peakList.add(new Peak(number, prevX, true));
					peakList.add(new Peak(number, pair.x, false));
				} else {
					peakList.add(new Peak(number, pair.x, true));
					peakList.add(new Peak(number, prevX, false));
				}
				isPeak = true;
				number++;
			}
			
		}
		

		Collections.sort(peakList);
		int upPeak = 0; // 다른 봉우리를 포함되어지지 않은 봉우리 
		int containPeak = 0; //다른 봉우리를 포함하지 않는 봉우리 
		Stack<Peak> stack = new Stack<>();
		Stack<Integer> peakStack = new Stack<>();
		int peakNumber = 0;
		
		for (int i = 0; i < peakList.size(); i++) {
			boolean check = peakList.get(i).isStrart;

			// 만약 봉우리가 시작하는 변이라면 스택에 담는다
			if (check == true) {
				peakStack.add(peakNumber);
			}

			// 만약 봉우리가 끝나는 변이라면
			else {
				// stack의 가장 윗부분을 빼주고
				int left = peakStack.pop();

				// 만약 스택이 비워져 있다면 얘는 noCover이다
				if (peakStack.isEmpty()) {
					upPeak++;
				}

				// 만약 스택이 차있고 왼쪽 괄호 바로 다음에 오른쪽 괄호가 오는 경우는 NoContain (( )경우
				// 포함은 되어있지만 포함하지는 않는다
				if (left == peakNumber) {
					containPeak++;
				}

				// 아닌 경우엔 포함되어 있으면서 포함도 한다
				// 시작하는 봉우리	를 만날 때 마다 봉우리 갯수를 늘려준다
				peakNumber++;
			}

		}		
		System.out.println(upPeak + " " + containPeak);
	}

}

class Peak implements Comparable<Peak> {
	int peakNumber;
	int startY;
	int startX;
	boolean isStrart;
	
	public Peak(int n, int sx, boolean isStart) {
		this.peakNumber = n;
		this.startX = sx;
		this.isStrart = isStart;
	}

	@Override
	public int compareTo(Peak o) {
		return this.startX - o.startX;
	}
	
	@Override
	public boolean equals(Object obj) {
		Peak p = (Peak) obj;
		return peakNumber == p.peakNumber;
	}

	@Override
	public String toString() {
		return "Peak [start=" + peakNumber + "startX=" + startX + ", isStrart=" + isStrart
				+ "]";
	}




	
}

class Pair implements Comparable<Pair>{
	int y, x;
	
	public Pair(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public int compareTo(Pair o) {
		if (this.x == o.x) {
			return this.y - o.y;
		}
		return this.x - o.x;
	}
}