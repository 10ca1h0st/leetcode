#include <iostream>
#include <string>
#include <vector>

using namespace std;


class Solution{
	public:
		string convert(string s,int numRows){
			if(numRows==1){
				return s;
			}
			int row,column;
			string res = "";
			int length = s.size();
			int tmp_row,tmp_column;
			
			row = numRows;
			column = 2*round_down(length,2*row-2)+round_up(length%(2*row-2),row);
			cout<<"rows:"<<row<<" columns:"<<column<<endl;
			vector<vector<char>> tmp(row,vector<char>(column,0));
			//cout<<tmp.size()<<endl;
			///*
			for(int i=1;i<=length;i++){
				tmp_column = 2*round_down(i,2*row-2)+round_up(i%(2*row-2),row)-1;
				if(i%(2*row-2)>row){
					tmp_row = row-(i%(2*row-2))%row-1;
				}
				else if(0<i%(2*row-2)){
					tmp_row = i%(2*row-2)-1;
				}
				else{
					tmp_row = 1;
				}
				cout<<s[i-1]<<"-->tmp_row:"<<tmp_row<<" tmp_column:"<<tmp_column<<endl;
				tmp[tmp_row][tmp_column] = s[i-1];
			}
			int count = 0;
			char temp;
			for(int i=0;i<row;i++){
				for(int j=0;j<column;j++){
					temp = tmp[i][j];
					if(temp!=0){
						count++;
						res.append(1,temp);
						
					}
				}
			}
			cout<<count<<endl;
			//*/
			return res;
		}
		
		int round_down(int x,int y){
			return x/y;
		}
		
		int round_up(int x,int y){
			return x/y+(x%y?1:0);
		}
};


int main(){
	Solution* s  = new Solution();
	//cout << s->round_down(3,2) << endl;
	//cout << s->round_up(3,2) << endl;
	//cout<<s->convert("123456789",1);
	string a = "123";
	a+='2';
	a+="777";
	cout<<a;
	return 0;
}
