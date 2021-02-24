class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < (cols+1)/2; j++) {
                int left = A[i][j];
                int right = A[i][cols - 1 - j];
                A[i][j] = 1 - right;
                A[i][cols - 1 - j] = 1 - left;
            }
        }
        return A;
    }
}