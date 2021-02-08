class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int length = arr.length;
        if(length == 1) {
            return 1;
        }
        if(length == 2) {
            return arr[0]==arr[1]?1:2;
        }
        // [left, right)表示一个湍流数组
        int left = 0;
        int right = 0;
        // 表示arr[right-1] - arr[right-2]
        int dir = 0;
        right = 2;
        dir = arr[right - 1] - arr[right - 2];
        // 保证dir非零
        while(dir == 0 && right < length) {
            left = right - 1;
            right++;
            dir = arr[right - 1] - arr[right - 2];
        }
        int ret = 0;
        if(dir == 0) {
            // right == length
            return 1;
        }
        else {
            ret = 2;
        }
        if(dir > 0) {
            dir = 1;
        }
        else {
            dir = -1;
        }
        while(right < length) {
            int curDir = arr[right] - arr[right - 1];
            right++;
            // 判断[left, right)是否为湍流数组
            if(curDir == 0) {
                // 不是湍流数组
                ret = Math.max(ret, right - left - 1);
                while(curDir == 0 && right < length) {
                    left = right - 1;
                    right++;
                    curDir = arr[right - 1] - arr[right - 2];
                }
                if(right == length) {
                    return ret;
                }
                if(curDir > 0) {
                    dir = 1;
                }
                else {
                    dir = -1;
                }
            }
            else if(curDir * dir > 0) {
                // 不是湍流数组
                // curDir与dir同号
                ret = Math.max(ret, right - left - 1);
                left = right - 2;
                if(curDir > 0) {
                    dir = 1;
                }
                else {
                    dir = -1;
                }
            }
            else {
                // 是湍流数组
                // curDir * dir < 0
                ret = Math.max(ret, right - left);
                if(curDir > 0) {
                    dir = 1;
                }
                else {
                    dir = -1;
                }
            }
        }
        return ret;
    }
}