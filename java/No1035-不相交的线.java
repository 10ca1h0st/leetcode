// 官方解答 可以转换为最长公共子序列问题
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            int num1 = nums1[i - 1];
            for (int j = 1; j <= n; j++) {
                int num2 = nums2[j - 1];
                if (num1 == num2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/uncrossed-lines/solution/bu-xiang-jiao-de-xian-by-leetcode-soluti-6tqz/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//动态规划 击败6.11%
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        Map<Integer, List<Integer>> num2Index1 = new HashMap<>();
        Map<Integer, List<Integer>> num2Index2 = new HashMap<>();
        for(int i = 0; i < nums1.length; i++) {
            List<Integer> list = num2Index1.getOrDefault(nums1[i], new ArrayList<Integer>());
            list.add(i);
            num2Index1.put(nums1[i], list);
        }
        for(int i = 0; i < nums2.length; i++) {
            List<Integer> list = num2Index2.getOrDefault(nums2[i], new ArrayList<Integer>());
            list.add(i);
            num2Index2.put(nums2[i], list);
        }
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for(int i = 1; i <= nums1.length; i++) {
            for(int j = 1; j <= nums2.length; j++) {
                if(nums1[i-1] == nums2[j-1]) {
                    int choice1 = dp[i-1][j-1] + 1;
                    int choice2 = 0;
                    int position = 0;
                    for(int index : num2Index2.getOrDefault(nums1[i-1], new ArrayList<Integer>())) {
                        if(index <= j-1) {
                            position = index;
                        }
                        else {
                            break;
                        }
                    }
                    choice2 = dp[i][position+1];
                    int choice3 = 0;
					position = 0;
                    for(int index : num2Index1.getOrDefault(nums2[j-1], new ArrayList<Integer>())) {
                        if(index <= i-1) {
                            position = index;
                        }
                        else {
                            break;
                        }
                    }
                    choice3 = dp[position+1][j];
                    dp[i][j] = Math.max(choice1, Math.max(choice2, choice3));
                }
                else {
                    int choice1 = dp[i-1][j-1];
                    int choice2 = 0;
                    int position = 0;
                    for(int index : num2Index2.getOrDefault(nums1[i-1], new ArrayList<Integer>())) {
                        if(index <= j-1) {
                            position = index;
                        }
                        else {
                            break;
                        }
                    }
                    choice2 = dp[i][position+1];
                    int choice3 = 0;
                    position = 0;
                    for(int index : num2Index1.getOrDefault(nums2[j-1], new ArrayList<Integer>())) {
                        if(index <= i-1) {
                            position = index;
                        }
                        else {
                            break;
                        }
                    }
                    choice3 = dp[position+1][j];
                    dp[i][j] = Math.max(choice1, Math.max(choice2, choice3));
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }
}




//超时
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        Map<Integer, List<Integer>> num2Index1 = new HashMap<>();
        Map<Integer, List<Integer>> num2Index2 = new HashMap<>();
        for(int i = 0; i < nums1.length; i++) {
            List<Integer> list = num2Index1.getOrDefault(nums1[i], new ArrayList<Integer>());
            list.add(i);
            num2Index1.put(nums1[i], list);
        }
        for(int i = 0; i < nums2.length; i++) {
            List<Integer> list = num2Index2.getOrDefault(nums2[i], new ArrayList<Integer>());
            list.add(i);
            num2Index2.put(nums2[i], list);
        }
        int ans = search(nums1, 0, num2Index2, 0);
        return ans;
    }

    public int search(int[] nums1, int start1, Map<Integer, List<Integer>> num2Index2, int start2) {
        int max = 0;
        for(int i = start1; i < nums1.length; i++) {
            int p1 = nums1[i];
            List<Integer> choices = num2Index2.getOrDefault(p1, new ArrayList<Integer>());
            for(int p2Index : choices) {
                if(p2Index >= start2) {
                    int ans = search(nums1, i+1, num2Index2, p2Index+1) + 1;
                    max = Math.max(max, ans);
                }
            }
        }
        return max;
    }
}