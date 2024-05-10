import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static int N, M;
    static StringBuilder sb;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            solution(br);
        }

    }

    public static void solution(BufferedReader br) throws  Exception{
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //문서의 양
        int M = Integer.parseInt(st.nextToken()); //알고 싶은 문서 번호

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Document> pq = new PriorityQueue<>();
        Queue<Document> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            pq.offer(new Document(input, i)); // 우선순위 입력 값과 번호 할당
            q.offer(new Document(input, i));
        }

        int ans = 0;
        loop:
        while (!pq.isEmpty()) {
            Document pqdocument = pq.poll();
            while(!q.isEmpty()) {
                Document document = q.poll();
                
                if (pqdocument.priority != document.priority) {
                    q.offer(document);
                    continue;
                }

                ans++;

                if (document.number == M)
                    break loop;

                break;
            }
        }

        System.out.println(ans);
    }
}

class Document implements Comparable<Document>{
    int priority;
    int number;

    Document(int priority, int number) {
        this.priority = priority;
        this.number = number;
    }

    @Override
    public int compareTo(Document o) {
        return o.priority - this.priority;
    }
}