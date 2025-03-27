#include <string>
#include <vector>
#include <iostream>
#include <climits>
#include <cmath>
#include <cassert>
#include <algorithm>
#include <queue>
#include <set>
#include <stack>
#pragma warning(disable:4996)
#define endl '\n'
using namespace std;
typedef pair<int,int> pii;
typedef long long int ll;

int N,M;
vector<pii> g[50001];
priority_queue<pii, vector<pii>, greater<pii> > q;

int dijkstra() {
    vector<int> dist(N+1,INT_MAX);
    q.push({0,1});
    while(!q.empty()) {
        int u=q.top().second;
        int src_u=q.top().first;
        q.pop();

        if(dist[u]<src_u) continue;

        for(int i=0;i<g[u].size();i++) {
            int v=g[u][i].first;
            int u_v=g[u][i].second;
            if(src_u+u_v<dist[v]) {
                dist[v]=src_u+u_v;
                q.push({dist[v],v});
            }
        }
    }
    return dist[N];
}

int main(){
    //freopen("input.txt","r",stdin);

    cin>>N>>M;
    for(int i=0;i<M;i++) {
        int u,v,w; cin>>u>>v>>w;
        g[u].push_back({v,w});
        g[v].push_back({u,w});
    }

    int ans=dijkstra();
    cout<<ans<<endl;

    //fclose(stdin);
    return 0;
}
