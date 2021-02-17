class Solution {
    public int minKBitFlips(int[] A, int K) {
        int length = A.length;
        // 之后要修改A，为了不修改输入，因此复制一下，修改复制的数组
        // int[] _A = Arrays.copyOf(A, length);
        int[] _A = A;
        int left = 0;
        int right = 0;
        int ret = 0;
        while(right - left < K - 1) {
            right++;
        }
        while(right < length) {
            right++;
            // 现在窗口为[left, right)
            // 如果_A[left] == 0，翻转[left, right)中的元素，并删除翻转后开头连续的1
            if(_A[left] == 0) {
                ret++;
                int size = K;
                boolean head0 = true;
                while(size > 0) {
                    int e = _A[right-size];
                    if(e == 0) {
                        if(head0) {
                            size--;
                            left++;
                            continue;
                        }
                        _A[right-size] = 1;
                    }
                    else {
                        head0 = false;
                        _A[right-size] = 0;
                    }
                    size--;
                }
            }
            else {
                // 删除开头连续的1
                while(left < right && _A[left] == 1) {
                    left++;
                }
            }
            // 将窗口[left, right)的长度补到K-1
            while(right < length && right - left < K - 1) {
                right++;
            }
        }
        // 两种情况，窗口为空和非空
        if(left == right) {
            return ret;
        }
        // 窗口非空时，若窗口中全为1
        while(left < right && _A[left] == 1) {
            left++;
        }
        if(left == right) {
            return ret;
        }
        return -1;
    }
}

class Solution {
    public int minKBitFlips(int[] A, int K) {
        int len = A.length;
        // 边界队列
        Deque<Integer> transformRange = new LinkedList<>();
        
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            // 到达当前队列中最小的边界时，要将其排除
            if (!transformRange.isEmpty() && transformRange.peekFirst() == i) transformRange.pollFirst();
            // 第i个元素的实际值，为A[i]加上翻转次数
            int cur = A[i] + transformRange.size();

            // 当前位置的实际值不为1的情况
            if (cur % 2 == 0) {
                // 表示新的边界已经到达数组长度上线，该情况不可能成立，返回-1
                if (i + K > len) return -1;
                // 将[i, i + K]之间的元素翻转一次，将翻转的范围边界加入队列
                transformRange.offerLast(i + K);
                cnt++;
            }
        }

        return cnt;
    }
}

作者：ggeorge500
链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/solution/hua-dong-chuang-kou-tan-xin-ke-po-li-jie-cwhf/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。