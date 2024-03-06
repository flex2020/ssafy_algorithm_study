package boj;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main1520 {
	private static int N, M, answer;
	private static int[][] map;
	private static int[][] dp;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) dp[i][j] = -1;
		}
		
		System.out.println(dfs(0, 0));
	}
	
	
	// x, y -> N-1, N-1 까지 가는 경우의 수를 리턴
	private static int dfs(int x, int y) {
		// 도착했으면
		if (x == N-1 && y == M-1) {
			return 1;
		}
		// (x,y)에 가본적이 있으면
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		
		dp[x][y] = 0;
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] >= map[x][y]) continue;
			dp[x][y] += dfs(nx, ny);
		}
		return dp[x][y];
	}
}
