import java.io.*;

import java.util.*;

/**
 * Main_15961_회전초밥_김규형
 *
 * @author 김규형
 */
public class Main {
    public static void main(String[] args) throws IOException{
        solution();
    }

    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N,d,k,c;
        int belt[];
        int ans = 0;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹을수 있는 접시의 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        belt = new int[N+k];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            belt[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0 ; i <k; i++) {
            belt[N+i] = belt[i];
        } // 원형큐를 흉내

        // 입력 처리 종료

        //투포인터 피봇 선언
        int start = 0;
        int end = k;
        int eaten = 0;
        int[] visited = new int[d + 1];

        for (int i = 0; i < k; i++) {
            if (visited[belt[i]] == 0)
                eaten++;

            visited[belt[i]]++;
        }

        while (end < N + k) {
            if (--visited[belt[start]] == 0)  //더이상 먹지 못하는 초밥이라면?
                eaten--;
            start++;


            if (visited[belt[end]]++ == 0) //지금까지 먹지 않은 초밥이였다면?
                eaten++;
            end++;

            if (visited[c] == 0) {//쿠폰으로 사먹기 가능?
                eaten++;
                visited[c]++;
            }


            ans = Math.max(eaten,  ans);
        }

        System.out.println(ans);
    }


}