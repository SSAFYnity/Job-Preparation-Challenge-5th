#include <string>
#include <vector>
#include <iostream>
#include <climits>
#include <cmath>
#include <cassert>
#include <algorithm>
#include <string.h>
#pragma warning(disable:4996)
#define endl '\n'
using namespace std;
typedef pair<int,int> pii;
typedef long long int ll;

typedef struct node {
    int id,N,M;
    vector<pii> yx;
}node;

vector<node> v;
vector<node> pick;
int h[102];
int C,P;

node rotate(node n) {

    node ret;
    // swap(ret.N,ret.M); //java는 이거 되나?
    ret.id=n.id; ret.N=n.M, ret.M=n.N;
    int N=n.N, M=n.M;
    for(int i=0;i<n.yx.size();i++) {
        int y=n.yx[i].first;
        int x=n.yx[i].second;
        pii rot={x,N-1-y};
        ret.yx.push_back(rot);
    }
    return ret;
}

void init() {
    node n1;
    n1.id=1,n1.N=4, n1.M=1;
    n1.yx.push_back({0,0});
    n1.yx.push_back({1,0});
    n1.yx.push_back({2,0});
    n1.yx.push_back({3,0});
    v.push_back(n1);

    //여기서 n1 그대로 쓰면 동기화 되나?
    node n2;
    n2.id=2,n2.N=2, n2.M=2;
    n2.yx.push_back({0,0});
    n2.yx.push_back({1,0});
    n2.yx.push_back({0,1});
    n2.yx.push_back({1,1});
    v.push_back(n2);

    node n3;
    n3.id=3,n3.N=2, n3.M=3;
    n3.yx.push_back({0,1});
    n3.yx.push_back({0,2});
    n3.yx.push_back({1,0});
    n3.yx.push_back({1,1});
    v.push_back(n3);

    node n4;
    n4.id=4,n4.N=2, n4.M=3;
    n4.yx.push_back({0,0});
    n4.yx.push_back({0,1});
    n4.yx.push_back({1,1});
    n4.yx.push_back({1,2});
    v.push_back(n4);

    node n5;
    n5.id=5,n5.N=2, n5.M=3;
    n5.yx.push_back({0,1});
    n5.yx.push_back({1,0});
    n5.yx.push_back({1,1});
    n5.yx.push_back({1,2});
    v.push_back(n5);

    node n6;
    n6.id=6,n6.N=2, n6.M=3;
    n6.yx.push_back({0,2});
    n6.yx.push_back({1,0});
    n6.yx.push_back({1,1});
    n6.yx.push_back({1,2});
    v.push_back(n6);

    node n7;
    n7.id=7,n7.N=2, n7.M=3;
    n7.yx.push_back({0,0});
    n7.yx.push_back({1,0});
    n7.yx.push_back({1,1});
    n7.yx.push_back({1,2});
    v.push_back(n7);

    cin>>C>>P;
    for(int i=0;i<C;i++) {
        int x; cin>>x;
        h[i]=x;
    }


    //넘버 맞춤 rotate
    pick.push_back(v[P-1]);
    if(P!=2) {
        pick.push_back(rotate(pick[0]));
        if(P!=1&&P!=3&&P!=4) {
            pick.push_back(rotate(pick[1]));
            pick.push_back(rotate(pick[2]));
        }
    }
}

void solve() {

    int cnt=0;
    for(int k=0;k<pick.size();k++) {
        node b=pick[k];
        int M=b.M;
        //각 블럭 모양에 따라서 가능한 drop위치 구하기
        int maxyfx[3]={0,0,0};
        for(int i=0;i<b.yx.size();i++) {
            int y=b.yx[i].first;
            int x=b.yx[i].second;
            maxyfx[x]=max(maxyfx[x],y);
        }

        for(int i=0;i<C;i++) {
            //{i,i+M} 를 시작으로
            int flag=1;
            if(i+M-1>=C) continue;
            for(int j=0;j<M&&i+j<C;j++) {
                if(h[i+j]+maxyfx[j]!=h[i]+maxyfx[0]){flag=0;break;}
            }
            if(flag) {
                cnt++;
            }
        }
    }
    cout<<cnt<<endl;
}

int main(){
    //freopen("input.txt","r",stdin);

    init();
    solve();


    //fclose(stdin);
    return 0;
}
