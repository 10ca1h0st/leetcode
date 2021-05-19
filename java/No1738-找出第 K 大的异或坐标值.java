class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] prefixXor = new int[rows][cols+1];
        for(int i = 0; i < rows; i++) {
            int xor = prefixXor[i][0];
            for(int j = 1; j < cols+1; j++) {
                xor = xor ^ matrix[i][j-1];
                prefixXor[i][j] = xor;
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int[][] V = new int[rows][cols];
        for(int j = 0; j < cols; j++) {
            V[0][j] = prefixXor[0][j+1];
            pq.add(V[0][j]);
        }
        for(int i = 1; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                V[i][j] = V[i-1][j] ^ prefixXor[i][j+1];
                pq.add(V[i][j]);
            }
        }
        int pop = k-1;
        while(pop > 0) {
            pq.poll();
            pop--;
        }
        return pq.poll();
    }
}