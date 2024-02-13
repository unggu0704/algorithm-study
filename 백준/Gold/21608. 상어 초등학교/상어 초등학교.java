import java.io.*;
import java.util.*;

/**
 * Main_16935_배열돌리기3_김규형
 * 
 * @author 김규형
 */

public class Main {

	static int N;
	static int M;
	static long answer = 0;
	
	static int[] DX = {-1, 0, 1, 0};
	static int[] DY = {0, 1, 0, -1};

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		int size = N * N;
	
		List<Student> students = new ArrayList<Student>();
		List<Integer> inputs = new ArrayList<Integer>();
		Student[][] classroom = new	Student[N][N];
		
		/*
		 * 초기 자료구조 초기화 
		 */
		for (int i = 0; i < size + 1; i++) {
			students.add(new Student(i));
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				classroom[i][j] = new Student(0);
			}
		}

		for (int i = 1; i < size + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int stuNo = Integer.parseInt(st.nextToken());
			inputs.add(stuNo);
			Student student = students.get(stuNo);
			
			while (st.hasMoreTokens()) {
				student.closedStudnets.add(students.get(Integer.parseInt(st.nextToken()))); //친한 친구를 추가 
			}
		}
		// 입력 종료 
		
		for (int i = 0; i < size; i++) {
			Student student = students.get(inputs.get(i)); //앉힐 학생 
			int maxHappines = 0;
			int maxSpace = 0;
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					int happines = 0;
					int space = 0;
					
					for (int d = 0; d < 4; d++) {
						int dy = y + DY[d];		int dx = x + DX[d];
						
						if (dy <= -1 || dx <= -1 || dy >= N || dx >= N)
							continue;
						
						Student closdedStudent = classroom[dy][dx]; //인접한 학생을 가져온다.
						
						if (classroom[dy][dx].stuNo == 0)
							space++;

						if (student.closedStudnets.contains(closdedStudent)) {
							happines++; //친한 학생이 옆에 있다면 행복도를 올린다.
						}
					}
					
					if (classroom[y][x].stuNo == 0) { //빈자리인가? 
						if (student.y == -1 && student.x == -1) {
							student.y = y;		student.x = x;
						}
						
						if (happines == maxHappines && space > maxSpace) {
							student.y = y;		student.x = x;
							maxSpace = space;
						}
						
						if (happines > maxHappines) {
							student.y = y;		student.x = x;
							maxHappines = happines;
							maxSpace = space;
						}
					}
					
		
				}
			}
			
			
			classroom[student.y][student.x] = student;
		//	print(classroom);
		}
		
	//	print(classroom);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int happines = 0;
				for (int d = 0; d < 4; d++) {
					int dy = i + DY[d];		int dx = j + DX[d];
					
					if (dy <= -1 || dx <= -1 || dy >= N || dx >= N)
						continue;
					
					Student closdedStudent = classroom[dy][dx]; //인접한 학생을 가져온다.

					if (classroom[i][j].closedStudnets.contains(closdedStudent)) {
						happines++; //친한 학생이 옆에 있다면 행복도를 올린다.
					}
				}
				
				switch (happines) {
				case 1:
					answer += 1;
					break;
				case 2:
					answer += 10;
					break;
				case 3:
					answer += 100;
					break;
				case 4:
					answer += 1000;
					break;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static void print(Student[][] classroom) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(classroom[i][j].stuNo + " ");
			}
			System.out.println();
		}
		System.out.println("==========================");
	}
	
}

class Student {
	int stuNo;
	List<Student> closedStudnets = new ArrayList<Student>();

	int y = - 1, x = - 1;
	Student (int n) {
		this.stuNo = n;
	}
}