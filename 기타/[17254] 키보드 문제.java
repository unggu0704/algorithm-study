import java.util.*;

class multi_keyborad{
	Scanner s = new Scanner(System.in);
	int keyboard_num ; int push_count; int max_count;
	ArrayList<Keyboard> keyboard_list = new ArrayList<>();
	
	multi_keyborad(){
		keyboard_num = s.nextInt() + 1; push_count =  s.nextInt();
		for(int i =0 ; i<keyboard_num; i++) {
		//	System.out.println(i + "번째 키보드 생성");
			Keyboard k = new Keyboard(i);
			keyboard_list.add(k);
		}
		int time, k_num = 0; String input;
		for(int j =0 ; j<push_count; j++) {
			k_num = s.nextInt();
			time = s.nextInt(); max_count = Math.max(time, max_count);
			input = s.next();
			keyboard_list.get(k_num).input_key(time,input);
		}
		find_word();
		
	}
	
	void find_word() {
		ArrayList<String> line = new ArrayList<>();
		ArrayList<Integer> num = new ArrayList<>(); int target = 0;
		for(int j = 0; j<=max_count; j++) { // 시간 

			for(int i = 1; i<keyboard_num; i++) { // 키보드 넘버 
				if(keyboard_list.get(i).if_word(j)) {
	//				System.out.println("조건문 들어옴 시간: " + j +"초 키보드 넘버: " +i );
					num.add (keyboard_list.get(i).num);
					line.add ( keyboard_list.get(i).
							output_word(j));
				}
			}
			if(num.size() > 0) {
				int count = num.size();
				for(int w = 0; w<count; w++) {
					target = num.indexOf (Collections.min(num));
					num.remove(target);
					System.out.print(line.get(target));
					line.remove(target);
				}
			}
			
		}
	}
}

class Keyboard{
	int num = 0;
	ArrayList<Integer> time_line = new ArrayList<>();
	List<String> word_line = new LinkedList<>();
	int index = 0;
	Keyboard(int num){
		this.num = num;
	}
	
	void input_key(int t, String i) {
		time_line.add(t); word_line.add(i);
	}
	
	boolean if_word(int time) {
		
		for(int i =0; i<time_line.size(); i++) {
		//	System.out.println("시간: " + time + "저장된 시간: " + time_line.get(i));
			if(time == time_line.get(i)) {
				index = i;
				return true;
			}
		}
		return false;
	}
		
	String output_word(int time) {
		
		return word_line.get(index);
	}
	
	
}

	

public class Main {
	public static void main(String args[]) {
		long beforeTime = System.currentTimeMillis();
		
		
		multi_keyborad m = new multi_keyborad();
		//time(beforeTime);
	}
	
	public static void time(long beforeTime) {
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("\n" + secDiffTime + "ms");

	}
}	

