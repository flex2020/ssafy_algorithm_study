package jungol;

import java.util.*;
import java.io.*;

public class Main1681 {
	private static int N;
	private static int[][] graph;
	private static boolean[] visited;
	private static int answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		answer = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1][N+1];
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N+1];
		dfs(1, 0, 0);
		System.out.println(answer);
	}
	
	private static void dfs(int cur, int cost, int cnt) {
		if (answer < cost) return;
		if (cnt == N && cur == 1) {
			// 다 방문했는지 확인
			answer = Math.min(answer, cost);
			return;
		}
		
		for (int i=1; i<=N; i++) {
			// i번 장소에 아직 방문하지 않았다면 ㄱㄱ
			if (!visited[i] && graph[cur][i] > 0) {
				visited[i] = true;
				dfs(i, cost + graph[cur][i], cnt + 1);
				visited[i] = false;
			}
		}
	}
}
