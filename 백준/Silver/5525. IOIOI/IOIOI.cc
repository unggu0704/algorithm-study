#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);

	int n, m; string str;
	cin >> n >> m >> str;

	int ans = 0;
	for (int i = 0; i < m; i++) {
		
		int ioi = 0; //IOI의 개수
		if (str[i] == 'O') continue;

		while (str[i + 1] == 'O' && str[i + 2] == 'I') {
			ioi++;

			if (ioi == n) {
				ans++;
				ioi--; 
			}
            
			i += 2; 
		}
	}

	cout << ans << '\n';

}