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
    }

    public static void solution(BufferedReader br) throws IOException{
        int count = Integer.parseInt(br.readLine());

        List<List<Integer>> rankList = new ArrayList<>();
        Integer[] inputList = new Integer[count];
        int[] indegree = new int[count];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            rankList.add(new ArrayList<>());
        }

        for (int i = 0; i < count; i++) {
            inputList[i] = Integer.parseInt(st.nextToken()) - 1; // 1등 -> 0등으로 값 변경
        }

        // 1 2 0
        for (int i = count - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                rankList.get(inputList[i]).add(inputList[j]); // 순위가 낮을수록 나보다 앞의 리스트를 갖는다.
                indegree[inputList[i]]++;
            }
        } // 초기 간선 정리

        //  print(rankList);

        int changeCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < changeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            if (rankList.get(from).contains(to)) { // from 안에 to 가 있따 -> 원래는 from의 순위가 뒤다.
                rankList.get(from).remove((Integer) to); //from 안에서 to를 지운다.
                rankList.get(to).add(from); // to 안에 from 을 추가한다.
                indegree[from]--; //from의 순위는 올라갔다.
                indegree[to]++; //to의 순위는 내려갓따.
            } else { //역방향
                rankList.get(to).remove((Integer) from);
                rankList.get(from).add(to);
                indegree[to]--;
                indegree[from]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i< indegree.length; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        if (queue.size() > 1) {
            System.out.println("?");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (queue.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }

            int team = queue.poll();
            sb.append((team + 1) + " ");

            // 진입 차수 재설정
            for (int j = 0; j < rankList.size(); j++) {
                if (rankList.get(j).contains((Integer) team)) {
                    rankList.get(j).remove((Integer) team);
                    if(--indegree[j] == 0) {
                        queue.add(j);
                    }
                }
            }

        }

        System.out.println(sb.toString());
    }

}
