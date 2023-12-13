import java.util.*;


public class Main {

	public static void main(String[] args) {

		Stock s1 = new Stock();
		
	}

}

class Stock {
	Scanner s =new Scanner(System.in);
	ArrayList<Integer> listStock = new ArrayList<>();
	ArrayList<Long> Answer = new ArrayList<>();
	Stock(){
		int test_count, input;
		test_count = s.nextInt();
		for(int i = 0; i< test_count; i++) {
			input = s.nextInt();
			listStock.clear();
			for(int j = 0; j <input; j++) {
				listStock.add(s.nextInt());
			}
			execute_Stock();
			
		}
		printAnswer();
	}
	
	void execute_Stock() {
		int max_value = 0;
		long sum = 0;
		for(int i = listStock.size()-1; i >= 0; i--) {
			if(listStock.get(i) > max_value) {
		//		System.out.println(listStock.get(i) + " 의 값은 현재 max_value보다 큼 변경 실시 ");
				max_value = listStock.get(i);
			}
			else {
				sum += (max_value - listStock.get(i));
	//			System.out.println("주식 차익 구매 현재 차익 sum: " + sum);
			}
		}
		Answer.add(sum);
	}
	
	void printAnswer() {
		for(Long e: Answer) {
			System.out.println(e);
		}
	}
}
