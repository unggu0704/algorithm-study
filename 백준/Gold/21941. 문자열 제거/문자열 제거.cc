#include <iostream> // cin, cout 사용을 위해 추가
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

struct Info {
    string word;
    int score;
};

int d[1001]; 
vector<Info> v; 
string sentence; // 문장

long long dp(int x) {
    if (d[x] != -1) return d[x];
    if (x >= sentence.length()) return 0;
    long long result = 0;

    for (int i = 0; i < v.size(); i++) {
        if (v[i].word == sentence.substr(x, v[i].word.length())) {
            result = max(result, dp(x + v[i].word.length()) + v[i].score);
        }
    }
    result = max(result, dp(x + 1) + 1);
    return d[x] = result;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(false);
    int M;
    cin >> sentence >> M;
    fill(d, d + 1001, -1);
    for (int i = 0; i < M; i++) {
        string w;
        int sc;
        cin >> w >> sc;
        if (w.length() >= sc) continue;
        v.push_back({w, sc});
    }

    long long answer = 0;
    for (int i = sentence.length() - 1; i >= 0; i--) {
        answer = max(answer, dp(i));
    }
    
    cout << answer; // 변수명 오타 수정

    return 0; // main 함수의 반환값 추가
}
