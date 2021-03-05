import java.util.Scanner;

public class HDOJ1000 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        while(s.hasNextInt()){
            System.out.println(s.nextInt()+s.nextInt());
        }
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = null;
        int a = 0;
        int b = 0;
        int res = 0;
        while(sc.hasNext()) {
            line = sc.nextLine();
            String[] ab = line.split(" ");
            a = Integer.valueOf(ab[0]);
            b = Integer.valueOf(ab[1]);
            res = a + b;
            System.out.println(String.format("%d", res));
        }
    }
}
