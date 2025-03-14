#include <iostream>

using namespace std;

int map[10][10];
int paper[5] = { 5, 5, 5, 5, 5 };
int result = 100;

bool check(int i, int j, int k)
{
	for (int x = i; x <= i + k; x++)
	{
		for (int y = j; y <= j + k; y++)
		{
			if (map[x][y] == 0)
				return false;
		}
	}
	return true;
}

void fill(int i, int j, int k, int v)
{
	for (int x = i; x <= i + k; x++)
	{
		for (int y = j; y <= j + k; y++)
		{
			map[x][y] = v;
		}
	}
}

bool fill_check()
{
	for (int i = 0; i < 10; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			if (map[i][j] == 1)
				return false;
		}
	}
	return true;
}

bool range_check(int x, int y)
{
	if (x < 10 && y < 10)
		return true;

	return false;
}

void recur(int cnt)
{
	if (result < cnt)
		return;

	if (fill_check())
	{
		if (result > cnt)
			result = cnt;

		return;
	}

	for (int i = 0; i < 10; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			if (map[i][j] == 1)
			{
				for (int k = 4; k >= 0; k--)
				{
					if (paper[k] > 0 && check(i, j, k) && range_check(i + k, j + k))
					{
						paper[k]--;

						fill(i, j, k, 0);

						recur(cnt + 1);

						paper[k]++;

						fill(i, j, k, 1);
					}
				}

				return;
			}
		}
	}
}

int main()
{
	ios_base::sync_with_stdio(0);
	cin.tie();

	for (int i = 0; i < 10; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			cin >> map[i][j];
		}
	}

	recur(0);

	cout << (result == 100 ? -1 : result);
}