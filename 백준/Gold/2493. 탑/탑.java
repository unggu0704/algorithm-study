import java.io.*;
import java.util.*;

/**
 * 1일 N알골 BOJ_2212_센서
 * 
 * 입력 N = 10.000 K = 1.000
 * 
 * @author 김규형
 *
 */
class Top {
	int height;
	int recieverTop = 0;
	
	Top(int h) {
		this.height = h;
	}
}
public class Main {

	static int N;
	static int M;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		List<Top> input = new ArrayList<Top>();

		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			input.add(new Top(Integer.parseInt(st.nextToken())));
		}
		
		Stack<Top> stack = new Stack<>();
		stack.add(input.get(N- 1));
		for (int i = N - 2; i >= 0; i--) {
			Top thisTop = input.get(i); //다음 탑의 위치 
			
			while (!stack.isEmpty() && thisTop.height >= stack.peek().height) {
				Top top = stack.pop();
				top.recieverTop = i + 1;
			}
			
			stack.add(thisTop);
		}
		
		for (Top top : input) {
			System.out.print(top.recieverTop + " ");
		}
	}
}