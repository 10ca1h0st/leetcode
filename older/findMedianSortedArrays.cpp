#include <iostream>
#include <vector>
using namespace::std;

class Solution {
public:
    double max(int a,int b){
        return a>b?a:b;
    }
    double min(int a,int b){
        return a>b?b:a;
    }

    double findMedianSortedArrays(vector<int>& A,vector<int>& B){
        int i,j,m,n;
        m = A.size();
        n = B.size();
        if(m>n){
            vector<int> temp = A;
            A = B;
            B = temp;
            int temp2 = m;
            m = n;
            n = temp2;
        }
        int iMin = 0,iMax = m,halfLen = (m+n+1)/2;
        i=0;
        j=0;
        while(iMin<=iMax){
            i = (iMin+iMax)/2;
            j = halfLen-i;
            if(i>iMin && A[i-1]>B[j]){
                iMax = i-1;
            }
            else if(i<iMax && B[j-1]>A[i]){
                iMin = i+1;
            }
            else{
                int maxLeft = 0;
                if(i==0){maxLeft = B[j-1];}
                else if(j==0){maxLeft = A[i-1];}
                else{
                    maxLeft = max(A[i-1],B[j-1]);
                }
                if((m+n)%2==1){
                    //m+n is odd
                    return maxLeft;
                }
                int minRight = 0;
                if(i==m){minRight = B[j];}
                else if(j==n){minRight = A[i];}
                else{
                    minRight = min(A[i],B[j]);
                }
                return (maxLeft+minRight)/2.0;
                
            }

        }
        return 0.0;	
    }
};

int main(){
	vector<int> a = {1,2,3,4,5};
	vector<int> b = {};
	Solution *s = new Solution;
	double res = s->findMedianSortedArrays(a,b);
	cout<<res<<endl;
	return 0;
}
