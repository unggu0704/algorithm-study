#include <iostream>
#include <algorithm>
using namespace std;
string n;
int main()
{
    ios::sync_with_stdio(0), cin.tie(0);
    cin >> n;
    sort(n.begin(), n.end(), greater<char>());
    cout << n;
    return 0;
}