#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

struct fileName
{
	int index;
	string head;
	int number;
};

vector<fileName> v;

bool cmp(const fileName& f1, const fileName& f2)
{
	if (f1.head == f2.head)
	{
		if (f1.number == f2.number)
		{
			return f1.index < f2.index;
		}
		else
		{
			return f1.number < f2.number;
		}
	}
	else
	{
		return f1.head < f2.head;
	}
}

vector<string> solution(vector<string> files)
{
	vector<string> ans;

	for (int i = 0; i < files.size(); i++)
	{
		vector<int> idx;

		for (int j = 0; j < files[i].size(); j++)
		{
			if ('0' <= files[i][j] && files[i][j] <= '9')
			{
				idx.push_back(j);
			}
		}

		string head = "";

		for (int j = 0; j < idx[0]; j++)
		{
			head += tolower(files[i][j]);
		}
		string number = files[i].substr(idx[0], idx.size());

		fileName f;
		f.index = i;
		f.head = head;
		f.number = stoi(number);

		v.push_back(f);
	}

	sort(v.begin(), v.end(), cmp);

	for (int i = 0; i < v.size(); i++)
	{
		ans.push_back(files[v[i].index]);
	}

	return ans;
}