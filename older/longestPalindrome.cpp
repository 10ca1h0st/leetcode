#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

using namespace::std;

class Solution{
	public:
		/* needs too much time
		string longestPalindrome(string s){
			//palindrome = [A1,A2,...,An]
			//palindrome should satisfy Ai==A(length-1-i)
			string palindrome = "";
			int maxLength = 0;
			int subLength = 0;
			int sLength = s.size();
			bool res;
			for(int i = 0;i<sLength;i++){
				if(res){
					break;
				}
				subLength = sLength - i;
				for(int j=0;j<i+1;j++){
					if(res){
						break;
					}
					res = isPalindrome(s.substr(j,subLength));
					if(res){
						maxLength = subLength;
						palindrome = s.substr(j,subLength);
					}
				}
			}
			return palindrome;
		}
		bool isPalindrome(string s){
			//cout<<s<<endl;
			int length = s.size();
			for(int i = 0;i<=(length-1)/2;i++){
				//cout<<s[i]<<"=="<<s[length-1-i]<<endl;
				if(s[i] != s[length-1-i]){
					return false;
				}
			}
			return true;
		}
		*/
		
		/*
		//动态规划 
		string longestPalindrome(string s){
			int start=0,end=0,maxLength=0;
			int length = s.size();
			vector<vector<int>> isPalindrome(length,vector<int>(length,0));
			//isPalindrome数组应该只用到右上半部分的三角形 
			int i=0,j=0;
			for(i=0;i<length;i++){
				isPalindrome[i][i] = 1;
				if(maxLength<=1){
					maxLength = 1;
					start = i;
					end = i;
				}
				j = i+1;
				if(j<length){
					if(s[i] == s[j]){
						isPalindrome[i][j] = 1;
						maxLength = 2;
						start = i;
						end = j;
					}
				}
			}
				
			int substrL = 3;
			for(substrL=3;substrL<=length;substrL++){
				for(i=0;i<length-substrL+1;i++){
					j = i+substrL-1;
					if(s[i]==s[j]&&isPalindrome[i+1][j-1]){
						isPalindrome[i][j] = 1;
						maxLength = substrL;
						start = i;
						end = j;
					}
				}
			}
			
			return s.substr(start,maxLength);
		}
		*/
		//镜像
		string longestPalindrome(string s){
			int length = s.size();
			int start=0,maxLength=0;
			int i=0;
			vector<int> lr(2,0);
			for(i=0;i<length;i++){
				lr = expand_from_centre(s,i,i);
				if(maxLength<(lr[1]-lr[0]+1)){
					start = lr[0];
					maxLength = lr[1]-lr[0]+1;
				}
				if(i<length-1){
					lr = expand_from_centre(s,i,i+1);
					if(maxLength<(lr[1]-lr[0]+1)){
						start = lr[0];
						maxLength = lr[1]-lr[0]+1;
					}	
				}
				
			}
			return s.substr(start,maxLength);
		} 
		
		//i==j means that the length of palindrome is even
		vector<int> expand_from_centre(string s,int i,int j){
			int length = s.size();
			int left=0,right=0;
			while(i>=0 && j<length && s[i]==s[j]){
				left = i;
				right = j;
				i = i-1;
				j = j+1;
			}
			return vector<int>{left,right};
		}
		
		
}; 

int main(){
	Solution *s = new Solution();
	//bool r = s->isPalindrome("abcbbcba");
	//cout<<r<<endl;
	string res = "";
	res = s->longestPalindrome("cabcbbcbas");
	cout<<res<<endl;
	res = s->longestPalindrome("cbbd");
	cout<<res<<endl;
	res = s->longestPalindrome("a");
	cout<<res<<endl;
	res = s->longestPalindrome("abba");
	cout<<res<<endl;
	res = s->longestPalindrome("aa");
	cout<<res<<endl;
}
