import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 데일리 과제
 * 1244 - 스위치 켜고 끄기 S4
 * @author 김규형
 *
 */
public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] switches = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        List<People> peopleList = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            peopleList.add(new People(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        // 입력 종료

        for (People people : peopleList) {
            int remoteNum = people.remoteNum;
            if (people.sex == 1) {
                while (remoteNum < N + 1) {
                    if (remoteNum % people.remoteNum == 0) //값이 나누어 떨어지는가?
                        changeRemote(switches, remoteNum);
                    remoteNum++;
                }
            } else {
                changeRemote(switches, remoteNum);
                int index = 1;
                while (true) {
                    int left = remoteNum - index;
                    int right = remoteNum + index;

                    if (left <= 0 || right <= 0 || left > N || right > N || switches[left] != switches[right])
                        break;


                    changeRemote(switches, left);
                    changeRemote(switches, right);
                    index++;
                }
            }
        }

        int count = -1;
        for (int s : switches) {
            if (count == -1) {
                count++;
                continue;
            }

            if (count == 0) {
                sb.append(s);
            } else {
                sb.append(" " + s);
            }

            if (count++ >= 19)  {
                sb.append("\n");
                count = 0;
            }
        }

        System.out.println(sb.toString());
    }

    public static void changeRemote(int[] switches, int index) {
        if (switches[index] == 0) {
            switches[index] = 1;
        } else {
            switches[index] = 0;
        }
    }
}

class People {
    int sex;
    int remoteNum;

    public People(int sex, int remoteNum) {
        this.sex = sex;
        this.remoteNum = remoteNum;
    }
}