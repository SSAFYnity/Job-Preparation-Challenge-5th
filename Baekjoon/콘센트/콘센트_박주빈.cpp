#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, M;
	cin >> N >> M;

	priority_queue<int, vector<int>, greater<int>> pq;
	vector<int> v(N);

	for (int i = 0; i < N; i++)
		cin >> v[i];

	sort(v.begin(), v.end(), greater<>());

	for (int i = 0; i < min(M, N); i++)
		pq.push(v[i]);

	for (int i = M; i < N; i++)
	{
		int top = pq.top();
		pq.pop();
		pq.push(v[i] + top);
	}

	while (pq.size() != 1)
		pq.pop();

	cout << pq.top() << "\n";
};