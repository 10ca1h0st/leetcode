class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        int iStart = 0;
        int iEnd = m-1;
        int jStart = 0;
        int jEnd = n-1;
        while(iStart < iEnd && jStart < jEnd) {
            // iStart行从jStart到jEnd
            for(int j = jStart; j <= jEnd; j++) {
                res.add(matrix[iStart][j]);
            }
            // jEnd列从iStart到iEnd
            for(int i = iStart+1; i < iEnd; i++) {
                res.add(matrix[i][jEnd]);
            }
            // row iEnd from col jEnd to jStart
            for(int j = jEnd; j >= jStart; j--) {
                res.add(matrix[iEnd][j]);
            }
            // col jStart from row iEnd to iStart
            for(int i = iEnd-1; i > iStart; i--) {
                res.add(matrix[i][jStart]);
            }
            iStart++;
            iEnd--;
            jStart++;
            jEnd--;
        }
        if(iStart > iEnd || jStart > jEnd) {
            return res;
        }
        if(iStart == iEnd && jStart == jEnd) {
            res.add(matrix[iStart][jStart]);
        }
        if(iStart != iEnd) {
            // only jStart==jEnd
            for(int i = iStart; i <= iEnd; i++) {
                res.add(matrix[i][jStart]);
            }
        }
        if(jStart != jEnd) {
            for(int j = jStart; j <= jEnd; j++) {
                res.add(matrix[iStart][j]);
            }
        }
        return res;
    }
}