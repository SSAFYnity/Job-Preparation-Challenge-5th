#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int ans = 0;
int arr[11][11];
bool visited[11];

void dfs(int player, int sum)
{
	if (player == 11)
	{
		ans = max(ans, sum);
		return;
	}

	for (int i = 0; i < 11; i++)
	{
		if (arr[player][i] != 0 && !visited[i])
		{
			visited[i] = true;
			dfs(player + 1, sum + arr[player][i]);
			visited[i] = false;
		}
	}
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(NULL);

	int C;
	cin >> C;

	while (C--)
	{
		for (int i = 0; i < 11; i++)
		{
			for (int j = 0; j < 11; j++)
				cin >> arr[i][j];
		}

		dfs(0, 0);

		cout << ans << "\n";
		ans = 0;
	}
}