#include <string>
#include <vector>
#include <iostream>

using namespace std;

// 보드 상에서 2×2를 모두 찾고, 지워질 블록을 표시해 주는 함수
// 지워야 하는 블록이 있다면 true, 없다면 false 반환
bool markBlocks(int m, int n, vector<string>& board, vector<vector<bool>>& toRemove) {
    bool found = false;
    
    for (int r = 0; r < m - 1; r++) {
        for (int c = 0; c < n - 1; c++) {
            char block = board[r][c];
            
            if (block == ' ') continue;
            
            if (board[r][c+1] == block &&
                board[r+1][c] == block &&
                board[r+1][c+1] == block) {
                    toRemove[r][c]   = true;
                    toRemove[r][c+1] = true;
                    toRemove[r+1][c] = true;
                    toRemove[r+1][c+1] = true;
                    found = true;
            }
        }
    }
    return found;
}

// 블록을 실제로 제거하고, 제거된 개수를 반환
int removeBlocks(int m, int n, vector<string>& board, vector<vector<bool>>& toRemove) {
    int removedCount = 0;
    for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
            if (toRemove[r][c]) {
                board[r][c] = ' ';
                removedCount++;
            }
        }
    }
    return removedCount;
}

// 블록들을 아래로 떨어뜨리는 함수
void dropBlocks(int m, int n, vector<string>& board) {
    // 각 열에 대하여 처리
    for (int c = 0; c < n; c++) {
        int idx = m - 1;
        for (int r = m - 1; r >= 0; r--) {
            if (board[r][c] != ' ') {
                board[idx][c] = board[r][c];
                idx--;
            }
        }
        for (int r = idx; r >= 0; r--) {
            board[r][c] = ' ';
        }
    }
}

int solution(int m, int n, vector<string> board) {
    int answer = 0;

    while (true) {
        vector<vector<bool>> toRemove(m, vector<bool>(n, false));

        bool found = markBlocks(m, n, board, toRemove);
        
        if (!found) break;
        
        int removed = removeBlocks(m, n, board, toRemove);
        answer += removed;

        // 블록 떨어뜨리기
        dropBlocks(m, n, board);
    }

    return answer;
}
