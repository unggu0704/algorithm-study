
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    static int N;
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());

        Node[][] map = new Node[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Node node = new Node(i,j, Integer.parseInt(st.nextToken()));
                map[i][j] = node;
            }
        }
        // 입력 종료 
        map[N-1][N-1].routeCount = 1; // 목적지 셋팅 

        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (map[i][j].value == 0) continue;

                //System.out.println(i +"," + j + "탐색 시작! ");
                int jumpValue = map[i][j].value;
                int jumpY = i + jumpValue;  int jumpX = j + jumpValue;

                if (jumpY < N) { 
                 //   System.out.println(jumpY + "," + j + "의 routeCount: " + map[jumpY][j].routeCount);
                    map[i][j].routeCount += map[jumpY][j].routeCount;
                }

                if (jumpX < N) {
                 //   System.out.println(i + "," + jumpX + "의 routeCount: " + map[i][jumpX].routeCount);
                    map[i][j].routeCount += map[i][jumpX].routeCount;
                }
            }
        }

        System.out.println(map[0][0].routeCount);

        
    }
}

class Node {
    int y;
    int x;
    int value;
    long routeCount;

    Node(int y, int x, int v) {
        this.y = y;
        this.x = x;
        this.value = v;
        this.routeCount = 0;
    }
}
