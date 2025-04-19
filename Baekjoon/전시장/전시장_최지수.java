import java.util.*;
import java.io.*;

public class Main {
    static int n, s;
    static Map<Integer, Integer> map = new TreeMap<>();
    static ArrayList<Integer> list = new ArrayList<>();
    static int[][] dp;
    
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class Painting implements Comparable<Painting> {
        int h, c;
        public Painting(int h, int c) {
            this.h = h;
            this.c = c;
        }
        public int compareTo(Painting o) {
            if (this.h == o.h) return o.c - this.c;
            return this.h - o.h;
        }
    }

    public static int bin(int h, int idx) {
        int start = 0;
        int end = idx-1;
        int p = (start+end)/2;
        while (start <= end && start >= 0) {
            p = (start+end)/2;
            if (list.get(p) > h-s) end = p-1;
            else if (list.get(p) < h-s) start = p+1;
            else return dp[p][2];
        }
        if (end >= 0) return dp[end][2];
        return 0;
    }

    public static void main(String[] args) throws IOException {

        n = read();
        s = read();

        for (int i = 0; i < n; i++) {
            int h = read();
            int c = Math.max(map.getOrDefault(h, 0), read());
            map.put(h, c);
        }
        map.put(0, 0);
        list.addAll(map.keySet());
        n = map.size();

        dp = new int[n][3];
        // 선택 안함, 함, 이제까지의 최댓값
        for (int i = 1; i < list.size(); i++) {
            int h = list.get(i);
            dp[i][0] = dp[i-1][2];
            dp[i][1] = bin(h, i) + map.get(h);
            dp[i][2] = Math.max(dp[i-1][2], dp[i][1]);
        }

        System.out.println(dp[n-1][2]);
    }
}

