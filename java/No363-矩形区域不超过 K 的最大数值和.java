// 自己的解法，将二维矩阵通过枚举子矩阵的起始和终止行，变换为一维
// 这个思路来自
// 面试题 17.24. 最大子矩阵 https://leetcode-cn.com/problems/max-submatrix-lcci/
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = k + 1;
        for(int row = 0; row < rows; row++) {
            // zip代表将第row行到第bottom行压缩到一个一维数组中
            // zip[i] = matrix[row][i] + matrix[row+1][i] + ... + matrix[bottom][i]
            int[] zip = new int[cols];
            // 以第row行为内部矩阵的第一行
            for(int bottom = row; bottom < rows; bottom++) {
                // 以第bottom行为内部矩阵的最后一行
                for(int col = 0; col < cols; col++) {
                    zip[col] += matrix[bottom][col];
                }
                int ans = maxSumSubArray(zip, k);
                if(ans <= k) {
                    if(max <= k) {
                        max = Math.max(max, ans);
                    }
                    else {
                        max = ans;
                    }
                }
            }
        }
        return max;
    }

// 当问题为一维时
    public int maxSumSubArray(int[] array, int k) {
        // 当遍历到第i个元素时，record中保存的是，array[0]+...+array[i]，array[1]+...+array[i]，...，arr[i]
        List<Integer> record = new ArrayList<>();
        int max = 0;
        record.add(array[0]);
        if(array[0] > k) {
            max = k + 1;
        }
        else {
            max = array[0];
        }
        for(int i = 1; i < array.length; i++) {
            int endWithI = 0;
            for(int j = 0; j < record.size(); j++) {
                endWithI = record.get(j) + array[i];
                if(endWithI <= k) {
                    if(max <= k) {
                        max = Math.max(max, endWithI);
                    }
                    else {
                        max = endWithI;
                    }
                }
                record.set(j, endWithI);
            }
            record.add(array[i]);
            if(array[i] <= k) {
                if(max <= k) {
                    max = Math.max(max, array[i]);
                }
                else {
                    max = array[i];
                }
            }
        }
        return max;
    }
}

// 官方解答使用TreeSet加快计算一维时的最大值
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        ans = Math.max(ans, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }
}
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。