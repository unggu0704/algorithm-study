#include<bits/stdc++.h>
#define N 101
using namespace std;


char a[N],b[N],c[N];
int s[N][N][N],i,j,k,u,v;

int main(){
    
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    
    cin>>a>>b>>c;
    for(i=0;a[i];++i)for(j=0;b[j];++j)for(k=0;c[k];++k){
        if(a[i]==b[j]&&b[j]==c[k])
            s[i+1][j+1][k+1]=s[i][j][k]+1;
        else{
            u=max(s[i+1][j][k],max(s[i][j+1][k],s[i][j][k+1]));
            v=max(s[i+1][j+1][k],max(s[i+1][j][k+1],s[i][j+1][k+1]));
            
            s[i+1][j+1][k+1]=max(u,v);
        }
    }
    
    
    cout<<s[i][j][k];
    
    return 0;
}
 
