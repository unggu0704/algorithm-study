import java.util.*;


class hide_seek{
	Scanner s = new Scanner(System.in);
	int a, b;
	int sujin;
	int deep_count = 0;
	Queue<node> queue = new LinkedList<>();
	//ArrayList<Integer> route = new ArrayList<>();
	boolean[] flag = new boolean[100001];
	long beforeTime;
	hide_seek(){

		a= s.nextInt(); b = s.nextInt();
		beforeTime = System.currentTimeMillis();
		if( a ==b) {
			System.out.println(0);
			System.out.println(a);
			System.exit(0);
		}
		
		if( a > b) {
			walk();
		}
		node s = new node(a,null);
		queue.add(s); 
		//route.add(a);
		flag[a] = true;
		s.root = 1;
		
		find_route();
	}
	
	void find_route() {
		int coordinate = 0;
		int n_coordinate = 0;
		int input_count = 1, count = 0; 
		while(true) {
			deep_count++;
			count = input_count; input_count = 0;
			for(int i =0; i <count; i++) {
				node s = queue.poll();
				coordinate =s.data;
				
				n_coordinate = coordinate - 1;
				
				if(n_coordinate == b) {
					Stack<Integer> stack = new Stack<>();
					while(true) {
						stack.add(s.data);
						if(s.root == 1) { break; }
						s = s.top;

					}
					System.out.println(deep_count);
					for(int s1 =stack.size() -1; s1 !=-1; s1 --) {
						System.out.print(stack.get(s1) +" ");
					}
					System.out.print(n_coordinate);

					System.exit(0);
				}
				
				//checking(n_coordinate,s);
				if(n_coordinate > -1 && !flag[n_coordinate]) {
				//	System.out.println(n_coordinate +"를 찾음 ");
					node n = new node(n_coordinate,s);
					//route.add(n_coordinate);
					flag[n_coordinate]  =true;
					queue.add(n);
					input_count++;
				}
				n_coordinate = coordinate +1;
				
				if(n_coordinate == b) {
					Stack<Integer> stack = new Stack<>();
					while(true) {
						stack.add(s.data);
						if(s.root == 1) { break; }
						s = s.top;

					}
					System.out.println(deep_count);
					for(int s1 =stack.size() -1; s1 !=-1; s1 --) {
						System.out.print(stack.get(s1) +" ");
					}
					System.out.print(n_coordinate);
					System.exit(0);
				}
				//checking(n_coordinate,s);
				if(n_coordinate < 100001  && !flag[n_coordinate]) {
			//		System.out.println(n_coordinate +"를 찾음 ");
					node n = new node(n_coordinate,s);
					queue.add(n);
					//route.add(n_coordinate);
					flag[n_coordinate] = true;
					input_count++;
				}
				n_coordinate = coordinate *2;
				
				if(n_coordinate == b) {
					Stack<Integer> stack = new Stack<>();
					while(true) {
						stack.add(s.data);
						if(s.root == 1) { break; }
						s = s.top;

					}
					System.out.println(deep_count);
					for(int s1 =stack.size() -1; s1 !=-1; s1 --) {
						System.out.print(stack.get(s1) +" ");
					}
					System.out.print(n_coordinate);
					System.exit(0);
					
				}
				//checking(n_coordinate,s);
				if(n_coordinate < 100001 && !flag[n_coordinate]) {
				//	System.out.println(n_coordinate +"를 찾음 ");
					//route.add(n_coordinate);
					flag[n_coordinate] = true;
					node n = new node(n_coordinate,s);
					queue.add(n);
					input_count++;
				}
			} 
		}
	}
	
	void walk() {
		System.out.println(a- b);
		for(int i = a; i !=b -1; i--) {
			System.out.print(i + " ");
		}
		System.exit(0);
	}
	
	void checking(int c, node s) {
		if(c == b) {
			Stack<Integer> stack = new Stack<>();
			while(true) {
				stack.add(s.data);
				if(s.root == 1) { break; }
				s = s.top;

			}
			System.out.println(deep_count);
			for(int i =stack.size() -1; i !=-1; i --) {
				System.out.print(stack.get(i) +" ");
			}
			System.out.print(c);
			long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
			long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
			System.out.println("시간차이(m) : "+secDiffTime + "ms");
			System.exit(0);
		}
	}
	
	void init_boolean() {
		for(int i =0; i< 100001; i++) {
			flag[i] = false;
		}
	}
	
}

class node{
	node top;
	int data;
	int root = 0;
	
	node(int d, node t){
		data = d;
		top = t;
	}
	
}



public class Main {

	public static void main(String[] args) {
		hide_seek h = new hide_seek();

	}

}
