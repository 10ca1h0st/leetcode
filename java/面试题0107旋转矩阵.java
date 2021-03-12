class Solution {
    public void rotate(int[][] matrix) {
        // (i, j) -> (j, N-1-i) -> (N-1-i, N-1-j) -> (N-1-j, i) -> (i, j)
        int N = matrix.length;
        for(int i = 0; i < N/2; i++) {
            int k = N - 2*i;
            for(int j = i; j < i+k-1; j++) {
                int a = matrix[i][j];
                int b = matrix[j][N-1-i];
                int c = matrix[N-1-i][N-1-j];
                int d = matrix[N-1-j][i];
                // a->b->c->d->a
                matrix[i][j] = d;
                matrix[j][N-1-i] = a;
                matrix[N-1-i][N-1-j] = b;
                matrix[N-1-j][i] = c;
            }
        }
    }
}