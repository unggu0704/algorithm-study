import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] realation = new int[N + 1]; 
        int[] size = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            realation[i] = i; //초기에는 자기 자신이 root
        }

        st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> candyCountMap = new HashMap<>();
        for (int i = 1; i < N + 1; i++) {
            candyCountMap.put(i, Integer.parseInt(st.nextToken()));
        }

        int[][] input = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        // 입력 종료 
        for (int i = 0; i < M; i++) { // -> 10만
            int node1 = input[i][0];
            int node2 = input[i][1];

            int noed1_parent = find(realation, node1);
            int noed2_parent = find(realation, node2);


            if (size[noed1_parent] > size[noed2_parent]) {
                realation[noed2_parent] = noed1_parent;
                size[noed2_parent] += size[noed1_parent]; // 트리의 높이를 합친다.
            } else {
                realation[noed1_parent] = noed2_parent;
                size[noed1_parent] += size[noed2_parent]; // 트리의 높이를 합친다.
            }
        }
        // 아이들의 관계를 열결함
        
        Set<Integer> set = new HashSet<>();
        
        for (int i : realation) { // 10만 
            set.add(find(realation, i)); // 루트만 받아온다 .
        }

        ArrayList<FriendRelation> list = new ArrayList<>();
        
        for (int root : set) {
            int candyCount = 0;
            int friendCount = 0;
            for (int i = 1; i < N + 1; i++) {
                if (root == find(realation, i)) {
                    candyCount += candyCountMap.get(i);
                    friendCount++;
                }
            }
            if (friendCount >= K) continue; //이미 이 관계로 공명이 된다면 만들지 않는다.

            list.add(new FriendRelation(root, candyCount, friendCount));
        }

        // FriendRelation[] arr = list.toArray(FriendRelation[]::new);
        // backTracking(arr, 1, 0, 0, K);

        int[][] dp = new int[2][K];

        for (FriendRelation friendRelation : list) {
            for (int i = 0; i < K; i++) {
                if (friendRelation.childCount <= i) {
                    dp[0][i] = Math.max(dp[1][i], friendRelation.candyCount + dp[1][i - friendRelation.childCount]);
                }
            }

            for (int i = 0; i < K; i++) {
                dp[1][i] = dp[0][i];
            }
        }
        System.out.println(dp[1][K - 1]);
        
    }
    static int MAX = Integer.MIN_VALUE;
    public static void backTracking(FriendRelation[] arr, int index, int candyCount, int childCount, int K) {
        if (childCount >= K) {
            return;
        }

        MAX = Math.max(candyCount, MAX);
        for (int i = index; i < arr.length; i++) {
            backTracking(arr, i + 1, candyCount + arr[i].candyCount, childCount + arr[i].childCount, K);
        }
    }

    public static int find(int[] parent, int index) {
        if (parent[index] == index)
            return index;
    
        return parent[index] = find(parent, parent[index]); //매번 루트를 갱신해줌 
    }

  
}

class FriendRelation {
    int root;
    int candyCount;
    int childCount;

    FriendRelation(int r, int candyCount, int childCount) {
        this.root = r;
        this.candyCount = candyCount;
        this.childCount = childCount;
    }
}
