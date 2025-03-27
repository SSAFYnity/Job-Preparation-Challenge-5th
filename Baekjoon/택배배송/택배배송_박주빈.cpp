#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

vector<pair<int, int>> G[50001];
int dist[50001];
priority_queue <pair<int, int>> pq;

void dijkstra()
{
	dist[1] = 1;
	pq.push(make_pair(-1, 1));

	while (!pq.empty())
	{
		int cur = pq.top().second;
		int d = -pq.top().first;

		pq.pop();

		if (dist[cur] < d) continue;

		for (auto p : G[cur])
		{
			int node = p.first, dd = p.second;
			int& dnode = dist[node];
			if (0 < dnode && dnode <= d + dd) continue;
			dnode = d + dd;
			pq.push(make_pair(-(d + dd), node));
		}
	}
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(NULL);

	int N, M;
	cin >> N >> M;

	while (M--)
	{
		int x, y, d;
		cin >> x >> y >> d;

		G[x].emplace_back(make_pair(y, d));
		G[y].emplace_back(make_pair(x, d));
	}

	dijkstra();

	cout << dist[N] - 1;
}

