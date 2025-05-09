import java.util.StringTokenizer;
import java.io.*;

public class Main {
    final static int[] DX = {-1, 1, 0, 0};
    final static int[] DY = {0, 0, -1, 1};

    static int n;
    static int[][] arr;
    static int[][] dp;  // 각 칸에서 이동할 수 있는 최대 칸 수 저장
    static int answer;

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static int dfs(int x, int y) {
        // 이미 방문한 칸은 dp 배열에 저장된 값을 리턴
        if(dp[x][y] != -1) {
            return dp[x][y];
        }

        // 현재 칸에서 이동할 수 있는 칸들 중 가장 긴 경로를 찾아서 dp 배열에 저장
        dp[x][y] = 1;  // 최소 1번은 자기 자신을 포함하므로 1로 초기화
        for(int d = 0; d < 4; d++) {
            int nx = DX[d] + x;
            int ny = DY[d] + y;
            if(isValid(nx, ny) && arr[nx][ny] > arr[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());  // 1 <= 대나무 숲의 크기 <= 500
        arr = new int[n][n];  // 대나무 숲
        dp = new int[n][n];   // dp 배열, -1로 초기화될 예정

        // 대나무 숲의 정보 입력받기
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;  // dp 배열 초기화 (-1은 아직 방문하지 않았음을 의미)
            }
        }

        // 모든 칸에 대해 dfs 호출하여 최대 경로 길이를 구함
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        // 최댓값 출력 (답은 경로 길이이므로 +1이 필요함)
        System.out.println(answer);
    }
}
