import java.io.*;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		solution();
	}


    /**
     * 1. 마을을 분리한다
     * 2. 지울 수 있는 길 -> 분리된 두 마을 사이의 길
     *                 -> 중복되지 않은 다른 길이 
     * @throws IOException
     */
    public static void solution() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        List<Node>[] lists;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lists = new ArrayList[N + 1];   
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i < N+1; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            lists[n1].add(new Node(n2, weight));
            lists[n2].add(new Node(n1, weight));
        }
        // 그래프 셋팅 완료

        pq.add(new Node(1, 0));

        int dist = 0;
        int max = 0;
        while (!pq.isEmpty()) {
            
            Node node = pq.poll();

            if (visited[node.name]) continue;
            else visited[node.name] = true;

            max = Math.max(node.weight, max);
            dist += node.weight; // 최소 힙  더하기 

            for (int i = 0; i < lists[node.name].size(); i++) { // 인접 노드를 모두 확인해 가장 작은 값을 최소 힙으로 탐색 
                Node nextNode = lists[node.name].get(i);
                if (visited[nextNode.name]) continue;
                else pq.add(nextNode);
            }
        }

        System.out.println(dist - max);
        
    }
}



class Node implements Comparable<Node>{
    int name;
    int weight;
    boolean visited;
    Node(int name, int weight) {
        this.weight = weight;
        this.name = name;
    }

    @Override
    public int compareTo(Node n) {
        return this.weight - n.weight;
    }
}



