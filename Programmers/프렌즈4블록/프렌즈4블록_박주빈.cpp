#include <string>
#include <vector>
#include <map>

using namespace std;

bool check(int i, int j, vector<string>& board)
{
	char c = board[i][j];

	if (c != 'X' && board[i][j + 1] == c
		&& board[i + 1][j] == c && board[i + 1][j + 1] == c)
		return true;
	else
		return false;
}

int remove(int m, int n, vector<string>& board)
{
	map<pair<int, int>, bool> ma;

	for (int i = 0; i < m; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (i + 1 < m && j + 1 < n && check(i, j, board))
			{
				ma.insert({ make_pair(i,j), true });
				ma.insert({ make_pair(i,j + 1), true });
				ma.insert({ make_pair(i + 1,j), true });
				ma.insert({ make_pair(i + 1,j + 1), true });
			}
		}
	}

	for (auto iter = ma.begin(); iter != ma.end(); iter++)
	{
		board[iter->first.first][iter->first.second] = 'X';
	}

	return ma.size();
}

void move(int m, int n, vector<string>& board)
{
	for (int i = m - 1; i >= 0; i--)
	{
		for (int j = n - 1; j >= 0; j--)
		{
			if (board[i][j] == 'X')
			{
				int y = i;
				while (y >= 0)
				{
					if (board[y][j] != 'X')
					{
						board[i][j] = board[y][j];
						board[y][j] = 'X';
						break;
					}
					else
					{
						y--;
					}
				}
			}
		}
	}
}


int main(int m, int n, vector<string> board)
{
	int answer = 0;

	int eraseCnt;
	while (1)
	{
		eraseCnt = remove(m, n, board);
		if (eraseCnt != 0)
		{
			answer += eraseCnt;
			move(m, n, board);
		}
		else
		{
			break;
		}
	}

	return answer;
}