class Solution {
    public int removeDuplicates(int[] nums) {
        int copyIndex = 0;
        int prev = 0;
        int count = 1;
        for(int i = 0; i < nums.length; ) {
            i++;
            while(i < nums.length && nums[i] == nums[prev]) {
                count++;
                i++;
            }
            for(int j = 0; j < Math.min(count, 2); j++) {
                nums[copyIndex] = nums[prev];
                copyIndex++;
            }
            prev = i;
            count = 1;
        }
        return copyIndex;
    }
}

// 官方解答
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}

// 再次做 2021-4-18
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length <= 2) {
            return nums.length;
        }
        int slow = 2;
        int fast = 2;
        while(fast < nums.length) {
            if(nums[fast] == nums[fast-1] && nums[fast] == nums[fast-2]) {
                // 连续第三次出现
                int flag = nums[fast];
                while(fast < nums.length) {
                    if(flag == nums[fast]) {
                        fast++;
                    }
                    else {
                        break;
                    }
                }
            }
            else if(nums[fast] == nums[fast-1]) {
                // 连续第二次出现
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
            else {
                // 第一次出现
                nums[slow] = nums[fast];
                slow++;
                fast++;
                if(fast < nums.length && nums[fast] == nums[fast-1]) {
                    // 第二次出现
                    nums[slow] = nums[fast];
                    slow++;
                    fast++;
                }
            }
        }
        return slow;
    }
}