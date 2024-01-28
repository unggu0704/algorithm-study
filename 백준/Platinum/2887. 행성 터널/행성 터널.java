import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    public static void main(String[] args) throws IOException {
        long answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<PlanetEdge> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine()); // N개의 행성

        List<Planet> space = new ArrayList<>(N);

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            space.add(new Planet(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))); //입력받은 즉시 행성을 생성
        }

        space.sort(Comparator.comparingInt(p -> p.x));
        for (int i = 0; i < N - 1; i++) {
            int weight = Math.abs(space.get(i).x - space.get(i + 1).x);
            list.add(new PlanetEdge(space.get(i).planetNum, space.get(i + 1).planetNum, weight));
        }
        space.sort(Comparator.comparingInt(p -> p.y));
        for (int i = 0; i < N - 1; i++) {
            int weight = Math.abs(space.get(i).y - space.get(i + 1).y);
            list.add(new PlanetEdge(space.get(i).planetNum, space.get(i + 1).planetNum, weight));
        }
        space.sort(Comparator.comparingInt(p -> p.z));
        for (int i = 0; i < N - 1; i++) {
            int weight = Math.abs(space.get(i).z - space.get(i + 1).z);
            list.add(new PlanetEdge(space.get(i).planetNum, space.get(i + 1).planetNum, weight));
        }

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        Collections.sort(list);

        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            PlanetEdge edge = list.get(i);

            // 사이클이 발생하는 간선은 제외.
            if (find(edge.start) != find((edge.end))) {
                answer += edge.weight;
                union(edge.start, edge.end);
            }
        }

        System.out.println(answer);
        br.close();
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

}

class PlanetEdge implements Comparable<PlanetEdge>{
    int start;
    int end;

    int weight;
    PlanetEdge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(PlanetEdge p) {
        return this.weight - p.weight;
    }
}

class Planet {
    Planet parent = this;
    
    int planetNum;
    int x,y,z;
    int linkWeight = Integer.MAX_VALUE;

    Planet(int planetNum, int x, int y, int z) {
        this.planetNum = planetNum;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}



