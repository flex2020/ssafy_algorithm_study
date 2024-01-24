package swea;

import java.util.*;
import java.io.*;

public class Solution2001 {
	private static int N, M, answer;
	private static int[][] graph;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			graph = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			solve();
			System.out.println("#" + tc + " " + answer);
		}
	}
	private static void solve() {
		for (int i=0; i<N-M+1; i++) {
			for (int j=0; j<N-M+1; j++) {
				int sum = 0;
				for (int dx=0; dx<M; dx++) {
					for (int dy=0; dy<M; dy++) {
						sum += graph[i+dx][j+dy];
					}
				}
				answer = Math.max(answer, sum);
			}
		}
	}

}
