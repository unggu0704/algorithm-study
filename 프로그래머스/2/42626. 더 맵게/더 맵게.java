import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        Arrays.sort(scoville);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i : scoville) {
            pq.offer(i);
        }
        
        int answer = 0;
        while (true) {
            if (pq.isEmpty()) {
                answer = -1;
                break;
            }
            
            int minS = pq.poll(); // 가장 작은 스코빌 
            
            //가장 작은 스코빌이 K 이상이라면?
            if (minS >= K) {
                break;
            }
            
            if (pq.isEmpty()) {
                answer = -1;
                break;
            }
            
            int minSs = pq.poll(); // 두번째로 작은 스코빌
            
            int mix = minS + minSs * 2;
            
            pq.offer(mix);
            answer++;
        }
        

        
        
        return answer;
    }
} 