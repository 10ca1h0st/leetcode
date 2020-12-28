#include <iostream>
#include <algorithm>
#include <cctype>
#include <string>

using namespace std;

void ltrim(string &s) {
	s.erase(s.begin(),find_if(s.begin(),s.end(),[](int ch) {
        return !isspace(ch);
    }));
}

void ltrimZERO(string &s) {
	s.erase(s.begin(),find_if(s.begin(),s.end(),[](int ch) {
        return !(ch=='0');
    }));
}

class Solution{
	public:
		int myAtoi(string str){
			int flag=1;
			ltrim(str);
			if(str.size()==0) return 0;
			if(str[0]!='-' && str[0]!='+' && !isdigit(str[0])) return 0;
			if(str[0]=='-'){
				flag = -1;
				str.erase(0,1);
			}
			else if(str[0]=='+'){
				flag = 1;
				str.erase(0,1);
			}
			else{
				//nothing
			}
			if(!isdigit(str[0])){
				return 0;
			}
			ltrimZERO(str);
			long res = 0;
			int max_len = 10;
			while(isdigit(str[0]) && max_len-->=1){
				res = res*10;
				res = res + (str[0]-'0');
				str.erase(0,1);
			}
			if(isdigit(str[0])) return flag==1?INT_MAX:INT_MIN;
			res = res*flag;
			if(res>INT_MAX){
				return INT_MAX;
			}
			if(res<INT_MIN){
				return INT_MIN;
			}
			return int(res);
			 
	}
};

int main(){
	Solution* s = new Solution();
	cout<<s->myAtoi("  0000000000012345678")<<endl;
	return 0;
}


