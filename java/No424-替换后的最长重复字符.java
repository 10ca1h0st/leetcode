class Solution {
    public int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }
}

// 自己写的使用滑动窗口，较慢的版本
class Solution {
    public int characterReplacement(String s, int k) {
        // 区间包含left，不包含right，区间表示该区间可以通过替换，变成重复子串
        int left = 0;
        int right = 0;
        // 在区间中的字母出现的次数
        int[] num = new int[26];
        int maxNum = 0;
        char[] chs = s.toCharArray();
        // 包含重复字母的最长子串长度
        int ret = 0;
        while(right < chs.length) {
            char ch = chs[right];
            right++;
            // 现在的区间为[left, right)
            num[ch-'A']++;
            maxNum = Arrays.stream(num).max().getAsInt();
            while(right - left > maxNum + k) {
                num[chs[left]-'A']--;
                left++;
                maxNum = Arrays.stream(num).max().getAsInt();
            }
            ret = Math.max(ret, right - left);
        }
        return ret;
    }
}

