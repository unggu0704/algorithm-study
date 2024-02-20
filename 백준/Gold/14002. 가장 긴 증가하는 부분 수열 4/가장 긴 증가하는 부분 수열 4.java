import java.io.*;
import java.util.*;

/**
 * Main_16235_나무재테크_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static int K;
	static int answer = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[] input = new int[N];
		int[] binaryArr = new int[N];
		int[] indexArr = new int[N];
		
 		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 종료 
		
		Arrays.fill(binaryArr, Integer.MAX_VALUE);
		
		binaryArr[0] = input[0];
		
		for (int i = 0; i < N; i++) {
			int index = Arrays.binarySearch(binaryArr, input[i]);
			if (index < 0) { //배열에 이게 없다면 
				index = (index + 1) * -1;
				binaryArr[index] = input[i];
			}
			indexArr[i] = index;	// 해당 번호가 몇번째에 들어가야할지에 대한   index를 담고 있는 배열 
		}
		
		List<Integer> answers = new ArrayList<Integer>();
		for (int i : binaryArr) {
			if (i == Integer.MAX_VALUE || answers.contains(i))
				break;
			answer++;
			answers.add(i);
		}
		
		
		System.out.println(answer);
		answer--;
		
		Stack<Integer> stack = new Stack<>();
		for (int i = N - 1; i >= 0; i--) {
			if (answer == - 1)
				break;
			
			if (answer == indexArr[i]) {
				stack.add(input[i]);
				answer--;
			}
		}

		int stackSize = stack.size();
		for (int i = 0; i < stackSize; i++) {
			if (i == 0)
				sb.append(stack.pop());
			else
				sb.append(" ").append(stack.pop());
		}
		
		System.out.print(sb.toString());
		
		
	}
	
}