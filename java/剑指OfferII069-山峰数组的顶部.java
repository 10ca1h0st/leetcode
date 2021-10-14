class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int ans = 1;
        while (ans < arr.length-1) {
            if (arr[ans] > arr[ans-1] && arr[ans] > arr[ans+1]) {
                return ans;
            }
            ans++;
        }
        return -1;
    }
}