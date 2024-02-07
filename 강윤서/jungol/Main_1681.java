package 강윤서.jungol;

import java.io.*;
import java.util.*;

public class Main_1681 {
	static int N, answer;
	static int[][] board;
	static boolean[] isVisited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		answer = Integer.MAX_VALUE;

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isVisited = new boolean[N];
		dfs(0, 0, 1);
		
		System.out.println(answer);
	}
	public static void dfs(int node, int dist, int cnt) {
		if (cnt == N) {
			if (board[node][0] != 0) {
				answer = Math.min(answer, dist + board[node][0]);
			}
			return ;
		}
		for (int i=1; i<N; i++) {
			if (i == node) continue;
			if (!isVisited[i] && board[node][i] != 0 && dist+board[node][i] < answer) {
				isVisited[i] = true;
				dfs(i, dist + board[node][i], cnt+1);
				isVisited[i] = false;
			}
		}
	}
}