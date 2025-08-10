import java.util.*;

class Solution {
    
    int[] arr;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                
                if(computers[i][j] == 1) {
                    int aRoot = isParent(i);
                    int bRoot = isParent(j);
                    
                    if (aRoot != bRoot) { //부모 합치기
                        arr[bRoot] = aRoot;
                    }
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(isParent(arr[i]));
        }
        
        answer = set.size();
        
        
        return answer;
    }
    
    public int isParent(int num) {
        if (num == arr[num]) { //부모를 반환
            return num;
        }
        
        return arr[num] = isParent(arr[num]);
    }
}


