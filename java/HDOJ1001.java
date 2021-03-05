import java.util.Scanner;

public class HDOJ1001 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String nStr = sc.nextLine();
            int n = Integer.valueOf(nStr);
//            int n = sc.nextInt();
            int sum = 0;
//            for(int i = 1; i <= n; i++) {
//                sum += i;
//            }
//            System.out.println(sum);
            System.out.println(sumN(n));
            System.out.println();
        }
    }

    public static int sumN(int n) {
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
