class Solution {
    public String maximumTime(String time) {
        char[] chs = time.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '?') {
                if (i == 0) {
                    if (chs[i+1] != '?') {
                        if (chs[i+1] >= '4') {
                            chs[i] = '1';
                        } else {
                            chs[i] = '2';
                        }
                    } else {
                        chs[i] = '2';
                        chs[i+1] = '3';
                    }
                    i++;
                } else if (i == 1) {
                    if (chs[i-1] != '2') {
                        chs[i] = '9';
                    } else {
                        chs[i] = '3';
                    }
                } else if (i == 3) {
                    chs[i] = '5';
                } else if (i == 4) {
                    chs[i] = '9';
                }
            }
        }
        return new String(chs);
    }
}