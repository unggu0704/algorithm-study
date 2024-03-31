#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <sstream>
using namespace std;

int n, q;
vector<string> inputs[7];
vector<string>::iterator it1;
vector<string>::iterator it2;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n >> q;
	cin.ignore();

	for (int i = 0; i < n; i++)
	{
		string input;
		getline(cin, input);

		stringstream ss(input);
		string temp;
		vector<string> maked;

		while (getline(ss, temp, '#'))
		{
			maked.push_back(temp);
		}

		string this_log = maked[0];
		int this_lv = stoi(maked[1]);

		//this_lv이하의 레벨에 모두 넣는다
		for (int i = 1; i <= this_lv; i++)
			inputs[i].push_back(this_log);
	}

	//레벨별로 로그를 시간순으로 정렬
	for (int i = 1; i <= 6; i++)
		sort(inputs[i].begin(), inputs[i].end());

	for (int i = 0; i < q; i++)
	{
		string input;
		getline(cin, input);

		stringstream ss(input);
		string temp;
		vector<string> maked;

		while (getline(ss, temp, '#'))
		{
			maked.push_back(temp);
		}

		string start = maked[0];
		string end = maked[1];
		int this_lv = stoi(maked[2]);

		it1 = lower_bound(inputs[this_lv].begin(), inputs[this_lv].end(), start);
		it2 = upper_bound(inputs[this_lv].begin(), inputs[this_lv].end(), end);

		cout << it2 - it1 << "\n";
	}

	return 0;
}