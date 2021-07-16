class Solution {
    public int search(int[] nums, int target) {
        int leftBoundary = binaryLeft(nums, target);
        if (leftBoundary == -1) {
            return 0;
        }
        int rightBoundary = binaryRight(nums, target);
        return rightBoundary - leftBoundary + 1;
    }

    private int binaryLeft(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int middle = -1;
        while (low <= high) {
            middle = low + (high - low) / 2;
            if (nums[middle] > target) {
                high = middle - 1;
            } else if (nums[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        if (low < nums.length && nums[low] == target) {
            return low;
        }
        return -1;
    }

    private int binaryRight(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int middle = -1;
        while (low <= high) {
            middle = low + (high - low) / 2;
            if (nums[middle] > target) {
                high = middle - 1;
            } else if (nums[middle] < target) {
                low = middle + 1;
            } else {
                low = middle + 1;
            }
        }
        if (high >= 0 && nums[high] == target) {
            return high;
        }
        return -1;
    }
}