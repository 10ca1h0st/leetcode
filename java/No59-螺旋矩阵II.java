class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int iStart = 0;
        int iEnd = n-1;
        int jStart = 0;
        int jEnd = n-1;
        int e = 1;
        while(iStart < iEnd && jStart < jEnd) {
            for(int j = jStart; j <= jEnd; j++) {
                matrix[iStart][j] = e;
                e++;
            }
            for(int i = iStart+1; i < iEnd; i++) {
                matrix[i][jEnd] = e;
                e++;
            }
            for(int j = jEnd; j >= jStart; j--) {
                matrix[iEnd][j] = e;
                e++;
            }
            for(int i = iEnd-1; i > iStart; i--) {
                matrix[i][jStart] = e;
                e++;
            }
            iStart++;
            iEnd--;
            jStart++;
            jEnd--;
        }
        if(iStart == iEnd) {
            matrix[iStart][jStart] = e;
        }
        return matrix;
    }
}