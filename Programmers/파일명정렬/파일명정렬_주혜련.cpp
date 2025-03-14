#include <string>
#include <vector>
#include <string.h>
#include <algorithm>
#include <iostream>
#include <climits>
#include <cmath>
#include <cassert>
using namespace std;
typedef pair<int,int> pii;
typedef pair<string,pii> pp;
typedef long long ll;
#define endl '\n'

vector<string> solution(vector<string> files) {
    //1. 1개씩 HEAD, NUMBER, 순서 추출
    vector<pp> v;
    for(int i=0;i<files.size();i++){
        //각 file마다 추출하기
        string file=files[i];
        string head="", number="";
        int Idx=0;
        //HEAD 추출
        for(;Idx<file.size();Idx++){
            //숫자면 break;
            if(file[Idx]>='0'&&file[Idx]<='9') break;
            
            //대문자면 소문자로 변환
            if(file[Idx]>='A'&&file[Idx]<='Z') head+=(file[Idx]-'A'+'a');
            else head+=file[Idx];
        }
        //NUMBER 추출
        int Idx2=Idx;
        for(;Idx2<file.size();Idx2++){
            if(file[Idx2]<'0'||file[Idx2]>'9') break; //숫자가 아니면 break
            
            //처음 0 담지않기
            if(number.size()>=5) break;
            
            number+=file[Idx2];
        }
        
        //v에 담기
        int num=stoi(number);
        v.push_back({head,{num,i}});
        // cout<<"head: "<<v[i].first<<" number: "<<v[i].second.first<<" cnt: "<<v[i].second.second<<endl;
    }
    
    //2. 정렬
    sort(v.begin(),v.end());
    
    //3. 정렬된 순서로 넣기
    vector<string> answer;
    for(int i=0;i<v.size();i++){
        int idx=v[i].second.second;
        answer.push_back({files[idx]});
    }
    
    
    return answer;
}
