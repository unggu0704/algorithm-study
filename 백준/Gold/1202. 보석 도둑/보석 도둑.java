import java.io.*;
import java.util.*;

public class Main {

static int N;
static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<Jewrly> jewrlyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewrlyList.add(new Jewrly(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(jewrlyList, (o1, o2) -> o1.weight - o2.weight);

        ArrayList<Integer> bagList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            bagList.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(bagList); // 적은가방부터 오름차순 정렬
        
        PriorityQueue<Jewrly> pq = new PriorityQueue<>((j1, j2) -> {
            int a1 = j1.value;
            int a2 = j2.value;

            return a2 - a1;
        }); //가치를 기준으로 내림차순 정렬 

        long answer = 0;
        for (int i = 0, j = 0; i < M; i++) { // i는 가방의 인덱스, j는 보석 인덱스
            while ( j < N && jewrlyList.get(j).weight <= bagList.get(i)) {
                pq.add(jewrlyList.get(j++));
            }
            
            if (pq.isEmpty()) continue;

            answer += pq.poll().value; //가장 가치가 높은 셋트 가져오기 
        }

        System.out.println(answer);
    }
}

class Jewrly {
    int weight;
    int value;

    Jewrly(int w, int v) {
        this.weight = w;
        this.value = v;
    }
}