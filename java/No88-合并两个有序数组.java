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