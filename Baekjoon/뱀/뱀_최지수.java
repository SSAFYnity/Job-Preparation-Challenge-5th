import java.util.*;
import java.io.*;

public class Main {
    static int n, m, f, k, answer, len, inf = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    static int[][] arr, goals;


    static int[][] dr = {{1,0,-1,0}, {0,-1,0,1}};
    static boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= n;}

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class WV{
        int y, x;
        public WV(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new int[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = -1;
        }

        k = Integer.parseInt(br.readLine());
        ArrayDeque<WV> que = new ArrayDeque<>();
        que.add(new WV(0,0));
        arr[0][0] = 1;
        int dir = 3;

        int time = 0;
        int timeLimit = 10000;

        int rdx = 1;
        st = new StringTokenizer(br.readLine());
        int nt = Integer.parseInt(st.nextToken());
        char c = st.nextToken().charAt(0);
        for (int t = 0; t <= 10100; t++) {
            WV now = que.peekLast();
            int y = now.y + dr[0][dir];
            int x = now.x + dr[1][dir];
            if (isOut(y, x) || arr[y][x] == 1) {
                answer = t + 1;
                break;
            } else if (arr[y][x] != -1) {
                WV last = que.poll();
                arr[last.y][last.x] = 0;
            }
            que.offer(new WV(y, x));
            arr[y][x] = 1;

            if (nt - 1 == t) {
                if (c == 'L') dir = (dir + 3) % 4;
                else dir = (dir + 1) % 4;
                if (rdx == k) nt = inf;
                else {
                    rdx++;
                    st = new StringTokenizer(br.readLine());
                    nt = Integer.parseInt(st.nextToken());
                    c = st.nextToken().charAt(0);
                }
            }
        }

        System.out.println(answer);
    }
}

