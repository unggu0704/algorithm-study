import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
 
 /**
  * SWEA_1233_사칙연산_유효성검사_김규형
  * 메모리
  * 21,996 kb
  * 시간
  * 135 ms
  * 
  * @author 김규형
  *
  */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int index = Integer.parseInt(br.readLine());
        for (int i = 1; i <= index; i++) {
        	System.out.print("#" + i + " ");
            solution(br);
        }
    }
    
    static int N;
   
    public static void solution(BufferedReader br) throws IOException {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int[] kyuCards = new int[9];
    	int[] wooyongCards = new int[9];
    	
    	for (int i = 0; i < 9; i++) {
    		kyuCards[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	int index = 0;
    	for (int i = 1; i < 19; i++) {
    		for (int j = 0; j < 9; j++) {
    			if (i == kyuCards[j]) {
    				break;
    			}
    			
    			if (j == 8) {
    				wooyongCards[index++] = i;
    			}
    		}
    	}
    	
    	Arrays.sort(wooyongCards);
    	
    	int win = 0, lose = 0;
    	do {
    		int wooyong = 0;	int kyu = 0;
    		for (int i = 0; i < 9; i++) {
    		//	System.out.print(wooyongCards[i] + " ");
    			if (wooyongCards[i] > kyuCards[i]) {
    				wooyong += wooyongCards[i] + kyuCards[i];
    			} else if (wooyongCards[i] < kyuCards[i]) {
    				kyu += wooyongCards[i] + kyuCards[i];
    			}
    		}
    		
    		if (kyu > wooyong) {
    			win++;
    		} else if (kyu < wooyong)   {
    			lose++;
    		}
    		
    	} while(np(wooyongCards));
    	
    	
    	System.out.println(win + " " + lose);
    }
    
    public static boolean np(int[] arr) {
    	int i = arr.length - 1;
    	while (i > 0 && arr[i - 1] >= arr[i]) {
    		--i;
    	}
    	
    	if (i == 0) {
    		return false;
    	}
    	
    	int j = arr.length - 1;
    	while (arr[i - 1] >= arr[j]) {
    		--j;
    	}
    	
    	swap(arr, i - 1, j);

    	//Arrays.sort(arr, i + 1, arr.length);
    	
    	 int k = arr.length - 1;
    	    //교환한 인덱스 뒤에서부터 끝까지 오름차순 정렬
	    while (i < k) {
	        swap(arr, i++, k--);

	    }
    	
    	return true;
    }
    
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}