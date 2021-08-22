class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int[] source = {0, 0};
        int distance = manhattanDistance(source, target);
        for (int[] ghost : ghosts) {
            int ghostDistance = manhattanDistance(ghost, target);
            if (ghostDistance <= distance) {
                return false;
            }
        }
        return true;
    }

    public int manhattanDistance(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/escape-the-ghosts/solution/tao-tuo-zu-ai-zhe-by-leetcode-solution-gjga/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。