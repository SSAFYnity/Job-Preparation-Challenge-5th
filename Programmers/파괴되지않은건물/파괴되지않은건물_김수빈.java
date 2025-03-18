class Solution {
    public int solution(int[][] board, int[][] skill) {
        int r = board.length, c = board[0].length;
        // 차이 배열 이용
        int[][] diff = new int[r + 1][c + 1];  // 끝까지 변화하는 경우가 있으므로 + 1
        for (int[] s : skill) {
            int w = (s[0] == 1) ? -s[5] : s[5];  // 적군이면 -w, 아군이면 w
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];
            diff[r1][c1] += w;   // 변화량 기록
            diff[r2 + 1][c1] -= w;  // 되돌리기
            diff[r1][c2 + 1] -= w;  // 되돌리기
            diff[r2 + 1][c2 + 1] += w;  // 두 번 변화한 것 다시 되돌리기
        }
        // 누적합 구하기
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i > 0) diff[i][j] += diff[i - 1][j];  // 위쪽 값 추가
                if (j > 0) diff[i][j] += diff[i][j - 1];  // 왼쪽 값 추가
                if (i > 0 && j > 0) diff[i][j] -= diff[i - 1][j - 1];  // 중복 제거
            }
        }
        // 파괴되지 않은 건물 찾기
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] + diff[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
