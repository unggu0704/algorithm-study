import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while (true) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if (num == 0)
                break;

            int[] lottoList = new int[num];
            int[] combine = new int[6];
            for (int i = 0; i < num; i++) {
                lottoList[i] = Integer.parseInt(st.nextToken());
            }         
            backTracking(lottoList, combine, 0, 0);
            System.out.println();
        }
    }

      public static void backTracking(int[] lottoList, int[] combine, int depth, int index) {
        if (depth == 6) {
            for (int i : combine) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = index; i < lottoList.length; i++) {
            combine[depth++] = lottoList[i];
            backTracking(lottoList, combine, depth, i + 1);
            depth--;
        }
        return ;
    }
}