package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_14890 {
	static int N, L, answer;
	static int[][] board;
	static int[] dr = { 0, 1 }, dc = { 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		board = new int[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
			board[i][N] = board[i][N - 1];
		}
		for (int i = 0; i <= N; i++) {
			board[N][i] = board[N - 1][i];
		}
		for (int i = 0; i < N; i++) {
			
			if (dfs(i, 0, 0, 0, 1)) { // 우
				answer++;
			}
			if (dfs(0, i, 1, 0, 1)) { // 하
				answer++;
			}
		}
		System.out.println(answer);
	}

	public static boolean dfs(int r, int c, int dir, int next, int cnt) {
		if (dir == 0 && c == N) {
			if (next == -1 && cnt <= L) return false;
			return true;
		}
		if (dir == 1 && r == N) {
			if (next == -1 && cnt <= L) return false;
			return true;
		}
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if (board[r][c] < board[nr][nc]) {
			if (board[nr][nc] - board[r][c] > 1)
				return false;
			if (cnt < L)
				return false; // 경사로 놓을 공간 없음
			if (next == -1 && cnt < L * 2)
				return false;
			return dfs(nr, nc, dir, 1, 1);
		} else if (board[r][c] > board[nr][nc]) {
			if (board[r][c] - board[nr][nc] > 1)
				return false;
			if (next < 0 && cnt < L)
				return false;
			return dfs(nr, nc, dir, -1, 1);
		} else {
			return dfs(nr, nc, dir, next, cnt + 1);
		}
	}
}
