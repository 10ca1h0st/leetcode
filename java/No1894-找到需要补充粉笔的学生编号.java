class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        if (chalk.length == 1) {
            return 0;
        }
        int[] prefixSum = new int[chalk.length+1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i-1] + chalk[i-1];
            if (prefixSum[i] > k) {
                return i-1;
            }
        }
        int mod = k % prefixSum[chalk.length];
        if (mod == 0) {
            return 0;
        }
        int i = binarySearch(prefixSum, mod);
        return i-1;
    }

    public int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length-1;
        int mid = 0;
        while (low <= high) {
            mid = low + (high-low)/2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] == target) {
                return mid + 1;
            } else {
                if (arr[mid-1] < target) {
                    return mid;
                }
                high = mid-1;
            }
        }
        return -1;
    }
}