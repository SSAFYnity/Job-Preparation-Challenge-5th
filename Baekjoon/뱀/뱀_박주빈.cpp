#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>
#include <queue>

using namespace std;

int map[101][101];
int n, k, l;
int ans;
int dir = 1;
int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { -1, 0, 1, 0 };

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n >> k;

	for (int i = 0; i < k; i++)
	{
		int row, col;
		cin >> row >> col;
		map[row][col] = 2;
	}

	cin >> l;

	queue<pair<int, char>> time;

	for (int i = 0; i < l; i++)
	{
		int x;
		char c;
		cin >> x >> c;
		time.push({ x, c });
	}

	map[1][1] = 1;
	deque<pair<int, int>> dq;
	dq.push_back({ 1, 1 });

	while (1)
	{
		ans++;
		int nRow = dq.back().first + dy[dir];
		int nCol = dq.back().second + dx[dir];

		if (nRow <= 0 || nRow > n || nCol <= 0 || nCol > n || map[nRow][nCol] == 1)
			break;

		if (map[nRow][nCol] != 2)
		{
			map[dq.front().first][dq.front().second] = 0;
			dq.pop_front();
		}

		map[nRow][nCol] = 1;
		dq.push_back({ nRow, nCol });

		if (ans == time.front().first)
		{
			char ch = time.front().second;

			if (ch == 'D')
				dir = (dir + 1) % 4;
			else
				dir = (dir - 1 + 4) % 4;

			time.pop();
		}
	}

	cout << ans << "\n";
}