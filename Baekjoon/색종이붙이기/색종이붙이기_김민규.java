import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5}; // 1~5 크기 색종이 개수
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void dfs(int ci, int cj, int cnt) {
        if (ci == 10) { // 10행 도달 시 종료 조건
            ans = Math.min(ans, cnt);
            return;
        }

        if (cnt >= ans) return; // 현재 cnt가 이미 ans보다 크면 탐색 불필요

        if (cj == 10) {
            dfs(ci + 1, 0, cnt);
            return;
        }

        if (map[ci][cj] == 1) {
            for (int size = 5; size >= 1; size--) { // 큰 색종이부터 붙여봄
                if (paper[size] > 0 && canAttach(ci, cj, size)) {
                    attach(ci, cj, size, 0); // 색종이 붙이기
                    paper[size]--;
                    dfs(ci, cj + 1, cnt + 1);
                    attach(ci, cj, size, 1); // 원복
                    paper[size]++;
                }
            }
        } else {
            dfs(ci, cj + 1, cnt);
        }
    }

    public static boolean canAttach(int ci, int cj, int size) {
        if (ci + size > 10 || cj + size > 10) return false;
        for (int i = ci; i < ci + size; i++) {
            for (int j = cj; j < cj + size; j++) {
                if (map[i][j] != 1) return false;
            }
        }
        return true;
    }

    public static void attach(int ci, int cj, int size, int state) {
        for (int i = ci; i < ci + size; i++) {
            for (int j = cj; j < cj + size; j++) {
                map[i][j] = state;
            }
        }
    }
}
