class Solution {
    public boolean search(int[] nums, int target) {
        int length = nums.length;
        int zeroIndex = 1;
        for(; zeroIndex < length; zeroIndex++) {
            if(nums[zeroIndex] < nums[zeroIndex-1]) {
                break;
            }
        }
        if(zeroIndex == length) {
            if(nums[0] == nums[length-1]) {
                if(target == nums[0]) {
                    return true;
                }
                return false;
            }
            zeroIndex = 0;
            if(Arrays.binarySearch(nums, 0, length, target) < 0) {
                return false;
            }
            return true;
        }
        if(target > nums[zeroIndex-1] || target < nums[zeroIndex]) {
            return false;
        }
        int index = 0;
        if(target >= nums[0]) {
            index = Arrays.binarySearch(nums, 0, zeroIndex, target);
        }
        else {
            index = Arrays.binarySearch(nums, zeroIndex, length, target);
        }
        if(index < 0) {
            return false;
        }
        return true;
    }
}

// 官方题解 使用二分搜索
class Solution {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                ++l;
                --r;
            } else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}