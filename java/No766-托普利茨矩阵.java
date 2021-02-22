class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        // (i, j) --> (i+1, j+1)
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 每一条从左上到右下的对角线起点属于第一行或第一列
        for(int row = 0; row < rows; row++) {
            boolean success = allEquals(matrix, row, 0);
            if(!success) {
                return false;
            }
        }
        for(int col = 0; col < cols; col++) {
            boolean success = allEquals(matrix, 0, col);
            if(!success) {
                return false;
            }
        }
        return true;
    }

    private boolean allEquals(int[][] matrix, int i, int j) {
        int val = matrix[i][j];
        while(i < matrix.length && j < matrix[0].length) {
            if(matrix[i][j] != val) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
}