#include <string>
#include <vector>
#include <string.h>
#include <iostream>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <unordered_map>
using namespace std;
typedef pair<int,string> pii;
typedef long long ll;
#define endl '\n'

unordered_map<string,string> m1;
vector<pii> v;

vector<string> split(string str){
    vector<string> ret;
    char* p=strtok((char*)str.c_str()," ");
    while(p){
        ret.push_back(p);
        p=strtok(NULL," ");
    }
    
    return ret;
}

vector<string> solution(vector<string> record) {
    for(string rec:record){
        auto inp=split(rec);
        // for(string x:inp) cout<<x<<" "; cout<<endl;
        if(inp[0]=="Enter"){
            string A=inp[1];
            string B=inp[2];
            
            m1[A]=B;
            v.push_back({0,A});
        }
        else if(inp[0]=="Leave"){
            string A=inp[1];
            v.push_back({1,A});
        }
        else{//Change
            string A=inp[1];
            string B=inp[2];
            
            m1[A]=B;
        }
    }
    
    
    vector<string> answer;
    for(int i=0;i<v.size();i++){
        string id=v[i].second;
        string name=m1[id];
        if(v[i].first==0){
            answer.push_back(name+"님이 들어왔습니다.");
        }
        else answer.push_back(name+"님이 나갔습니다.");
        
    }
    
    return answer;
}
