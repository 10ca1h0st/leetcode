class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // 首先将每一个整数对按照索引0升序排序，索引0相同时，按照索引1降序排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] > b[0]) {
                    return 1;
                }
                else if(a[0] < b[0]) {
                    return -1;
                }
                // a[0] == b[0]
                if(a[1] > b[1]) {
                    return -1;
                }
                else if(a[1] < b[1]) {
                    return 1;
                }
                // a[0] == b[0], a[1] == b[1]
                return 0;
            }
        });
        // 按照排序之后的顺序，将整数对中的第二个数拿出来，组成一个一维数组，该数组的最长递增字序列的长度就是答案
        int ret = 0;
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for(int i = 0; i < dp.length; i++) {
            int max = 0;
            for(int j = 0; j < i; j++) {
                if(envelopes[j][1] < envelopes[i][1]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = Math.max(dp[i], max);
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}