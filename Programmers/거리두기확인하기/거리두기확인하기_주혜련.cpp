#include <string>
#include <vector>
#include <algorithm>
#include <string.h>
#include <iostream>
#include <climits>
#include <cmath>
#include <cassert>
#include <queue>
using namespace std;
typedef pair<int,int> pii;

vector<pii> pv;
queue<pii> q;
int chked[6][6];
char arr[6][6];
int way[4][2]={{-1,0},{0,1},{1,0},{0,-1}};
int N=5;
int bfs(int sy,int sx){
    memset(chked,0,sizeof(chked));
    while(!q.empty()) q.pop();
    q.push({sy,sx});
    chked[sy][sx]=1;
    while(!q.empty()){
        int y=q.front().first;
        int x=q.front().second;
        q.pop();
        
        for(int i=0;i<4;i++){
            int ny=y+way[i][0];
            int nx=x+way[i][1];
            if(ny>=0&&nx>=0&&ny<N&&nx<N&&chked[ny][nx]==0&&arr[ny][nx]!='X'){
                if(arr[ny][nx]=='P'&&chked[y][x]<=2){
                    return 0;
                } 
                chked[ny][nx]=chked[y][x]+1;
                q.push({ny,nx});
            }
        }
    }
    return 1;
}

int solve(){
    for(int i=0;i<pv.size();i++){
        int ret=bfs(pv[i].first,pv[i].second);
        if(ret==0) return 0;
    }
    return 1;
}

vector<int> solution(vector<vector<string>> places) {
    int tc=0;
    vector<int> answer;
    
    while(tc<5){
        memset(arr,0,sizeof(arr));
        pv.clear();
        for(int i=0;i<N;i++)for(int j=0;j<N;j++){
            arr[i][j]=places[tc][i][j];
            if(arr[i][j]=='P') pv.push_back({i,j});
        }
        answer.push_back(solve());
        tc++;
    }
    return answer;
    
}
