package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_3197 {
	static int R, C, day, order;
	static int[][] board;
	static boolean[][] visited, iceVisited;
	static Queue<int[]> curQ, curLand, newQ;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		visited = new boolean[R][C];
		iceVisited = new boolean[R][C];
		curQ = new ArrayDeque<>();
		curLand = new ArrayDeque<>();
		newQ = new ArrayDeque<>();
		for (int i=0; i<R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j=0; j<C; j++) {
				if (input[j] == 'L') {
					// 백조도 땅의 일부
					curLand.offer(new int[] {i, j});
					iceVisited[i][j] = true;
					
					board[i][j] = 1;
					if (order == 0) {
						order++;
						visited[i][j] = true;
						board[i][j] = 1;
						curQ.offer(new int[] {i, j});
					} else {
						board[i][j] = 2;
					}
				} else if (input[j] == 'X') {
					board[i][j] = -1;
				} else {
					curLand.offer(new int[] {i, j});
					iceVisited[i][j] = true;
				}
			}
		}
		while (true) {
			if (move()) { 
				break;
			}
			melt(); 
			day++;
		}
		System.out.println(day);
	}
	public static void melt() {
		int size = curLand.size();
		for (int s=0; s<size; s++) {
			int[] cur = curLand.poll();
			for (int i=0; i<4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (0<=nr && nr<R && 0<=nc && nc<C && board[nr][nc] == -1 && !iceVisited[nr][nc]) {
					// 땅과 맞닿은 빙하 -> 녹아야 함
					board[nr][nc] = 0; // 빙하 땅으로 바꿔주기
					curLand.offer(new int[] {nr, nc});
					iceVisited[nr][nc] = true;
				}
			}
		}
	}
	public static boolean move() {
		while (!newQ.isEmpty()) curQ.offer(newQ.poll());
		while (!curQ.isEmpty()) {
			int[] cur = curQ.poll();
			if (board[cur[0]][cur[1]] == 2) return true; 
			for (int i=0; i<4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (0<=nr && nr<R && 0<=nc && nc<C && !visited[nr][nc]) {
					if (board[nr][nc] != -1) {
						visited[nr][nc] = true;
						curQ.offer(new int[] {nr, nc});
					} else {
						newQ.offer(cur);
					}
				}
			}
		}
		return false;
	}
}
