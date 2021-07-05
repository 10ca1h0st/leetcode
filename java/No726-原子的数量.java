class Solution {
    public String countOfAtoms(String formula) {
        Map<String, Integer> atoms = new HashMap<>();
        char[] array = formula.toCharArray();
        int i = 0;
        Deque<String> stack = new ArrayDeque<>();
        while(i < array.length) {
            if(array[i] == '(') {
                stack.push("(");
                i++;
            }
            else if(array[i] == ')') {
                // 解析数字
                i++;
                int count = 1;
                if(i < array.length && array[i] >= '0' && array[i] <= '9') {
                    count = array[i] - '0';
                    i++;
                    while(i < array.length && array[i] >= '0' && array[i] <= '9') {
                        count = count * 10 + array[i] - '0';
                        i++;
                    }
                }
                Deque<String> stack2 = new ArrayDeque<>();
                String top = stack.pop();
                while(!top.equals("(")) {
                    String[] split = top.split(":");
                    String atom = split[0];
                    int n = Integer.valueOf(split[1]);
                    n = n * count;
                    top = atom + ":" + String.valueOf(n);
                    stack2.push(top);
                    top = stack.pop();
                }
                if(stack.isEmpty()) {
                    // 空栈，修改map
                    while(!stack2.isEmpty()) {
                        top = stack2.pop();
                        String[] split = top.split(":");
                        String atom = split[0];
                        int n = Integer.valueOf(split[1]);
                        int v = atoms.getOrDefault(atom, 0);
                        v = v + n;
                        atoms.put(atom, v);
                    }
                }
                else {
                    while(!stack2.isEmpty()) {
                        top = stack2.pop();
                        stack.push(top);
                    }
                }
            }
            else {
                // 大写字母
                i = parseAtom(array, i, atoms, stack);
            }
        }

        int keysCount = atoms.size();
        PriorityQueue<String> pq = new PriorityQueue<>();
        for(String k : atoms.keySet()) {
            pq.add(k);
        }
        StringBuilder ans = new StringBuilder();
        while(!pq.isEmpty()) {
            String top = pq.poll();
            int count = atoms.get(top);
            ans.append(top);
            if(count != 1) {
                ans.append(count);
            }
        }
        return ans.toString();
    }

    public int parseAtom(char[] formula, int start, Map<String, Integer> atoms, Deque<String> stack) {
        StringBuilder sb = new StringBuilder();
        sb.append(formula[start]);
        int i = start + 1;
        int count = 1;
        while(i < formula.length) {
            if(formula[i] <= 'z' && formula[i] >= 'a') {
                // 一个原子内
                sb.append(formula[i]);
                i++;
            }
            else if(formula[i] <= '9' && formula[i] >= '0') {
                // 原子的个数
                count = formula[i] - '0';
                i++;
                while(i < formula.length && formula[i] >= '0' && formula[i] <= '9') {
                    count = count * 10 + formula[i] - '0';
                    i++;
                }
                break;
            }
            else {
                // 另一个原子或者左右括号
                break;
            }
        }
        String atom = sb.toString();
        if(stack.isEmpty()) {
            int v = atoms.getOrDefault(atom, 0);
            v = v + count;
            atoms.put(atom, v);
        }
        else {
            String top = atom + ":" + String.valueOf(count);
            stack.push(top);
        }
        return i;
    }
}