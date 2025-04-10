#include <string>
#include <vector>
#include <iostream>
#include <climits>
#include <cmath>
#include <cassert>
#include <algorithm>
#include <queue>
#include <string.h>
#pragma warning(disable:4996)
#define endl '\n'
using namespace std;
typedef pair<int,int> pii;
typedef long long int ll;


pii node[300002];
int N,S;
int ans;
int dp[300002];
int limit[300002];

void init() {
    cin>>N>>S;
    node[0]={0,0};
    for(int i=1;i<=N;i++) {
        cin>>node[i].first>>node[i].second;
    }
    sort(node,node+N+1);
}


void solve() {
    for(int i=1;i<=N;i++) {
        for(limit[i]=limit[i-1];limit[i]<i;++limit[i])
            if(node[i].first-node[limit[i]].first<S) break;
        --limit[i];
    }

    for(int i=1;i<=N;i++) {
        dp[i]=max(dp[i-1],dp[limit[i]]+node[i].second);
    }
    cout<<dp[N]<<endl;
}

int main(){
    freopen("input.txt","r",stdin);

    init();
    solve();

    fclose(stdin);
    return 0;
}
