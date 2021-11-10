class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        for (int i = 0; i < timeSeries.length-1; i++) {
            if (timeSeries[i+1]-timeSeries[i] > duration) {
                ans += duration;
            } else {
                ans += timeSeries[i+1]-timeSeries[i];
            }
        }
        ans += duration;
        return ans;
    }
}