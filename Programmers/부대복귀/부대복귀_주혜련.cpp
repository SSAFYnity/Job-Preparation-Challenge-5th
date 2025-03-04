#include <string>
#include <vector>
#include <string.h>
#include <algorithm>
#include <iostream>
#include <climits>
#include <cassert>
#include <cmath>
#include <queue>
using namespace std;
typedef pair<int,int> pii;
typedef long long ll;

vector<int> g[100001];
int N;
int dist[100001];

priority_queue<pii,vector<pii>,greater<>> q;

void dijkstra(int src){
    for(int i=0;i<=N;i++) dist[i]=INT_MAX;
    
    q.push({0,src});
    dist[src]=0;
    
    while(!q.empty()){
        int u=q.top().second;
        int src_u=q.top().first;
        q.pop();
        
        if(dist[u]<src_u) continue;
        
        // cout<<"u: "<<u<<" src_u: "<<src_u<<endl;
        
        for(int i=0;i<g[u].size();i++){
            int v=g[u][i];
            if(src_u+1<dist[v]){
                dist[v]=src_u+1;
                q.push({dist[v],v});
            }
        }
    }
}

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    //init
    N=n;
    for(int i=0;i<roads.size();i++){
        int u=roads[i][0], v=roads[i][1];
        g[u].push_back(v);
        g[v].push_back(u);
    }
    
    //solve
    //1. dest-> 각 정점사이 최단거리 구하기: 다익스트라
    dijkstra(destination);
    //2. sources -> dist[] 구해서 ans에 넣기
    vector<int> answer;
    for(int src:sources){
        if(dist[src]==INT_MAX) dist[src]=-1;
        answer.push_back(dist[src]);
    }
    
    
    return answer;
}
