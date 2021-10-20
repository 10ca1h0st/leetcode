class Solution {
    public int minMoves(int[] nums) {
        int minNum = Arrays.stream(nums).min().getAsInt();
        int res = 0;
        for (int num : nums) {
            res += num - minNum;
        }
        return res;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/solution/zui-xiao-cao-zuo-ci-shu-shi-shu-zu-yuan-3meg3/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。