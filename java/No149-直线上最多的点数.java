class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1);
            }
            ret = Math.max(ret, maxn);
        }
        return ret;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/max-points-on-a-line/solution/zhi-xian-shang-zui-duo-de-dian-shu-by-le-tq8f/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

class Solution {
    public int maxPoints(int[][] points) {
        Map<String, Integer> k2count = new HashMap<>();
        int ans = 0;
        for(int i = 0; i < points.length; i++) {
            int[] base = points[i];
            k2count = new HashMap<>();
            for(int j = i + 1; j < points.length; j++) {
                int yDiff = points[j][1] - base[1];
                int xDiff = points[j][0] - base[0];
                String k = null;
                if(yDiff * xDiff == 0) {
                    if(yDiff == 0) {
                        k = "0";
                    }
                    else {
                        k = "infinite";
                    }
                }
                else if(yDiff * xDiff < 0) {
                    int gcd = getGCD(Math.abs(yDiff), Math.abs(xDiff));
                    yDiff = Math.abs(yDiff) / gcd;
                    xDiff = Math.abs(xDiff) / gcd;
                    k = "-" + String.valueOf(yDiff) + "/" + String.valueOf(xDiff);
                }
                else {
                    // yDiff * xDiff > 0
                    int gcd = getGCD(Math.abs(yDiff), Math.abs(xDiff));
                    yDiff = Math.abs(yDiff) / gcd;
                    xDiff = Math.abs(xDiff) / gcd;
                    k = String.valueOf(yDiff) + "/" + String.valueOf(xDiff);
                }
                int origin = k2count.getOrDefault(k, 0);
                origin++;
                if(origin > ans) {
                    ans = origin;
                }
                k2count.put(k, origin);
            }
        }
        return ans + 1;
    }

    public int _getGCD(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        int mod = max % min;
        if (mod == 0) {
            return min;
        } else {
            return getGCD(mod, min);
        }
    }
	
	// 非递归求gcd
	public int getGCD(int a, int b) {
        int max = Math.max(a ,b);
        int min = Math.min(a, b);
        int remainder = max % min;
        while(remainder != 0) {
            max = min;
            min = remainder;
            remainder = max % min;
        }
        return min;
    }
}