#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <string.h>
#include <climits>
#include <cmath>
#include <cassert>
#pragma warning(disable:4996)
using namespace std;
typedef pair<int,int> pii;
vector<vector<int>> key,lock;
int N,M;
int lock0Cnt;
int tmp[21][21];
void rotateKey(){
    for(int i=0;i<M;i++)for(int j=0;j<M;j++) tmp[j][M-1-i]=key[i][j];
    for(int i=0;i<M;i++)for(int j=0;j<M;j++) key[i][j]=tmp[i][j];
}

bool solve(){
    for(int i=1-M;i<N;i++){
        for(int j=1-M;j<N;j++){
            //key[0][0] <-> lock[i+a][j+b] 부터 M*M 비교
            int cnt=0, flag=1;
            for(int a=0;a<M;a++){
                for(int b=0;b<M;b++){
                    int ly=i+a, lx=j+b;
                    if(ly>=0&&lx>=0&&ly<N&&lx<N){
                        if(lock[ly][lx]==0&&key[a][b]==1) cnt++;
                        else if(lock[ly][lx]==1&&key[a][b]==0){}
                        else {flag=0;break;}
                    }
                }
                if(flag==0) break;
            }
            if(flag==1&&cnt==lock0Cnt) return true;
        }
    }
    
    return false;
}

bool solution(vector<vector<int>> _key, vector<vector<int>> _lock) {
    //init
    key=_key; lock=_lock;
    N=lock.size(), M=key.size();
    for(int i=0;i<N;i++)for(int j=0;j<N;j++){
        if(lock[i][j]==0) lock0Cnt++;
    }
    
    //rotate
    for(int i=0;i<4;i++){
        rotateKey();
        if(solve()==true) return true;
    }
    
    return false;
}
