#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

// 전역 변수 및 상수 선언
const int INF = 1e9; // 충분히 큰 값
int N, M;
long long fuel;
int board[21][21]; // 0: 빈칸, 1: 벽

// 승객 정보를 저장할 구조체
struct Passenger {
    int sr, sc;
    int er, ec;
    bool done;
};

Passenger passengers[400]; // 최대 M명의 승객 정보를 저장

struct Node {
    int r;
    int c;
};

// 택시 위치
int taxi_r, taxi_c;

// 방향 정의 (상, 하, 좌, 우)
int dr[4] = {-1, 1, 0, 0};
int dc[4] = {0, 0, -1, 1};


// BFS를 통해 (sr, sc)에서 각 위치까지의 최단 거리 맵 생성
void getDistMap(int sr, int sc, int dist[21][21])
{
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            dist[i][j] = -1;
        }
    }

    queue<Node> q;
    dist[sr][sc] = 0;
    q.push({sr, sc});

    while (!q.empty()) {
        int r = q.front().r;
        int c = q.front().c;
        q.pop();

        for (int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            if (board[nr][nc] == 0 && dist[nr][nc] == -1) {
                dist[nr][nc] = dist[r][c] + 1;
                q.push({nr, nc});
            }
        }
    }
}


int main()
{
    cin >> N >> M >> fuel;

    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            cin >> board[i][j];
        }
    }

    cin >> taxi_r >> taxi_c;
    taxi_r--, taxi_c--;

    // 승객 정보 입력
    for (int i=0; i<M; i++) {
        cin >> passengers[i].sr >> passengers[i].sc >> passengers[i].er >> passengers[i].ec;

        passengers[i].sr--;
        passengers[i].sc--;
        passengers[i].er--;
        passengers[i].ec--;
        passengers[i].done = false;
    }

    // M명의 승객을 모두 태우기 위한 반복
    for (int count=0; count<M; count++) {
        int distMap[21][21];
        getDistMap(taxi_r, taxi_c, distMap);

        int minDist = INF, idx = -1;
        for (int i=0; i<M; i++) {
            if (!passengers[i].done) {
                int r = passengers[i].sr;
                int c = passengers[i].sc;
                int d = distMap[r][c];

                if (d == -1) continue;

                if (d < minDist ||
                    (d == minDist && (r < passengers[idx].sr || (r == passengers[idx].sr && c < passengers[idx].sc)))
                ) {
                    minDist = d;
                    idx = i;
                }
            }
        }

        if (idx == -1
            || minDist == INF
            || fuel < minDist) {
            cout << -1 << "\n";
            return 0;
        }

        taxi_r = passengers[idx].sr;
        taxi_c = passengers[idx].sc;
        fuel -= minDist;

        int distToDest[21][21];
        getDistMap(taxi_r, taxi_c, distToDest);
        int destDist = distToDest[passengers[idx].er][passengers[idx].ec];

        if(destDist == -1 || fuel < destDist) {
            cout << -1 << "\n";
            return 0;
        }

        taxi_r = passengers[idx].er;
        taxi_c = passengers[idx].ec;
        fuel -= destDist;
        fuel += destDist * 2;
        passengers[idx].done = true;
    }

    cout << fuel << "\n";
    return 0;
}
