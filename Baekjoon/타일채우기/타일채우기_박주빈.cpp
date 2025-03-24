#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int dp[31] = { 0, };
	int n;
	cin >> n;

	if (n % 2 == 1)
	{
		cout << 0;
		return 0;
	}

	dp[0] = 1;
	dp[2] = 3;

	for (int i = 4; i <= n; i += 2)
	{
		for (int j = 0; j < i - 2; j += 2)
		{
			dp[i] += dp[j] * 2;
		}

		dp[i] += dp[i - 2] * dp[2];
	}

	cout << dp[n];
	return 0;
}