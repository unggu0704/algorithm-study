import java.util.*;

class battle{
	Scanner s = new Scanner(System.in);
	int human_count , max_power= 0; 
	ArrayList<Integer> arr = new ArrayList<>();
	
	battle(){
		int input = 0;
		human_count = s.nextInt();
		for(int i =0; i<human_count; i++) {
			input = s.nextInt();
			max_power = Math.max(max_power, input);
			arr.add(input);
		}
		calculate_Percentage();
	}
	
	void calculate_Percentage() {
		int red_team = 0, blue_team = 0;
		int index = 0, end_pointer = human_count - 1;
		while(true) {
			index = arr.get(red_team);
			if(index == max_power) { break;	}
			
			red_team++;
		}
		
		while(true) {
			index = arr.get(end_pointer);
			if(index == max_power) { break;	}
			end_pointer--;
			blue_team++;
		}
	//	System.out.println("R: " + red_team + " B: " + blue_team);
		if(red_team > blue_team) {
			System.out.println("B");
		}
		else if(blue_team > red_team) {
			System.out.println("R");
		}
		else {
			System.out.println("X");
		}

	}
}

	

public class Main {
	public static void main(String args[]) {
		long beforeTime = System.currentTimeMillis();
		battle e = new battle();
		//time(beforeTime);
	}
	
	public static void time(long beforeTime) {
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("\n" + secDiffTime + "ms");

	}
}	

