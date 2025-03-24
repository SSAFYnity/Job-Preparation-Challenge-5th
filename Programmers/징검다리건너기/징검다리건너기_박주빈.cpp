#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool binarySearch(int& n, vector<int>& stones, int& k)
{
	int cnt = 0;

	for (int i = 0; i < stones.size(); i++)
	{
		if (stones[i] - n <= 0)
			cnt++;
		else
			cnt = 0;

		if (cnt >= k)
			return true;
	}

	return false;
}

int solution(vector<int> stones, int k)
{
	int ans = 0;
	int Start = 1; int End = *max_element(stones.begin(), stones.end());

	while (Start <= End)
	{
		int mid = (Start + End) / 2;

		if (binarySearch(mid, stones, k))
			End = mid - 1;
		else
			Start = mid + 1;
	}

	return Start;
}
