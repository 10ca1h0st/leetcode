class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n+1];
        diff[0] = 0;
        for (int i = 0; i < bookings.length; i++) {
            int[] booking = bookings[i];
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];
            diff[first] += seats;
            if (last != n) {
                diff[last+1] -= seats;
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans[i] = 0 + diff[i+1];
            } else {
                ans[i] = ans[i-1] + diff[i+1];
            }
        }
        return ans;
    }
}