class Solution {
	// 判断数组的最后一个数，是否能将数组分割成两部分，第一部分比该数小，第二部分比该数大
	// 如果可以，则递归判断那两部分是否满足条件
    int[] postorderG = null;
    public boolean verifyPostorder(int[] postorder) {
        postorderG = postorder;
        int length = postorderG.length;
        if(length == 0 || length == 1) {
            return true;
        }
        return helper(0, length-1);
    }
    boolean helper(int start, int end) {
        if(end == start) {
            return true;
        }
        // end - start >= 1
        int root = postorderG[end];
        int i = 0;
        // the position where root is placed
        int peek = -1;
        int prev = postorderG[start];
        for(i = start+1; i < end; i++) {
            if(prev > root) {
                if(postorderG[i] < root) {
                    return false;
                }
            }
            else {
                // prev < root
                if(postorderG[i] > root) {
                    peek = i;
                }
            }
            prev = postorderG[i];
        }
        if(peek == -1) {
            return helper(start, end-1);
        }
        return helper(start, peek-1) && helper(peek, end-1);
    }
}