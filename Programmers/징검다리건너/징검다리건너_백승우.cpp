#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool canCross(long long& x, const vector<int>& stones, int& k) {
    int consecutive = 0;  // 연속으로 소진된 돌의 개수

    for (int s : stones) {
        if (s < x) { // s < x라면 x명이 지나가기에 부족(즉, 소진되어버린 돌)
            consecutive++;
        } else {
            consecutive = 0;
        }
        
        if (consecutive >= k) { // 연속 소진 돌 개수가 k 이상이면 건널 수 없음
            return false;
        }
    }
    return true;
}

int solution(vector<int> stones, int k) {
    // stones 배열 내 최댓값을 upper bound로 삼음
    long long left = 1;              // 최소 1명은 시도 가능
    long long right = 0;

    for (int s : stones) {
        if (s > right) right = s;
    }

    // 이분 탐색
    long long answer = 0;
    while (left <= right) {
        long long mid = (left + right) / 2;

        if (canCross(mid, stones, k)) { // mid명이 건널 수 있다면, 더 큰 값도 가능한지 탐색
            answer = mid;
            left = mid + 1;
        } else { // mid명이 건너기 불가능하다면, 더 작은 범위 탐색
            right = mid - 1;
        }
    }

    return (int)answer;
}
