import java.util.*;

class run{
	Collection arr = new ArrayList<Integer>();
	Collection del_arr = new ArrayList<Integer>();
	run(){
		for(int i = 1; i< 10001; i++) {
			arr.add(i);
		}
		for(int i = 1; i< 10001; i++) {
			if(!del_arr.contains(i)) {
			self_number(i);
			}
		}
		ArrayList<Integer> arr1 = new ArrayList<Integer>(arr);
		ArrayList del_arr1 = new ArrayList<Integer>(del_arr);
		arr1.removeAll(del_arr1);
		for(int j = 0; j<arr1.size(); j++) {
			System.out.println(arr1.get(j));
		}


	}

	int self_number(int n){
		if(n > 10000) {
			return 0 ;
		}
		int n_origin = 0, n_ten = 0 , n_hundred = 0,n_thou = 0, max_n = 0;
		if( n >= 1000 && n <=10000) {
			n_thou = n /1000;
			n_hundred = (n - (n_thou *1000)) /100;
			n_ten = (n - (n_hundred *100)- (n_thou *1000) )/10;
			n_origin = n - (n_thou)*1000 - (n_hundred)*100 - (n_ten) * 10;
		}
		if( n >= 100 && n <1000) {
			n_hundred = n / 100;
			n_ten = (n - (n_hundred *100) )/10;
			n_origin = n - (n_hundred)*100 - (n_ten) * 10;
		}
		if( n >= 10 && n < 100) {
			n_ten = n /10;
			n_origin = n - (n_ten) * 10;
		}
		if( n < 10 && n >0) {
			n_origin = n;
		}
		//System.out.println("현재 숫자: " + n + "  1의자리 " + n_origin + " 10의 자리 " +n_ten + " 100의 자리 " + n_hundred + " 1000의 자리 " + n_thou);
		max_n = n_origin + n_ten + n_hundred + n_thou + n;
		if(max_n > 10000) { return 0 ; }
		
		if(!del_arr.contains(max_n)) {
			del_arr.add(max_n);
		}
		
		
		self_number(max_n);
		return 0;
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

