
import java.io.*;
import java.util.*;


/**
    N = 4 -> 3
            AB | CD
            AC | BD
            AD | BC
            = 4C2 * 1/2! = 3
            
    N = 6 -> 10
             ABC | DEF
             ABD | CEF
             ABE | CDF
             ABF | CED
             ADC | BEF
             AEC | CBF
             AFC | DEB
             DBC | AEF
             EBC | DAF
             FBC | DEA
             6C3 * 3C3 * 1/2 *  = 10

             NC2/N * 1/2 = 정답
             20C10 * 1/2 = 400만 이하? -> 40ms
             
 */

public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    static int N;
    static int answer = Integer.MAX_VALUE;
    static int[][] abiltyMap;

    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        abiltyMap = new int[N][N];

        int[] allTeam = new int[N]; // 모든 팀의 이름이 들어가 있는 배열 
        int[] combine = new int[N/2]; // 팀의 조합 start 팀

        for (int i = 0; i < N; i++) {
            allTeam[i] = i;
        }
        

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j  =0; j < N; j++) {
                abiltyMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 종료 

        backTracking(allTeam, combine, 0, 0);

        System.out.println(answer);
    }

    public static void backTracking(int[] allTeam, int[] combine, int depth, int index) {
        if (depth == N / 2) { // 인원이 반으로 갈렸다면?
            int[] alterCombine = new int[N / 2];


            int alterIndex = 0;
            for (Integer i : allTeam) {
                if (!Arrays.stream(combine).anyMatch(i :: equals)) { //배열 내에 맞는게 없다면 
                    alterCombine[alterIndex++] = i; // 상대편에 넣는다!
                }
            }

            int aTeamScore = 0;
            int bTeamScore = 0;
            for (int i : combine) {
                for (int j : combine) {
                    aTeamScore += abiltyMap[i][j];
                }
            }

            for (int i : alterCombine) {
                for (int j : alterCombine) {
                    bTeamScore += abiltyMap[i][j];
                }
            }

            // System.out.println("A 팀 ");
            // for (int i = 0; i < depth; i++) {
            //     System.out.print(combine[i] + " ");
            // }
            // System.out.println();

            //  System.out.println("B 팀 ");
            // for (int i = 0; i < depth; i++) {
            //     System.out.print(alterCombine[i] + " ");
            // }
            // System.out.println();

            // System.out.print(aTeamScore + " | " + bTeamScore);
            // System.out.println();
            answer = Math.min(answer, Math.abs(bTeamScore - aTeamScore));
            return;
        }


        for (int i = index; i < N; i++) {
            combine[depth] = allTeam[i]; // 전체 멤버 중에 한명씩 조합 팀에 넣는다.
            backTracking(allTeam, combine, depth + 1, i + 1);
        }

    }
}


