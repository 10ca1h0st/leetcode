import java.util.Scanner;

public class HDOJ1002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.valueOf(sc.nextLine());
        int i = 1;
        while(true) {
            String line = sc.nextLine();
            String num1 = line.split(" ")[0];
            String num2 = line.split(" ")[1];
            System.out.println(String.format("Case %d:", i));
            String res = null;
            if(num1.length() >= num2.length()) {
                res = add(num1, num2);
                System.out.println(String.format("%s + %s = %s", num1, num2, res));
            }
            else {
                res = add(num2, num1);
                System.out.println(String.format("%s + %s = %s", num1, num2, res));
            }
            i++;
            if(i > cases) {
                break;
            }
            System.out.println();
        }

    }

    public static String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int length1 = num1.length();
        int length2 = num2.length();
//        assume length1 >= length2
        i = -1;
        int add = 0;
        while(length2 + i >= 0) {
            int sum = Character.getNumericValue(num1.charAt(length1 + i)) +
                    Character.getNumericValue(num2.charAt(length2 + i)) +
                    add;
            add = 0;
            if(sum < 10) {
                sb.append(sum);
            }
            else {
                add = 1;
                sb.append(sum%10);
            }
            i--;
        }
        while(length1 + i >= 0) {
            int sum = Character.getNumericValue(num1.charAt(length1 + i)) + add;
            add = 0;
            if(sum < 10) {
                sb.append(sum);
            }
            else {
                add = 1;
                sb.append(sum%10);
            }
            i--;
        }
        return sb.reverse().toString();
    }
}
