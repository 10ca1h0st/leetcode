class Solution {
    public int pileBox(int[][] box) {
        // 将箱子按照宽度升序排序，
        Arrays.sort(box, new Comparator<int[]>() {
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
                // a[0] == b[0] && a[1] == b[1]
                if(a[2] > b[2]) {
                    return -1;
                }
                else if(a[2] < b[2]) {
                    return 1;
                }
                return 0;
            }
        });
        // 排好序的箱子，以box[i]为最底下箱子可堆起来的箱子堆的最大高度为dp[i]
        int[] dp = new int[box.length];
        int ret = 0;
        for(int i = 0; i < box.length; i++) {
            dp[i] = box[i][2];
        }
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < i; j++) {
                if(box[j][1] < box[i][1] && box[j][2] < box[i][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + box[i][2]);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}