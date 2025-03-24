#include <string>
#include <vector>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cassert>
#include <cmath>
#include <iostream>
using namespace std;
typedef pair<int,int> pii;
typedef long long ll;
#define endl '\n'

vector<int> stones;
int K,N;

bool isValid(int k){//건너갈 수 있음
    for(int i=0;i<N;i++) stones[i]-=k;
    
    bool ret=true;
    for(int i=0;i<N;i++){
        if(stones[i]<=0){
            int cnt=0;
            while(cnt<=K&&i+cnt<N&&stones[i+cnt]<=0) cnt++;
            if(cnt>=K){
                ret=false; break;
            }
            else i+=cnt;
        }
    }
    
    for(int i=0;i<N;i++) stones[i]+=k;

    return ret;
}

ll solve(){
    ll s=0, e=200000000;
    ll ans=0;
    while(s<=e){
        ll m=(s+e)/2;
        if(isValid(m)){
            s=m+1;
            ans=max(ans,m);
        }
        else e=m-1;
    }
    return ans;
}

int solution(vector<int> _stones, int k) {
    K=k; stones=_stones;
    N=_stones.size();
    
    //solve
    int answer = solve()+1;
    return answer;
}
