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

int arr[11][11];
int ans;
int visited[11];

void dfs(int peo, int sum){

    if(peo == 11){
        ans = max(ans, sum);
        return;
    }

    for(int i=0; i<11; i++){
        if(arr[peo][i] && !visited[i]){
            visited[i] = 1;
            dfs(peo+1, sum + arr[peo][i]);
            visited[i] = 0;
        }
    }
}

int main(){
    //freopen("input.txt","r",stdin);

    int T; cin>>T;
    while(T--){
        for(int i=0; i<11; i++){
            for(int k=0; k<11; k++){
                cin>>arr[i][k];
            }
        }

        dfs(0,0);
        cout<<ans<<endl;
        ans = 0;
        memset(visited, 0, sizeof(visited));
    }

    //fclose(stdin);
    return 0;
}
