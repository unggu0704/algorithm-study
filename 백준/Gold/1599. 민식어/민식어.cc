#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
 
map<string,string> m;
map<string,string>::iterator iter;
vector<pair<string,string>> myv;
 
bool compare(pair<string,string> a,pair<string,string> b){
    return a.second < b.second;
}
 
void init(){
    m = { {"a", "a"},{"b","b"},{"k","c"},{"d","d"},{"e","e"},{"g","f"},
	{"h","g"},{"i","h"},{"l","i"},{"m","j"},{"n","k"},{"ng","l"},{"o","m"},{"p","n"},
	{"r","o"},{"s","p"},{"t","q"},{"u","r"},{"w","s"},{"y","t"} };
}

int main(void){
    int t;
    cin >> t;
    init();
 
    for(int i = 0;i < t;i++){
        string str;
        cin >> str;       
        string order = ""; 
 
        for (int i = 0; i  < str.size();i++){
            if(str[i] != 'n'){ 
                string tmp = "";
                tmp = tmp + str[i];
                iter = m.find(tmp);
                 string new_ = iter->second;
                 order = order.append(new_);
            }
            else if(str[i] == 'n'){
 
                   if(str[i+1] == 'g'){ 
                    string temp = "";
                    temp = temp + str[i];
                    temp = temp + str[i+1];
                    iter = m.find(temp);
                    string new_ = iter->second;
                    order = order.append(new_);
                    continue;
                }
 
                else if(str[i+1] != 'g'){ 
                    string temp = "";
                    temp = temp + str[i];
                    iter = m.find(temp);
                    string new_ = iter->second;
                    order = order.append(new_);
                }
 
 
            }
        }
        myv.push_back({str,order});
 
    }
 
    sort(myv.begin(),myv.end(),compare);

    for (int i = 0; i < myv.size(); i++){
        cout << myv[i].first << '\n';
    }
}