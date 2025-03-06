#include <iostream>
#include <algorithm>
#include <string>
#include <queue>
#include <vector>

using namespace std;

struct vectorCompare {
	bool operator()(vector<int> v1, vector<int>v2)
	{
		return v1[1] > v2[1];
	}
};

int solution(vector < vector<int>> jobs)
{
	int ans = 0, j = 0, t = 0;

	sort(jobs.begin(), jobs.end());
	priority_queue<vector<int>, vector<vector<int>>, vectorCompare> pq;

	while (j < jobs.size() || !pq.empty())
	{
		if (jobs.size() > j && t >= jobs[j][0])
		{
			pq.push(jobs[j++]);
			continue;
		}

		if (!pq.empty())
		{
			t += pq.top()[1];
			ans += t - pq.top()[0];
			pq.pop();
		}
		else
		{
			t = jobs[j][0];
		}
	}

	return ans / jobs.size();
}