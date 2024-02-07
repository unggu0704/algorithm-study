import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
 /**
  * SWEA_1233_사칙연산_유효성검사_김규형
  * 메모리
  * 21,996 kb
  * 시간
  * 135 ms
  * 
  * @author 김규형
  *
  */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int index = Integer.parseInt(br.readLine());
        for (int i = 1; i <= index; i++) {
        	System.out.print("#" + i + " ");
            solution(br);
        }
 
 
    }
     
    public static void solution(BufferedReader br) throws IOException {
    	int N = Integer.parseInt(br.readLine());
    	StringTokenizer st;
    	Node[][] square = new Node[N][N];
    	
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < N; j++) {
    			square[i][j] = new Node(i,j, Integer.parseInt(st.nextToken()));
    		}
    	}
    	// 입력 종료
    	
    	Queue<Node> q = new ArrayDeque<>();
    	int[] DY = {-1, 0, 1, 0};
    	int[] DX = {0, 1, 0, -1};
    	int max = -1;
    	int count = 0;
    	Node answer = square[0][0];
    	List<Node> answers = new ArrayList<>();
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			q.clear();
    			count = 1;

    			q.add(square[i][j]);
    			while (!q.isEmpty()) {
    				Node node = q.poll();
    				
    				for (int w = 0; w < 4; w++) {
    					int y = node.y + DY[w];	
    					int x = node.x + DX[w];
    					
    					if (y >= N || x >= N || x <= -1 || y <= -1 || square[y][x].num != node.num + 1) 
    						continue;
    					count++;
    					q.add(square[y][x]);
    				}
    			}
    			
    			if (count > max) { //여기가 더 크다면?
    				max = count;
    				answers.clear();
    				answers.add(square[i][j]);
    			} else if (count == max) {
    				answers.add(square[i][j]);
    			}
    			
    			
    		}
		}
    	Collections.sort(answers);
    	
    	System.out.println(answers.get(0).num + " " + max);
    	
    }
}

class Node implements Comparable<Node> {
	int y; int x;
	int num;
	
	Node(int y, int x, int num) {
		this.y = y;
		this.x = x;
		this.num = num;
	}

	@Override
	public int compareTo(Node o) {
		return this.num - o.num;
	}
}