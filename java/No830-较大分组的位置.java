class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ret = new ArrayList<>();
        int start = 0;
        char prevCh = 0;
        char curCh = 0;
        int i = 0;
        prevCh = s.charAt(i);
        start = i;
        for(i = 1; i < s.length(); i++) {
            curCh = s.charAt(i);
            if(curCh == prevCh) {
                continue;
            }
            else {
                if(i - start >= 3) {
                    // List.of返回的实例是不可修改的
                    List<Integer> e = List.of(start, i-1);
                    ret.add(e);
                }
                start = i;
                prevCh = curCh;
            }
        }
        if(i - start >= 3) {
            List<Integer> e = List.of(start, i-1);
            ret.add(e);
        }
        return ret;
    }
}