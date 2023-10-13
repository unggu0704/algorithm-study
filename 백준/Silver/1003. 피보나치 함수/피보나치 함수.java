import java.util.*;

class fibonachi{
	Scanner s = new Scanner(System.in);
	int count = 0, imax = 0; // 총 구해야할 갯수, 구해야할 값 중에 최댓 
	int arr[];  //arr: 구해야할 숫자들, fib: 피보나치 값들을 가지고 있는 배
	ArrayList<Integer>[] fib;
	fibonachi(){
		count = s.nextInt();// 총 갯수 입력 
		arr = new int[count];
		for(int i =0; i < count; i++) {
			arr[i] = s.nextInt();
			imax = Math.max(arr[i],imax);
		}
		
		fib = new ArrayList[imax+1];
		if(imax >= 0) {
			fib[0] = new ArrayList<>(); 
			fib[0].add(1); fib[0].add(0);
		}
		if(imax >= 1) {
			fib[1] = new ArrayList<>(); 
			fib[1].add(0); fib[1].add(1);
		}
			
		make_fib();
		
		
		for(int j = 0; j<count; j++) {
			is_print(arr[j]);
		}
	}
	
	public void make_fib() {
		int first_val = 0, second_val;
		for(int i =2; i<imax+1; i++) {
			fib[i] = new ArrayList<>();
			first_val = fib[i-1].get(0) + fib[i-2].get(0);
			second_val = fib[i-1].get(1) + fib[i-2].get(1);
			fib[i].add(first_val);	fib[i].add(second_val);
		
		}
	}
	
	public void is_print(int t) {
		System.out.println(fib[t].get(0) + " " + fib[t].get(1));
	}
	
}
	
public class Main {
	public static void main(String args[]) {
		long beforeTime = System.currentTimeMillis();
		fibonachi f = new fibonachi();
	//	time(beforeTime);
	}
	
	public static void time(long beforeTime) {
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("\n" + secDiffTime + "ms");

	}
}	

