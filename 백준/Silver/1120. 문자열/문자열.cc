#include <iostream>
#include <cstdio>
#include <algorithm>
#include <queue>
#include <vector>
#include <cstring>
#include <cmath>
#include <string>

using namespace std;

string x, y;
void solution(){
    int mini = 987654321;
    
    for (int i = 0; i < y.size(); i++){
        if (i + x.size() > y.size()) 
            continue;
        int diff = 0;
        
        for (int j = 0; j < x.size(); j++){
            if (x[j] != y[i + j]) 
                diff++;
        }
        mini = min(diff, mini);
    }
    cout << mini;
}
int main(){
    cin.tie(0);
    cout.tie(0);
    cin >> x >> y;
    
    solution();
    
    return 0;
}