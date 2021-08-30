import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

/*
给定一个整数数组，调整数组元素顺序，是的偶数元素在前，奇数元素在后，并且保证原先输入数组的奇数内部顺序以及偶数内部顺序不变。例如，输入为{2,1,3,6,4,7,8,5}，则输出应该为{
 */

public class dianxin02 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        int n = Integer.valueOf(line);
        int[] nums = new int[n];
        line = bf.readLine();
        String[] split = line.split(",");
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.valueOf(split[i]);
        }
        int[] nums2 = new int[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 0) {
                nums2[index] = nums[i];
                index++;
            }
        }
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 1) {
                nums2[index] = nums[i];
                index++;
            }
        }
        StringJoiner stringJoiner = new StringJoiner(",");
        for (int num : nums2) {
            stringJoiner.add(String.valueOf(num));
        }
        System.out.println(stringJoiner.toString());
    }
}
