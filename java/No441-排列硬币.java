class Solution {
    public int arrangeCoins(int n) {
        int low = 1;
        int high = n;
        int mid = 0;
        while (low <= high) {
            mid = low + (high-low)/2;
            long count = (1+mid)*(long)mid/2;
            if (count < n) {
                low = mid+1;
            } else if (count > n) {
                high = mid-1;
            } else {
                return mid;
            }
        }
        return high;
    }
}