#include <iostream>
#include <vector>
#include <set>

using namespace std;

bool visit[201][201][201] = { 0, };
set<int> ans;
int A, B, C;

void dfs(int a, int b, int c)
{
	if (visit[a][b][c]) return;
	visit[a][b][c] = true;

	if (a == 0) ans.insert(c);

	if (a + b <= B) dfs(0, a + b, c);
	else dfs(a + b - B, B, c);

	if (a + c <= C) dfs(0, b, a + c);
	else dfs(a + c - C, b, C);

	if (b + a <= A) dfs(b + a, 0, c);
	else dfs(A, b + a - A, c);

	if (b + c <= C) dfs(a, 0, b + c);
	else dfs(a, b + c - C, C);

	if (c + a <= A) dfs(c + a, b, 0);
	else dfs(A, b, c + a - A);

	if (c + b <= B) dfs(a, c + b, 0);
	else dfs(a, B, c + b - B);

	return;
}

int main()
{
	cin >> A >> B >> C;

	dfs(0, 0, C);

	for (auto k : ans)
	{
		cout << k << " ";
	}

	return 0;
}