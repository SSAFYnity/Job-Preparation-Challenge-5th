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
    //     // �ҿ� �ð��� ���� �ð����� �۰� ��û �ð��� ���� start���� ũ��
    //     // �̷��� ¥�� ��û�ð� �����ε� �ҿ�ð��� ���� ª�Ƽ� ���� �� ��� ���ѷ���...
    //     if(pq.top().first<now &&pq.top().second>start){
    //         now+=pq.top().first;
    //         start=pq.top().second;
    //         returnTime+=now-start;
    //         pq.pop();
    //     }
    //     else now++;
    // }

    // �۾� Ƚ��
    int idx = 0;

    // �۾��Ҷ����� Ȯ��(�ҿ�ð�, ��û�ð� ������ �ֱ�)
    while (idx < jobs.size() || !pq.empty()) {
        // ���డ�� (ť�� �ִ� �κ�)
        while (idx < jobs.size() && jobs[idx][0] <= now) {
            pq.push({ jobs[idx][1], jobs[idx][0] }); // �ҿ�ð�, ��û�ð�
            idx++;
        }
        // �����ϴ� �κ�
        if (!pq.empty()) {
            now += pq.top().first; // �ҿ�ð�
            start = pq.top().second; // ��û�ð�
            pq.pop();
            returnTime += now - start;
        }
        // ó�� �����Ѱ� ����
        else {
            now = jobs[idx][0];
        }
    }

    answer = returnTime / jobs.size();

    return answer;
}