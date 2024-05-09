import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int c;
    static StringBuilder sb;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        final Map<Integer, Integer> map = new HashMap<>();

        final List<Integer> list = new ArrayList<>();
        final List<Integer> list2 = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(st.nextToken());
            list.add(input);
            list2.add(input);
            map.put(input, map.getOrDefault(list.get(i), 0) + 1);
        }

        list.sort((o1, o2) -> {
            if(map.get(o2).equals(map.get(o1))) {
                return list2.indexOf(o1) - list2.indexOf(o2);
            }
            return map.get(o2) - map.get(o1);
        });

        sb = new StringBuilder();
        for (Integer num : list) {
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString());
    }
}