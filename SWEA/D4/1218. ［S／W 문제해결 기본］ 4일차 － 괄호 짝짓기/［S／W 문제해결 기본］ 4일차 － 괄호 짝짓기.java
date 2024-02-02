import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 1; i < 11; i++) {
			System.out.print("#" + i + " " + solution(br));
			System.out.println();
		}


	}
	
	public static int solution(BufferedReader br) throws IOException {
		br.readLine();
		List<Character> left = new ArrayList<>();
		left.add('(');	left.add('[');	left.add('{');	left.add('<');

		Map<Character, Character> map = new HashMap<>();
		
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		map.put('<', '>');
		
		String str = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i <str.length(); i++) {
			char ch = str.charAt(i); // 한줄을 받는다.
			
			if (left.contains(ch)) { //여는 괄호라면?
				stack.add(ch);
			} else { //닫는 괄호라면?
				if (map.get(stack.pop()) != ch) { //맞는지 체크!
					return 0;
				}
			}
		}
		
		if (stack.isEmpty()) {
			return 1;
		}
		return 0;
	}
}