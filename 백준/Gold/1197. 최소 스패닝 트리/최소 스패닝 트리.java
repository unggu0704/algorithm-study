import java.io.*;
import java.util.*;



public class Main {
	public static void main(String[] args) throws IOException{
        solution();
	}

	static int[] DX = {0,-1,1,0};
	static int[] DY = {-1,0,0,1};
	static int[] unionFind;
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 
        int E = Integer.parseInt(st.nextToken());  // 간선 
        Link[] arr = new Link[E];
        unionFind = new int[V+1];
        long ans = 0;
        int index = 0;
        for(int i = 0; i < E; i++) {
        	 st =new StringTokenizer(br.readLine());
        	 Link l = new Link(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        	 arr[index++] = l;
        }
       
        Arrays.sort(arr, new Comparator<Link>(){
        	@Override
        	public int compare(Link o1, Link o2) {
        		return o1.weight > o2.weight ? 1 : -1;
        	}
        });
        
        for(int i = 1 ; i< V + 1; i++) {
        	unionFind[i] = i; // 자기자신이 부모로 설정 
        }
        
        for(int i = 0; i < E; i++) {
        	if(union(arr[i].parent, arr[i].child)) ans += arr[i].weight;
        }
        
        System.out.println(ans);
        
    }
    
    public static boolean union(int x, int y) {
    	x = find(x);
    	y = find(y);
    	if(x == y) return false;
    	
    	if(x >= y) unionFind[y] = x;
    	else unionFind[x] = y;
    	return true;
    }
    
    public static int find(int x) {
    	if(unionFind[x] == x) return x;
    	else return find(unionFind[x]);
    }
}

class Link{
	int weight; // 수가 작을 수록 부모 
	int parent, child;
	Link(int n1,int n2, int w){
		if(n1 > n2) {
			this.child = n2;
			this.parent = n1;
		}
		else {
			this.child = n1;
			this.parent = n2;
		}
		this.weight = w;
	}
}
