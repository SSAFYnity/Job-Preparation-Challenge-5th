#include <iostream>
#include <string>
#include <unordered_map>

#define MAX_N 200'001

using namespace std;

int parent[MAX_N], parentSize[MAX_N];

// 유니온 파인드 초기화
void initUnionFind(int n)
{
    for (int i = 0; i < n; i++) {
        parent[i] = i;
        parentSize[i] = 1;
    }
}

// 루트 노드를 찾는 함수
int findParent(int x)
{
    if (parent[x] == x) return x;
    return parent[x] = findParent(parent[x]);
}

// 두 집합을 합치는 함수
int unionParent(int a, int b)
{
    a = findParent(a);
    b = findParent(b);

    if (a != b) {
        if (parentSize[a] < parentSize[b]) swap(a, b);
        parent[b] = a;
        parentSize[a] += parentSize[b];
    }

    return parentSize[a];
}


int main()
{
    int T;
    cin >> T;

    while (T--) {
        int F;
        cin >> F;

        unordered_map<string, int> nameToIndex;
        nameToIndex.reserve(2 * F);
        nameToIndex.max_load_factor(0.7f);

        initUnionFind(2 * F);

        int indexCount = 0;

        for (int i = 0; i < F; i++) {
            string name1, name2;
            cin >> name1 >> name2;

            if (nameToIndex.find(name1) == nameToIndex.end()) {
                nameToIndex[name1] = indexCount++;
            }
            if (nameToIndex.find(name2) == nameToIndex.end()) {
                nameToIndex[name2] = indexCount++;
            }

            int index1 = nameToIndex[name1];
            int index2 = nameToIndex[name2];

            cout << unionParent(index1, index2) << "\n";
        }
    }
    return 0;
}
