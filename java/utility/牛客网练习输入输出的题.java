import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://ac.nowcoder.com/acm/contest/5652?&headNav=www#question

public class nk {
//输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据包括多组。
    public static void main1(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        while(line != null) {
            System.out.println(Integer.valueOf(line.split(" ")[0]) + Integer.valueOf(line.split(" ")[1]));
            line = bf.readLine();
        }
    }

//输入第一行包括一个数据组数t(1 <= t <= 100)
//接下来每行包括两个正整数a,b(1 <= a, b <= 10^9)
    public static void main2(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testcases = Integer.valueOf(bf.readLine());
        String line = null;
        while(testcases-- > 0) {
            line = bf.readLine();
            System.out.println(Integer.valueOf(line.split(" ")[0]) + Integer.valueOf(line.split(" ")[1]));
        }
    }

//输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为0 0则结束输入
    public static void main3(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        while(!("0 0".equals(line))) {
            System.out.println(Integer.valueOf(line.split(" ")[0]) + Integer.valueOf(line.split(" ")[1]));
            line = bf.readLine();
        }
    }

//输入数据包括多组。
//每组数据一行,每行的第一个整数为整数的个数n(1 <= n <= 100), n为0的时候结束输入。
//接下来n个正整数,即需要求和的每个正整数。
    public static void main4(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        while(!("0".equals(line))) {
            String[] nums = line.split(" ");
            int count = Integer.valueOf(nums[0]);
            int sum = 0;
            for(int i = 1; i < count+1; i++) {
                sum += Integer.valueOf(nums[i]);
            }
            System.out.println(sum);
            line = bf.readLine();
        }
    }

//输入有两行，第一行n
//第二行是n个空格隔开的字符串
    public static void main5(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.valueOf(bf.readLine());
        String line = bf.readLine();
        String[] strs = new String[count];
        int i = 0;
        for(String str : line.split(" ")) {
            strs[i++] = str;
        }
        Arrays.sort(strs);
        for(i = 0; i < strs.length; i++) {
            System.out.print(strs[i]);
            if (i + 1 < strs.length) {
                System.out.print(" ");
            }
        }
    }

//多个测试用例，每个测试用例一行。
//每行通过,隔开，有n个字符，n＜100
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        while(line != null) {
            String[] split = line.split(" ");
            Arrays.sort(split);
            for(int i = 0; i < split.length; i++) {
                System.out.print(split[i]);
                if(i+1 < split.length) {
                    System.out.print(",");
                }
            }
            System.out.println();
            line = bf.readLine();
        }
    }
}
