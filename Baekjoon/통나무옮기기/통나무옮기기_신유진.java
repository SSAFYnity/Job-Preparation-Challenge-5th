import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int x, y, dir, count; // dir: 0은 가로, 1은 세로

        Point(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }
    }

    static int N;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx = { -1, 1, 0, 0 }; // 상 하 좌 우
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N][2];

        List<int[]> B = new ArrayList<>();
        List<int[]> E = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'B')
                    B.add(new int[] { i, j });
                else if (map[i][j] == 'E')
                    E.add(new int[] { i, j });
            }
        }

        int bx = B.get(1)[0], by = B.get(1)[1];
        int bdir = (B.get(0)[0] == B.get(1)[0]) ? 0 : 1;

        int ex = E.get(1)[0], ey = E.get(1)[1];
        int edir = (E.get(0)[0] == E.get(1)[0]) ? 0 : 1;

        Point start = new Point(bx, by, bdir, 0);
        Point end = new Point(ex, ey, edir, 0);

        System.out.println(bfs(start, end));
    }

    static boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static boolean canMove(int x, int y, int dir) {
        if (!isInside(x, y))
            return false;

        if (dir == 0) {
            return isInside(x, y - 1) && isInside(x, y + 1) &&
                    map[x][y - 1] != '1' && map[x][y] != '1' && map[x][y + 1] != '1';
        } else {
            return isInside(x - 1, y) && isInside(x + 1, y) &&
                    map[x - 1][y] != '1' && map[x][y] != '1' && map[x + 1][y] != '1';
        }
    }

    static boolean canRotate(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!isInside(i, j) || map[i][j] == '1')
                    return false;
            }
        }
        return true;
    }

    static int bfs(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y][start.dir] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur.x == end.x && cur.y == end.y && cur.dir == end.dir) {
                return cur.count;
            }

            // 이동
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (canMove(nx, ny, cur.dir) && !visited[nx][ny][cur.dir]) {
                    visited[nx][ny][cur.dir] = true;
                    queue.offer(new Point(nx, ny, cur.dir, cur.count + 1));
                }
            }

            // 회전
            int ndir = 1 - cur.dir;
            if (canRotate(cur.x, cur.y) && !visited[cur.x][cur.y][ndir]) {
                visited[cur.x][cur.y][ndir] = true;
                queue.offer(new Point(cur.x, cur.y, ndir, cur.count + 1));
            }
        }

        return 0;
    }
}
