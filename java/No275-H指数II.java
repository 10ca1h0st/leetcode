class Solution {
    public int hIndex(int[] citations) {
        int low = 0;
        int high = citations.length - 1;
        int middle = -1;
        int ans = -1;
        while (low <= high) {
            middle = low + (high - low) / 2;
            int order = citations.length - 1 - middle;
            if (order + 1 > citations[middle]) {
                ans = order;
                low = middle + 1;
            } else if (order < citations[middle]) {
                high = middle - 1;
            } else {
                // order == citations[middle]
                high = middle - 1;
            }
        }
        if (ans == -1) {
            ans = citations.length;
        }
        return ans;
    }
}