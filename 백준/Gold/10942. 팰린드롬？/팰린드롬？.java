import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}

    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N, M;
        N = Integer.parseInt(st.nextToken());
        int[] sequence = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        boolean[][] visited = new boolean[N + 1][N + 1];

        int S, E;
        int flag = 0;
        for (int i = 0; i < M; i++) {
            flag = 1;
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken()) - 1;
            E = Integer.parseInt(st.nextToken()) - 1;
            int inits = S;  int inite = E;
            while (S <= E) {
                if (visited[S][E]) break;

                if (sequence[S] == sequence[E]) {
                    S++;
                    E--;
                }
                else {
                    flag = 0;
                    break;
                }
            }

            if (flag == 1) 
                visited[inits][inite] = true;

            sb.append(flag + "\n");
        }

        System.out.println(sb);

    }
}



