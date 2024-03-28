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
		int startIndex = 0;
		// 꼭짓점 입력 받기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			lst.add(new Pair(y, x));
			//가장 왼쪽에 있는 꼭지점 찾기 
			if (x < startX) {
				startX = x;
				startIndex = i;
			}
		}

		startIndex++;
		boolean isPeak = true;
		List<Peak> peakList = new ArrayList<Peak>();
		int number = 0;
		
		for (int i = 0; i < N - 1; i++) {
			int index = (startIndex + i) % N;
			
			Pair pair = lst.get(index);
			
			//이전에 바닥이였는데 위로 올라간 경우 봉우리가 시작되었다.
			if (isPeak && pair.y > 0) {
				peakList.add(new Peak(number, pair.y, pair.x));
				isPeak = false;
			}
			
			//다시 바닥으로 들어가면 하나의 봉우리를 생성
			if (!isPeak && pair.y < 0) {
				peakList.add(new Peak(number++, pair.y, pair.x));
				isPeak = true;
			}
			
		}
		
		Collections.sort(peakList);
		int upPeak = 0; // 다른 봉우리를 포함되어지지 않은 봉우리 
		int containPeak = 0; //다른 봉우리를 포함하지 않는 봉우리 
		Stack<Peak> stack = new Stack<>();
		
		int peakNumber = 0;
		for (Peak peak : peakList) {
			if (stack.contains(peak)) {
				Peak removePeak = stack.pop();
				if (stack.isEmpty()) 
					upPeak++;
				
				if (peakNumber == removePeak.peakNumber) 
					containPeak++;
				
			} else {
				stack.add(peak);
				peakNumber = peak.peakNumber;
			}
		}
		
		System.out.println(upPeak + " " + containPeak);
	}

}

class Peak implements Comparable<Peak> {
	int peakNumber;
	int startY;
	int startX;
	
	public Peak(int n, int sy, int sx) {
		this.peakNumber = n;
		this.startY = sy;
		this.startX = sx;
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
		return "Peak [peakNumber=" + peakNumber + ", startY=" + startY + ", startX=" + startX + "]";
	}


	
}

class Pair {
	int y, x;
	
	public Pair(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "Pair [y=" + y + ", x=" + x + "]";
	}
	
	
}