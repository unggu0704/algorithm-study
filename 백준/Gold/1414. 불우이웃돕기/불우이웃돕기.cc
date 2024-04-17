#include <bits/stdc++.h>
using namespace std;

int leftArr[51], rightArrr[51];

tuple<int, int, int> edge[100000];		

int Find(int a) {
	if (a == leftArr[a]) return a;
	return a = Find(leftArr[a]);
}


void isUnion(int a, int b) {
	a = Find(a);
	b = Find(b);
	if (a == b) 
	return;

	if (rightArrr[a] < rightArrr[b]) {
		leftArr[a] = b;
	}
	else {
		leftArr[b] = a;
	}

	if (rightArrr[a] == rightArrr[b]) {
		rightArrr[a]++;
	}
}

int ch2int(char c) {
	int ret = 0;
	if ('a' <= c && c <= 'z') {
		ret = c - 97 + 1;
	}
	else if ('A' <= c && c <= 'Z') {
		ret = c - 65 + 27;
	}


	return ret;
}

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, tot = 0, edge_cnt = 0;
	cin >> n;
	for (int i = 1; i <= n; i++) {
		string str;
		cin >> str;
		for (int j = 1; j <= str.size(); j++) {
			if (str[j - 1] != '0') {
				int len = ch2int(str[j - 1]);
				tot += len;
				if (i != j) edge[edge_cnt++] = { len, i, j };
			}
		}
		leftArr[i] = i;
	}

	sort(edge, edge + edge_cnt);
	
	
	int cnt = 0, used = 0;
	for (int i = 0; i < edge_cnt; i++) {
		int u, v, w;
		
		tie(w, u, v) = edge[i];
		if (Find(u) == Find(v)) continue;

		isUnion(u, v);
		used += w;
		cnt++;

		if (cnt == n - 1) break;
	}

	if (cnt < n - 1) cout << "-1";
	else cout << tot - used;

	return 0;
}