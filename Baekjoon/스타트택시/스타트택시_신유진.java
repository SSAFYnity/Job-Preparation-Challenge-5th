import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = null;
    static int N, M, fuel, targetNum;
    static boolean[][] visit = null;
    static int[] dx = { -1, 0, 0, 1 }; // 좌 상 하 우
    static int[] dy = { 0, -1, 1, 0 };
    static int[] sp = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // NxN 영역
        M = Integer.parseInt(st.nextToken()); // M개의 승객
        fuel = Integer.parseInt(st.nextToken()); // 남은 연료

        // 영역 데이터 입력 받기
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sp = new int[2];
        st = new StringTokenizer(br.readLine());
        sp[1] = Integer.parseInt(st.nextToken());
        sp[0] = Integer.parseInt(st.nextToken());

        // 손님 데이터 입력 받기
        int number = 2;
        int[][] dData = new int[M + 3][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            map[y1][x1] = number; // 손님 시작 위치
            dData[number][0] = x2;
            dData[number][1] = y2;
            number++;
        }

        // 손님 태우고 데려다 주기
        int answer = 0;
        for (int i = 0; i < M; i++) {
            // 손님 태우기
            visit = new boolean[N + 1][N + 1];
            int cnt = 0;
            if (map[sp[1]][sp[0]] > 1) {
                // 택시와 승객이 같은 위치에 서 있으면 그 승객까지의 최단거리는 0
                targetNum = map[sp[1]][sp[0]];
                map[sp[1]][sp[0]] = 0;
            } else {
                cnt = findPoint(sp, -1);
            }

            fuel -= cnt; // 현재 연료 - 손님 태운 값
            if (fuel < 0 || cnt == -1) {
                answer = -1;
                break;
            }

            // 손님 데려다주기
            visit = new boolean[N + 1][N + 1];
            int original = map[dData[targetNum][1]][dData[targetNum][0]];
            map[dData[targetNum][1]][dData[targetNum][0]] = targetNum * -1;
            cnt = findPoint(sp, targetNum);
            fuel -= cnt;
            if (fuel < 0 || cnt == -1) {
                answer = -1;
                break;
            }
            map[dData[targetNum][1]][dData[targetNum][0]] = original;

            fuel += (cnt * 2);
        }

        System.out.println(answer == -1 ? -1 : fuel);

    }

    static int findPoint(int[] sp, int num) {
        Queue<int[]> taxi = new ArrayDeque<>();
        taxi.add(sp);
        int cnt = 0;
        PriorityQueue<int[]> tmp = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        while (!taxi.isEmpty()) {
            int size = taxi.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                int[] cur = taxi.poll();

                if (visit[cur[1]][cur[0]])
                    continue;

                visit[cur[1]][cur[0]] = true;

                for (int d = 0; d < dx.length; d++) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];

                    if (nx < 1 || nx > N || ny < 1 || ny > N || visit[ny][nx] || map[ny][nx] == 1)
                        continue;

                    // 손님 태우기
                    if (num == -1 && map[ny][nx] > 1) {
                        tmp.add(new int[] { nx, ny });
                    }

                    // 손님 데려다 주기
                    if (map[ny][nx] != 0 && (num == (map[ny][nx] * -1))) {
                        sp[0] = nx;
                        sp[1] = ny;
                        return cnt;
                    }

                    taxi.add(new int[] { nx, ny });
                }
            }

            // 손님을 태울 수 있다면
            if (!tmp.isEmpty()) {
                int[] customer = tmp.poll();
                sp[0] = customer[0];
                sp[1] = customer[1];
                targetNum = map[sp[1]][sp[0]];
                map[sp[1]][sp[0]] = 0;
                return cnt;
            }
        }

        return -1;
    }

    static void print() {
        for (int i = 0; i <= N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
