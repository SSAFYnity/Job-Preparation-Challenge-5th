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

int N;
int h[80001];
stack<pii> s;

int main(){
    //freopen("input.txt","r",stdin);

    //init
    cin>>N;
    for(int i=0;i<N;i++) {
        int k; cin>>h[i];
    }

    //solve
    stack<pii> tmp;
    ll sum=0;
    for(int i=N-1;i>=0;i--) {

        //같거나 큰것 중에 가장 가까운 것 찾기
        while(!s.empty()&&s.top().first<h[i]) {
            tmp.push(s.top());
            s.pop();
        }

        //cnt 계산
        ll cnt=0;
        if(!s.empty()) {
            cnt=s.top().second-i-1;
            // cout<<"i: "<<i<<" h[i]: "<<h[i]<<endl;
            // cout<<"top h: "<<s.top().first<<" i: "<<s.top().second<<endl;
        }
        else cnt=N-i-1;
        sum+=cnt;


        //stack 원상복구 + h[i]도 추가
        while(!tmp.empty()) {
            s.push(tmp.top());
            tmp.pop();
        }
        if(s.empty()||h[i]<s.top().first) s.push({h[i],i});
        else if(h[i]==s.top().first) s.top().second=i;
        else {
            while(!s.empty()&&h[i]>s.top().first) s.pop();
            s.push({h[i],i});
        }
    }
    cout<<sum<<endl;


    //fclose(stdin);
    return 0;
}
