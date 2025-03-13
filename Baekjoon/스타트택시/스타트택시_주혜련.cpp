#include <algorithm>
#include <iostream>
#include <string.h>
#include <climits>
#include <cassert>
#include <queue>
#include <cmath>
#pragma warning(disable:4996)
using namespace std;
typedef pair<int, int> pii;
typedef pair<pii, pii> pp;

int N, M, K;
int arr[21][21];
int chk[21][21];
vector<pp> v;
int deleted[401];
int ty, tx;

queue<pii> q;
int way[4][2] = { {-1,0},{0,-1},{1,0},{0,1} };

void init() {
	cin >> N >> M >> K;
	for (int i = 0; i < N; i++)for (int j = 0; j < N; j++) cin >> arr[i][j];
	cin >> ty >> tx; ty--, tx--;
	for (int i = 0; i < M; i++) {
		int sy, sx, ey, ex; cin >> sy >> sx >> ey >> ex;
		v.push_back({ {sy-1,sx-1},{ey-1,ex-1} });
		arr[sy-1][sx-1] = -(int)v.size();
	}

}

pii findPassenger() {
	memset(chk, 0, sizeof(chk));
	while (!q.empty()) q.pop();
	chk[ty][tx] = 1;
	q.push({ ty,tx });

	int minDist = INT_MAX;

	int p = -1, cnt = -1;
	pii p_pos = { INT_MAX,INT_MAX };
	while (!q.empty()) {
		int y = q.front().first;
		int x = q.front().second;
		q.pop();

		if (arr[y][x] < 0) {
			if (minDist > chk[y][x]) {
				minDist = chk[y][x];
				p = -arr[y][x], cnt = chk[y][x] - 1;
				p_pos = { y,x };
			}
			else if (minDist == chk[y][x]) {
				if (p_pos.first>y||(p_pos.first==y&&p_pos.second>x)) {
					p = -arr[y][x];
					assert(cnt==chk[y][x]-1);
					p_pos = { y,x };
				}
			}
		}
		if (minDist<chk[y][x]) break;

		for (int i = 0; i < 4; i++) {
			int ny = y + way[i][0];
			int nx = x + way[i][1];
			if (ny >= 0 && nx >= 0 && ny < N && nx < N && chk[ny][nx] == 0 && arr[ny][nx] != 1) {
				chk[ny][nx] = chk[y][x] + 1;
				q.push({ ny,nx });
			}
		}
	}
	ty = p_pos.first, tx = p_pos.second;
	return { p,cnt };
}

int toDest(int p) {
	memset(chk, 0, sizeof(chk));
	while (!q.empty()) q.pop();
	chk[ty][tx] = 1;
	q.push({ ty,tx });

	while (!q.empty()) {
		int y = q.front().first;
		int x = q.front().second;
		q.pop();

		if (y == v[p].second.first && x == v[p].second.second) {
			ty = y, tx = x;
			return chk[y][x] - 1;
		}

		for (int i = 0; i < 4; i++) {
			int ny = y + way[i][0];
			int nx = x + way[i][1];
			if (ny >= 0 && nx >= 0 && ny < N && nx < N && arr[ny][nx] != 1 && chk[ny][nx] == 0) {
				chk[ny][nx] = chk[y][x] + 1;
				q.push({ ny,nx });
			}
		}
	}
	return -1;
}

int solve() {
	for (int i = 0; i < M; i++) {
		//최단거리 승객 찾기
		pii ret = findPassenger();
		if (ret.first == -1) return -1; //경로가 존재X
		if (ty == INT_MAX && tx == INT_MAX) return -1;

		int p = ret.first-1;
		int cnt = ret.second;
		arr[v[p].first.first][v[p].first.second] = 0;

		//이동
		K -= cnt; if (K <= 0) return -1;

		//목적지로 이동
		cnt = toDest(p);
		if (cnt == -1) return -1; //경로가 존재X
		K -= cnt; if (K < 0) return -1;

		//도착성공 +연료*2
		K += cnt * 2;
	}
	return K;
}

int main() {
	//freopen("sample_input.txt", "r", stdin);

	init();
	int ans=solve();
	cout << ans << endl;

	//fclose(stdin);
}
