class Solution {
    public boolean detectCapitalUse(String word) {
        // 若第 1 个字母为小写，则需额外判断第 2 个字母是否为小写
        if (word.length() >= 2 && Character.isLowerCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))) {
            return false;
        }
        
        // 无论第 1 个字母是否大写，其他字母必须与第 2 个字母的大小写相同
        for (int i = 2; i < word.length(); ++i) {
            if (Character.isLowerCase(word.charAt(i)) ^ Character.isLowerCase(word.charAt(1))) {
                return false;
            }
        }
        return true;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/detect-capital/solution/jian-ce-da-xie-zi-mu-by-leetcode-solutio-449z/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。