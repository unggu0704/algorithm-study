import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static String[] strs;
    public static int len;
    public static String odd;
    public static HashMap<String, Integer> map;
    public static String ERROR = "I'm Sorry Hansoo";
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        strs = br.readLine().split("");
        len = strs.length;
        map = getHashMap(strs);

        if(!canPalindrome())  {
            System.out.println(ERROR);
            System.exit(0);
        }
        
        System.out.println( makePalindrome(getSortedList(), isOdd()) );
    }
    public static boolean isOdd() {
        return len % 2 != 0;
    }

    public static ArrayList<String> getSortedList() {
        ArrayList<String> tmpList = new ArrayList<>();
        for(String s : map.keySet()) {
            for(int i=0; i<map.get(s)/2; i++) {
                tmpList.add(s);
            }
        }
        Collections.sort(tmpList);
        
        return tmpList;
    }

    public static HashMap<String, Integer> getHashMap(String[] strs) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String str : strs) {
            map.put(str, map.getOrDefault(str, 0)+1);
        }
        return map;
    }
    public static String makePalindrome(ArrayList<String> strs, boolean isOdd) {
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        for(String s : strs) 
            sb.append(s);
        
        result.append(sb);
        if(isOdd) result.append(odd);
        result.append(sb.reverse());

        return result.toString();
    }
    public static boolean canPalindrome() {
        if(len % 2 == 0) {
            for(Integer value : map.values()) {
                if(value%2 != 0) return false;
            }
            return true;
        }

        int cnt = 1;
        
        for(String key : map.keySet()) {
            if(cnt<0) return false;
            if(map.get(key)%2 != 0) {
                cnt--;
                odd = key;
            }
        }
        
        return true;
    }
}