import java.util.*;


class hide_seek{
	Scanner s = new Scanner(System.in);
	int a, b;
	int deep_count = 0;
	Queue<node> queue = new LinkedList<>();
	boolean[] flag = new boolean[100001];
	long beforeTime;
	int[] teleport = {0,-1,1};
	
	hide_seek(){
		a= s.nextInt(); b = s.nextInt();
		if( a ==b) {
			System.out.println(0);
		//	System.out.println(a);
			System.exit(0);
		}
		
		if( a > b) {
			walk();
		}
		node s = new node(a,null);
		queue.add(s); 
		flag[a] = true;
		s.root = 1;
		
		find_route();
	}
	
	void find_route() {
		int coordinate = 0;
		int n_coordinate = 0;
		int input_count = 1, count = 0; 
		boolean endCheck = false;
		while(true) {
			deep_count++;
			count = input_count; 
			input_count = 0;
			for(int i =0; i <count; i++) {
				node s = queue.poll();
				coordinate =s.data;
				
				for(int j = 0; j < 3; j++) {
					if(j ==1 || j == 2) n_coordinate = coordinate + teleport[j];
					else n_coordinate = coordinate * 2;
					if(n_coordinate <= -1 || n_coordinate >= 100001 || flag[n_coordinate]) continue;
					
					if(checking(n_coordinate,s)) endCheck = true;
					else flag[n_coordinate] = true;
					node n = new node(n_coordinate,s);
					queue.add(n);	
					input_count++;
				}
			}
			if(endCheck) {
				System.out.println(minTime);
				break;
			}
		}
	}
	
	void walk() {
		System.out.println(a- b);
		/*
		for(int i = a; i !=b -1; i--) {
			System.out.print(i + " ");
		}
		*/
		System.exit(0);
	}
	
	int minTime = 99999999;
	boolean checking(int c, node s) {
		int time = -1;
		if(c == b) {
			Stack<Integer> stack = new Stack<>();
			stack.add(c);
			while(true) {
				stack.add(s.data);
				if(s.root == 1) { break; }
				s = s.top;
			}
			double preValue = -121;
			for(int i =stack.size() -1; i !=-1; i --) {
				double value = stack.get(i);
				if(value / 2 != preValue) {
					time++;
				}
				preValue = value;
			}
			minTime = Math.min(time, minTime);
			return true;
		}
		return false;
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
