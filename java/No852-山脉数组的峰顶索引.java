class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int prev = 0;
        int index = 1;
        while(index < arr.length) {
            if(arr[prev] > arr[index]) {
                return prev;
            }
            prev = index;
            index++;
        }
        return -1;
    }
}