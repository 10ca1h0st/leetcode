class Solution {
    public String removeDuplicates(String S) {
        int length = S.length();
        if(length <= 1) {
            return S;
        }
        int prev = 0;
        int cur = 1;
        StringBuilder sb = new StringBuilder();
        boolean duplicate = false;
        while(cur < length) {
            if(S.charAt(prev) == S.charAt(cur)) {
                duplicate = true;
                prev += 2;
                cur += 2;
            }
            else {
                sb.append(S.charAt(prev));
                prev++;
                cur++;
            }
        }
        if(prev < length) {
            sb.append(S.charAt(prev));
        }
        if(duplicate) {
            return removeDuplicates(sb.toString());
        }
        return sb.toString();
    }
}

// 官方解答
class Solution {
    public String removeDuplicates(String S) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for (int i = 0; i < S.length(); ++i) {
            char ch = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/solution/shan-chu-zi-fu-chuan-zhong-de-suo-you-xi-4ohr/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。