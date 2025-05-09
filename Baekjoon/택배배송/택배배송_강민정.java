import java.io.*;
import java.util.*;

public class Main {
    final static int MAX_SIZE = 50001;

    static boolean[] visit;
    static int n;       // 1 <= 헛간의 수 <= 50,000
    static int m;       // 1 <= 길의 수 <= 50,000
    static List<Path>[] path;
    static int answer;

    static class Path implements Comparable<Path> {
        int b;
        int price;
        Path(int b, int price) {
            this.b = b;
            this.price = price;
        }

        @Override
        public int compareTo(Path path) {
            return this.price - path.price;     // 오름차순 정렬
        }
    }

    public static void dijkstra() {
        PriorityQueue<Path> pq = new PriorityQueue();
        pq.offer(new Path(1, 0));
        while(!pq.isEmpty()) {
            Path cur = pq.poll();
            visit[cur.b] = true;
            if(cur.b == n) {
                answer = Math.min(answer, cur.price);
                return;
            }
            for(Path other : path[cur.b]) {
                if(visit[other.b]) {
                    continue;
                }
                pq.offer(new Path(other.b, cur.price + other.price));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new boolean[MAX_SIZE];
        path = new ArrayList[MAX_SIZE];
        answer = Integer.MAX_VALUE;

        for(int i=0; i<MAX_SIZE; i++) {
            path[i] = new ArrayList();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 양방향 연결
            path[a].add(new Path(b, c));
            path[b].add(new Path(a, c));
        }

        dijkstra();

        System.out.println(answer);
    }
}