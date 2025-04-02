#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <set>
using namespace std;

vector<vector<string>> shapes(8, vector<string>());
vector<int> ans(8, 0);

void Init()
{
	shapes[1].push_back("0");
	shapes[1].push_back("0000");

	shapes[2].push_back("00");

	shapes[3].push_back("001");
	shapes[3].push_back("10");

	shapes[4].push_back("100");
	shapes[4].push_back("01");

	shapes[5].push_back("000");
	shapes[5].push_back("01");
	shapes[5].push_back("101");
	shapes[5].push_back("10");

	shapes[6].push_back("000");
	shapes[6].push_back("00");
	shapes[6].push_back("011");
	shapes[6].push_back("20");

	shapes[7].push_back("000");
	shapes[7].push_back("02");
	shapes[7].push_back("110");
	shapes[7].push_back("00");
}

void Solve(int idx, int shape, vector<int>& board)
{
	for (int i = 0; i < shapes[shape].size(); i++)
	{
		int size = shapes[shape][i].length();
		set<int> s;
		int k = 0;
		bool isAns = true;

		for (int j = idx; j < idx + size; j++)
		{
			if (j >= c) {
				isAns = false;
				break;
			}

			s.insert(board[j] - (shapes[shape][i][k] - '0'));
			k++;
		}

		if (!isAns) continue;
		if (s.size() == 1) ans[shape]++;
	}
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	Init();
	int c, p;
	cin >> c >> p;
	vector<int> board(c);

	for (int i = 0; i < c; i++)
	{
		cin >> board[i];
	}

	for (int i = 0; i < c; i++)
	{
		for (int j = 1; j <= 7; j++)
		{
			Solve(i, j, board);
		}
	}

	cout << ans[p];
};