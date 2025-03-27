class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length, m = board[0].length;
        int[][] prefix = new int[n][m];
        for (int[] s: skill) {
            int d = s[5] * (s[0] == 1 ? -1 : 1);
            prefix[s[1]][s[2]] += d;
            if (s[3] < n-1) prefix[s[3]+1][s[2]] -= d;
            if (s[4] < m-1) prefix[s[1]][s[4]+1] -= d;
            if (s[3] < n-1 && s[4] < m-1) prefix[s[3]+1][s[4]+1] += d;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) prefix[i][j] += prefix[i-1][j];
        }
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) prefix[i][j] += prefix[i][j-1];
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                answer += (board[i][j] + prefix[i][j]) > 0 ? 1 : 0;
            }
        }
        return answer;
    }
}