class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        long[] prefixSum = new long[candiesCount.length + 1];
        long sum = prefixSum[0];
        for(int i = 1; i < prefixSum.length; i++) {
            sum = sum + candiesCount[i-1];
            prefixSum[i] = sum;
        }
        boolean[] ans = new boolean[queries.length];
        int index = 0;
        for(int[] query : queries) {
            int favType = query[0];
            int favDay = query[1];
            int dailyCap = query[2];
            // 每天至少吃一个
            long min = favDay;
            // 每天最多吃dailyCap个
            long max = (long)dailyCap * (favDay + 1);
            if(prefixSum[favType+1] > min && prefixSum[favType] < max) {
                ans[index] = true;
            }
            else {
                ans[index] = false;
            }
            index++;
        }
        return ans;
    }
}