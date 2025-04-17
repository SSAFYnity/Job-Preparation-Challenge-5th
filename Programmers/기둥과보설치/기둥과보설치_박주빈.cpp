#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int arr[101][101][2];
int N;

bool build(int x, int y, int a)
{
	if (a == 0)
	{
		if (y == 0) return true;
		if (x > 0 && arr[x - 1][y][1]) return true;
		if (y < N && arr[x][y][1]) return true;
		if (y > 0 && arr[x][y - 1][0]) return true;
	}
	else
	{
		if (y > 0 && arr[x][y - 1][0]) return true;
		if (x < N && y > 0 && arr[x + 1][y - 1][0]) return true;
		if (x > 0 && x < N && arr[x - 1][y][1] && arr[x + 1][y][1]) return true;
	}

	return false;
}

bool remove(int x, int y, int a)
{
	arr[x][y][a] = 0;

	if (a == 0)
	{
		if (y < N && arr[x][y + 1][0] && !build(x, y + 1, 0))
			return false;
		if (y < N && arr[x][y + 1][1] && !build(x, y + 1, 1))
			return false;
		if (x > 0 && y < N && arr[x - 1][y + 1][1] && !build(x - 1, y + 1, 1))
			return false;
	}
	else
	{
		if (arr[x][y][0] && !build(x, y, 0))
			return false;
		if (x < N && arr[x + 1][y][0] && !build(x + 1, y, 0))
			return false;
		if (x > 0 && arr[x - 1][y][1] && !build(x - 1, y, 1))
			return false;
		if (x < N && arr[x + 1][y][1] && !build(x + 1, y, 1))
			return false;
	}

	return true;
}

vector<vector<int>> solution(int n, vector<vector<int>> build_frame) {
	N = n;
	vector<vector<int>> answer;

	for (auto frame : build_frame)
	{
		int x = frame[0];
		int y = frame[1];
		int a = frame[2];
		int b = frame[3];

		if (b == 0)
		{
			if (!remove(x, y, a)) arr[x][y][a] = 1;
		}
		else
		{
			if (build(x, y, a)) arr[x][y][a] = 1;
		}
	}

	for (int i = 0; i <= n; i++)
	{
		for (int j = 0; j <= n; j++)
		{
			if (arr[i][j][0]) answer.push_back({ i, j, 0 });
			if (arr[i][j][1]) answer.push_back({ i, j, 1 });
		}
	}

	return answer;
}