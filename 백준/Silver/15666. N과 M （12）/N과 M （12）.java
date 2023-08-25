import java.io.*;
import java.util.*;



public class Main {
	public static void main(String[] args) throws IOException{
        solution();
	}

	static int N, M;
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        Integer[] arr = new Integer[N];
        Integer[] combine = new Integer[M];
        st =new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
      //  Set<Integer> set = new HashSet<Integer>(Arrays.asList(arr));
       // arr = set.toArray(new Integer[0]);
        
        backTracking(0,0,arr,combine);
        System.out.println(sb);
        
    }

    static StringBuilder sb = new StringBuilder();
    public static void backTracking(int n, int m, Integer[] arr, Integer[] combine) {
    	if(m == M) {
    		for(int i : combine) {
    			sb.append(i + " ");
    		}
    		sb.append("\n");
    		return;
    	}
    	
    	int remember = 0 ;
    	for(int i = n; i < arr.length; i++) {
    		if(remember == arr[i]) continue;
    		combine[m++] = arr[i];
    		remember = arr[i];
    		backTracking(i,m,arr,combine);
    		m--;
    	}
    }
}
