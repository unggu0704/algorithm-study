import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	static int ans = 0;
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	boolean flag = false;
    	int N = Integer.parseInt(br.readLine());
    	int Edge = Integer.parseInt(br.readLine());
    	  	
    	PC[] pcList = new PC[N];
    	
    	for(int i = 0; i< N; i++) {
    		PC p = new PC(i+1);
    		pcList[i] = p;
    	}
    	
    	
    	for(int i = 0; i < Edge; i++) {
    		
			st = new StringTokenizer(br.readLine());
			
			int input1 = Integer.parseInt(st.nextToken()) - 1;
			int input2 = Integer.parseInt(st.nextToken()) - 1;
			pcList[input1].linkList.add(pcList[input2]); // 연ruf 
			pcList[input2].linkList.add(pcList[input1]); // 연ruf 
		
    	}
    	// 입력 종료 
    	
    	Queue<PC> inflecList = new LinkedList<PC>();
    	pcList[0].infelction = true;
    	listCopy(pcList[0].linkList, inflecList);

    	
    	while(!inflecList.isEmpty()){
    		PC pc = inflecList.poll();
    	
    		
    		listCopy(pc.linkList, inflecList);
    	}
    	
    	System.out.println(ans);
    }
    
    static void listCopy(ArrayList<PC> a, Queue<PC> b) {
    	for(PC i : a) {
    		if(i.infelction) continue;
    		ans++;
    		i.infelction = true;
    		b.add(i);
    	}
    }
    
}

class PC{
	ArrayList<PC> linkList = new ArrayList<>();
	boolean infelction = false;
	int name;
	
	PC(int n){
		name = n;
	}
	
	
}


