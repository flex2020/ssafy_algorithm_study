package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_14501 {
	static int N, answer;
	static int[] T, P, dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N+1];
		for (int i=0; i<N; i++) {
			dp[i+1] = Math.max(dp[i], dp[i+1]);
			if (i + T[i] > N) continue;
			dp[i+T[i]] = Math.max(dp[i+T[i]], dp[i] + P[i]);
			
		}
		for (int i=0; i<=N; i++) {
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}

}
