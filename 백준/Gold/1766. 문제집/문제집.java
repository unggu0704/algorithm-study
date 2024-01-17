import javax.print.attribute.IntegerSyntax;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int 문제집 = Integer.parseInt(st.nextToken());
        int 규칙개수 = Integer.parseInt(st.nextToken());

        List<List<Integer>> 문제집학습순서 = new ArrayList<>();
        PriorityQueue<Integer> 쉬운문제집큐 = new PriorityQueue<>(); //우선순위 큐의 기본은 오름차순

        int[] 진입차수 = new int[문제집]; //진입차수 정보
        List<Integer> 정답리스트 = new ArrayList<>();

        for (int i = 0; i < 문제집; i++) {
            문제집학습순서.add(new ArrayList<Integer>());
        } //이중 리스트 할당

        for (int t = 0; t < 규칙개수; t++) {
            st = new StringTokenizer(br.readLine());
            int 선수문제집 = Integer.parseInt(st.nextToken()) - 1;
            int 후행문제집 = Integer.parseInt(st.nextToken()) - 1;

            if (선수문제집 == 후행문제집) continue;

            문제집학습순서.get(선수문제집).add(후행문제집);
            진입차수[후행문제집]++;
        }

        while (true) {
            for (int i = 0; i < 진입차수.length; i++) {
                if (진입차수[i] == 0) {
                    쉬운문제집큐.add(i);
                    진입차수[i] = Integer.MIN_VALUE; // 다시 체킹 하지 않게 설정
                }
            }

            if (쉬운문제집큐.isEmpty())
                break;

            int 풀문제집 = 쉬운문제집큐.poll();
            정답리스트.add(풀문제집);
            List<Integer> 후행문제집들 = 문제집학습순서.get(풀문제집);

            for (int i : 후행문제집들) {
                진입차수[i]--;
            }
        }
        for (int answer: 정답리스트) {
            sb.append((answer + 1) + " ");
        }
        System.out.println(sb.toString());
    }
}
