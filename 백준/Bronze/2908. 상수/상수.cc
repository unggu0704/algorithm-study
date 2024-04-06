#include <string>
#include <iostream>

using namespace std;

int main(void) {
	string x, y;
	cin >> x >> y;
	string big;

	for (int i = 2; i >= 0; i--) {
		if (x[i] > y[i]) {
			big = x; break;
		}
		else if (x[i] < y[i]) {
			big = y; break;
		}
	}
	cout << big[2] << big[1] << big[0];
	return 0;
}