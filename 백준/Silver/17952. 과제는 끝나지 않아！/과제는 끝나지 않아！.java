import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Task> stack = new Stack<>();
		int ans = 0;
		N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		
		int input = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			input = Integer.parseInt(st.nextToken());
			

			
			if (input == 0) {
				if (stack.isEmpty())
					continue;
				
				Task task = stack.peek();
				
				int point = task.isTask();
				if (point > 0) {
					ans += point;
					stack.pop();
				}
				
				continue;
			}
			
			Task task = new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			int point = task.isTask();
			if (point > 0) {
				ans += point;
			} else {
				stack.add(task);
			}
		}
		
		System.out.println(ans);
	}

}

class Task {
	int point;
	int time;
	
	Task(int p, int t) {
		this.point = p;
		this.time = t;
	}
	
	int isTask() {
		time--;
		
		if (time == 0)
			return point;
		
		return 0;
	}
}