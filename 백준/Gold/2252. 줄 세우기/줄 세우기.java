
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}

    /**
     * 2252 : 줄 세우기 
     *  
     * union - find ...?
     * 부모를 연결해야하는게 쉬울거 같음...
     * 하나씩 요소들의 부모를 출력 
     * 5 4
     * 2 1
     * 3 2
     * 3 4
     * 
     * 3 2 1 4
     * 
     * @throws IOException
     */

    static boolean[] visited;
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N + 1]; // 0은 비어있다.
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            Node n = new Node(i);
            nodes[i] = n;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            nodes[child].parentList.add(nodes[parent]); // 부모 노드들을 추가 
        }

        for (int i = 1; i < N + 1; i++) {
            if (visited[i]) continue;

            find(nodes[i]);

            // visited[nodes[i].value] = true;
            // System.out.print(nodes[i].value + " ");
        }

    }

    public static void find(Node node) {
        for (Node n : node.parentList) {
            if (visited[n.value]) continue; // 이미 해당 노드의 부모는 탐색했기에 찾지 않음 

            find(n);
        } //계속해서 부모를 찾는다.
        // 더이상 부모가 없다면
        visited[node.value] = true;
        System.out.print(node.value + " ");
    }
}

class Node {
    int value;

    ArrayList<Node> parentList = new ArrayList<>();

    Node(int value) {
        this.value = value;
    }
}


