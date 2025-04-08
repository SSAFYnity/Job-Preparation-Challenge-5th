import java.util.*;
import java.io.*;

public class Main {
    static int n, answer, inf = Integer.MAX_VALUE;
    static boolean[][] forest;
    static PriorityQueue<Log> que = new PriorityQueue<>();
    static Set<Integer> visit = new HashSet<>();
    
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class Log implements Comparable<Log>{
        int l, r, u, d, cnt, hash;
        public Log(int u, int d, int l, int r, int cnt) {
            this.u = u;
            this.d = d;
            this.l = l;
            this.r = r;
            this.cnt = cnt;
            this.hash = u;
            hash = hash * 100 + d;
            hash = hash * 100 + l;
            hash = hash * 100 + r;
        }
        public int compareTo(Log o) {
            return this.cnt - o.cnt;
        }
    }

    static Log move(Log log, int dir) {
        if (dir == 1) return new Log(log.u-1, log.d-1, log.l, log.r, log.cnt+1);
        else if (dir == 2) return new Log(log.u+1, log.d+1, log.l, log.r, log.cnt+1);
        else if (dir == 3) return new Log(log.u, log.d, log.l-1, log.r-1, log.cnt+1);
        else if (dir == 4) return new Log(log.u, log.d, log.l+1, log.r+1, log.cnt+1);
        else {
            int ud = (log.u+log.d) / 2;
            int lr = (log.l+log.r) / 2;
            int ud_diff = ((ud-log.u) ^ 1);
            int lr_diff = ((lr-log.l) ^ 1);
            return new Log(ud-ud_diff, ud+ud_diff, lr-lr_diff, lr+lr_diff, log.cnt+1);
        }

    }

    static boolean invalid(Log log) {
        return log.l < 0 || log.r >= n || log.u < 0 || log.d >= n;
    }

    static boolean oneInvalid(Log log) {
        return forest[log.u][log.l] || forest[log.d][log.r] || forest[(log.u+log.d)/2][(log.l+log.r)/2];
    }

    static boolean rotInvalid(Log log) {
        boolean val = false;
        int ud = (log.u+log.d) / 2;
        int lr = (log.l+log.r) / 2;
        if (ud == 0 || ud == n-1 || lr == 0 || lr == n-1) return true;
        for (int i = ud-1; i <= ud+1; i++) {
            for (int j = lr-1; j <= lr+1 && !val; j++) val |= forest[i][j];
        }
        return val;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        forest = new boolean[n][n];
        int l=inf,r=-1,u=inf,d=-1;
        int gl=inf,gr=-1,gu=inf,gd=-1;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = str.charAt(j);
                forest[i][j] = (c == '1');
                if (c == '0' || c == '1') continue;
                if (c == 'B') {
                    l = Math.min(l,j);
                    r = Math.max(r,j);
                    u = Math.min(u,i);
                    d = Math.max(d,i);
                } else {
                    gl = Math.min(gl,j);
                    gr = Math.max(gr,j);
                    gu = Math.min(gu,i);
                    gd = Math.max(gd,i);
                }
            }
        }

        Log start = new Log(u,d,l,r,0);
        Log goal = new Log(gu, gd, gl, gr, 0);

        que.add(start);
        visit.add(start.hash);

        while (!que.isEmpty()) {
            Log now = que.poll();
            if (now.hash == goal.hash) {
                answer = now.cnt;
                break;
            }
            Log rot = move(now, 0);
            if (!invalid(rot) && !rotInvalid(rot) && !visit.contains(rot.hash)) {
                que.add(rot);
                visit.add(rot.hash);
            }
            for (int i = 1; i < 5; i++) {
                Log next = move(now, i);
                if (invalid(next) || oneInvalid(next) || visit.contains(next.hash)) continue;
                que.add(next);
                visit.add(next.hash);
            }
        }

        System.out.println(answer);
    }
}

