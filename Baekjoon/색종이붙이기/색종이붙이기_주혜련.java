import java.io.*;
import java.util.*;

import static java.lang.Math.min;


public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[][] chked, arr;
    private static List<yx> l;
    private static int ans;
    private static int[] paperCnt;


    public static class yx {
        public int y;
        public int x;

        public yx(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));


        chked = new int[11][11];
        arr = new int[11][11];
        l = new ArrayList<yx>();
        ans = Integer.MAX_VALUE;
        paperCnt= new int[]{0, 5, 5, 5, 5, 5};

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) l.add(new yx(i, j));
            }
        }

        solve(0, 0);
        if(ans==Integer.MAX_VALUE) ans=-1;
        System.out.println(ans);

    }

    private static void solve(int cnt, int sum) {
        if (cnt == l.size()) {
            ans = min(ans, sum);
            return;
        }
        if(sum>=ans) return;
        int y = l.get(cnt).y, x = l.get(cnt).x;
        if (chked[y][x] == 1) {
            solve(cnt + 1, sum);
            return;
        }

        for (int k = 5; k >= 1; k--) {
            boolean flag = true;
            if(paperCnt[k]<=0) continue;
            for (int i = 0; i < k; i++)
                for (int j = 0; j < k; j++) {
                    int cy = y + i, cx = x + j;

                    if (cy < 0 || cx < 0 || cy >= 10 || cx >= 10 || !(arr[cy][cx] == 1 && chked[cy][cx] == 0)) {
                        flag = false;
                        break;
                    }
                }

            if (flag == true) {
                for (int i = 0; i < k; i++)
                    for (int j = 0; j < k; j++) {
                        int cy = y + i, cx = x + j;
                        chked[cy][cx] = 1;
                    }
                paperCnt[k]--;

                solve(cnt + 1, sum+1);
                for (int i = 0; i < k; i++)
                    for (int j = 0; j < k; j++) {
                        int cy = y + i, cx = x + j;
                        chked[cy][cx] = 0;
                    }
                paperCnt[k]++;
            }
        }
    }

}
