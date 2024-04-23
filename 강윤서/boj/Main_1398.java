package 강윤서.boj;
import java.io.*;
public class Main_1398 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[100];
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			long N = Long.parseLong(br.readLine());
			for (int i=0; i<100; i++) {
				if (i >= 25) {
					dp[i] = Math.min(dp[i-25]+1, Math.min(dp[i-10]+1, i));
				} else if (i >= 10) {
					dp[i] = Math.min(dp[i-10]+1, i);
				} else {
					dp[i] = i;
				}
			}
//			System.out.println(Arrays.toString(dp));
//			System.out.println(dp[50]);
			int answer = 0;
			while (N > 100) {
				answer += dp[(int)(N%100)];
				N /= 100;
			}
			answer += dp[(int) N];
			System.out.println(answer);
			
		}
	}
}
