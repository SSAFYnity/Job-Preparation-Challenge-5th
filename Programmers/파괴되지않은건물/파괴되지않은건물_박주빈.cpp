#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> board, vector<vector<int>> skill)
{
	int ans = 0;

	int sum[board.size() + 1][board[0].size() + 1];
	fill(&sum[0][0], &sum[board.size()][board[0].size() + 1], 0);

	for (auto info : skill)
	{
		int r1 = info[1];
		int c1 = info[2];
		int r2 = info[3];
		int c2 = info[4];

		int degree = (info[0] == 2) ? info[5] : -info[5];

		sum[r1][c1] += degree;
		sum[r1][c2 + 1] += -degree;
		sum[r2 + 1][c1] += -degree;
		sum[r2 + 1][c2 + 1] += degree;
	}

	for (int col = 0; col < board[0].size() + 1; col++)
	{
		for (int row = 0; row < board.size(); row++)
		{
			sum[row + 1][col] += sum[row][col];
		}
	}

	for (int row = 0; row < board[0].size() + 1; row++)
	{
		for (int col = 0; col < board.size(); col++)
		{
			sum[row][col + 1] += sum[row][col];
		}
	}

	for (int i = 0; i < board.size(); i++)
	{
		for (int j = 0; j < board[0].size(); j++)
		{
			board[i][j] += sum[i][j];

			if (board[i][j] > 0)
				ans++;
		}
	}

	return ans;
}
