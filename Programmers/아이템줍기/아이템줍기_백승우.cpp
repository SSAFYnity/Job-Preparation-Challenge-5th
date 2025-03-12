#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <cstring>

using namespace std;

int dy[4] = {0, 0, 1, -1};
int dx[4] = {1, -1, 0, 0};

struct Node {
    int r;
    int c;
};


int solution(vector<vector<int>> rectangle, int characterX, int characterY, int itemX, int itemY) {
    int answer = 0;
    
    // [#1] 캐릭터 좌표, 아이템 좌표 모두 2배
    characterX *= 2, characterY *= 2, itemX *= 2, itemY *= 2;

    const int MAX = 101;  // 확대 후 사용할 최대 크기
    static int board[MAX][MAX];
    memset(board, 0, sizeof(board));

    // [#2] 직사각형을 board 배열에 모두 입력
    for (int i = 0; i < rectangle.size(); i++) {
        int x1, y1, x2, y2;

        for (int j = 0; j < rectangle[0].size(); j++) {
            rectangle[i][j] = rectangle[i][j] * 2;
        }

        x1 = rectangle[i][0], y1 = rectangle[i][1];
        x2 = rectangle[i][2], y2 = rectangle[i][3];

        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                board[y][x] = 1;
            }
        }
    }

    // [#3] 직사각형의 내부는 모두 0으로
    for (int i = 0; i < rectangle.size(); i++) {
        int x1, y1, x2, y2;

        x1 = rectangle[i][0], y1 = rectangle[i][1];
        x2 = rectangle[i][2], y2 = rectangle[i][3];

        for (int y = y1 + 1; y < y2; y++) {
            for (int x = x1 + 1; x < x2; x++) {
                board[y][x] = 0;
            }
        }                
    }

    // [#4] BFS
    queue<Node> q;
    q.push({characterY, characterX});

    while (!q.empty()) {
        int y = q.front().r;
        int x = q.front().c;
        q.pop();

        if (y == itemY && x == itemX) {
            break;
        }

        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];

            if (board[nextY][nextX] == 1) {
                q.push({nextY, nextX});
                board[nextY][nextX] = board[y][x] + 1;
            }
        }
    }

    answer = board[itemY][itemX] / 2;
    return answer;
}
