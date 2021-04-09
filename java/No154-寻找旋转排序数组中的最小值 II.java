class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int length = nums.length;
        int right = length-1;
        if(length == 1) {
            return nums[0];
        }
        int mid = 0;
        while(left <= right) {
            mid = left + (right-left)/2;
            if(nums[left] < nums[mid] && nums[mid] < nums[right]) {
                return nums[left];
            }
            else if(nums[left] > nums[mid]) {
                left++;
            }
            else if(nums[mid] > nums[right]) {
                left = mid + 1;
            }
            else if(nums[left] < nums[mid] && nums[mid] == nums[right]) {
                return nums[left];
            }
            else if(nums[left] == nums[mid] && nums[mid] < nums[right]) {
                return nums[left];
            }
            else if(nums[left] == nums[mid] && nums[mid] == nums[right]) {
                if(left == right) {
                    return nums[left];
                }
                left++;
            }
        }
        return 0;
    }
}

// 官方题解
class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return nums[low];
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/xun-zhao-xuan-zhuan-pai-xu-shu-zu-zhong-de-zui--16/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。