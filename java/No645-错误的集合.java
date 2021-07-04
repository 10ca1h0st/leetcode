class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int xor1 = 0;
        for(int i = 1; i <= n; i++) {
            xor1 = xor1 ^ (i ^ nums[i-1]);
        }
        int[] count = new int[n+1];
        for(int i = 0; i < n; i++) {
            count[nums[i]]++;
        }
        int a = 0;
        for(int i = 1; i <= n; i++) {
            if(count[i] > 1) {
                a = i;
                break;
            }
        }
        int b = xor1 ^ a;
        return new int[] {a, b};
    }
}

// 官方解答
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        int lowbit = xor & (-xor);
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & lowbit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & lowbit) == 0) {
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }
        for (int num : nums) {
            if (num == num1) {
                return new int[]{num1, num2};
            }
        }
        return new int[]{num2, num1};
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/set-mismatch/solution/cuo-wu-de-ji-he-by-leetcode-solution-1ea4/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。