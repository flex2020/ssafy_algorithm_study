package boj;

import java.io.*;
import java.util.*;

public class Main7579 {
	private static int N, M, answer;
	private static int[] mem, cost, dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mem = new int [N];
		cost = new int[N];
		dp = new int[10001]; // 최대로 사용할 수 있는 비용 1만
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) mem[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) cost[i] = Integer.parseInt(st.nextToken());
		// dp[i]를 i메모리를 만들수 있는 최소비용이라 하면 O(NM) 시간복잡도 + 메모리 초과 가능성
		// dp[i] i만큼 비용을 들였을때 가장 크게 절약할 수 있는 메모리
		// 역순으로 하는 이유: 뒤에서부터 채워야 중복된 연산이 없음
		for (int j=0; j<N; j++) {
			for (int i=10000; i>=cost[j]; i--) {
				dp[i] = Math.max(dp[i], dp[i-cost[j]] + mem[j]);
			}
		}
		
		for (int i=0; i<10001; i++) {
			if (dp[i] >= M) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
	}

}
