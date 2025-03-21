import java.util.*;

class Solution {
    static boolean[][] visit;
    static char[][] boardData;
    static int answer = 0;

    public int solution(int m, int n, String[] board) {
        boardData = new char[m][n];
        visit = new boolean[m][n];

        // 보드 초기화
        for (int i = 0; i < m; i++) {
            boardData[i] = board[i].toCharArray();
        }

        while (true) {
            if (findBlock(m, n)) { // 2×2 블록 찾기
                removeBlock(m, n);  // 블록 삭제 및 정리
            } else {
                break;
            }
        }

        return answer;
    }

    // 2×2 블록 찾기
    static boolean findBlock(int m, int n) {
        boolean removeBlock = false;
        visit = new boolean[m][n]; // 방문 배열 초기화

        for (int y = 0; y < m - 1; y++) {
            for (int x = 0; x < n - 1; x++) {
                char value = boardData[y][x];

                // 빈 공간이 아니면서 2×2 블록을 이루는지 확인
                if (value != '-' &&
                        value == boardData[y][x + 1] &&
                        value == boardData[y + 1][x] &&
                        value == boardData[y + 1][x + 1]) {

                    visit[y][x] = true;
                    visit[y][x + 1] = true;
                    visit[y + 1][x] = true;
                    visit[y + 1][x + 1] = true;
                    removeBlock = true;
                }
            }
        }
        return removeBlock;
    }

    // 블록 삭제 및 아래로 내리기
    static void removeBlock(int m, int n) {
        int count = 0;

        // 방문된 블록을 제거('-'로 변경)
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                if (visit[y][x]) {
                    boardData[y][x] = '-';
                    count++;
                }
            }
        }
        answer += count;

        // 블록을 아래로 내리기
        for (int x = 0; x < n; x++) {
            ArrayList<Character> newColumn = new ArrayList<>();

            // 기존 블록 중 살아남은 블록만 리스트에 저장
            for (int y = m - 1; y >= 0; y--) {
                if (boardData[y][x] != '-') {
                    newColumn.add(boardData[y][x]);
                }
            }

            // 리스트의 값들을 아래쪽부터 채움
            int y = m - 1;
            for (char c : newColumn) {
                boardData[y--][x] = c;
            }
            while (y >= 0) {
                boardData[y--][x] = '-'; // 남은 공간은 '-'
            }
        }
    }
}
