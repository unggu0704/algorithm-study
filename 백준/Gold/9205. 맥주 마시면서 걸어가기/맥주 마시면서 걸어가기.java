
import java.util.*;
import java.io.*;
import java.math.*;
/**
 *
 * - 알고리즘 풀이

 * */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
        
        for (int i = 0; i < tc; i++) 
            canAttendFestival(br);
    }
    
    public static void canAttendFestival(BufferedReader br) throws IOException{
        StringTokenizer st;
        int convinenceStoreCount = Integer.parseInt(br.readLine()); //편의점

        st = new StringTokenizer(br.readLine());

        Node home = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), false); // 집의 위치를 받는다.

        List<Node> nodeList = new ArrayList<>(); //집이 아닌 곳들의 List
        for (int i = 0; i < convinenceStoreCount; i++) {
            st = new StringTokenizer(br.readLine());
            nodeList.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), false));
        }

        //마지막은 페스티벌
        st = new StringTokenizer(br.readLine());
        nodeList.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), true));

        /////////////////////// 입력 종료 ////////////////

        Queue<Node> queue = new LinkedList<>(); // 지금 가지고 있는 맥주로 갈 수 있는 Node
        queue.add(home); //처음은 집에서 시작

        // 한 Node를 기점으로 맥주를 마시면서 갈 수 있는 장소를 탐색
        while(!queue.isEmpty()) {
            Node nowLocation = queue.poll();

            if (nowLocation.isFestival) { //축제에 도착!
                System.out.println("happy");
                return;
            }

            for (Node node : nodeList) {
                if (node.visited)
                    continue;

                int distance = Math.abs(node.y - nowLocation.y) + Math.abs(node.x - nowLocation.x); // 두 지점 사이의 멘헤튼 거리

                if (distance <= 1000) {//맥주 20상자로 갈 수 있음
                    queue.add(node);
                    node.visited = true;
                }
            }
        }

        System.out.println("sad");
    }
}

class Node {
    int y, x; // 2차원 상의 Node의 좌표
    boolean visited; // 이미 방문한 편의점 인가? or 집인가
    boolean isFestival; // 페스티벌인가?

    Node(int y, int x, boolean isFestival) {
        this.y = y;
        this.x = x;
        this.isFestival = isFestival;
    }
}