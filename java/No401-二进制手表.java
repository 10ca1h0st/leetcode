class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for(int minute = 0; minute < 60; minute++) {
            int minuteBits = Integer.bitCount(minute);
            if(minuteBits > turnedOn) {
                continue;
            }
            for(int hour = 0; hour < 12; hour++) {
                int hourBits = Integer.bitCount(hour);
                if(hourBits + minuteBits == turnedOn) {
                    String res = String.valueOf(hour) + ":";
                    if(minute < 10) {
                        res += "0" + String.valueOf(minute);
                    }
                    else {
                        res += String.valueOf(minute);
                    }
                    ans.add(res);
                }
            }
        }
        return ans;
    }
}