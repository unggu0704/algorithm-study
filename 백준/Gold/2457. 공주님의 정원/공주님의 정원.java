import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 2번
 * @author 김규형
 *
 */
public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		List<Project> projectList = new ArrayList<>();
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			
			projectList.add(new Project(start, end));
		}
		// 입력 종료
		
		Collections.sort(projectList);
		
		int end = 301;
		int finish = 1201;
		int count = 0;
		
		
		boolean flag = false;
		while (end < finish) {
			flag = false;
			
			int max = 0;
			count++;
			Iterator<Project> iter = projectList.iterator();
			while(iter.hasNext() ) {
				Project project = iter.next();
				if (project.start > end) 
					break;
				
				if (end < project.end) { //기존 종료일보다 앞에 잇는것 중에 가장 큰 종료일을 찾는다.
					flag = true;
					max = Math.max(max, project.end);
				}
			}
			
			if (!flag)
				break;
			
			end = max;
		}
		
		if (!flag)
			System.out.println(0);
		else
			System.out.println(count);
	}

}

class Project implements Comparable<Project>{
	int start, end;
	
	public Project(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Project o) {
		if (start == o.start) 
			return o.end - end;
			
		return start - o.start;
	}

	@Override
	public String toString() {
		return "Project [start=" + start + ", end=" + end + "]";
	}
	
	

}