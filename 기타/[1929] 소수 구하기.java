import java.util.*;


public class Main {

	public static void main(String[] args) {

		primeNumber();
		
	}
	
	static void primeNumber() {
		Scanner s = new Scanner(System.in);
		int input_number1 = s.nextInt();
		int input_number2 = s.nextInt();
		boolean prime_flag;
		ArrayList<Integer> prime_list = new ArrayList<>();
	
		for(int check_number=input_number1; check_number<= input_number2; check_number++) {
			prime_flag = true;
			if(check_number == 1) { prime_flag = false; }
			if(check_number == 2 ) {
				prime_flag = false;
				prime_list.add(2);
			}
			
			for(int i =2; i<= Math.sqrt(check_number); i++) {
				if(check_number % i == 0) {
				//	System.out.println(check_number +" 은 " + i +"로 나누어 떨어짐 ");
					prime_flag = false;
					break;
				}
			}
			if(prime_flag) {
				prime_list.add(check_number);
			}
		}
		
		for(Integer a : prime_list) {
			System.out.println(a);
		}
			
	}

}
	

