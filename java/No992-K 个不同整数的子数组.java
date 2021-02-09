public class Solution {

    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    /**
     * @param A
     * @param K
     * @return 最多包含 K 个不同整数的子区间的个数
     */
    private int atMostKDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];

        int left = 0;
        int right = 0;
        // [left, right) 里不同整数的个数
        int count = 0;
        int res = 0;
        // [left, right) 包含不同整数的个数小于等于 K
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            // [left, right) 区间的长度就是对结果的贡献
            res += right - left;
        }
        return res;
    }
}

// 使用直接的滑动窗口的解法
public class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        int length = A.length;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> numCount = new LinkedHashMap<>();
        int count = 0;
        while (right < length) {
            int toAdd = A[right++];
            int windowsRightSamNum = 0;
            numCount.put(toAdd, numCount.getOrDefault(toAdd, 0) + 1);

            // 如果当前窗口中字母个数满足K，需要看当前窗口右侧连续位置上还有多少个字符和当前窗口中的字符相同
            if (numCount.size() == K) {
                windowsRightSamNum = calWindowRightSameNumCount(numCount, right, A);
            }

            // 当前窗口中的字符满足K，子数组个数总数+1；
            // 修正值需加上当前窗口右边和当前窗口中的字符相同的字符个数；
            // 左移窗口
            while (numCount.size() == K) {
                count += 1;
                count += windowsRightSamNum;
                updateWindowsValue(numCount, A[left++]);
            }
        }
        return count;
    }

    private int calWindowRightSameNumCount(Map<Integer, Integer> numCount, int right, int[] A) {
        int countTmp = 0;
        for (int i = right; i < A.length; i++) {
            if (!numCount.containsKey(A[i])) {
                break;
            } else {
                countTmp += 1;
            }
        }
        return countTmp;
    }

    private void updateWindowsValue(Map<Integer, Integer> numCount, int key) {
        if (numCount.get(key) == 1) {
            numCount.remove(key);
        } else {
            numCount.put(key, numCount.get(key) - 1);
        }
    }
}

