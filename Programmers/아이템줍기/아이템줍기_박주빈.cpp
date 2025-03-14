#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

int MAP[101][101];

vector<vector<int>> Rect;
int dx[] = { 1, -1, 0, 0 };
int dy[] = { 0, 0, 1, -1 };

bool rectCheck(int nx, int ny)
{
	for (auto figure : Rect)
	{
		if (figure[0] < nx && figure[1] < ny
			&& figure[2] > nx && figure[3] > ny)
		{
			return true;
		}
	}

	return false;
}

int BFS(int cx, int cy, int ix, int iy)
{
	queue<pair<int, pair<int, int>>> q;
	q.push(make_pair(1, make_pair(cx, cy)));

	MAP[cx][cy] = 1;

	while (!q.empty())
	{
		int cost = q.front().first;
		int x = q.front().second.first;
		int y = q.front().second.second;
		q.pop();

		if (x == ix && y == iy)
		{
			return (cost - 1) / 2;
		}

		for (int i = 0; i < 4; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (MAP[nx][ny] < 0 && !rectCheck(nx, ny))
			{
				MAP[nx][ny] = cost;
				q.push(make_pair(cost + 1, make_pair(nx, ny)));
			}
		}
	}

	return -1;
}

int solution(vector<vector<int>> rect, int cx, int cy, int ix, int iy)
{
	for (auto figure : rect)
	{
		for (int i = figure[0] * 2; i <= figure[2] * 2; i++)
			MAP[i][figure[1] * 2] = -1;

		for (int i = figure[1] * 2; i <= figure[3] * 2; i++)
			MAP[figure[0] * 2][i] = -1;

		for (int i = figure[0] * 2; i <= figure[2] * 2; i++)
			MAP[i][figure[3] * 2] = -1;

		for (int i = figure[1] * 2; i <= figure[3] * 2; i++)
			MAP[figure[2] * 2][i] = -1;

		Rect.push_back({ figure[0] * 2, figure[1] * 2, figure[2] * 2, figure[3] * 2 });
	}

	return BFS(cx * 2, cy * 2, ix * 2, iy * 2);
}