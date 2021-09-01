class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1s = version1.split("\\.");
        String[] v2s = version2.split("\\.");
        // for (String s : v1s) {
        //     System.out.println(s);
        // }
        int index = 0;
        while (index < v1s.length && index < v2s.length) {
            long v1 = Long.valueOf(v1s[index]);
            long v2 = Long.valueOf(v2s[index]);
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
            index++;
        }
        while (index < v1s.length) {
            long v = Long.valueOf(v1s[index]);
            if (v != 0) {
                return 1;
            }
            index++;
        } 
        while (index < v2s.length) {
            long v = Long.valueOf(v2s[index]);
            if (v != 0) {
                return -1;
            }
            index++;
        }
        return 0;
    }
}