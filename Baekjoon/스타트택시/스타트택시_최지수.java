import java.util.*;
import java.io.*;

public class Main {
    static int n, m, f, k, answer, len, inf = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    static int[][] arr, goals;


    static int[][] dr = {{-1,0,0,1}, {0,-1,1,0}};
    static boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= n;}

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class WV {
        int y, x, cnt;
        public WV(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
        public void move(int y, int x) {
            this.y = y;
            this.x = x;
        }
        public String toString() {
            return "[" + this.y + ", " + this.x + " -> " + this.cnt + "]";
        }
    }

    static boolean fincon(WV start, WV now, boolean running) {
        if (running) {
            int[] temp = goals[arr[start.y][start.x]];
            return temp[0] == now.y && temp[1] == now.x;
        } else {
            return arr[now.y][now.x] > 0;
        }
    }

    static void afterfin(WV now, boolean running) {
        if (running) {
            f += now.cnt;
        } else {
            f -= now.cnt;
        }
    }

    static WV bfs(WV start, Queue<WV> que, boolean running) {
        boolean[][] visit = new boolean[n][n];
        que.clear();
        que.add(new WV(start.y, start.x, 0));
        visit[start.y][start.x] = true;
        while (!que.isEmpty()) {
            WV now = que.poll();
            if (fincon(start, now, running)) {
                afterfin(now, running);
                return now;
            } else if (f < now.cnt) break;
            for (int d = 0; d < 4; d++) {
                int y = now.y + dr[0][d];
                int x = now.x + dr[1][d];
                if (isOut(y,x) || arr[y][x] == -1 || visit[y][x]) continue;
                que.add(new WV(y,x,now.cnt+1));
                visit[y][x] = true;
            }
        }
        return new WV(-1, -1, -1);
    }

    public static void main(String[] args) throws IOException {
        
        n = read();
        m = read();
        f = read();
        answer = -1;
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = read() * (-1);
            }
        }

        WV taxi = new WV(read()-1, read()-1, 0);
        WV pass = new WV(-1, -1, 0);
        goals = new int[m+1][2];
        for (int i = 0; i < m; i++) {
            arr[read()-1][read()-1] = i + 1;
            goals[i+1][0] = read()-1;
            goals[i+1][1] = read()-1;
        }

        Queue<WV> que = new LinkedList<>();
        PriorityQueue<WV> nearque = new PriorityQueue<>((o, t) -> {
            if (o.cnt == t.cnt) {
                if (o.y == t.y) return o.x - t.x;
                else return o.y - t.y;
            } else return o.cnt - t.cnt;
        });

        for (int i = 0; i < m; i++) {
            // 제일 가까운 승객 찾기
            pass = bfs(taxi, nearque, false);
            if (pass.y == -1) break;

            // 찾고나서는 거기까지 도달하기
            if (f <= 0) break;
            pass.cnt = 0;

            // 도착지까지 운행
            taxi = bfs(pass, que, true);
            if (taxi.y == -1) break;
            arr[pass.y][pass.x] = 0;
            taxi.cnt = 0;

            if (i == m-1) answer = f;
        }

        System.out.println(answer);
    }
}

