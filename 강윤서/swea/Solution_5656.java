package 강윤서.swea;
import java.io.*;
import java.util.*;

public class Solution_5656 {
	static int N, W, H, answer;
	static int[][] board;
	static int[] selected;// W 중에 구슬 쏠 곳
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			board = new int[H][W];
			selected = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			perm(0);
			System.out.println("#" + tc + " " + answer);
		}

	}

	public static void perm(int cnt) {
		if (cnt == N) {
			simulate(board);
			return;
		}
		if (answer == 0) return ; // 이미 최적의 답을 냈으면 그만
		for (int i = 0; i < W; i++) {
			selected[cnt] = i;
			perm(cnt + 1);
		}
	}

	public static void simulate(int[][] board) {
		int[][] map = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = board[i][j];
			}
		}
		// selected에 저장된 거 순서대로 떨어뜨리기
		for (int j = 0; j < N; j++) {
			int idx = selected[j];
			for (int i = 0; i < H; i++) {
				if (map[i][idx] != 0) { // 구슬과 처음으로 닿음
					map = bfs(map, i, idx);
					break;
				}
			}
		}
		answer = Math.min(answer, calculate(map));
	}

	public static int[][] bfs(int[][] map, int r, int c) {
		Queue<int[]> Q = new ArrayDeque<>();
		Q.offer(new int[] {r, c, map[r][c]});
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			map[cur[0]][cur[1]] = 0; // 벽돌 깨짐
			if (cur[2] > 1) { // 연쇄 폭발 검사
				for (int i=0; i<4; i++) {
					for (int j=1; j<cur[2]; j++) {
						int nr = cur[0] + dr[i] * j;
						int nc = cur[1] + dc[i] * j;
						if (nr < 0 || nr >= H || nc < 0 || nc >= W) break;
						if (map[nr][nc] != 0) {
							Q.offer(new int[] {nr, nc, map[nr][nc]});
						}
					}
				}
			}
		}
		// 아래로 떨어뜨려주기
		Queue<Integer> down = new ArrayDeque<>();
		for (int j=0; j<W; j++) {
			down.clear();
			for (int i=H-1; i>=0; i--) {
				if (map[i][j] != 0) {
					down.offer(map[i][j]);
				}
			}
			for (int i=H-1; i>=0; i--) {
				if (!down.isEmpty()) {
					map[i][j] = down.poll();
				} else {
					map[i][j] = 0;
				}
			}
		}
		return map;
	}
	public static void print(int[][] map) {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				System.out.print(map[i][j] + " " );
			}
			System.out.println();
		}
	}
	public static int calculate(int[][] map) {
		int result = 0;
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if (map[i][j] > 0) {
					result++;
				}
			}
		}
		return result;
	}

}
