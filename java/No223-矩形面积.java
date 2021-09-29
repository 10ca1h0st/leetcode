class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        if (ax1 >= bx2 || ax2 <= bx1 || ay1 >= by2 || by1 >= ay2) {
            return calc(ay2, ay1, ax1, ax2) + calc(by2, by1, bx1, bx2);
        }
        int[] fourX = new int[] {ax1, ax2, bx1, bx2};
        Arrays.sort(fourX);
        int[] fourY = new int[] {ay1, ay2, by1, by2};
        Arrays.sort(fourY);
        int ans = 0;
        if (fourX[0] == ax1) {
            ans += calc(ay2, ay1, ax1, bx1);
        } else {
            ans += calc(by2, by1, bx1, ax1);
        }
        if (fourX[3] == ax2) {
            ans += calc(ay2, ay1, bx2, ax2);
        } else {
            ans += calc(by2, by1, ax2, bx2);
        }
        ans += calc(fourY[3], fourY[0], fourX[1], fourX[2]);
        return ans;

    }

    public int calc(int up, int down, int left, int right) {
        return (up-down) * (right-left);
    }
}