import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static final int N = 10;
    private static final int INF = Integer.MAX_VALUE;
    private static int ans = INF;
    private static int[][] arr = new int[N][N];
    private static int[] paper = {0, 5, 5, 5, 5, 5};

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(ans == INF ? -1 : ans);
    }

    private static void dfs(int idx, int cnt) {
        if (ans <= cnt) {
            return;
        }
        if (idx == N * N) {
            ans = Math.min(ans, cnt);
            return;
        }
        int x = idx / N;
        int y = idx % N;
        if (arr[x][y] == 1) {
            for (int k = 5; k > 0; k--) {
                if (paper[k] > 0 && check(x, y, k)) {
                    paper[k]--;
                    change(x, y, k);
                    dfs(idx + 1, cnt + 1);
                    paper[k]++;
                    change(x, y, k);
                }
            }
        } else {
            dfs(idx + 1, cnt);
        }
    }

    private static void change(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                arr[i][j] = arr[i][j] == 0 ? 1 : 0;
            }
        }
    }

    private static boolean check(int x, int y, int size) {
        if (!(x + size <= N && y + size <= N)) {
            return false;
        }
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
