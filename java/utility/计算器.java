import java.util.Stack;

public class 计算器 {
//    计算中缀表达式的计算器，使用双栈
//    https://leetcode-cn.com/problems/basic-calculator/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--47/

    public static void main(String[] args) {
        String s = "-1+2*3";
        System.out.println(calculate(s));
        System.out.println(s);
    }

    public static int calculate(String s) {
        if(isOperation(s.substring(0,1))) {
            StringBuilder sb = new StringBuilder(s);
            sb.insert(0, "0");
            s = sb.toString();
        }
        char[] array = s.toCharArray();
        int n = array.length;
        Stack<Integer> num = new Stack<>();
        Stack<Character> op = new Stack<>();
        int temp = -1;
        for (int i = 0; i < n; i++) {
            if (array[i] == ' ') {
                continue;
            }
            // 数字进行累加
            if (isNumber(array[i])) {
                if (temp == -1) {
                    temp = array[i] - '0';
                } else {
                    temp = temp * 10 + array[i] - '0';
                }
            }
            else {
                //将数字入栈
                if (temp != -1) {
                    num.push(temp);
                    temp = -1;
                }
                //遇到操作符
                if (isOperation(array[i] + "")) {
                    String curOp = array[i] + "";
                    while (!op.isEmpty()) {
                        if (op.peek() == '(') {
                            break;
                        }
                        String stackOp = op.peek() + "";
                        if(!cmpOperationOrder(stackOp, curOp)) {
//                            栈顶的操作符的优先级小于遇到的操作符的优先级
                            break;
                        }
                        //不停的出栈，进行运算，并将结果再次压入栈中
                        int num1 = num.pop();
                        int num2 = num.pop();
                        op.pop();
                        if ("+".equals(stackOp)) {
                            num.push(num1 + num2);
                        }
                        else if("-".equals(stackOp)) {
                            num.push(num2 - num1);
                        }
                        else if("*".equals(stackOp)) {
                            num.push(num1 * num2);
                        }
                        else {
                            num.push(num2 / num1);
                        }

                    }
                    //当前运算符入栈
                    op.push(array[i]);
                }
                else {
                    //遇到左括号，直接入栈
                    if (array[i] == '(') {
                        op.push(array[i]);
                    }
                    //遇到右括号，不停的进行运算，直到遇到左括号
                    if (array[i] == ')') {
                        while (op.peek() != '(') {
                            int num1 = num.pop();
                            int num2 = num.pop();
                            if (op.pop() == '+') {
                                num.push(num1 + num2);
                            } else {
                                num.push(num2 - num1);
                            }
                        }
                        op.pop();
                    }

                }
            }
        }
        if (temp != -1) {
            num.push(temp);
        }
        //将栈中的其他元素继续运算
        while (!op.isEmpty()) {
            int num1 = num.pop();
            int num2 = num.pop();
            String stackOp = op.pop() + "";
            if ("+".equals(stackOp)) {
                num.push(num1 + num2);
            }
            else if("-".equals(stackOp)) {
                num.push(num2 - num1);
            }
            else if("*".equals(stackOp)) {
                num.push(num1 * num2);
            }
            else {
                num.push(num1 / num2);
            }
        }
        return num.pop();
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isOperation(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

//    比较op1的优先级是不是大于等于op2
    private static boolean cmpOperationOrder(String op1, String op2) {
        if(op2.equals("-") || op2.equals("+")) {
            return true;
        }
        else {
//            op2是乘除
            if(op1.equals("*") || op1.equals("/")) {
                return true;
            }
            return false;
        }
    }
}
