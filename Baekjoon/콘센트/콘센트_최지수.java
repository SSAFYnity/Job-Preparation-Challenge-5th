import java.util.*;
import java.io.*;

public class Main {
    
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {

        int n = read();
        int m = read();

        int maxi = 0;
        PriorityQueue<Integer> con = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            con.add(0);
        }
        PriorityQueue<Integer> que = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            que.add(read());
        }

        while (!que.isEmpty()) {
            int now = con.poll();
            now += que.poll();
            maxi = Math.max(maxi, now);
            con.add(now);
        }

        System.out.println(maxi);
    }
}

