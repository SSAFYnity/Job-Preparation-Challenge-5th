#include <iostream>
using namespace std;

const int MAX = 80000;

int N;
long long height[MAX];

long long stack_height[MAX];
long long stack_count[MAX];
int top = -1;

int main()
{
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> height[i];
    }

    long long result = 0;

    // 오른쪽에서 왼쪽으로 순회
    for (int i = N - 1; i >= 0; i--) {
        long long cnt = 0;

        // stack의 top보다 현재 건물이 크면 pop 하면서 count 누적
        while (top >= 0 && stack_height[top] < height[i]) {
            cnt += stack_count[top] + 1;
            top--;
        }

        // 결과 누적
        result += cnt;

        // 현재 건물 push
        top++;
        stack_height[top] = height[i];
        stack_count[top] = cnt;
    }

    cout << result << "\n";

    return 0;
}
