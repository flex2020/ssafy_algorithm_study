import java.util.*;
import java.io.*;

public class Solution9229 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            int[] nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            int result = -1;
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int sum = nums[i] + nums[j];
                    if (sum <= M) {
                        result = Math.max(result, sum);
                    }
                }
            }

            System.out.println("#" + testCase + " " + result);
        }
    }
}