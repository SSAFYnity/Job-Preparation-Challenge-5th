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

int N,M;
int arr[10001];
priority_queue<int,vector<int>,greater<int> > q;

void init() {
    cin>>N>>M;
    for(int i=0;i<N;i++) cin>>arr[i];
    sort(arr,arr+N,greater<int>());
}

void solve() {
    int next=0;
    int cur=0;
    while(next<N) {
        //꽉 차있으면 1개씩 빼기
        assert(q.size()<=M);
        if(q.size()==M) {
            if(!q.empty()) {
                cur=q.top();
                q.pop();
            }
        }

        //한개 추가
        q.push(arr[next++]+cur);
    }

    while(!q.empty()) {
        cur=q.top();
        q.pop();
    }

    cout<<cur<<endl;
}

int main(){
    freopen("input.txt","r",stdin);

    init();
    solve();


    fclose(stdin);
    return 0;
}
