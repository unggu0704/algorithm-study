import java.util.*;
import java.io.*;

class room{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n; int greed; int index;
	PriorityQueue<lecture> arr_time = new PriorityQueue<>();
	lecture[] time;
	room() throws IOException{
		String str = br.readLine();
		int n = Integer.parseInt(str);
		time = new lecture[n];
		for(int i =0 ;i<n; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
			lecture l = new lecture( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			arr_time.add(l);
		}
		
		
		
		System.out.println(count_lec());
	}
	
	int count_lec() {
		int end_time; int count = 0; boolean flag = false;
		lecture tmp;
		PriorityQueue<Integer> index = new PriorityQueue<Integer>();
		while(!arr_time.isEmpty()) {
			flag = false;
			tmp =  arr_time.poll();
		//	System.out.println("output  start: " + tmp.s_time + "end: " + tmp.e_time);
			index.add(tmp.e_time);
			if(tmp.s_time < (int) index.peek()) {
				count++;
			}
			else {
				index.remove();
			}
		}
		
		
		return count;
	}
	
	/*
	int count_lec() {
		int end_time; int count = 0;
		Iterator<lecture> i_arr; 
		lecture tmp;
		while(!arr_time.isEmpty()) {
			end_time = arr_time.poll().e_time;
			count++; 
			i_arr = arr_time.iterator();
			while(i_arr.hasNext()) {
		//		System.out.println("현재 끝나는 시간 " + end_time);
				tmp = i_arr.next();
		//		System.out.println("output  start: " + tmp.s_time + "end: " + tmp.e_time);
				if(tmp.s_time == end_time) {
					//System.out.println(arr_time.get(i).s_time + "를 찾음 ");
					end_time = tmp.e_time; 
					i_arr.remove();
					
				} 
			} 
			
		}
		return count;
	}*/
	

}

class lecture implements Comparable<lecture>{
	int s_time, e_time;
	
	lecture(int s, int e){
		s_time = s;
		e_time = e;
	}
	
	 @Override
	 public int compareTo(lecture target) {
	        return this.s_time <= target.s_time ? -1 : 1;
	 }
}


class Main {
	public static void main(String args[]) throws IOException {
		long beforeTime = System.currentTimeMillis();
		room m = new room();
		//time(beforeTime);
	}
	
	public static void time(long beforeTime) {
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("\n" + secDiffTime + "ms");

	}

}	

