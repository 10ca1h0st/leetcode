class Solution {
    public int maxLength(List<String> arr) {
        int[] ss = new int[arr.size()];
        int index = 0;
        for(String e : arr) {
            for(char c : e.toCharArray()) {
                int order = c - 'a';
                if((ss[index] & (1<<order)) != 0) {
                    ss[index] = 0;
                    break;
                }
                ss[index] = ss[index] | (1<<order);
            }
            index++;
        }
        // for(int i : ss) {
        //     System.out.println(i);
        // }
        int ans = backtrace(arr, ss, 0, 0);
        return ans;
    }

    public int backtrace(List<String> arr, int[] ss, int start, int v) {
        if(start == ss.length) {
            return 0;
        }
        int cur = ss[start];
        int ans1 = 0;
        int ans2= 0;
        // 选cur
        if(cur !=0 && (cur & v) == 0) {
            // 可以选
            ans1 = arr.get(start).length() + backtrace(arr, ss, start+1, cur|v);
        }
        // 不选cur
        ans2 = backtrace(arr, ss, start+1, v);
        int ans = Math.max(ans1, ans2);
        return ans;
    }
}