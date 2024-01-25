import java.io.*;

public class SolutionSWEA1859 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            int[] nums = new int[N];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(input[i]);
            }
            
            long maxProfit = (long)nums[N - 1];
            long result = 0;
            for (int i = N - 2; i >= 0; i--) {
                if (maxProfit > nums[i]) {
                    result += maxProfit - nums[i];
                } else {
                    maxProfit = nums[i];
                }
            }

            System.out.println("#" + testCase + " " + result);
        }
	}
}