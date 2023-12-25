import java.io.*;
import java.util.*;

public class Main {

static int N;
static int M;
static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            solution(br);
        }
        System.out.println(sb);
    }

    public static void solution(BufferedReader br) throws IOException {
      
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        boolean[][] visitedMap = new boolean[N][M];
        HashSet<Character> keyList = new HashSet<>(); // 가지고 있는 열쇠 집합 
        int answer = 0;
        ArrayList<Coordinate> startList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if ((i == 0 || j == 0 || i == N - 1 || j == M - 1) && input.charAt(j) != '*') { // 경계면 미리 저장
                    startList.add(new Coordinate(i,j, input.charAt(j)));
                }
                map[i][j] = input.charAt(j);
            }
        }



        String hasKey = br.readLine(); // 이미 가지고 있는 키 받아오기 
        if (!hasKey.equals("0")) {
            for (int i = 0; i < hasKey.length(); i++) {
                keyList.add(hasKey.charAt(i));
            }
        }
        // 입력 종료
        if (startList.isEmpty()) { // 들어갈 곳이 없다면 탐색할 필요가 없다.
            sb.append(0 + "\n");
            return;
        }

        boolean variance = true;
        while (variance) {
            variance = false;
            initVisited(visitedMap); // 방문 변수 초기화
            Queue<Coordinate> queue = new LinkedList<>();

            for (Coordinate coordinate : startList) { // 시작 지점에 문이 있을 경우 열쇠가 있는 곳만 탐색
                 if ('A' <= coordinate.info && coordinate.info <= 'Z' ) {
                    char wantingKey = (char) (coordinate.info + 32);
                    if (!keyList.contains(wantingKey)) {
                        continue;
                    }
                }
                queue.add(coordinate);
            }

            while (!queue.isEmpty()) {
                Coordinate coordinate = queue.poll(); 

                for (int i = 0; i < 5; i++) {
                    int dx = coordinate.x + DX[i];
                    int dy = coordinate.y + DY[i];

                    if (dx <= -1 || dy <= -1 || dy >= N || dx >= M || map[dy][dx] == '*' || visitedMap[dy][dx]) //경계를 벗어나거나, * 벽이거나, 이미방문한 노드는 탐색하지 않음 
                        continue;

                    visitedMap[dy][dx] = true;
                    if (map[dy][dx] == '$') { // 문서라면?
                        answer++;
                    } else if ('A' <= map[dy][dx] && map[dy][dx] <= 'Z' ) { // 문이라면?
                        char wantingKey = (char) ((int)map[dy][dx] + 32);
                        if (!keyList.contains(wantingKey)) { // 키가 없다면 탐색을 못함 
                            continue;
                        }
                    } else if ('a' <= map[dy][dx] && map[dy][dx] <= 'z') { // 열쇠라면?
                        keyList.add(map[dy][dx]); // 열쇠를 새로 넣는다!
                        variance = true;  //새로운 열쇠를 얻었기에 한번더 탐색한다. 
                    }
                    map[dy][dx] = '.';
                    queue.add(new Coordinate(dy, dx, map[dy][dx]));

                }
            }
        }

        sb.append(answer + "\n");
    }
    static int[] DX = {0, 1, 0, -1, 0};
    static int[] DY = {1, 0, -1, 0, 0};

    public static void initVisited(boolean[][] visitedMap) {
        for (int i = 0; i < visitedMap.length; i++) {
            for (int j = 0; j < visitedMap[i].length; j++) {
                visitedMap[i][j] = false;
            }
        }
    }

}

class Coordinate {
    int y;
    int x;
    char info;
    Coordinate(int y, int x, char info) {
        this.y = y;
        this.x = x;
        this.info = info;
    }
}

