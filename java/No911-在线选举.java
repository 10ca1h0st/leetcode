class TopVotedCandidate {
    List<Integer> tops;
    Map<Integer, Integer> voteCounts;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        tops = new ArrayList<Integer>();
        voteCounts = new HashMap<Integer, Integer>();
        voteCounts.put(-1, -1);
        int top = -1;
        for (int i = 0; i < persons.length; ++i) {
            int p = persons[i];
            voteCounts.put(p, voteCounts.getOrDefault(p, 0) + 1);
            if (voteCounts.get(p) >= voteCounts.get(top)) {
                top = p;
            }
            tops.add(top);
        }
        this.times = times;
    }
    
    public int q(int t) {
        int l = 0, r = times.length - 1;
        // 找到满足 times[l] <= t 的最大的 l
        while (l < r) {
            int m = l + (r - l + 1) / 2;
            if (times[m] <= t) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return tops.get(l);
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/online-election/solution/zai-xian-xuan-ju-by-leetcode-solution-4835/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。