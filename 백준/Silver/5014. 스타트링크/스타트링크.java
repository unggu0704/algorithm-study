
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    static int F, S, G, U, D;

    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        

        F = Integer.parseInt(st.nextToken()); // 빌딩의 높이 
        S = Integer.parseInt(st.nextToken()); // 강호가 있는 곳 
        G = Integer.parseInt(st.nextToken()); // 스타트링크의 위치 
        U = Integer.parseInt(st.nextToken()); // 위로 U층 가는 버튼 
        D = Integer.parseInt(st.nextToken()); // 아래로 D층 가는 버튼 

        /*
         * BFS를 활용한 문제...
         * dp의 최적 값을 탐색한담면 복잡한 계산에는 답이 틀릴수가 있을것 같음...
         * BFS 를 통해 모든 부분을 1회씩 탐색 -> visited를 활용
         * 음... 이게 왜 BFS..?
         */
        
        if (S == G) {
            System.out.println(0);
            System.exit(0);
        }

         Queue<Integer> queue = new LinkedList<>();

         boolean[] visited = new boolean[F + 1]; // 방문한 빌딩 층 

        visited[S] = true;
        int nowFloor = S; // 해당 층에서 엘리베이터 탑승 
        int answer = 1;
        int upFloor = nowFloor + U;
        int downFloor = nowFloor - D;

        if (upFloor == G || downFloor == G) {
            System.out.println(answer);
            System.exit(0);
        }

        if (upFloor < F + 1 && !visited[upFloor]) { //간적이 없고, F층보다 작다면
            queue.add(upFloor);
            visited[upFloor] = true;
        }

        if (downFloor > 0 && !visited[downFloor]) {
            queue.add(downFloor);
            visited[downFloor] = true;
        }



        while (!queue.isEmpty()) {
            answer++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                nowFloor = queue.poll(); // 해당 층에서 엘리베이터 탑승 

                upFloor = nowFloor + U;
                downFloor = nowFloor - D;

                if (upFloor == G || downFloor == G) {
                    System.out.println(answer);
                    System.exit(0);
                }

                if (upFloor < F + 1 && !visited[upFloor]) { //간적이 없고, F층보다 작다면
                    queue.add(upFloor);
                    visited[upFloor] = true;
                }

                if (downFloor > 0 && !visited[downFloor]) {
                    queue.add(downFloor);
                    visited[downFloor] = true;
                }
            }
        }

        System.out.println("use the stairs");

    }
}


