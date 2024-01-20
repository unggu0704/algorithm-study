#include <iostream>
#include <algorithm>
using namespace std;

int main(){
    long long loap[1000001];
    int n;
    cin >> n;
    for(int i = 0; i < n;i++){
        cin >> loap[i];
    }
    sort(loap, loap + n);
    
    long long most = 0;
    for (int i = 0; i < n;i++){
        if(most < loap[i]*(n-i)){ 
            most = loap[i]*(n-i); 
        }
    }
    
    
    cout << most;
    return 0;
}