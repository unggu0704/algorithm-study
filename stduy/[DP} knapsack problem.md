# knapsack problem

> 배낭문제
> 

[7579번: 앱](https://www.acmicpc.net/problem/7579)

- **예제 입력**
    
    ```jsx
    5 60
    30 10 20 35 40
    3 0 3 5 4
    ```
    
- 예제 출력
    
    ```jsx
    > 6
    ```
    

| 앱 \ 비용 | 1 | 2 | 3 | 4 | 5 | 6 |
| --- | --- | --- | --- | --- | --- | --- |
| 0(30) | 0 | 0 | 30 | 30 | 30 | 30 |
| 1(30,10) | 10 | 10 | 40 | 40 | 40 | 40 |
| 2(30,10,20) | 10 | 10 | 40 | 40 | 40 | 60 |
| 3(30,10,20,35) | 10 | 10 | 40 | 40 | 45 | 60 |
| 4(30,10,20,35,40) | 10 | 10 | 40 | 50 | 50 | 60 |
- `0` 번일 때에는 `30` 의 비용이 3이기 때문에  1,2,3에는 모두 0이 들어간다.
- `1` 번일 경우 `30 - 3` 과 `10 - 0` 의 조합으로 테이블을 채운다.
- `2` 번일 경우 `20 - 3` 이 추가 된다.
- `4` 번일 경우 `35 - 5` 을 추가
- `5` 번일 경우 `40 - 4` 을 추가

빈 칸에 값을 적는데는 다음과 같은 점화식이 계산된다.

> $dp[i][j]=max(dp[i-1][j-c]+mem, dp[i-1][j])$
> 

**해당 메모리를 포함할 경우**

$dp[i-1][j-c]+mem$

→ 현재 메모리 값과 그 무게에 뺸만큼의 이전 값을 더하기 

**해당 메모리를 포함하지 않을 경우**

$dp[i-1][j]$

→ 그냥 위에 값 가져오기 

> (3,5)의 값을 구하는 예시
> 

$max(dp[2][0] + mem, dp[2][5])$ $= max(10 + 35, 40)$

→ 이런 식으로 최댓값을 찾는다.

## Java 코드 풀이

```java
import java.io.*;
import java.util.*;

public class Main {

static int N;
static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

         int[] app = new int[N];
         int[] cost = new int[N];
         int answer = Integer.MAX_VALUE;
         int allCost = 0;

         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < N; i++) {
            app[i] = Integer.parseInt(st.nextToken());
         }

         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            allCost += cost[i];
         }

        int[][] dp = new int[N][100001];
         for (int i = 0; i < N; i++) { // 행의 반복문 
            int mem = app[i]; // 해당 앱의 메모리 
            int c = cost[i]; // 해당 앱의 철거 비용

            for (int j = 0; j < 100000; j++) { //열의 반복문 
                if (i == 0) { // 위의 값이 없으면 우선 셋팅
                    if (j >= c)
                        dp[i][j] = mem;
                }
                else {
                    if (j >= c) { //비용이 음수가 되는 경우 
                        dp[i][j] = Math.max(dp[i - 1][j - c] + mem, dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            
                if (dp[i][j] >= M) {
                    answer = Math.min(answer, j);
                }
            }
         }
         System.out.println(answer);
    }
}
```
