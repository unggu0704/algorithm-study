import java.util.*;


class Leave{
	Scanner s = new Scanner(System.in);
	int N, r_N;
	ArrayList<Integer> T = new ArrayList<Integer>();
	ArrayList<Integer> P = new ArrayList<Integer>();
	int[] efficiency;
	Leave(){
		N = s.nextInt();
		r_N = N;
		for(int i = 0; i <N; i++) {
			T.add(s.nextInt());
			P.add(s.nextInt());
		}
		cal_Efficiency();
		cut_Table();
		calcau(0);	
		System.out.println(end_max);
	}
	
	void cut_Table() {
		int last_number = N-1, far_time = 1;
		for(int i = N-1 ; i > -1; i--) {
//			System.out.println(far_time + " vs " + T.get(last_number));
			if(far_time  < T.get(last_number)) {
		//		System.out.println(i +"번째 값 제외 ");
				efficiency[i] = -2;
			}
			far_time++;	last_number--;
		}
	}
	
	void cal_Efficiency() {
		efficiency = new int[N];		
		for(int i =0; i< N; i++) {
			efficiency[i] = P.get(i) / T.get(i); 
		}
	}
	
	int end_max = 0;
	
	int calcau(int flag) {
	//	System.out.println("calcau 함수 진입 현재 flag 값: "+ flag);
		int max = -99, index = 0;
		boolean end_check = false;
		int save_index[];
		for(int i = 0; i<N; i++) {
	//		System.out.println("반복중 ");
			if(max < efficiency[i] && check_Time(i)) {
	
				//print_eff();
	///			System.out.println("최댓값 발견 " + efficiency[i] +" " + i + " 번째 값 ");
				max = efficiency[i];
	//			System.out.println("현재 max : " + max);
				index = i;
				save_index = copy_array(efficiency);
				time_set(index);
//				System.out.println(P.get(index)+"의 값을 " +flag+ "에 추가함 ");
				calcau(P.get(index)+flag);
				efficiency = copy_array(save_index);
				end_check = true;
			}
			else if(max == -1 ) {}//end_check = false; break;}
			else if(max == efficiency[i]  && check_Time(i) ) {
	//			System.out.println(max +" 과 " + efficiency[i] + "의 값이 같음 calcau() 재진");
				save_index = copy_array(efficiency);
				time_set(i);
	//			System.out.println(P.get(i)+"의 값을 " +flag+ "에 추가함 ");
				calcau(P.get(i)+flag);
				efficiency = copy_array(save_index);
	//			System.out.println("중복 이전값 탐색 ");
				end_check = true;
			}
		}
		if(end_check) {
	//		System.out.println("현재 값 저장하고 다음 값 찾으러 출발 ");
	//		time_set(index); // 최댓값 넣은 건 셋팅함 
	//		System.out.println(P.get(index)+"의 값을 " +flag+ "에 추가함 ");
	//		calcau(P.get(index)+flag);
		}
		else {
			if(end_max < flag) {
	//			System.out.println("현재 end_max의 값: " + end_max + " vs flag의 값: " + flag);
				end_max = flag;
			}
		}
		return 0;
	}
	
	void time_set(int index) {
		int degree = T.get(index);
		for(int j = 0; j<degree; j++) {
			if(index > N-1) { break; }
			//P.set(flag, 0);
			efficiency[index] = -1;
			index++;
		}
	//	print_eff();
	}
	
	int[] copy_array(int array[]) {
		int output_array[] = new int[array.length];
		for(int i =0; i< array.length; i++) {
			output_array[i] = array[i];
		}
		return output_array;
	}
	
	boolean check_Time(int i) {
		if(efficiency[i] == -2) { return false; }
	//	System.out.println("아래의 리스트에서 " + i + "번째 값이 적절한지 검사.");
	//	print_eff();
		int count = 0;
		try{
			count = T.get(i);
		for(int j = 0; j < count; j++) {
			if(efficiency[i++] == -1) {
				return false;
			}
		}
		}catch(IndexOutOfBoundsException e) {
			return true;
		}
		return true;
	}
	
	
	void print_eff() {
		for(int i =0 ; i<N; i++) {
			System.out.print(efficiency[i]+ " ");
		}
		System.out.println();
		
	}
}



public class Main {

	public static void main(String[] args) {
		Leave e = new Leave();

	}

}
