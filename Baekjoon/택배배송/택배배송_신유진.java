import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Vertex implements Comparable<Vertex> {
        int edge;
        int value;

        Vertex(int edge, int value) {
            this.edge = edge;
            this.value = value;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 헛간 개수
        int M = Integer.parseInt(st.nextToken()); // 길의 개수

        // 길의 데이터 받기
        List<Vertex>[] roads = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            roads[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            roads[a].add(new Vertex(b, c));
            roads[b].add(new Vertex(a, c));
        }

        // 다익스트라 시작
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visit = new boolean[N + 1];
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();

            if (visit[cur.edge])
                continue;

            visit[cur.edge] = true;

            for (int i = 0; i < roads[cur.edge].size(); i++) {
                Vertex next = roads[cur.edge].get(i);

                if (visit[next.edge])
                    continue;

                if (dist[cur.edge] + next.value < dist[next.edge]) {
                    dist[next.edge] = dist[cur.edge] + next.value;
                    pq.add(new Vertex(next.edge, dist[next.edge]));
                }
            }
        }

        System.out.println(dist[N]);
    }
}