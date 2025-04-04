import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Edge>[] edges;
    static int[] dist;
    static final int MAX_DIST = 50000 * 1000 + 1;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, MAX_DIST);
        for(int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }
        pq.add(new Edge(1, 0));
        dist[1] = 0;
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(e.dist != dist[e.from])
                continue;
            for(Edge edge : edges[e.from]){
                int newDist = edge.dist + e.dist;
                if(newDist < dist[edge.from]){
                    dist[edge.from] = newDist;
                    pq.add(new Edge(edge.from, newDist));
                }
            }
        }
        System.out.println(dist[N]);
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int dist;

        Edge(int from, int dist){
            this.from = from;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
