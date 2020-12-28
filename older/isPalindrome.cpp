#include <iostream>
#include <vector>

using namespace std;

class Solution{
	public:
		bool isPalindrome(int x){
			if(x<0) return false;
			if(x<10 && x>=0) return true;
			if(x%10==0) return false;
			vector<int> xs;
			while(x>0){
				xs.push_back(x%10);
				x = x/10;
			}
			
			int i=0;
			int j=xs.size()-1;
			while(i<j){
				if(xs[i]!=xs[j]) break;
				i++;
				j--;
			}
			if(i<j) return false;
			return true;
		}
}; 

int main(){
	Solution* s = new Solution();
	cout<<s->isPalindrome(1000021)<<endl;
	return 0;
}
