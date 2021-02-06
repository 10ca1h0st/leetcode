class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int length = cardPoints.length;
        int remaining = length - k;
        int totalSum = Arrays.stream(cardPoints).sum();
        int left = 0;
        int right = 0;
        int remainingSum = 0;
        int minRemainingSum = Integer.MAX_VALUE;
        // 窗口[left, right)表示在取走k张牌之后，剩下的牌
        for(; right < remaining; right++) {
            remainingSum += cardPoints[right];
        }
        while(right <= length) {
            // 现在窗口为[left, right)，且remainingSum表示区间和
            minRemainingSum = Math.min(minRemainingSum, remainingSum);
            if(right == length) {
                break;
            }
            remainingSum += cardPoints[right];
            remainingSum -= cardPoints[left];
            right++;
            left++;
        }
        return totalSum - minRemainingSum;
    }
}