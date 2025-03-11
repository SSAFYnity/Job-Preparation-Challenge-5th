#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int map[501][501];
int visited[501][501] = { 0 , };
int n;

int dx[] = { -1, 1 ,0 ,0 };
int dy[] = { 0, 0, -1, 1 };

int dfs(int x, int y)
{
	if (visited[x][y])
		return visited[x][y];

	int result = 0;

	for (int i = 0; i < 4; i++)
	{
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (nx < 1 || ny < 1 || nx > n || ny > n)
			continue;

		if (map[x][y] >= map[nx][ny])
			continue;

		int val = dfs(nx, ny) + 1;

		result = max(result, val);
	}

	return visited[x][y] = result;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n;

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
			cin >> map[i][j];
	}

	int ans = 0;

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			if (visited[i][j])
				continue;

			ans = max(ans, dfs(i, j));
		}
	}

	cout << ans + 1;
}
