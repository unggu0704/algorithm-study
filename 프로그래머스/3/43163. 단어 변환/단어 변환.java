import java.util.*;

class Solution {
    Queue<String> queue = new LinkedList<>();
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        // target이 words에 없으면 변환 불가능
        boolean targetExists = false;
        for (String word : words) {
            if (word.equals(target)) {
                targetExists = true;
                break;
            }
        }
        if (!targetExists) return 0;
        
        int answer = 0;
        int length = begin.length();
        int wordsLength = words.length;
        visited = new boolean[words.length];

        queue.offer(begin);

        while (true) {
            if (queue.isEmpty())
                return 0;

            int size = queue.size();
            answer++;
            for (int i = 0; i < size; i++) {
                String word = queue.poll(); // 현재 탐색 중인 단어
                
                // 각 단어와 비교 (단순화된 구조)
                for (int k = 0; k < wordsLength; k++) {
                    if (visited[k])
                        continue;

                    String targetWord = words[k]; // 이번에 비교할 단어
                    
                    // 정확히 한 글자만 다른지 확인
                    int diffCount = 0;
                    for (int p = 0; p < length; p++) {
                        if (word.charAt(p) != targetWord.charAt(p)) {
                            diffCount++;
                            if (diffCount > 1) break; // 2개 이상 다르면 중단
                        }
                    }
                    
                    // 정확히 1개만 다를 때만 처리
                    if (diffCount == 1) {
                        visited[k] = true;
                        queue.offer(targetWord);

                        if (targetWord.equals(target))
                            return answer;
                    }
                }
            }
        }
    }
}