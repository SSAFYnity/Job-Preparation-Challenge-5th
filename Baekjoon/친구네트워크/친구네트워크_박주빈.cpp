#include <iostream>
#include <algorithm>
#include <map>

using namespace std;

int t, n, temp;
int parent[200001];
int net[200001];

string s1, s2;
map<string, int> m;

int Find(int k)
{
	if (k == parent[k])
		return k;

	return parent[k] = Find(parent[k]);
}

void Union(int a, int b)
{
	a = Find(a);
	b = Find(b);

	if (a != b)
	{
		parent[a] = b;
		net[b] += net[a];
		net[a] = 0;
	}
}

int main()
{
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	cin >> t;

	while (t--)
	{
		temp = 1;
		m.clear();

		for (int i = 0; i < 200001; i++)
		{
			parent[i] = i;
			net[i] = 1;
		}

		cin >> n;

		for (int i = 0; i < n; i++)
		{
			cin >> s1 >> s2;

			if (m[s1] == 0)
				m[s1] = temp++;
			if (m[s2] == 0)
				m[s2] = temp++;

			int k1 = m[s1];
			int k2 = m[s2];

			Union(k1, k2);
			cout << max(net[Find(k1)], net[Find(k2)]) << "\n";
		}
	}

	return 0;
}