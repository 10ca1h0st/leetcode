class NumMatrix {

    int[][] prefixSum;

    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        if(rows == 0) {
            return;
        }
        int cols = matrix[0].length;
        prefixSum = new int[rows][cols+1];
        for(int i = 0; i < rows; i++) {
            int sum = 0;
            prefixSum[i][0] = 0;
            for(int j = 1; j <= cols; j++) {
                sum += matrix[i][j-1];
                prefixSum[i][j] = sum;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for(int i = row1; i <= row2; i++) {
            sum += prefixSum[i][col2+1] - prefixSum[i][col1];
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */