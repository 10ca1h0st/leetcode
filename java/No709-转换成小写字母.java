class Solution {
    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch >= 65 && ch <= 90) {
                ch |= 32;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/to-lower-case/solution/zhuan-huan-cheng-xiao-xie-zi-mu-by-leetc-5e29/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。