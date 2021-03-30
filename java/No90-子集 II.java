class Solution {
    private int[] count;
    private List<List<Integer>> res;
    private List<Integer> inRes;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        count = new int[21];
        res = new ArrayList<>();
        inRes = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            count[nums[i]+10]++;
        }
        backtrace(0);
        return res;
    }

    public void backtrace(int index) {
        List<Integer> tmp = new ArrayList<>(inRes);
        res.add(tmp);
        for(int i = index; i < count.length; i++) {
            if(count[i] != 0) {
                inRes.add(i-10);
                count[i]--;
                backtrace(i);
                count[i]++;
                inRes.remove(inRes.size()-1);
            }
        }
    }
}