class Solution {
    private int m;
    private int n;

    public boolean searchMatrix(int[][] matrix, int target) {
        m = matrix.length;
        n = matrix[0].length;
        int left = 0;
        int right = XY2Index(new int[]{m-1, n-1});
        while(left <= right) {
            int middle = left + (right-left)/2;
            int[] xy = Index2XY(middle);
            int val = matrix[xy[0]][xy[1]];
            if(val == target) {
                return true;
            }
            else if(target > val) {
                left = middle + 1;
            }
            else {
                right = middle - 1;
            }
        }
        return false;
    }

    public int[] Index2XY(int index) {
        int[] res = new int[2];
        res[0] = index/n;
        res[1] = index%n;
        return res;
    }

    public int XY2Index(int[] xy) {
        return xy[0]*n + xy[1];
    }
}