class Solution {
	public int strStr(String s, String p) {
		int sLength = s.length();
		int pLength = p.length();
        if(sLength < pLength) {
            return -1;
        }
        if(sLength == pLength) {
            if(s.equals(p)) {
                return 0;
            }
            return -1;
        }
        if(pLength == 0) {
            return 0;
        }
        if(pLength == 1) {
            for(int i = 0; i < sLength; i++) {
                if(s.charAt(i) == p.charAt(0)) {
                    return i;
                }
            }
            return -1;
        }
		// 构造过程参考
		// https://leetcode-cn.com/problems/implement-strstr/solution/shua-chuan-lc-shuang-bai-po-su-jie-fa-km-tb86/
		// 构造匹配串的转移数组
		// next[i]表示当p[i]不匹配时，p的指针应该跳转到哪里然后重新开始匹配
		int[] next = new int[pLength];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int j = 0;
		for(; i < pLength; i++) {
			if(p.charAt(j) == p.charAt(i-1)) {
				next[i] = j+1;
				j++;
			}
			else {
				// 此时一定有p[0:j-1]（包括p[j-1]） == p[i-2-(j-1):i-2]
				// 此时相当于用p[j]匹配p[i-1]，匹配失败
				while(j >= 0 && p.charAt(j) != p.charAt(i-1)) {
					// 此时对p[0:j-1]的操作都是对p[i-2-(j-1):i-2]的操作
					j = next[j];
				}
				// 最后求出的j代表
				// p[i-2-(j-1):i-2]中有长为j的相同的前缀和后缀
				// 且后缀还等于p[0:j-1]
				// 因此可以从p[j+1]开始匹配
				next[i] = j + 1;
				j++;
			}
		}
		
		// i代表当前匹配到s[i]，j代表当前匹配到p[j]
		for(i = 0, j = 0; i < sLength; i++) {
			while(j >= 0 && s.charAt(i) != p.charAt(j)) {
				j = next[j];
			}
			j++;
            if(j == pLength) {
                return i - pLength + 1;
            }
		}
		
		return -1;
	}
}