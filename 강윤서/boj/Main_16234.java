package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_16234 {
	static int N, L, R, day, number = 1;
	static int[][] board, visited;
	static int[] dr = {0, 1, -1, 0};
	static int[] dc = {1, 0, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		visited = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			number = 1;
			for (int i=0; i<N; i++) {
				Arrays.fill(visited[i], 0);
			}
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (visited[i][j] == 0) {
						visited[i][j] = number;
						dfs(i, j, number);
						number++;
					}
				}
			}
			if (!move(number)) break;
			day += 1;
		}
		System.out.println(day);
	}
	public static void dfs(int r, int c, int num) {
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (0<=nr && nr<N && 0<=nc && nc<N && visited[nr][nc] == 0) {
				int value = Math.abs(board[r][c] - board[nr][nc]);
				if (L <= value && value <= R) {
					visited[nr][nc] = num;
					dfs(nr, nc, num);
				}
			}
		}
	}
	public static boolean move(int num) {
		boolean flag = false; // 이동이 일어났는지 판단
		int[] cnt = new int[num+1];
		int[] people = new int[num+1];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				cnt[visited[i][j]] += 1;
				people[visited[i][j]] += board[i][j];
			}
		}
		for (int i=1; i<num; i++) {
			people[i] = people[i] / cnt[i];
		}
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (board[i][j] != people[visited[i][j]]) {
					flag = true;
				}
				board[i][j] = people[visited[i][j]];
			}
		}
		return flag;
	}
}

