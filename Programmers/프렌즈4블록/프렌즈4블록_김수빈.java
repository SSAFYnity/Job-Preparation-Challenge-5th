import java.util.*;

class Solution {
    static char[][] arr;
    // 4개 겹친 블록 터뜨리는 함수
    private static int popBoard(int n, int m) {
        int[][] ds = {{0, 1}, {1, 0}, {1, 1}};  // 우, 하 ,우하 확인
        boolean[][] check = new boolean[m][n];  // 터뜨리는지 확인하는 배열
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                char friend = arr[i][j];
                // 빈공간이면 넘어가기
                if (friend == '1') {
                    continue;
                }
                boolean flag = true;
                for (int[] d : ds) {
                    int nr = i + d[0];
                    int nc = j + d[1];
                    // 캐릭터가 같지 않다면 break
                    if (arr[nr][nc] != friend) {
                        flag = false;
                        break;
                    }
                }
                // 캐릭터가 같으면 check 배열에 표시
                if (flag) {
                    check[i][j] = true;
                    for (int[] d : ds) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        check[nr][nc] = true;
                    }
                }

            }
        }
        int count = 0;
        // 없어진 공간 빈공간으로 만들기
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j]) {
                    arr[i][j] = '1';
                    count++;
                }
            }
        }
        return count;
    }
    // 빈 공간 내리는 함수
    private static void downBoard(int n, int m) {
        for (int j = 0; j < n; j++) {
            Queue<Integer> queue = new LinkedList<>();
            for (int i = m - 1; i >= 0; i--) {
                if (arr[i][j] == '1') {
                    queue.offer(i);
                } else {
                    if (!queue.isEmpty()) {
                        int idx = queue.poll();
                        arr[idx][j] = arr[i][j];
                        arr[i][j] = '1';
                        queue.offer(i);
                    }
                }
            }
        }
    }

    public int solution(int m, int n, String[] board) {
        arr = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j);
            }
        }
        int answer = 0;

        while (true) {
            int count = popBoard(n, m);
            if (count > 0) {
                answer += count;
                downBoard(n, m);
            } else {
                break;
            }
        }

        return answer;
    }
}
