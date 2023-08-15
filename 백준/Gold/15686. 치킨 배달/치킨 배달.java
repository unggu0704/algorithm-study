import java.io.*;
import java.util.*;



public class Main {
	public static void main(String[] args) throws IOException{
        solution();
	}
    static Node map[][];
    static int N,M;
    static Stack<Node> chicken = new Stack<>();
    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Node[N][N];
        int input;
        for(int i = 0 ; i < N; i++){
            st =new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                input = Integer.parseInt(st.nextToken());
                Node n = new Node(input,i,j);
                map[i][j] = n;
            }
        }

        findChicken(0, -1);
        System.out.println(min);
    }
    
    public static void findChicken(int y, int x){
        if(chicken.size() == M){
            for(Node n : chicken){
            	n.visited = true;
                q.add(n);
            }
            initBFS();
            return;
        }
        for(int i = y ; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j].info == 2 && !map[i][j].chickenVistied && (i > y || j > x)){
                    chicken.add(map[i][j]);
                    findChicken(i,j);
                    chicken.pop();
                    map[i][j].chickenVistied = false;
                }
            }
        }
        return;
    }

    static long min = 2000000000;
    static int distance = 0;
    static long chickenDistance = 0;
    static Queue<Node> q = new LinkedList<>();
    static int DX[] = {-1,0,1,0};
    static int DY[] = {0,1,0,-1};

    public static void initBFS(){
        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                BFS();
            }
            distance++;
        }
        initMap();
        if(chickenDistance != 0 ) min = Math.min(chickenDistance, min);
        distance = 0;   chickenDistance = 0;

    }


    public static void BFS(){

        Node n = q.poll();
        if(n.info == 1){
            chickenDistance += distance;
        }
        for(int i = 0; i < 4; i++){
            int dx = n.x + DX[i];   int dy = n.y + DY[i];
            
            if(dy >= N || dx >= N || dy <= -1|| dx <= -1 || map[dy][dx].visited)  continue;

            q.add(map[dy][dx]);
            map[dy][dx].visited = true;
        }

    }

    public static void initMap(){
        for(int i = 0; i < N; i++){
            for(int  j =0; j < N; j++){
            	map[i][j].visited = false;
            }
        }
    }
}


class Node{
    int info = 0; // 0은 빈곳, 1은 집, 2는 치킨집
    boolean visited;
    boolean chickenVistied;
    int y,x;
    Node(int i, int y, int x){
        this.info = i;
        this.y = y;
        this.x = x;
    }


}

