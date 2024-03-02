import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**

 Main_19236_청소년상어_김규형
 @author 김규형
 */
public class Main {

    static int[] DY = {-1, -1,  0,  1, 1, 1, 0, -1};
    static int[] DX = { 0, -1, -1, -1, 0, 1, 1,  1};
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        Fish[][] originalMap = new Fish[4][4];
        List<Fish> fishList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                originalMap[i][j] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1,i, j, false, true);
                fishList.add(originalMap[i][j]);
            }
        }

        eatFish(originalMap, originalMap[0][0], 0, 0);

        System.out.println(ans);
    }

    public static void eatFish(Fish[][] originalMap, Fish shark, int ateCount, int depth) {
        Fish[][] map = copyMap(originalMap);
        //맵의 깊은 복사

        //해당 위치에 있는 물고기를 잡아 먹는다.
        ateCount += map[shark.y][shark.x].num;
        map[shark.y][shark.x].alive = false;
        map[shark.y][shark.x].shark = true;
        ans = Math.max(ans, ateCount); //정답 최댓값  갱신

        moveFish(map);
        map[shark.y][shark.x].shark = false;

        int dy = shark.y;
        int dx = shark.x;

        while (true) {

            dy += DY[shark.direction];
            dx += DX[shark.direction];

            if (dy >= 4 || dx >= 4 || dy <= -1 || dx <= -1)
                break;

            if (!map[dy][dx].alive)
                continue;

            eatFish(map, map[dy][dx], ateCount, depth + 1);
        }

    }

    public static void switchFish(Fish[][] map, Fish fish) {

        int dy = fish.y + DY[fish.direction];
        int dx = fish.x + DX[fish.direction];

        while (dy >= 4 || dx >= 4 || dy <= -1 || dx <= -1 || map[dy][dx].shark ) { //이동할 수 없다면 방향을 돌린다.
            fish.direction = ++fish.direction % 8; //45도 회전
            dy = fish.y + DY[fish.direction];
            dx = fish.x + DX[fish.direction];
        }

        Fish temp = map[fish.y][fish.x];
        map[fish.y][fish.x] = map[dy][dx];
        map[dy][dx] = temp;

        map[fish.y][fish.x].y = fish.y;    map[fish.y][fish.x].x = fish.x;
        map[dy][dx].y = dy;    map[dy][dx].x = dx;

    }

    public static void moveFish(Fish[][] map) {

        List<Integer> sharkNumlist = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j].shark || !map[i][j].alive) //상어이거나 0이면 담을 필요가 없다.
                    continue;

                sharkNumlist.add(map[i][j].num);
            }
        }

        Collections.sort(sharkNumlist);
        int num = 0;

        loop:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j].num == sharkNumlist.get(num)) {
                    switchFish(map, map[i][j]);
                    if (sharkNumlist.size() <= ++num)
                        break loop;

                    i = 0; j = -1;
                }
            }
        }
    }

    public static Fish[][] copyMap(Fish[][] map) {
        Fish[][] copyMap = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyMap[i][j] = new Fish(map[i][j].num, map[i][j].direction, i, j, map[i][j].shark, map[i][j].alive);;
            }
        }

        return copyMap;
    }
}

class Fish implements Comparable<Fish>{
    int num;
    boolean alive;
    int direction;
    int y, x;
    boolean shark;

    Fish(int n, int d, int y, int x, boolean shark, boolean alive) {
        this.num = n;
        this.direction = d;
        this.y = y;    this.x = x;
        this.shark = shark;
        this.alive = alive;
    }

    @Override
    public int compareTo(Fish o) {
        return num - o.num;
    }
}