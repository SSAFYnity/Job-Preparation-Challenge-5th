#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

vector<string> solution(vector<string> record)
{
	vector<string> answer, state;
	map<string, string> user;

	for (int i = 0; i < record.size(); i++)
	{
		string str[3];
		string token;
		stringstream ss(record[i]);
		int idx = 0;

		while (ss >> token)
			str[idx++] = token;

		if (str[0] == "Enter")
		{
			state.push_back("���� ���Խ��ϴ�.");
			answer.push_back(str[1]);
			user[str[1]] = str[2];
		}
		else if (str[0] == "Leave")
		{
			state.push_back("���� �������ϴ�.");
			answer.push_back(str[1]);
		}
		else
		{
			user[str[1]] = str[2];
		}
	}

	for (int i = 0; i < answer.size(); i++)
	{
		answer[i] = user[answer[i]] + state[i];
	}

	return answer;
}