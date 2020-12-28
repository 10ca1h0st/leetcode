#include <iostream>
#include <string>
#include <algorithm> //for revrese
#include <cmath> //for pow
using namespace::std;


class Solution{
	public:
		int reverse(int x){
			int flag = 0;
			if(x<0) flag = 1;
			x = pow(-1,flag)*x;
			string sx = to_string(x);
			//compare when it is string
			string s_int_max = to_string(INT_MAX);
			string s_int_min = to_string(INT_MIN);
			s_int_min = s_int_min.substr(1);
			int size = s_int_max.size();
			sx.resize(size,'0');
			std::reverse(sx.begin(),sx.end());
			if(flag){
				//negative
				int res = sx.compare(s_int_min);
				if(res>0) sx="0";
			}else{
				int res = sx.compare(s_int_max);
				if(res>0) sx="0";
			}
			x = stoi(sx);
			//cout<<x<<endl;
			if(flag) x = -x;
			return x;
		}
}; 

int main(){
	Solution* s = new Solution();
	cout<<s->reverse(123)<<endl;
	//cout<<LONG_MAX<<endl;
	return 0;
}
