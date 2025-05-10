#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct Job {
    int requestTime;
    int processTime;
    
    bool operator<(const Job& other) const
    {
        return processTime > other.processTime;
    }
};

int solution(vector<vector<int>> jobs) {
    // 요청 시각 기준으로 정렬
    sort(jobs.begin(), jobs.end());

    priority_queue<Job> pq;

    int currentTime = 0;
    int totalTurnaroundTime = 0;
    int index = 0;
    int jobCount = jobs.size();

    while (index < jobCount || !pq.empty()) {
        // 현재 시간까지 요청된 모든 작업을 대기 큐에 추가
        while (index < jobCount && jobs[index][0] <= currentTime) {
            pq.push({jobs[index][0], jobs[index][1]}); // {요청 시각, 소요 시간}
            index++;
        }

        if (!pq.empty()) {
            // 실행할 작업 선택 (소요 시간이 가장 짧은 작업)
            Job nowJob = pq.top();
            pq.pop();

            // 현재 시간 갱신 및 반환 시간 계산
            currentTime += nowJob.processTime;
            totalTurnaroundTime += (currentTime - nowJob.requestTime);
        } else { // 대기 큐가 비어 있다면
            // 새로운 요청이 들어오는 시점으로 이동
            currentTime = jobs[index][0];
        }
    }

    return totalTurnaroundTime / jobCount;
}
