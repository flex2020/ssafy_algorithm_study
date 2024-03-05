package boj;

import java.io.*;
import java.util.*;

public class Main2294 {
	private static int n, k;
	private static int[] coin, dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coin = new int[n];
		for (int i=0; i<n; i++) coin[i] = Integer.parseInt(br.readLine());
		dp = new int[k+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for (int i=1; i<=k; i++) {
			// i원 만드는데 필요한 동전의 최소 수 구하기
			for (int j=0; j<n; j++) {
				// i원에 다른 동전크기만큼 추가
				int cur = coin[j];
				if (i - cur < 0 || dp[i-cur] == Integer.MAX_VALUE) continue;
				dp[i] = Math.min(dp[i], dp[i-cur] + 1);
			}
		}
		
		System.out.println(dp[k] == Integer.MAX_VALUE ? -1 : dp[k]);
	}
}
