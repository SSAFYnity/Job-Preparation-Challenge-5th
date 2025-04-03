#include <iostream>
#include <string>
#include <vector>
#include <set>
using namespace std;

vector<int> ans;

bool isUnique(int i)
{
	int len = ans.size();

	for (int n = 0; n < len; n++)
	{
		if ((ans[n] & i) == ans[n])
		{
			return false;
		}
	}
	return true;
}

int solution(vector<vector<string>> relation)
{
	int row = relation.size();
	int col = relation[0].size();

	for (int i = 1; i < (1 << col); i++)
	{
		set<string> store;

		for (int j = 0; j < row; j++)
		{
			string temp;

			for (int k = 0; k < col; k++)
			{
				if (i & (1 << k))
				{
					temp += relation[j][k];
				}
			}
			store.insert(temp);
		}

		if (store.size() == row)
		{
			if (isUnique(i))
			{
				ans.push_back(i);
			}
		}
	}

	return ans.size();
}