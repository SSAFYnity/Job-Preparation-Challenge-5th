import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};  // 상하좌우
    static int[][] rotateCheck = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};  // 회전 체크

    static class State {
        int x, y;  // 중심 좌표
        int dir;  // 0: 가로, 1: 세로
        int cnt;  // 이동 횟수

        State(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    private static boolean canRotate(char[][] map, int x, int y) {
        for (int[] d : rotateCheck) {
            int nx = x + d[0];
            int ny = y + d[1];
            // 범위를 벗어남
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                return false;
            }
            // 통나무 위치
            if (map[nx][ny] == '1') {
                return false;
            }
        }
        return true;
    }

    private static int bfs (char[][] map, State start, State end) {
        boolean[][][] visited = new boolean[N][N][2];  // [x][y][방향]
        Queue<State> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y][start.dir] = true;

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (curr.x == end.x && curr.y == end.y && curr.dir == end.dir) {
                return curr.cnt;
            }

            // 상하좌우 이동
            for (int[] d : dirs) {
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];

                if (curr.dir == 0) {  // 가로
                    if (ny - 1 < 0 || ny + 1 >= N || nx < 0 || nx >= N) {
                        continue;
                    }
                    if (map[nx][ny] == '1' || map[nx][ny-1] == '1' || map[nx][ny+1] == '1') {
                        continue;
                    }
                } else if (curr.dir == 1) {  // 세로
                    if (nx - 1 < 0 || nx + 1 >= N || ny < 0 || ny >= N) {
                        continue;
                    }
                    if (map[nx][ny] == '1' || map[nx-1][ny] == '1' || map[nx+1][ny] == '1') {
                        continue;
                    }
                }
                if (!visited[nx][ny][curr.dir]) {
                    visited[nx][ny][curr.dir] = true;
                    State next = new State(nx, ny, curr.dir, curr.cnt + 1);
                    queue.offer(next);
                }
            }
            if(canRotate(map, curr.x, curr.y)) {
                int d;
                if (curr.dir == 0) {
                    d = 1;
                } else {
                    d = 0;
                }
                // 방문 안했을 경우
                if (!visited[curr.x][curr.y][d]) {
                    visited[curr.x][curr.y][d] = true;
                    State next = new State(curr.x, curr.y, d, curr.cnt + 1);
                    queue.offer(next);
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][N];  // 평지 정보
        List<int[]> B = new ArrayList<>();
        List<int[]> E = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'B') {
                    B.add(new int[] {i, j});
                } else if (map[i][j] == 'E') {
                    E.add(new int[]{i, j});
                }
            }
        }
        // 시작 상태
        int[] centerB = B.get(1);
        int dirB = (B.get(0)[0] == B.get(1)[0]) ? 0 : 1;
        // 목표 상태
        int[] centerE = E.get(1);
        int dirE = (E.get(0)[0] == E.get(1)[0]) ? 0 : 1;
        // 스테이트 설정
        State start = new State(centerB[0], centerB[1], dirB, 0);
        State end = new State(centerE[0], centerE[1], dirE, 0);

        int result = bfs(map, start, end);
        System.out.println(result);
    }
}
