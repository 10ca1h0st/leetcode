class Solution {
    public boolean checkRecord(String s) {
        int timesA = 0;
        int timesL = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'A') {
                timesL = 0;
                timesA++;
                if (timesA == 2) {
                    return false;
                }
            } else if (ch == 'L') {
                timesL++;
                if (timesL == 3) {
                    return false;
                }
            } else {
                timesL = 0;
            }
        }
        return true;
    }
}