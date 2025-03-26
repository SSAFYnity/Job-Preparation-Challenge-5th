#include <string>
#include <vector>
#include <string.h>
#include <iostream>
#include <algorithm>
#include <climits>
#include <cmath>
#include <unordered_map>
#include <climits>
using namespace std;
typedef pair<int,int> pii;
typedef long long ll;
#define endl '\n'

vector<string> arr;
vector<int> cand[9]; //[k]: bann[k]에 부합하는 arr의 idx들
int N,M;
unordered_map<ll,int> m1;
vector<int> v;
int chked[9];

void findCandidate(string str,int idx){//8
    // cout<<"str: "<<str<<endl;
    for(int i=0;i<arr.size();i++){
        if(arr[i].size()!=str.size()) continue;
        int flag=1; //해당하는지 여부
        for(int j=0;j<str.size();j++){
            if(str[j]!='*'&&str[j]!=arr[i][j]){
                flag=0; break;
            }
        }
        if(flag){
            // cout<<"arr[i]: "<<arr[i]<<endl;
            cand[idx].push_back(i);
        }
    }
}

void pick(int cnt){
    if(cnt==M){
        ll hash=0;
        for(int i=0;i<arr.size();i++){
            // if(chked[i]) cout<<arr[i]<<" ";
            hash=(hash<<1)+chked[i];
        }
        if(m1[hash]==0){
            m1[hash]=1;
            int mcnt=m1.size();
            // cout<<"mcnt: "<<mcnt<<endl;
        }
        return;
    }
    
    for(int i=0;i<cand[cnt].size();i++){
        int idx=cand[cnt][i];
        if(chked[idx]==0){
            chked[idx]=1;
            v.push_back(idx);
            
            pick(cnt+1);
            
            chked[idx]=0;
            v.pop_back();
        }
    }
}

int solution(vector<string> user_id, vector<string> banned_id) {
    arr=user_id; N=arr.size(); M=banned_id.size();
    ll sum=0;
    for(int i=0;i<M;i++){
        string id=banned_id[i];
        findCandidate(id,i);
    }
    
    pick(0);
    
    int answer = m1.size();
    return answer;
}
