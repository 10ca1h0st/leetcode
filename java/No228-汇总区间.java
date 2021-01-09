class Solution {
    public List<String> summaryRanges(int[] nums) {
        if(nums.length == 0) {
            return new ArrayList<>();
        }
        StringBuilder builder = new StringBuilder();
        List<String> ret = new ArrayList<>();
        int prev = nums[0];
        int start = prev;
        int end = prev;
        for(int i = 1; i < nums.length; i++) {
            if(prev == nums[i]-1) {
                end = nums[i];
            }
            else {
                if(end == start) {
                    builder.append(String.valueOf(start));
                }
                else {
                    builder.append(String.valueOf(start));
                    builder.append("->");
                    builder.append(String.valueOf(end));
                }
                ret.add(builder.toString());
                builder.setLength(0);
                start = nums[i];
                end = nums[i];
            }
            prev = nums[i];
        }
        if(end == start) {
            builder.append(String.valueOf(start));
        }
        else {
            builder.append(String.valueOf(start));
            builder.append("->");
            builder.append(String.valueOf(end));
        }
        ret.add(builder.toString());
        return ret;
    }
}