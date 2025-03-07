#include <iostream>
#include <algorithm>

#define SIZE 10

using namespace std;

int graph[SIZE][SIZE];
int eachPaperLeft[5+1] = {0, 5, 5, 5, 5, 5}; // 각 색종이 크기의 남은 개수
int leastPaperUsed = 26;

bool canAttach(int r, int c, int size)
{
    if (r + size > SIZE || c + size > SIZE) return false;

    for (int i = r; i < r + size; i++) {
        for (int j = c; j < c + size; j++) {
            if (graph[i][j] == 0) return false;
        }
    }
    return true;
}

void attachPaper(int r, int c, int size, int value)
{
    for (int i = r; i < r + size; i++) {
        for (int j = c; j < c + size; j++) {
            graph[i][j] = value;
        }
    }
}

void dfs(int r, int c, int used)
{
    if (used >= leastPaperUsed) return; // 가지치기
    
    if (c == SIZE) { // 다음 행으로 이동
        dfs(r + 1, 0, used);
        return;
    }

    if (r == SIZE) { // 모든 칸을 덮음
        leastPaperUsed = min(leastPaperUsed, used);
        return;
    }

    if (graph[r][c] == 0) { // 이미 덮여 있음
        dfs(r, c + 1, used);
        return;
    }
    
    // 큰 색종이부터 붙여보기
    for (int size = 5; size >= 1; size--) {
        if (eachPaperLeft[size] > 0 && canAttach(r, c, size)) {
            attachPaper(r, c, size, 0);
            eachPaperLeft[size]--;
            dfs(r, c + 1, used + 1);
            attachPaper(r, c, size, 1);
            eachPaperLeft[size]++;
        }
    }
}

int main()
{
    // 입력
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            cin >> graph[i][j];
        }
    }
    
    // DFS 탐색
    dfs(0, 0, 0);
    
    if (leastPaperUsed == 26) leastPaperUsed = -1; // 모든 칸을 덮을 수 없는 경우

    // 결과 출력
    cout << leastPaperUsed << endl;

    return 0;
}
