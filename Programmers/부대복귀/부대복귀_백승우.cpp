#include <string>
#include <vector>
#include <unordered_map>
#include <queue>

#define INF 1000000000

using namespace std;

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    vector<int> answer;
    unordered_map<int, vector<int>> graph;
    
    // 경로 저장
    for (int i = 0; i < roads.size(); i++) {
        graph[roads[i][0]].push_back(roads[i][1]);
        graph[roads[i][1]].push_back(roads[i][0]);
    }

    // 최단 거리 준비
    vector<int> dist(n + 1, INF);
    dist[destination] = 0;

    // BFS 최단 거리 계산
    queue<int> q;
    q.push(destination); // 목적지부터 시작
    while (!q.empty()) {
        int now = q.front();
        q.pop();
        
        for (int next : graph[now]) {
            if (dist[next] > dist[now] + 1) {
                dist[next] = dist[now] + 1;
                q.push(next);
            }
        }
    }

    // 결과 저장
    for (int source : sources) {
        if (dist[source] != INF) {
            answer.push_back(dist[source]);
        }
        else {
            answer.push_back(-1);
        }
    }

    return answer;
}