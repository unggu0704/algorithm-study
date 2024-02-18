import java.util.*;
import java.io.*;

/**
 * Main_18232_텔레포트정거장
 * 
 * @author 김규형
 */
public class Main {
    static int[] dx = {-1,1};
    static int[] map;
    static int N,M,S,E;
    static List[] list;
    public static void main(String[] args)throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N+1];
        list = new List[N+1];
        
        for (int i=0;i<N+1;i++)
            list[i] = new LinkedList<Integer>();
        //입력 종료 
        
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            list[x].add(y);
            list[y].add(x);
        }

        bfs(S);
        System.out.println(map[E]);
    }

    private static void bfs(int x) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);

        while(!queue.isEmpty()){
            
            int poll = queue.poll();
            
            if(poll==E) break;
            for(int i=0;i<2;i++){
                
                int nx = poll+dx[i];
                
                if(nx>=0 && nx<=N && map[nx]==0){
                    map[nx] = map[poll]+1;
                    queue.add(nx);
                }
            }
            for (int i=0;i<list[poll].size();i++){
                
                int next = (int) list[poll].get(i);
                
                if(map[next]==0){
                    map[next] = map[poll]+1;
                    queue.add(next);
                }
            }

        }


    }
}
