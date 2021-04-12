class Solution {
    public String largestNumber(int[] nums) {
        String[] numsStr = new String[nums.length];
        for(int i = 0; i < numsStr.length; i++) {
            numsStr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numsStr, (String s1, String s2) -> {
            int length1 = s1.length();
            int length2 = s2.length();
            if(length1 == length2) {
                for(int i = 0; i < length1; i++) {
                    if(s1.charAt(i) > s2.charAt(i)) {
                        return -1;
                    }
                    else if(s1.charAt(i) < s2.charAt(i)) {
                        return 1;
                    }
                }
                return 0;
            }
            else if(length1 < length2) {
                int index = 0;
                while(index < length2) {
                    for(int i = 0; i < length1 && index < length2; i++, index++) {
                        if(s1.charAt(i) > s2.charAt(index)) {
                            return -1;
                        }
                        else if(s1.charAt(i) < s2.charAt(index)) {
                            return 1;
                        }
                    }
                }
                if(length2 % length1 == 0) {
                    return 0;
                }
                else  {
                    int remainder = length2 % length1;
                    String r1 = s1 + s1.substring(0, remainder);
                    String r2 = s1.substring(0, remainder) + s1;
                    for(int j = 0; j < r1.length(); j++) {
                        if(r1.charAt(j) < r2.charAt(j)) {
                            return 1;
                        }
                        else if(r1.charAt(j) > r2.charAt(j)) {
                            return -1;
                        }
                    }
                    return 0;
                }
            }
            else {
                // length1 > length2
                int index = 0;
                while(index < length1) {
                    for(int i = 0; i < length2 && index < length1; i++, index++) {
                        if(s1.charAt(index) > s2.charAt(i)) {
                            return -1;
                        }
                        else if(s1.charAt(index) < s2.charAt(i)) {
                            return 1;
                        }
                    }
                }
                if(length1 % length2 == 0) {
                    return 0;
                }
                else  {
                    int remainder = length1 % length2;
                    String r1 = s2 + s2.substring(0, remainder);
                    String r2 = s2.substring(0, remainder) + s2;
                    for(int j = 0; j < r1.length(); j++) {
                        if(r1.charAt(j) < r2.charAt(j)) {
                            return -1;
                        }
                        else if(r1.charAt(j) > r2.charAt(j)) {
                            return 1;
                        }
                    }
                    return 0;
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for(String s : numsStr) {
            sb.append(s);
        }
        String ans = sb.toString();
        int i = 0;
		// 处理ans为"00"的情况
        while(sb.length() > 1 && ans.charAt(i) == '0') {
            sb.deleteCharAt(0);
            i++;
        }
        return sb.toString();
    }
}