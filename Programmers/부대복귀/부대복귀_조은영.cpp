#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int>v[100001];
vector<int> answer;
int dist[100001];

void bfs(int start) {
    queue<int>q;
    fill(dist, dist + 100001, -1);
    q.push(start);
    dist[start] = 0;

    while (!q.empty()) {
        int curr = q.front();
        q.pop();

        for (auto& node : v[curr]) {
            if (dist[node] == -1) {
                q.push(node);
                dist[node] = dist[curr] + 1;
            }
        }
    }


}

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {

    for (auto& road : roads) {
        v[road[0]].push_back(road[1]);
        v[road[1]].push_back(road[0]);
    }

    bfs(destination);

    for (auto& source : sources) {
        answer.push_back(dist[source]);
    }

    return answer;
}