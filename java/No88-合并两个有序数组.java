class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] helper = new int[m+n];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < m || j < n) {
            if(i == m) {
                helper[k++] = nums2[j++];
            }
            else if(j == n) {
                helper[k++] = nums1[i++];
            }
            else if(nums1[i] <= nums2[j]) {
                helper[k++] = nums1[i++];
            }
            else if(nums1[i] > nums2[j]) {
                helper[k++] = nums2[j++];
            }
        }
        for(k = 0; k < m+n; k++) {
            nums1[k] = helper[k];
        }
    }
}

// 官方解答，从后面开始，每次选取最大的数放到nums1的尾部，已证明不会覆盖到nums1之前的元素
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetco-rrb0/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。