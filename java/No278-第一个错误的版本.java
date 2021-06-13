/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        int middle = low + (high - low) / 2;
        int firstBad = -1;
        while(low <= high) {
            middle = low + (high - low) / 2;
            boolean b = isBadVersion(middle);
            if(firstBad != -1) {
                if(!b) {
                    low = middle + 1;
                }
                else {
                    firstBad = middle;
                    high = middle - 1;
                }
            }
            else {
                if(b) {
                    firstBad = middle;
                    high = middle - 1;
                }
                else {
                    low = middle + 1;
                }
            }
        }
        return firstBad;
    }
}