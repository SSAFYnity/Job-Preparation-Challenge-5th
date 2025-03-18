class Solution {
    static char[][] matrix;
    static int rowSize;
    static int colSize;

    /*
        2 * 2 형태로 모양이 같은 블록을 탐색
    */
    public boolean search() {
        boolean isPop = false;
        boolean[][] visit = new boolean[rowSize][colSize];  // 터진 블록을 기록할 배열

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (col + 1 >= colSize || row + 1 >= rowSize) {
                    continue;
                } else if (matrix[row][col] < 'A' || matrix[row][col] > 'Z') {
                    continue;
                }
                if (matrix[row][col] == matrix[row][col + 1] &&
                        matrix[row][col] == matrix[row + 1][col] &&
                        matrix[row][col] == matrix[row + 1][col + 1]) {
                    visit[row][col] = true;
                    visit[row][col + 1] = true;
                    visit[row + 1][col] = true;
                    visit[row + 1][col + 1] = true;
                    isPop = true;
                }
            }
        }

        // 터진 블록들을 드롭할 때는 `visit` 배열을 기반으로 드롭해야 함
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (visit[row][col]) {
                    matrix[row][col] = '.';  // 터진 블록을 '.'으로 표시 (빈 공간)
                }
            }
        }

        return isPop;
    }

    public void drop() {
        for (int col = 0; col < colSize; col++) {
            int idx = rowSize - 1;
            for (int row = rowSize - 1; row >= 0; row--) {
                if (matrix[row][col] != '.') {  // 터지지 않은 블록이면
                    matrix[idx--][col] = matrix[row][col];
                }
            }
            // 빈 공간을 남기기 위해서 나머지 공간을 '.'으로 채운다.
            for (int i = idx; i >= 0; i--) {
                matrix[i][col] = '.';
            }
        }
    }

    public int solution(int m, int n, String[] board) {
        int totalPopped = 0;  // 총 터진 블록 수
        matrix = new char[m][n];
        rowSize = m;
        colSize = n;

        // 게임판 복사
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = board[row].charAt(col);
            }
        }

        // 블록이 더 이상 터지지 않으면 종료
        while (true) {
            if (!search()) {
                break;
            }
            drop();
        }

        // 터진 블록의 수는 '.'으로 변경된 문자 개수
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == '.') {
                    totalPopped++;
                }
            }
        }

        return totalPopped;
    }
}
