#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    int now = 0, start = 0, returnTime = 0;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>>pq;

    sort(jobs.begin(), jobs.end());

    // while(!pq.empty()){
    //     // 소요 시간이 현재 시간보다 작고 요청 시간이 이전 start보다 크면
    //     // 이렇게 짜면 요청시간 이전인데 소요시간이 제일 짧아서 먼저 온 경우 무한루프...
    //     if(pq.top().first<now &&pq.top().second>start){
    //         now+=pq.top().first;
    //         start=pq.top().second;
    //         returnTime+=now-start;
    //         pq.pop();
    //     }
    //     else now++;
    // }

    // 작업 횟수
    int idx = 0;

    // 작업할때마다 확인(소요시간, 요청시간 순서로 넣기)
    while (idx < jobs.size() || !pq.empty()) {
        // 실행가능 (큐에 넣는 부분)
        while (idx < jobs.size() && jobs[idx][0] <= now) {
            pq.push({ jobs[idx][1], jobs[idx][0] }); // 소요시간, 요청시간
            idx++;
        }
        // 실행하는 부분
        if (!pq.empty()) {
            now += pq.top().first; // 소요시간
            start = pq.top().second; // 요청시간
            pq.pop();
            returnTime += now - start;
        }
        // 처리 가능한게 없음
        else {
            now = jobs[idx][0];
        }
    }

    answer = returnTime / jobs.size();

    return answer;
}