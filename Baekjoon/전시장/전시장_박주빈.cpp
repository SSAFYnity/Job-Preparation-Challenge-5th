#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

vector<pair<int, int>> v;
vector<int> limit;
vector<int> dp;

int main()
{
	int N, S;
	cin >> N >> S;

	v.resize(N + 1);
	limit.resize(N + 1);
	dp.resize(N + 1);

	for (int i = 1; i <= N; i++)
	{
		cin >> v[i].first >> v[i].second;
	}

	sort(v.begin(), v.end());

	for (int i = 1; i <= N; i++)
	{
		for (limit[i] = limit[i - 1]; limit[i] < i; limit[i]++)
		{
			if (v[i].first - v[limit[i]].first < S)
				break;
		}

		limit[i]--;
	}

	for (int i = 1; i <= N; i++)
	{
		dp[i] = max(dp[i - 1], dp[limit[i]] + v[i].second);
	}

	cout << dp[N];
}