import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int k;
	static Set<String> set = new HashSet<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		String[] input = new String[n];
			
		for (int i = 0; i < n; i++) {
			input[i] = br.readLine();
		}
		
		backTracking(input, new String[k], new boolean[n], 0);

		System.out.println(set.size());
		
	}
	
	public static void backTracking(String[] input, String[] combine, boolean[] visited, int depth) {
		if (depth == k) {
			String str = "";
			
			for (String c : combine) {
				str += c;
			}
			
			set.add(str);
			
			return;
		}
		
		for (int i = 0; i < input.length; i++) {
			if (visited[i])
				continue;
			
			combine[depth] = input[i];
			visited[i] = true;
			backTracking(input, combine, visited, depth + 1);
			visited[i] = false;
		}
	}
}