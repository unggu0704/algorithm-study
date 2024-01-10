import java.io.*;
import java.util.*;



import java.util.Scanner;
import java.io.FileInputStream;

public class Main {

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 조카 수
        int N = Integer.parseInt(st.nextToken()); // 과자 수
        int[] arr = new int[N]; // 과자의 길이를 담을 배열
        st = new StringTokenizer(br.readLine());
        int max = 0; // 길이 최대값
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        System.out.println(BinarySearch(arr, M, 1, max));
    }

    public static int BinarySearch(int[] arr, int M, int left, int right) {
        int result = 0;
        while (left <= right) {
            int mid =  left + (right - left) / 2;
            int count = 0;

            for (int i = 0; i < arr.length; i++) {
                count += arr[i] / mid;
            }

            if (count >= M) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;

    }


}