import java.util.*;
import java.io.*;

public class SolutionBaekJoon1339 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[26];

        char[] tmp;
        for (int i = 0; i < N; i++) {
            tmp = br.readLine().toCharArray();

            for (int j = 0; j < tmp.length; j++) {
                nums[tmp[j] - 'A'] += Math.pow(10, tmp.length - (j + 1));
            }
        }
        Arrays.sort(nums);

        int result = 0;
        int k = 9;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                break;
            }

            result += nums[i] * k;
            k--;
        }

        System.out.println(result);
    }

}
