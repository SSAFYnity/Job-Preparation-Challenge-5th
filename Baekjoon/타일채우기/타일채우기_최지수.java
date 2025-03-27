import java.util.*;
import java.io.*;

public class Main {
    static int n;

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {

        n = read();
        if (n % 2 != 0) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n+1];
        dp[2] = 3;
        for (int i = 4; i <= n; i+=2) {
            dp[i] = dp[i-2] * dp[2];
            for (int j = 4; j <= i - 2; j+=2) {
                dp[i] += dp[i-j] * 2;
            }
            dp[i] += 2;
        }

        System.out.println(dp[n]);
    }
}

