class Solution {
    public int compress(char[] chars) {
        char prev = 0;
        int count = 0;
        int index = 0;
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == prev) {
                count++;
            } else if (count > 0) {
                chars[index++] = prev;
                ans++;
                if (count > 1) {
                    int length = 0;
                    int _count = count;
                    while (_count > 0) {
                        _count = _count / 10;
                        length++;
                    }
                    index += length;
                    ans += length;
                    int _index = index - 1;
                    while (count > 0) {
                        chars[_index] = (char)('0'+(count%10));
                        count = count / 10;
                        _index--;
                    }
                    count = 0;
                }
                prev = chars[i];
                count = 1;
            } else {
                prev = chars[i];
                count = 1;
            }
        }
        chars[index++] = prev;
        ans++;
        if (count > 1) {
            int length = 0;
            int _count = count;
            while (_count > 0) {
                _count = _count / 10;
                length++;
            }
            index += length;
            ans += length;
            int _index = index - 1;
            while (count > 0) {
                chars[_index] = (char)('0'+(count%10));
                count = count / 10;
                _index--;
            }
            count = 0;
        }
        return ans;
    }
}