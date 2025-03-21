import java.util.*;
import java.io.*;

public class Main {
    static int n, m, f, k, answer, len, inf = Integer.MAX_VALUE;
    static TreeSet<Integer> set = new TreeSet<>();
    static StringBuilder sb = new StringBuilder();
    
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class Water {
        int a,b,c;
        public Water(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static Water pour(Water now, int dep, int arr) {
        if (dep == 1) {
            if (arr == 2) {
                if (now.a + now.b > m) {
                    return new Water(now.a - (m - now.b), m, now.c);
                } else {
                    return new Water(0, now.b + now.a, now.c);
                }
            } else {
                if (now.a + now.c > k) {
                    return new Water(now.a - (k - now.c), now.b, k);
                } else {
                    return new Water(0, now.b, now.c + now.a);
                }
            }
        } else if (dep == 2) {
            if (arr == 1) {
                if (now.a + now.b > n) {
                    return new Water(n, now.b - (n - now.a), now.c);
                } else {
                    return new Water(now.a + now.b, 0, now.c);
                }
            } else {
                if (now.b + now.c > k) {
                    return new Water(now.a, now.b - (k - now.c), k);
                } else {
                    return new Water(now.a, 0, now.c + now.b);
                }
            }
        } else {
            if (arr == 1) {
                if (now.a + now.c > n) {
                    return new Water(n, now.b, now.c - (n - now.a));
                } else {
                    return new Water(now.a + now.c, now.b, 0);
                }
            } else {
                if (now.b + now.c > m) {
                    return new Water(now.a, m, now.c - (m - now.b));
                } else {
                    return new Water(now.a, now.b + now.c, 0);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        n = read();
        m = read();
        k = read();

        boolean[][][] visit = new boolean[n+1][m+1][k+1];
        visit[0][0][k] = true;

        Queue<Water> que = new LinkedList<>();
        que.add(new Water(0, 0, k));
        set.add(k);
        while (!que.isEmpty()) {
            Water now = que.poll();
            for (int d = 1; d <= 3; d++) {
                for (int a = 1; a <= 3; a++) {
                    if (d == a) continue;
                    Water next = pour(now, d, a);
                    if (visit[next.a][next.b][next.c]) continue;
                    visit[next.a][next.b][next.c] = true;
                    if (next.a == 0) set.add(next.c);
                    que.add(next);
                }
            }
        }

        for (int i : set) {
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }
}

