import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        ArrayList<Integer> seq = new ArrayList<>();

        while (true) {
            int input = Integer.parseInt(st.nextToken());
            if (input == 0)
                break;
            
            seq.add(input);
        }
        // 입력 종료 

        int[][][] dp = new int[seq.size()][5][5];

    
        System.out.println(DDR(0,0,0,seq,dp));

    }

    public static int DDR(int step, int right, int left, ArrayList<Integer> seq, int[][][] dp) {
        if (step == seq.size()) 
            return 0;

        if (dp[step][left][right] != 0)
            return dp[step][left][right];

        int leftFeet = moveFeet(left, seq.get(step)) + DDR(step + 1, right, seq.get(step), seq, dp); // 왼발을 움직이고 다음 재귀로 새로 움직인 왼발의 정보를 줌 
        int rightFeet = moveFeet(right, seq.get(step)) + DDR(step + 1, seq.get(step), left, seq, dp); // 오른발을 움직이고 새로운 오른발 

        return dp[step][left][right] = Math.min(leftFeet, rightFeet);
    }

    public static int moveFeet(int feet, int destination) {
        if (feet == destination) {
            return 1;
        } else if ((feet == 1 && destination == 4) || (feet == 3 && destination == 4) || (feet == 2 && destination == 3) || (feet == 1 && destination == 2)
                    ||(destination == 1 && feet == 4) || (destination == 3 && feet == 4) || (destination == 2 && feet == 3) || (destination == 1 && feet == 2) ){
            return 3;
        } else if ((feet == 1 && destination == 3) || (feet == 2 && destination == 4) 
                    || (destination == 1 && feet == 3) || (destination == 2 && feet == 4)  ) {
            return 4;
        } else {
            return 2;
        }
        
    }

}