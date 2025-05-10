#include <iostream>
#include <queue>
#include <set>
#include <algorithm>

using namespace std;


bool visited[201][201][201];

struct State {
    int x, y, z;
    State(int _x, int _y, int _z) : x(_x), y(_y), z(_z) {}
};

struct PourResult {
    int from;
    int to;
};

/* from 용기의 물을 to 용기로 붓는 함수
 * (fx, fy): 현재 from, to 물의 양
 * (capF, capT): from, to 용기의 최대 용량
*/
PourResult pour(int fx, int fy, int capF, int capT) {
    int space = capT - fy;      // to 용기에 더 들어갈 수 있는 양
    int move = min(fx, space);  // 실제로 옮길 수 있는 양

    fx -= move;
    fy += move;
    PourResult result = {fx, fy};

    return result;
}

int main()
{
    int A, B, C;
    cin >> A >> B >> C;

    // 큐에는 State(물통의 상태)를 넣고 BFS 진행
    queue<State> q;

    // 시작 상태: A, B는 0, C는 가득
    State start(0, 0, C);
    visited[start.x][start.y][start.z] = true;
    q.push(start);

    // 첫 번째 물통(A)이 비어 있을 때, 세 번째 물통(C)의 물 양을 저장
    set<int> answers;
    answers.insert(C);

    while (!q.empty()) {
        State cur = q.front();
        q.pop();

        int x = cur.x;
        int y = cur.y;
        int z = cur.z;

        // 6가지 부을 수 있는 경우를 시도

        // [#1] x -> y
        {
            PourResult pr = pour(x, y, A, B);
            int nx = pr.from;
            int ny = pr.to;
            int nz = z;
            if(!visited[nx][ny][nz]) {
                visited[nx][ny][nz] = true;
                q.push(State(nx, ny, nz));
                if(nx == 0) answers.insert(nz);
            }
        }

        // [#2] x -> z
        {
            PourResult pr = pour(x, z, A, C);
            int nx = pr.from;
            int nz = pr.to;
            int ny = y;
            if(!visited[nx][ny][nz]) {
                visited[nx][ny][nz] = true;
                q.push(State(nx, ny, nz));
                if(nx == 0) answers.insert(nz);
            }
        }

        // [#3] y -> x
        {
            PourResult pr = pour(y, x, B, A);
            int ny = pr.from;
            int nx = pr.to;
            int nz = z;
            if(!visited[nx][ny][nz]) {
                visited[nx][ny][nz] = true;
                q.push(State(nx, ny, nz));
                if(nx == 0) answers.insert(nz);
            }
        }

        // [#4] y -> z
        {
            PourResult pr = pour(y, z, B, C);
            int ny = pr.from;
            int nz = pr.to;
            int nx = x;
            if(!visited[nx][ny][nz]) {
                visited[nx][ny][nz] = true;
                q.push(State(nx, ny, nz));
                if(nx == 0) answers.insert(nz);
            }
        }

        // [#5] z -> x
        {
            PourResult pr = pour(z, x, C, A);
            int nz = pr.from;
            int nx = pr.to;
            int ny = y;
            if(!visited[nx][ny][nz]) {
                visited[nx][ny][nz] = true;
                q.push(State(nx, ny, nz));
                if(nx == 0) answers.insert(nz);
            }
        }

        // [#6] z -> y
        {
            PourResult pr = pour(z, y, C, B);
            int nz = pr.from;
            int ny = pr.to;
            int nx = x;
            if(!visited[nx][ny][nz]) {
                visited[nx][ny][nz] = true;
                q.push(State(nx, ny, nz));
                if(nx == 0) answers.insert(nz);
            }
        }
    }

    for(int answer : answers) {
        cout << answer << " ";
    }

    return 0;
}
