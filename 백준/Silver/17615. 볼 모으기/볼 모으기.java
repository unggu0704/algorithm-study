
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        br.readLine();
        String ballList = br.readLine();
   
        int rCount = 0;
        int bCount = 0;

        for (int i = 0; i < ballList.length(); i++) {
            if (ballList.charAt(i) == 'R')
                rCount++;
            else
                bCount++;
        } 

        //전부 왼쪽으로
        int redLeft = rCount - fowradCalculate('R', ballList);
        int blueLeft = bCount - fowradCalculate('B', ballList);

        //전부 오른쪽으로 
        int redRight = rCount - backCalculate('R', ballList);
        int blueRight = bCount - backCalculate('B', ballList);

        int leftest = Math.min( redLeft, blueLeft);
        int rightest = Math.min(redRight, blueRight);

        System.out.println(Math.min(leftest, rightest));
    }

    public static int fowradCalculate(char C, String ballList) {
        int cnt = 0;
        for (int i = 0; i < ballList.length(); i++) {
            if (ballList.charAt(i) == C) {
                cnt++;
            } else {
                return cnt;
            }
        }
        System.out.println(0);
        System.exit(0);
        return cnt;
    }

    public static int backCalculate(char C, String ballList) {
        int cnt = 0;
        for (int i = ballList.length() - 1; i > -1; i--) {
            if (ballList.charAt(i) == C) {
                cnt++;
            } else {
                return cnt;
            }
        }
        System.out.println(0);
        System.exit(0);
        return cnt;
    }

    
}   