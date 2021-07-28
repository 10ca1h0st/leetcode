class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        int level = 0;
        int sum = 0;
        while (sum < label) {
            sum += (int)Math.pow(2, level);
            level++;
        }
        List<Integer> ans = new ArrayList<>(level);
        ans.add(0, label);
        int index = sum - (int)Math.pow(2, level-1);
        level--;
        int target = label;
        int order = sum;
        while (level > 0) {
            index = index - (int)Math.pow(2, level-1) + 1;
            int fatherIndex = index;
            int size = (int)Math.pow(2, level);
            int _size = size;
            int _order = order;
            while (size > 0) {
                if (order == target || order-1 == target) {
                    ans.add(0, fatherIndex);
                    target = fatherIndex;
                    break;
                }
                fatherIndex++;
                size -= 2;
                order -= 2;
            }
            order = _order - _size;
            level--;
            index--;
        }
        return ans;
    }
}