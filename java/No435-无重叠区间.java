class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0 || intervals.length == 1) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] > b[0]) {
                    return 1;
                }
                else if(a[0] < b[0]) {
                    return -1;
                }
                else {
                    // a[0] == b[0]
                    if(a[1] < b[1]) {
                        return -1;
                    }
                    else if(a[1] > b[1]) {
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }
            }
        });
        int start = 0;
        int cur_start = 0;
        int end = 0;
        int cur_end = 0;
        int remove = 0;
        start = intervals[0][0];
        end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            cur_start = intervals[i][0];
            cur_end = intervals[i][1];
            if(cur_start == start && cur_end == end) {
                remove++;
            }
            else if(cur_start == start && cur_end > end) {
                remove++;
            }
            else if(cur_start >= end) {
                start = cur_start;
                end = cur_end;
            }
            else if(cur_start > start && cur_start < end && cur_end > end) {
                remove++;
            }
            else if(cur_start > start && cur_start < end && cur_end <= end) {
                remove++;
                start = cur_start;
                end = cur_end;
            }
            else if(cur_start == start && cur_end < end) {
                remove++;
                start = cur_start;
                end = cur_end;
            }
        }
        return remove;
    }
}