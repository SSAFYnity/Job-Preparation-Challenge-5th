#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

#define MAX 51
#define SIZE 3

using namespace std;

struct POS
{
	int y, x;
} start[SIZE], dest[SIZE];

struct QUEUE
{
	POS tree[SIZE];
	int cnt, dir;
};

int N;
char map[MAX][MAX];
bool visited[MAX][MAX][2];
int dy[] = { -1, 1, 0, 0 };
int dx[] = { 0, 0, -1, 1 };

int bfs()
{
	queue<QUEUE> q;
	QUEUE tmp;
	tmp.tree[0] = start[0];
	tmp.tree[1] = start[1];
	tmp.tree[2] = start[2];
	tmp.cnt = 0;
	tmp.dir = 0;

	q.push(tmp);

	if (start[0].y == start[1].y)
	{
		visited[start[0].y][start[0].x][0] = true;
	}
	else
	{
		q.front().dir = 1;
		visited[start[0].y][start[0].x][1] = true;
	}

	while (!q.empty())
	{
		tmp = q.front();
		q.pop();

		bool isArrive = true;

		for (int i = 0; i < SIZE; i++)
		{
			if (tmp.tree[i].y != dest[i].y || tmp.tree[i].x != dest[i].x)
				isArrive = false;
		}

		if (isArrive) return tmp.cnt;

		for (int d = 0; d < 4; d++)
		{
			QUEUE ntmp;
			ntmp.dir = tmp.dir;
			ntmp.cnt = tmp.cnt + 1;

			bool isMove = true;

			for (int i = 0; i < SIZE; i++)
			{
				int ny = tmp.tree[i].y + dy[d];
				int nx = tmp.tree[i].x + dx[d];
				ntmp.tree[i] = { ny, nx };

				if (ny < 0 || ny >= N || nx < 0 || nx >= N) { isMove = false; continue; }
				if (map[ny][nx] == '1') { isMove = false; continue; }
			}

			if (!isMove || visited[ntmp.tree[0].y][ntmp.tree[0].x][ntmp.dir]) continue;
			visited[ntmp.tree[0].y][ntmp.tree[0].x][ntmp.dir] = true;
			q.push(ntmp);
		}

		QUEUE ntmp;
		ntmp.dir = 1 - tmp.dir;
		ntmp.cnt = tmp.cnt + 1;

		if (tmp.dir == 0)
		{
			bool isMove = true;

			for (int i = 0; i < SIZE; i++)
			{
				POS up = { tmp.tree[i].y - 1, tmp.tree[i].x };
				POS down = { tmp.tree[i].y + 1, tmp.tree[i].x };

				if (up.y < 0 || up.y >= N || down.y < 0 || down.y >= N || up.x < 0 || up.x >= N) isMove = false;
				if (map[up.y][up.x] == '1' || map[down.y][down.x] == '1') isMove = false;
			}

			if (isMove)
			{
				ntmp.tree[0] = { tmp.tree[1].y - 1, tmp.tree[1].x };
				ntmp.tree[1] = { tmp.tree[1].y, tmp.tree[1].x };
				ntmp.tree[2] = { tmp.tree[1].y + 1, tmp.tree[1].x };

				if (!visited[ntmp.tree[0].y][ntmp.tree[0].x][ntmp.dir])
				{
					visited[ntmp.tree[0].y][ntmp.tree[0].x][ntmp.dir] = true;
					q.push(ntmp);
				}
			}
		}
		else
		{
			bool isMove = true;

			for (int i = 0; i < SIZE; i++)
			{
				POS left = { tmp.tree[i].y, tmp.tree[i].x - 1 };
				POS right = { tmp.tree[i].y, tmp.tree[i].x + 1 };

				if (left.y < 0 || left.y >= N || left.x < 0 || left.x >= N || right.x < 0 || right.x >= N) isMove = false;
				if (map[left.y][left.x] == '1' || map[right.y][right.x] == '1') isMove = false;
			}

			if (isMove)
			{
				ntmp.tree[0] = { tmp.tree[1].y, tmp.tree[1].x - 1 };
				ntmp.tree[1] = { tmp.tree[1].y, tmp.tree[1].x };
				ntmp.tree[2] = { tmp.tree[1].y, tmp.tree[1].x + 1 };

				if (!visited[ntmp.tree[0].y][ntmp.tree[0].x][ntmp.dir])
				{
					visited[ntmp.tree[0].y][ntmp.tree[0].x][ntmp.dir] = true;
					q.push(ntmp);
				}
			}
		}
	}

	return 0;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	int sIdx = 0, dIdx = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			cin >> map[i][j];

			if (map[i][j] == 'B')
			{
				start[sIdx++] = { i,j };
				map[i][j] = '0';
			}

			if (map[i][j] == 'E')
			{
				dest[dIdx++] = { i,j };
				map[i][j] = '0';
			}
		}
	}

	cout << bfs();
};