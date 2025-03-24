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

vector<pii> v;
int N,M;
int chked[31][31];
int changedX[31];

int solution(int m, int n, vector<string> arr) {
    int answer = 0;
    int N=m, M=n;
    
    int stackCnt=0;
    while(1){
        v.erase(v.begin(),v.end());
        v.clear();
        memset(chked,0,sizeof(chked));
        
        //지울 애들 체크
        for(int i=0;i<N;i++)for(int j=0;j<M;j++){
            if(i+1<N&&j+1<M&&arr[i][j]!=' '&&
              arr[i][j]==arr[i][j+1]&&
              arr[i][j]==arr[i+1][j]&&
              arr[i][j]==arr[i+1][j+1]){
                if(chked[i][j]==0) {chked[i][j]=1; v.push_back({i,j});}
                if(chked[i+1][j]==0) {chked[i+1][j]=1; v.push_back({i+1,j});}
                if(chked[i][j+1]==0) {chked[i][j+1]=1, v.push_back({i,j+1});}
                if(chked[i+1][j+1]==0) {chked[i+1][j+1]=1, v.push_back({i+1,j+1});}
            }
        }
        
        if(v.size()==0) break;
        answer+=v.size();
        
        //지우기
        memset(changedX,0,sizeof(changedX));
        for(int i=0;i<v.size();i++){
            int y=v[i].first;
            int x=v[i].second;
            arr[y][x]=' ';
            changedX[x]=1;
        }
        
        //밑으로 당기기
        for(int j=0;j<M;j++){
            if(changedX[j]==0) continue;
            for(int i=N-1;i>=0;i--){
                if(arr[i][j]==' '){
                    for(int k=i-1;k>=0;k--){
                        if(arr[k][j]!=' '){
                            arr[i][j]=arr[k][j];
                            arr[k][j]=' ';
                            break;
                        }
                    }
                }
            }
        }
    
    }
    
    
    return answer;
}
