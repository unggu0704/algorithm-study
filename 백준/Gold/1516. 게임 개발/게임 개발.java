import java.io.*;
import java.util.*;


/**
 * Main_1516_게임개발_김규형
 * 
 * 위상정렬 문제
 * 한 건물을 짓고 다른 건물을 지어야한다.
 * 각 건물에 대한 리스트를 생성 
 * inDrgree가 0인 건물만 poll 할 것 
 * 
 * 동시에 짓더라도 정확한 inDegree 차수 계산하는게 핵심 
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static int K;
	static StringBuilder sb = new StringBuilder();

	static int[] DY = {-1, 0, 1, 0};
	static int[] DX = {0, 1, 0, -1};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		List<List<Integer>> buildingSet = new ArrayList<List<Integer>>();
		
		N = Integer.parseInt(st.nextToken()); 
		
		int[] indgree = new int[N];
		int[] answers = new int[N];
		int[] time = new int[N];
		
		for (int i = 0; i < N; i ++) {
			buildingSet.add(new ArrayList<Integer>()); //N 번 건물에 대한 리스트를 할당 
		}
		
		List<Integer> inputList;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			inputList = new ArrayList<>();
			
			time[i] = answers[i] = Integer.parseInt(st.nextToken()); //처음 입력은 시간이기에 시간을 저장한다.
			while (st.hasMoreTokens()) {
				inputList.add(Integer.parseInt(st.nextToken()));
			} // 우선 리스트를 입력한다.
			
			for (int j = 0; j < inputList.size() - 1; j++) {
				int nextBuilding = inputList.get(j) - 1; //다음에 지어져야할 건물 
				
				if (i == nextBuilding || nextBuilding == -1) //자기 자신를 진입 차수로 가질수는 없다. || -1은 건물이 아니다.
					continue; 
				
				buildingSet.get(i).add(nextBuilding);
				indgree[i]++; //진입 차수를 1 늘린다.
			}
		}
		//입력 처리 종료 및 진입 차수 설정 완료
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		int topologyCount = 0;
		
		while (N > topologyCount) { //사이클은 존재하지 않는다.
			for (int i = 0; i < N; i++) {
				if (indgree[i] == 0) {
					q.add(i);
					indgree[i]--; // -1로 만들어서 두번 탐색은 하지 않는다.
					topologyCount++; //지은 건물을 카운트 한다.
				}
			}
			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Integer building = q.poll();
				//진입 차수를 재조정한다.
				for (int j = 0; j < buildingSet.size(); j++) {			
					if (buildingSet.get(j).contains(building))  {//지어진 빌딩을 지우면서 차수를 조절한다.
						buildingSet.get(j).remove(building);
						answers[j] = Math.max(answers[j], time[j] + answers[building]); //remove 하면서 선행 건설 시간중 최댓값이 최소 건설 시간!
						indgree[j]--;
					}
				}
			}
		}
		
		for (Integer buildingTime : answers) {
			sb.append(buildingTime).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}