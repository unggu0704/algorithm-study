
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    static int N, W, L;

    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        

        N = Integer.parseInt(st.nextToken()); // 트럭의 갯수 
       W = Integer.parseInt(st.nextToken()); // 다리의 길이 
       L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중 

        int[] truckList = new int[N];
        int[] bridge = new int[W]; // 다리 

       st = new StringTokenizer(br.readLine());

       for (int i  = 0; i < N; i++) {
            truckList[i] = Integer.parseInt(st.nextToken());
       }

       int truckIndex = 0;
       int answer = 0;

       while (check(bridge, truckList)) {
            answer++;     // 시간은 흘러간다.
            // 트럭이 이동하는 작업 
            bridge[W - 1] = 0; // 가장 마지막 트럭 다리 빠져나감 
            for (int i = W - 1; i > 0 ; i--) {
                bridge[i] = bridge[i- 1];
            }
            bridge[0] = 0;

            // 다리 사이즈를 측정
            int weight = 0;
            for (int i : bridge) {
                weight += i;
            }
            if (truckIndex < truckList.length ) weight += truckList[truckIndex];

            if (weight <= L && truckIndex < truckList.length) { // 다음 트럭이 들어올수 있어야 실행 
                bridge[0] = truckList[truckIndex]; // 다음 트럭이 다리에 올라선다.
                truckList[truckIndex++] = 0;
            } 
       }

       System.out.println(answer);
    }

    public static boolean check(int[] bridge, int[] truck) {

        for (int t : truck) {
            if (t != 0)
                return true;
        }
        for (int b : bridge) {
            if (b != 0)
                return true;
        }

        return false;
    }
}


