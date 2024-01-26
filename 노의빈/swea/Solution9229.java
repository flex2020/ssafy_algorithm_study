package swea;

import java.util.*;
import java.io.*;

public class Solution9229 {
	private static int N, M, answer;
	private static int[] snacks;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			answer = -1;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			snacks = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			for (int i=0; i<N-1; i++) {
				for (int j=i+1; j<N; j++) {
					int sum = snacks[i] + snacks[j];
					if (sum <= M) {
						answer = Math.max(answer, sum);
					}
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}
