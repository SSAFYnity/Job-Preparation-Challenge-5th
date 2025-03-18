import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = { 1, 0, -1, 0 }; // 우 하 좌 상
    static int[] dy = { 0, 1, 0, -1 };
    static int N;
    static int[][] map = null;
    static Point head = new Point(1, 1);
    static Deque<Point> snake = new ArrayDeque<>();

    public static class Point {
        int x = 0;
        int y = 0;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사과 위치 받기
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = -1; // 사과
        }

        // 방향 정보 받기
        char[] dirArr = new char[10001];
        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            Character C = st.nextToken().charAt(0);

            dirArr[X] = C;
        }

        // 이동
        int time = 0;
        int dir = 0;
        snake.add(new Point(1, 1));
        map[1][1] = 1;
        while (true) {
            time++;

            if (!snakeMove(dir))
                break;

            if (dirArr[time] == 'D') { // 오른쪽 90도
                dir = (dir + 1) % 4;
            } else if (dirArr[time] == 'L') { // 왼쪽 90도
                dir = (dir + 3) % 4;
            }
        }

        System.out.println(time);
    }

    static boolean snakeMove(int d) {

        int nx = head.x + dx[d];
        int ny = head.y + dy[d];

        if (nx < 1 || nx > N || ny < 1 || ny > N || map[ny][nx] == 1) { // 벽이거나 뱀의 몸이라면
            return false;
        }

        head.x = nx;
        head.y = ny;

        snake.add(new Point(nx, ny));

        if (map[head.y][head.x] == -1) { // 사과라면 없어지고 꼬리는 그대로
            map[head.y][head.x] = 1;
        } else if (map[head.y][head.x] == 0) { // 아무것도 없다면
            // 꼬리 이동
            Point preTail = snake.poll(); // 꼬리(맨 앞)의 값을 제거
            map[preTail.y][preTail.x] = 0;
        }

        map[ny][nx] = 1;

        return true;
    }

}
