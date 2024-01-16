import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 알고리즘 풀이 
 * 24.01.16
 * @author 김규형
 */
public class Main {
	

	static ArrayList<ArrayList<Integer>> nodeMap = new ArrayList<>();
	static Queue<Integer> queue = new LinkedList<>();
	static ArrayList<Integer> numberList;
	static ArrayList<Integer> answerList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		numberList = new ArrayList<>();
        answerList = new ArrayList<>();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <N; i++) {
			numberList.add(i); // 1 ~ N 까지의 숫자가 들어간다. 
            nodeMap.add(new ArrayList<Integer>()); // 0 ~ N -1 까지의 인덱스 
        }
		// 이중 리스트 객체 할당
		
		ArrayList<Integer> inputList;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			inputList = new ArrayList<>();
			st.nextToken();
			while (st.hasMoreTokens()) {
				inputList.add(Integer.parseInt(st.nextToken()) - 1);
			} 
			Collections.reverse(inputList); // 리스트 뒤집기 
			
			for (int n = 0; n < inputList.size() - 1; n++) {
				int singer = inputList.get(n); //  가수를 얻기
				int nextSinger = inputList.get(n + 1); // singer 보다 앞서 공연해야하는 가수 
				
				if (singer == nextSinger) continue; // 자기 자신을 진입 노드로 가지는 것은 불가능하다.
				
				nodeMap.get(singer).add(nextSinger); 
			}
			// 위 과정을 거치면 모든 노드의 진입 차수를 이중 리스트 형식으로 얻게 된다.

		}

		// for (ArrayList<Integer> list : nodeMap) {
		// 	for (Integer num : list) {
		// 		System.out.print(num + " ");
		// 	}
		// 	System.err.println();
		// }


		while (!numberList.isEmpty()) {
			if (findSinger()) { // 더이상 찾을 노드가 없으면서, 숫자는 여전히 남아 잇을 경우 탐색이 불가능하다! 
				System.out.println("0 x");
				System.exit(0);
			}
		}

		for (Integer answer : answerList) {
			System.out.println(answer);
		}
	}
	
	public static boolean findSinger() {
		Iterator<Integer> iter = numberList.iterator();
		while (iter.hasNext()) {
			int i = iter.next(); //리스트의 요소에 참조할 때는 1을 빼줘야 맞다.
			if (nodeMap.get(i).size() == 0) { // 뒤에 진입 차수가 없는 노드를 찾는다.
				answerList.add(i + 1);
				iter.remove();

				deleteSinger(i); // 그래프의 진입 차수를 재설정한다.
				
				return false; //찾을 수 있는 노드가 있기에 바로 계산하러 간다.
			}
		}
		return true;
	}
	
    // 가수가 노래를 부름으로써 모든 리스트에서 해당 가수의 숫자를 지운다.
	public static void deleteSinger(int node) {
		Iterator<Integer> iter = numberList.iterator();
		while (iter.hasNext()) {
			int num = iter.next();
			ArrayList<Integer> tmpList = nodeMap.get(num); // 가수의 순서를 가져온다.
			 
			if (tmpList.contains(node)) { //해당 순서에 가수가 있다면 순회하면서 삭제
				Iterator<Integer> tmpIterator = tmpList.iterator();
				while (tmpIterator.hasNext()) {
					if (tmpIterator.next() == node) {
						tmpIterator.remove();
					}
				}
			}

		} 
	}
}
