package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_7579 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] m = new long[N];
		int[] c = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			m[i] = Long.parseLong(st.nextToken());
		}

		int total = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
			total += c[i];
		}
		
		long[] dp = new long[total + 1];
		for (int i = 0; i < N; i++) {
			for (int j = total; j >= 0; j--) {
				if (c[i] <= j) {
					dp[j] = Math.max(dp[j], dp[j - c[i]] + m[i]);
				} else if (j > 0) {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
			}
		}
//		System.out.println(Arrays.toString(dp));
//		System.out.println(dp.length);
		for (int i=0; i<=total; i++) {
			if (dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
		
	}

}
