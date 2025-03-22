import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N, M, fuel;
    private static int[][] arr;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] taxi = new int[2];
    private static int[][] guests;
    private static boolean[] picked;
    private static Queue<Guest> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1.dist == o2.dist) {
            if (o1.sx == o2.sx) {
                return Integer.compare(o1.sy, o2.sy);
            }
            return Integer.compare(o1.sx, o2.sx);
        }
        return Integer.compare(o1.dist, o2.dist);
    });

    static class Guest {
        int sx;
        int sy;
        int gx;
        int gy;
        int dist;
        int num;

        public Guest(int sx, int sy, int gx, int gy, int dist, int num) {
            this.sx = sx;
            this.sy = sy;
            this.gx = gx;
            this.gy = gy;
            this.dist = dist;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Guest{" +
                "sx=" + sx +
                ", sy=" + sy +
                ", gx=" + gx +
                ", gy=" + gy +
                ", dist=" + dist +
                ", num=" + num +
                '}';
        }
    }

    /*
     * == 모든 손님에 대하여 현재 위치 기준으로 제일 가까운 손님 찾기 ==
     * 1. 현재 위치에서 손님까지의 거리 계산
     * 2. 연료가 충분한지 확인, 부족하면 -1
     * 3. 현재 위치를 손님 위치로 변경, 연료 차감
     * 4. 현재 위치에서 목적지까지의 거리 계산
     * 5. 연료가 충분한지 확인, 부족하면 -1
     * 6. 현재 위치를 목적지 위치로 변경, 거리 만큼 연료 추가
     * */
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][N + 1];
        guests = new int[M][5];
        picked = new boolean[M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxi[0] = Integer.parseInt(st.nextToken());
        taxi[1] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            guests[i][0] = Integer.parseInt(st.nextToken());
            guests[i][1] = Integer.parseInt(st.nextToken());
            guests[i][2] = Integer.parseInt(st.nextToken());
            guests[i][3] = Integer.parseInt(st.nextToken());
            guests[i][4] = i;
        }
        while (true) {
            pq.clear();
            for (int i = 0; i < M; i++) {
                if (picked[i]) {
                    continue;
                }
                pq.offer(
                    new Guest(
                        guests[i][0],
                        guests[i][1],
                        guests[i][2],
                        guests[i][3],
                        bfs(guests[i][0], guests[i][1]),
                        guests[i][4]));
            }
            if (pq.isEmpty()) {
                break;
            }
            Guest guest = pq.poll();
            picked[guest.num] = true;
            // 손님에게 이동
            if (fuel < guest.dist) {
                fuel = -1;
                break;
            }
            fuel -= guest.dist;
            taxi[0] = guest.sx;
            taxi[1] = guest.sy;
            // 목적지로 이동
            int dist = bfs(guest.gx, guest.gy);
            if (fuel < dist) {
                fuel = -1;
                break;
            }
            fuel += dist;
            taxi[0] = guest.gx;
            taxi[1] = guest.gy;
        }
        System.out.println(fuel);
    }

    private static int bfs(int gx, int gy) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        q.offer(new int[] {taxi[0], taxi[1], 0});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            int cnt = now[2];
            if (nowX == gx && nowY == gy) {
                return cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if (!(1 <= nextX && nextX <= N && 1 <= nextY && nextY <= N)) {
                    continue;
                }
                if (visited[nextX][nextY] || arr[nextX][nextY] == 1) {
                    continue;
                }
                visited[nextX][nextY] = true;
                q.offer(new int[] {nextX, nextY, cnt + 1});
            }
        }
        return Integer.MAX_VALUE;
    }
}
