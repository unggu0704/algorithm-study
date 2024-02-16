import java.io.BufferedReader;

import java.util.*;


public class Main {
	public static void main(String[] args) {
		solution();
	}
	
	
    public static void solution() {
    	Scanner s = new Scanner(System.in);
    	
    	int N, Money, dMoney;
    	int[] City;
    	boolean[] flag;
    	N = s.nextInt();
    	City = new int[N];

    	
    	for(int i = 0; i < N; i++) {
    		City[i] = s.nextInt();
     	}
    	Money = s.nextInt();
    	// 입력 처리 
    	
    	Arrays.sort(City); // 예산대로 내림 차순 정렬 
    	
    	for(int i = 0; i < N; i++) {
    		dMoney = Money / ( N - i);
    		
    		if(dMoney > City[i]) { // 지불 할수 있다면? 
    			dMoney -= City[i];
    			Money = (Money - dMoney) + dMoney - City[i]; // 예산을 할당하고 남은돈은 다시 회수한다.
    		}
    		else { // 지불할수 없는 경우 
    			System.out.println(dMoney); // 나머지 뒤 금액은 상한가를 설정해서 할당 후 종료 
    			System.exit(0); 
    		}
    		
    	}
    	
    	System.out.println(City[N-1]); // 최댓값 출력 
    	
    	
    	
    	
    }
}


