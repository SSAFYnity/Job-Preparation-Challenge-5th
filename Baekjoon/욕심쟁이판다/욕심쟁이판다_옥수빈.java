import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] maxCell, forest;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        forest = new int[n][n];
        for (int y = 0; y < n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                forest[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        maxCell = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxCell[i][j] = -1; // 초기값 설정
            }
        }

        int answer = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                answer = Math.max(answer, dfs(x, y));
            }
        }

        System.out.println(answer);
    }

    public static int dfs(int x, int y) {
        if (maxCell[y][x] != -1) {
            return maxCell[y][x]; // 이미 계산된 경우 바로 반환
        }

        maxCell[y][x] = 1; // 현재 위치에서 최소 1칸은 이동 가능
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (forest[ny][nx] > forest[y][x]) { // 대나무가 더 많은 곳으로 이동
                maxCell[y][x] = Math.max(maxCell[y][x], dfs(nx, ny) + 1);
            }
        }

        return maxCell[y][x];
    }
}