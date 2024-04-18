#include <bits/stdc++.h>

#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)

using namespace std;

int N;
bool alpha[26 + 1];
vector<string> word;

void input() {
	//입력 받기
	cin >> N;
	cin.ignore();

	for (int i = 0; i < N; i++) {
		string s; getline(cin, s);

		word.push_back(s);
	}
}


vector<string> split(string &s) {
	string temp = "";
	vector<string> ret;
	for (unsigned int i = 0; i < s.size(); i++) {
		if (s[i] == ' ') {
			ret.push_back(temp);
			temp = "";
		}
		else {
			temp += s[i];
		}
	}
	ret.push_back(temp);
	return ret;
}


string keyWord(char& alpha) {
	string pos = "";

	pos += "["; pos += alpha; pos += "]";
	return pos;
}

bool isFirsyKey1(vector<string>& temp) {
	for (auto& t : temp) {
		if ('a' <= t[0] && t[0] <= 'z') {
			if (!alpha[t[0] - 'a']) {
				alpha[t[0] - 'a'] = true;
				string pos = keyWord(t[0]);
				t = pos + t.substr(1, t.size());
				return true;
			}
		}
		else {
			if (!alpha[t[0] - 'A']) {
				alpha[t[0] - 'A'] = true;
				string pos = keyWord(t[0]);
				t = pos + t.substr(1, t.size());
				return true;
			}
		}
	}
	return false;
}


bool isAnyWordKey(vector<string>& temp) {
	for (auto& t : temp) {
		for (unsigned int i = 0; i < t.size(); i++) {
			if (t[i] == ' ') continue;
			if (('a' <= t[i] && t[i] <= 'z') && !alpha[t[i] - 'a']) {
				alpha[t[i] - 'a'] = true;

				string pos = keyWord(t[i]);

				string ret = t.substr(0, i);
				ret += pos;
				ret += t.substr(i + 1, t.size());

				t = ret;
				return true;
			}
			else if (('A' <= t[i] && t[i] <= 'Z') && !alpha[t[i] - 'A']) {
				alpha[t[i] - 'A'] = true;

				string pos = keyWord(t[i]);

				string ret = t.substr(0, i);
				ret += pos;
				ret += t.substr(i + 1, t.size());

				t = ret;
				return true;
			}
		}
	}
	return false;
}

string resultWord(vector<string>& temp) {
	string result;
	for (auto& t : temp) {
		result += t;
		result += " ";
	}
	return result;
}

vector<string> solution() {
	vector<string> answer;

	for (auto& s : word) {

		vector<string> splitString = split(s);

		bool flag = isFirsyKey1(splitString);
		if (!flag) {
			isAnyWordKey(splitString);
		}

		answer.push_back(resultWord(splitString));
	}
	return answer;
}

void output(vector<string>& result) {
	for (auto& ptr : result) {
		cout << ptr << endl;
	}
}

int main() {
	fastio;
	input();
	vector<string> answer = solution();
	output(answer);

	return 0;
}