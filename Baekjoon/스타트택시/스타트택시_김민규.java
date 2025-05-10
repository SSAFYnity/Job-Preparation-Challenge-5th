import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] map;
    static int si, sj;
    static ArrayList<Node> p;

    static class Node implements Comparable<Node> {
        int r1, c1, r2, c2, d;

        //        현재 위치에서 최단거리가 가장 짧은 승객을 고른다.
//        그런 승객이 여러 명이면 그중 행 번호가 가장 작은 승객을,
//        그런 승객도 여러 명이면 그중 열 번호가 가장 작은 승객을 고른다.
        Node(int r1, int c1, int r2, int c2, int d) {
            this.r1 = r1;
            this.r2 = r2;
            this.c1 = c1;
            this.c2 = c2;
            this.d = d;
        }

        @Override
        // comparator가 아니라 comparable!!!!!!!
        public int compareTo(Node o) {
            if (this.d == o.d) {
                if (this.r1 == o.r1) {
                    return this.c1 - o.c1;
                }
                return this.r1 - o.r1;
            }
            return this.d - o.d;
        }
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1]; // 연산처리 편하게
        for (int[] r: map){
            Arrays.fill(r, -1);
        }
        p = new ArrayList<>(); // m명인데 번호를 1부터 매길 것
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        si = Integer.parseInt(st.nextToken());
        sj = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            // r, c 형태로 준다
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken()); // 출발 r,c
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken()); // 도착 r,c
            p.add(new Node(r1, c1, r2, c2, -1)); // 방문하지 않음을 의미하는 -1
        }

//        distance();
//        Collections.sort(p); // comparable 쓰기
        go();
    }

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, -1, 0, 1};

    public static void distance() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[n + 1][n + 1];
        int[][] dist = new int[n + 1][n + 1];
        for (int[] r: dist){
            Arrays.fill(r, -1);
        }
        q.offer(new int[]{si, sj}); // 클래스 안만들고 싶다
        v[si][sj] = true;
        dist[si][sj] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int ni = r + di[d];
                int nj = c + dj[d];

                if (ni < 1 || ni > n || nj < 1 || nj > n || map[ni][nj] == 1 || v[ni][nj]) {
                    continue; // 범위 체크
                }
                // 가중치가 동일하니까 이래도 됨
                v[ni][nj] = true;
                dist[ni][nj] = dist[r][c] + 1;
                q.offer(new int[]{ni, nj});
            }
        }

        for (Node passenger : p) {
            int pr = passenger.r1;
            int pc = passenger.c1;

            if (v[pr][pc]) {
                passenger.d = dist[pr][pc]; // 승객까지의 거리 저장하기
            } else {
                passenger.d = -1; // 도달 불가능한 경우
            }
        }

//        for (int[] r: dist){
//            System.out.println(Arrays.toString(r));
//        }
    }

    public static void go() {
        int f = k;

        while (!p.isEmpty()) {
            // 현재 위치 기준으로 최단거리 갱신
            distance();
            Collections.sort(p);
            Node passenger = p.get(0);

            if (passenger.d == -1 || passenger.d > f) {
                System.out.println(-1);
                return;
            }

            // 승객 출발 위치까지 이동
            f -= passenger.d;
            si = passenger.r1;
            sj = passenger.c1;

            // 승객의 목적지까지 최단거리
            int distToDest = bfs(si, sj, passenger.r2, passenger.c2);
            if (distToDest == -1 || distToDest > f) { // 못가면
                System.out.println(-1);
                return;
            }

            f -= distToDest;
            f += distToDest * 2; // 성공하면 충전 이벤트

            // 현재 택시 위치를 이전 도착지로 갱신
            si = passenger.r2;
            sj = passenger.c2;

            // 데려다 준 승객 제거
            p.remove(passenger);
        }

        // 모든 승객 데려다줬다면 남은 연료량 출력
        System.out.println(f);
    }

    public static int bfs(int sr, int sc, int er, int ec) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[n + 1][n + 1];

        q.offer(new int[]{sr, sc, 0}); // 거리만 이제 갖고있으면 됨
        v[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], d = cur[2];

            if (r == er && c == ec) {
                return d;
            }

            for (int dir = 0; dir < 4; dir++) {
                int ni = r + di[dir];
                int nj = c + dj[dir];

                if (ni < 1 || ni > n || nj < 1 || nj > n || map[ni][nj] == 1 || v[ni][nj]) {
                    continue;
                }

                v[ni][nj] = true;
                q.offer(new int[]{ni, nj, d + 1});
            }
        }
        return -1; // 갈 수 없음
    }

}