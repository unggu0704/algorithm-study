import java.util.*;

class run{
	Scanner s = new Scanner(System.in);
	ArrayList<Integer> arr = new ArrayList<>();
	double count, sum = 0;
	double imax;
	run(){
		count = s.nextInt();
		for(int i =0; i<count; i++) {
			arr.add(s.nextInt());
			imax = Math.max(arr.get(i),imax);
		}
	
		for(int j =0; j<count; j++) {
			//System.out.println(arr.get(j) + " " + sum);
			sum = (arr.get(j)/imax)*100 + sum;
	
		}
		System.out.println(sum/count);
	}
}

	

public class Main {
	public static void main(String args[]) {
		long beforeTime = System.currentTimeMillis();
		run r = new run();
		//time(beforeTime);
	}
	
	public static void time(long beforeTime) {
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("\n" + secDiffTime + "ms");

	}
}	

