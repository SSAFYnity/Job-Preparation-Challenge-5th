import java.util.*;

class Solution {
    static int N, M;
    static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
    static int[] dy = { -1, 1, 0, 0 };
    static char[][] data = null;

    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        N = storage.length; // 세로
        M = storage[0].length(); // 가로

        data = new char[N][M];
        for (int i = 0; i < N; i++) {
            data[i] = storage[i].toCharArray();
        }

        for (int i = 0; i < requests.length; i++) {
            if (requests[i].length() == 1) { // 지게차 이동
                moveForklift(requests[i].charAt(0));
            } else { // 크레인 이동
                moveCrane(requests[i].charAt(0));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (data[i][j] != '-') {
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void moveForklift(char request) {
        boolean[][] visit = new boolean[N][M];
        ArrayList<int[]> changeData = new ArrayList<>();
        // 첫번째 행
        for (int x = 0; x < M; x++) {
            // 옮겨졌거나 옮겨질 수 있는 컨테이너인지 탐색
            if (moveContainer(x, 0, request, changeData))
                continue;
            findContainer(request, x, 0, visit, changeData);
        }

        // 마지막 행
        for (int x = 0; x < M; x++) {
            if (moveContainer(x, N - 1, request, changeData))
                continue;
            findContainer(request, x, N - 1, visit, changeData);
        }

        // 첫번째 열
        for (int y = 0; y < N; y++) {
            if (moveContainer(0, y, request, changeData))
                continue;
            findContainer(request, 0, y, visit, changeData);
        }

        // 마지막 열
        for (int y = 0; y < N; y++) {
            if (moveContainer(M - 1, y, request, changeData))
                continue;
            findContainer(request, M - 1, y, visit, changeData);
        }

        for (int i = 0; i < changeData.size(); i++) {
            data[changeData.get(i)[1]][changeData.get(i)[0]] = '-';
        }
    }

    // 크레인으로 이동
    public static void moveCrane(char request) {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (data[y][x] == request) {
                    data[y][x] = '-'; // 옮겼다는 표시
                }
            }
        }
    }

    public static void findContainer(char request, int x, int y, boolean[][] visit, ArrayList<int[]> changeData) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { x, y });
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int nx = point[0];
            int ny = point[1];

            if (visit[ny][nx])
                continue;
            visit[ny][nx] = true;

            for (int d = 0; d < dx.length; d++) {
                int lx = nx + dx[d];
                int ly = ny + dy[d];

                if (lx < 0 || lx >= M || ly < 0 || ly >= N || visit[ly][lx])
                    continue;
                // 옮길 컨테이너인지 탐색
                if (moveContainer(lx, ly, request, changeData))
                    continue;

                // 이미 옮겨진 컨터이너라면 사방으로 다시 탐색
                if (data[ly][lx] == '-') {
                    q.add(new int[] { lx, ly });
                }
            }
        }
    }

    public static boolean moveContainer(int x, int y, char request, ArrayList<int[]> changeData) {
        if (data[y][x] == '-')
            return false;

        if (data[y][x] == request) {
            changeData.add(new int[] { x, y }); // 옮겼다는 표시
        }
        return true;
    }
}