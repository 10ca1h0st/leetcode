class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 第一行是否有0
        boolean firstRow = false;
        boolean firstCol = false;
        for(int j = 0; j < n; j++) {
            if(matrix[0][j] == 0) {
                firstRow = true;
                break;
            }
        }
        for(int i = 0; i < m; i++) {
            if(matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int j = 1; j < n; j++) {
            if(matrix[0][j] == 0) {
                for(int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for(int i = 1; i < m; i++) {
            if(matrix[i][0] == 0) {
                for(int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(firstRow) {
            Arrays.fill(matrix[0], 0);
        }
        if(firstCol) {
            for(int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}