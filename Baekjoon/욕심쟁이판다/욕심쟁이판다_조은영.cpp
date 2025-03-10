#include <iostream>
#include <vector>
using namespace std;

int arr[501][501];
int dp[501][501];
int N, ans;
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };

bool isRange(int x, int y) {
	return x >= 0 && x < N&& y >= 0 && y < N;
}

int DFS(int x, int y) {
	if (dp[x][y] != -1) return dp[x][y];

	dp[x][y] = 1;

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (isRange(nx, ny) && arr[nx][ny] > arr[x][y]) {
			dp[x][y] = max(dp[x][y], DFS(nx, ny) + 1);
		}
	}

	// 더이상 못가면 빠져나오는 곳
	return dp[x][y];
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
			dp[i][j] = -1;
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			ans=max(ans,DFS(i, j));
		}
	}

	cout << ans << "\n";

	return 0;
}