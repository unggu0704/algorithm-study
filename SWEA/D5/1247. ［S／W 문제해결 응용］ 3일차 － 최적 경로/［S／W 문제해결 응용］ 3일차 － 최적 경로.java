import java.util.*;


public class Solution {
    static int N ;
    static int [] customerX ;
    static int [] customerY ;
    static int comX,comY,homX,homY;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T  = sc.nextInt();
        for(int tc = 1; tc<=T; tc++) {
            N= sc.nextInt(); //

            customerX = new int[N];
            customerY = new int[N];
            comX = sc.nextInt();
            comY = sc.nextInt();
            homX = sc.nextInt();
            homY = sc.nextInt();
            for(int i =0; i<N; i++) {
                customerX [i] = sc.nextInt();
                customerY [i] = sc.nextInt();
            }
            order = new int [N];
            visted = new boolean[N];

            ans = 987654321;

            execute(0);
            System.out.println("#" +tc + " " + ans);
        }

    }
    static int [] order;
    static boolean[] visted;
    static int ans;
    public static void execute(int idx) {
        //N 에 도달한다면 ?
        if(idx == N) {
            int distance = 0; // 현재 거리
            int curX = comX; // 현재 X값이 회사의 값이 되어야한다. 회사에서 출발하니까
            int curY = comY;

            for(int i =0; i < N; i++) {
                //회사에서 출발해서 고객까지와의 거리를 계산
                distance += (Math.abs(curX - customerX[order[i]]) + Math.abs(curY - customerY[order[i]]));
                curX = customerX[order[i]];
                curY = customerY[order[i]];
            }
            // 위에서 고객- 고객 , 회사 -고객 간의 거리를 끝내고 마지막 현재값에서 집으로 가야하기위한 거리값 구하기
            distance += (Math.abs(curX-homX)+Math.abs(curY-homY)); // 거리값 계산 끝내기
            ans = Math.min(ans, distance);
            return;
        }

        //로직
        for(int i =0; i<N; i++) {
            if(visted[i]) continue; // 값이 true이면 넘긴다.
            order[idx]  = i;// 현재 i 값을 저장 idx로 체크
            visted[i] = true;
            execute(idx +1); //백트래킹
            visted[i] = false;
        }

    }
}