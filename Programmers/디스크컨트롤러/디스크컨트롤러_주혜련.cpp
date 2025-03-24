#include <string>
#include <vector>
#include <string.h>
#include <algorithm>
#include <iostream>
#include <climits>
#include <cmath>
#include <cassert>
#include <queue>
using namespace std;
typedef pair<int,int> pii;
typedef long long ll;
typedef struct req{
    int id, s, w;
    bool operator>(req& v2){
        if(w!=v2.w) return (w>v2.w);
        if(s!=v2.s) return (s>v2.s);
        return id>v2.id;
    }
}req;

int compStart(req v1, req v2){
    if(v1.s!=v2.s) return (v1.s<v2.s);
    return v1.id<v2.id;
}




priority_queue<req,vector<req>,greater<>> q;

int solution(vector<vector<int>> jobs) {
    vector<req> v;
    int Idx=0;
    for(int i=0;i<jobs.size();i++){
        int s=jobs[i][0], w=jobs[i][1];
        v.push_back({Idx++,s,w});
    }
    sort(v.begin(),v.end(),compStart);

    int time=0,vIdx=0;
    req cur={-1,-1,-1};
    int sum=0;
    while(!q.empty()||vIdx<v.size()){
        //1. 작업 끝났으면 반환
        if(cur.id!=-1&&time==cur.w){
            sum+=time-cur.s;
            cur={-1,-1,-1};
        }
        
        //2. 들어오는 작업 q에 넣기
        while(vIdx<v.size()&&v[vIdx].s==time){
            q.push(v[vIdx++]);
        }
        
        //3. q비어있으면 바로 뽑아서 작업 시키기
        if(cur.id==-1&&!q.empty()){
            req t=q.top(); q.pop();
            cur={t.id,t.s,time+t.w};
        }
        
        
        time++;
    }
    if(cur.id!=-1){
        sum+=cur.w-cur.s;
    }
    
    int answer = sum/jobs.size();
    return answer;
}
