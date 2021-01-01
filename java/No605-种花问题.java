class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int could = 0;
        int length = flowerbed.length;
        int i = 0;
        // 初始情况下，我们假设在-2处种植了花
        int lastOneIndex = -2;
        int capacity = 0;
        for(; i < length; i++) {
            if(flowerbed[i] == 1) {
                capacity = i - lastOneIndex - 1;
                capacity -= 2;
                if(capacity > 0) {
                    could += (capacity+1)/2;
                }
                lastOneIndex = i;
            }
        }
        // 最后时，我们假设在length+1处种植了花
        capacity = length+1 - lastOneIndex - 1;
        capacity -= 2;
        if(capacity > 0) {
            could += (capacity+1)/2;
        }
        if(could >= n) {
            return true;
        }
        return false;
    }
}