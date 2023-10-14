import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}
	  static boolean visited[];
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

    	int N = Integer.parseInt(st.nextToken());

    	int originRGB[][] = new int [N][3];
       
        int[] RGBmin = new int [3];
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < 3; j++) {
    			originRGB[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
        // 입력 종료 

    	int value;
        
        for (int r = 0; r < 3; r++ ) {
            
            int RGB[][] = copy(originRGB, N);
            for(int i = 2; i < N; i++) {
                for(int j = 0; j < 3; j++) {
                    value = 2000000000;
                    for(int prej = 0; prej < 3; prej++) {
                        if(prej == j) continue;	// 이전과 같은 색깔이면 넘어감 

                        if (i == N - 1 && j == r) continue;
                        if (i == 2 && prej == r) continue;
                        
                        value = Math.min(RGB[i-1][prej] + RGB[i][j], value); // 이전 i의 인덱스를 돌면서 최소 값을 찾음 
                    }
                    RGB[i][j] = value;
                }
            }
            value = 2000000000;
            for(int j = 0; j < 3; j++) {
                value = Math.min(value, RGB[N-1][j]);
            }
            RGBmin[r] = value + originRGB[0][r];
        }

        int result = 2000000000;
        for (int i : RGBmin) {
            result = Math.min(i, result);
        }

        System.out.println(result);
    	
    
    }
    
    public static int[][] copy(int arr[][], int N) {
        int[][] newArr = new int[N][3];

        for (int i = 0; i <arr.length; i++) {
            for (int j = 0; j <arr[i].length; j++) {
                newArr[i][j] = arr[i][j];
            }
        }

        return newArr;
    }
}



