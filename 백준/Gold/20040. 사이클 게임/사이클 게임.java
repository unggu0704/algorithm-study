import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parent = new int[N + 1]; 
        int[] size = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i; //초기에는 자기 자신이 root
        }

        int[][] input = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        // 입력 종료 
        int answer = 0;
        for (int i = 0; i < M; i++) {
            answer++;
            int node1 = input[i][0];
            int node2 = input[i][1];

            int noed1_parent = find(parent, node1);
            int noed2_parent = find(parent, node2);

            if (noed1_parent == noed2_parent) {
                System.out.println(answer);
                System.exit(0);
            }
            
            if (size[noed1_parent] > size[noed2_parent]) {
                parent[noed2_parent] = noed1_parent;
                size[noed2_parent] += size[noed1_parent]; // 트리의 높이를 합친다.
            } else {
                parent[noed1_parent] = noed2_parent;
                size[noed1_parent] += size[noed2_parent]; // 트리의 높이를 합친다.
            }
        }
        System.out.println(0);
    }

    public static int find(int[] parent, int index) {
        if (parent[index] == index)
            return index;
    
        return parent[index] = find(parent, parent[index]); //매번 루트를 갱신해줌 
    }
}