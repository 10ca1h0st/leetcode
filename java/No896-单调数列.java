class Solution {
    public boolean isMonotonic(int[] A) {
        int length = A.length;
        if(length == 1) {
            return true;
        }
        int i = 1;
        while(i < length && A[i] == A[i-1]) {
            i++;
        }
        if(i == length) {
            return true;
        }
        boolean up = (A[i] > A[i-1])?true:false;
        i++;
        for(; i < length ;i++) {
            if(A[i] > A[i-1]) {
                if(up) {
                    continue;
                }
                else {
                    return false;
                }
            }
            else if(A[i] < A[i-1]) {
                if(up) {
                    return false;
                }
                else {
                    continue;
                }
            }
            else {

            }
        }
        return true;
    }
}