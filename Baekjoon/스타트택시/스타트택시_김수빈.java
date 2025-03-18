import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int[][] area;
    static int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  // 이동 방향


    private static int[][] bfsAll(int sr, int sc) {
        int[][] dist = new int[N][N];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {sr, sc, 0});
        dist[sr][sc] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];

            for (int[] dxy : ds) {
                int nr = r + dxy[0], nc = c + dxy[1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && area[nr][nc] == 0 && dist[nr][nc] == -1) {
                    dist[nr][nc] = d + 1;
                    queue.add(new int[] {nr, nc, d + 1});
                }
            }
        }
        return dist;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);  // 활동 영역 범위
        M = Integer.parseInt(input[1]);  // 승객 수
        int fuel = Integer.parseInt(input[2]);  // 초기 연료
        area = new int[N][N];  // 활동 영역
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(line[j]);
            }
        }
        String[] startPos = br.readLine().split(" ");
        int dr = Integer.parseInt(startPos[0]) - 1;
        int dc = Integer.parseInt(startPos[1]) - 1;

        ArrayList<int[]> passengers = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int[] p = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            passengers.add(p);
        }


        int count = 0;

        while (count < M && fuel > 0) {
            int[][] dist = bfsAll(dr, dc);
            passengers.sort((p1, p2) -> {
                if (dist[p1[0] - 1][p1[1] - 1] != dist[p2[0] - 1][p2[1] -1])
                    return dist[p1[0] - 1][p1[1] - 1] - dist[p2[0] - 1][p2[1] -1];
                if (p1[0] != p2[0]) return p1[0] - p2[0];  // 행 번호 오름차순
                return p1[1] - p2[1];  // 열 번호 오름차순
            });


            int[] passenger = passengers.remove(0);
            int sr = passenger[0] - 1, sc = passenger[1] - 1;
            int er = passenger[2] - 1, ec = passenger[3] - 1;

            int x = dist[sr][sc];
            int y = bfsAll(sr, sc)[er][ec];
            // 갈 수 없는 경우
            if (x == -1 || y == -1) {
                System.out.println(-1);
                return;
            }
            fuel -= (x + y);
            // 연료가 다 닳은 경우
            if (fuel < 0) {
                System.out.println(-1);
                return;
            }
            fuel += y * 2;
            dr = er;
            dc = ec;
            count++;
        }

        System.out.println(count == M ? fuel : -1);
    }
}
