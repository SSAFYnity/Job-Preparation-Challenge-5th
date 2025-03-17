#include <iostream>
#include <vector>
#include <queue>


using namespace std;

vector<vector<int>> v;
vector<int> dist;

vector<int> bfs(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    v = vector<vector<int>>(n + 1, vector<int>());
    dist = vector<int>(n + 1, -1);

    for (vector<int> road : roads)
    {
        v[road[0]].push_back(road[1]);
        v[road[1]].push_back(road[0]);
    }

    queue<int> q;
    q.push(destination);
    dist[destination] = 0;

    while (!q.empty())
    {
        int here = q.front();
        q.pop();

        for (int i = 0; i < v[here].size(); ++i)
        {
            int to = v[here][i];
            if (dist[to] != -1)
                continue;

            q.push(to);
            dist[to] = dist[here] + 1;
        }
    }

    vector<int> answer;
    for (int n : sources)
        answer.push_back(dist[n]);

    return answer;
}