#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>

using namespace std;

int main()
{
	stack<int> stk;
	int n;
	cin >> n;

	vector<int> building(n);

	for (int i = 0; i < n; i++)
	{
		cin >> building[i];
	}

	long long ans = 0;

	for (int i = 0; i < n; i++)
	{
		while (!stk.empty() && stk.top() <= building[i])
		{
			stk.pop();
		}

		ans += stk.size();
		stk.push(building[i]);
	}

	cout << ans << "\n";

	return 0;
}