class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(r * c != nums.length * nums[0].length) {
            return nums;
        }
        int[][] ret = new int[r][c];
        int rOfNums = 0;
        int cOfNums = 0;
        int length = nums[0].length;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                ret[i][j] = nums[rOfNums][cOfNums];
                if(cOfNums == length - 1) {
                    cOfNums = 0;
                    rOfNums++;
                }
                else {
                    cOfNums++;
                }
            }
        }
        return ret;
    }
}