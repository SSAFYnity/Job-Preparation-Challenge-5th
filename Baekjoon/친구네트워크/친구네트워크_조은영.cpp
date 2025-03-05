#include <iostream>
#include <map>
#define MAX 200001
using namespace std;

int T, F;
string name1, name2;
int parent[MAX];
int friend_num[MAX];

void init() {
	fill(friend_num, friend_num + MAX, 1);

	for (int i = 1; i < MAX; i++) {
		parent[i] = i;
	}
}

int find(int u) {
	if (u != parent[u]) {
		parent[u] = find(parent[u]); // 경로 압축 적용
	}
	return parent[u];
}


void unionFind(int u, int v) {
	u = find(u);
	v = find(v);

	// 친구X
	if (u != v) {
		parent[u] = v;
		friend_num[v] += friend_num[u];
		friend_num[u] = 1;
	}

	cout << friend_num[v] << "\n";
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> T;

	while (T--) {
		cin >> F;
		int id = 1;
		map<string, int>mp;

		init();

		for (int i = 0; i < F; i++) {
			cin >> name1 >> name2;
			if (mp[name1] == 0) {
				mp[name1] = id++;
			}
			if (mp[name2] == 0) {
				mp[name2] = id++;
			}

			unionFind(mp[name1], mp[name2]);
		}

	}

	return 0;
}