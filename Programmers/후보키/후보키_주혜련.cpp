#include <string>
#include <vector>
#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <unordered_map>
using namespace std;
typedef pair<int,int> pii;
typedef long long ll;
int N,M;
vector<vector<string>> relation;
vector<int> v;
unordered_map<string,int> m1;


int ans;

//유일성 체크
bool check(){
    //유일성 체크
    m1.clear();
    for(int i=0;i<N;i++){
        //각 행마다, v의 row값들 중복있느지 체크
        string str="";
        for(int j=0;j<v.size();j++){
            int r=v[j];
            str+=relation[i][r];
        }
        if(m1[str]!=0) return false;
        m1[str]=1;
    }
    
    if(v.size()==1) return true;
    
    //최소성 체크
    for(int k=0;k<v.size();k++){
        //k가 빠져도 유일성 만족하면 false
        m1.clear();
        int flag=0;//중복아님
        for(int i=0;i<N;i++){
            //각 행마다, v의 row값들 중복있느지 체크
            string str="";
            for(int j=0;j<v.size();j++){
                if(j==k) continue;
                int r=v[j];
                str+=relation[i][r];
            }
            if(m1[str]!=0){
                flag=1; break; //중복 맞음
            }
            m1[str]=1;
        }
        //하나가 빠졌는데도 중복이 아니면 최소성X: false
        if(flag==0){
            return false;
        }
    }
    
    return true;
}

void pick(int cnt,int start,int end){
    if(cnt==end){
        //후보키 체크
        if(check()){
            ans++;
        }
        return;
    }
    
    for(int i=start;i<M;i++){
        v.push_back(i);
        pick(cnt+1,i+1,end);
        v.pop_back();
    }
}

int solution(vector<vector<string>> _relation) {
    relation=_relation;
    N=relation.size(), M=relation[0].size();
    
    for(int i=1;i<=M;i++){
        pick(0,0,i);
    }
    
    
    int answer = ans;
    return answer;
}
