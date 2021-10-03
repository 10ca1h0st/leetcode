class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        long a = numerator;
        long b = denominator;
        if (a*b < 0) {
            sb.append('-');
        }
        a = Math.abs(a);
        b = Math.abs(b);
        if (a < b) {
            sb.append("0.");
            a *= 10;
        } else {
            sb.append(a/b);
            if (a%b != 0) {
                sb.append(".");
            }
            a = a%b;
            a *= 10;
        }
        Map<Long, Integer> pos = new HashMap<>();
        while (a != 0) {
            if (pos.containsKey(a)) {
                sb.insert(pos.get(a).intValue(), '(');
                sb.append(')');
                break;
            }
            long div = a/b;
            long remain = a%b;
            sb.append(div);
            pos.put(a, sb.length()-1);
            a = remain*10;
        }
        return sb.toString();
    }
}