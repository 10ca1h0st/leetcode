class Solution {
    public int clumsy(int N) {
        StringBuilder onlyPlusAndMinus = new StringBuilder();
        int cur = N;
        // 将整个笨阶乘按照减号分割，计算每一部分的值，最后每一部分互相相减就可得到答案，注意，除了第一部分，其他部分在求值时要将加号变为减号，因为相当于加括号求值
        Queue<Integer> queue = new ArrayDeque<>();
        boolean firstPart = true;
        for(int i = N-1; i >= 1; i--) {
            char op = suffixOp(N, i+1);
            if(op == '*') {
                // 数i前跟随的操作符为乘法
                cur = cur * i;
            }
            else if(op == '/') {
                cur = cur / i;
            }
            else if(op == '+') {
                if(!firstPart) {
                    // 非第一部分，加括号求值，要变号
                    cur = cur - i;
                }
                else {
                    cur = cur + i;
                }
            }
            else if(op == '-') {
                if(firstPart) {
                    firstPart = false;
                }
                queue.offer(cur);
                cur = i;
            }
        }
        queue.offer(cur);
        int res = queue.poll();
        while(!queue.isEmpty()) {
            res = res - queue.poll();
        }
        return res;
    }

    // 从N, N-1, N-2, ..., 1, num代表数的值
    public char suffixOp(int N, int num) {
        assert(num <= N);
        assert(num > 0);
        int i = (N-num) % 4;
        if(i == 0) {
            return '*';
        }
        else if(i == 1) {
            return '/';
        }
        else if(i == 2) {
            return '+';
        }
        else {
            return '-';
        }
    }
}

// 官方解答
// java中，(-30)/4==(-7), 30/(-4)==(-7), (-30)/(-4)==7
class Solution {
    public int clumsy(int N) {
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(N);
        N--;

        int index = 0; // 用于控制乘、除、加、减
        while (N > 0) {
            if (index % 4 == 0) {
                stack.push(stack.pop() * N);
            } else if (index % 4 == 1) {
                stack.push(stack.pop() / N);
            } else if (index % 4 == 2) {
                stack.push(N);
            } else {
                stack.push(-N);
            }
            index++;
            N--;
        }

        // 把栈中所有的数字依次弹出求和
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/clumsy-factorial/solution/ben-jie-cheng-by-leetcode-solution-deh2/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。