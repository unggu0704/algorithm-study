import java.util.*;
import java.io.*;

class Simulate{
	
	int n, N, K, broken, count = 1;
	belt[] b;
	
	Simulate() throws IOException{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String br = bf.readLine();
		
		StringTokenizer st = new StringTokenizer(br);
		n =  Integer.parseInt(st.nextToken());
		N = n * 2;
		K = Integer.parseInt(st.nextToken());
		
		b = new belt[N];
		
		br = bf.readLine();
		st = new StringTokenizer(br);
		for(int i = 0 ; i< N; i++) {
			b[i] = new belt(Integer.parseInt(st.nextToken()));
		}
		
		is_Simulate();
	}
	
	void is_Simulate() {
		
		while(true) {
			turn_Belt();
		//	System.out.println(" trun belt ");
			//print();
			move_Robot();
		//	System.out.println(" move robot ");
			//print();
			input_Robot();
		//	System.out.println(" input_robot ");
			//print();
			check_broken();
			if(broken >= K) {
				System.out.println(count);
				break;
			}
		//	System.out.println("loop end: " + count);
			count++; broken = 0;
		}

	}
	
	void turn_Belt() {
		belt[] tmp = new belt[N];
		for(int i = 1; i < b.length; i++) {
			tmp[i] = b[i-1];
		}
		tmp[0] = b[N-1];
		
		b = tmp;
		b[n-1].robot = false;
	}
	
	void move_Robot(){
		for(int i = n-1; i != 0; i--) {
			if(b[i].robot && !b[i+1].robot && b[i+1].Durability > 0) {
				b[i].robot = false; 
				b[i+1].use();
			}
		}
		b[n-1].robot = false;
	}
	
	void input_Robot() {
		if(b[0].Durability > 0) {
			b[0].use();
		}
	}
	
	void check_broken() {
		for(int i = 0; i < N; i++) {
			if(b[i].Durability == 0) {
				broken++;
			}
		}
	}
	
	void print() {
		for(int i = 0; i<n; i++) {
			System.out.print(b[i].Durability+"("+b[i].robot+")" + " ");
		}
		System.out.println();
		for(int i = n; i<N; i++) {
			System.out.print(b[i].Durability + " ");
		}
		System.out.println();
	}
}

class belt{
	
	int Durability;
	boolean robot = false;
	
	belt(int d){
		Durability = d;
	}
	
	void use() {
		Durability--;
		robot = true;
	}
	
}


class Main {
	public static void main(String args[]) throws IOException {
		long beforeTime = System.currentTimeMillis();
		Simulate s = new Simulate();
		//time(beforeTime);
	}
	
	public static void time(long beforeTime) {
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("\n" + secDiffTime + "ms");

	}

}	

