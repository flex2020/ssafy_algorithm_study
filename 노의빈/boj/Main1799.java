package boj;

import java.io.*;
import java.util.*;

public class Main1799 {
	private static int N, answer1, answer2;
	private static int[][] board;
	private static boolean[][] visited;
	private static int[] dx = {1, 1, -1, -1}, dy = {1, -1, 1, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visited = new boolean[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		recursive(0, true);
		recursive(0, false);
		//System.out.println("answer1 = " + answer1 + ", answer2 = " + answer2);
		System.out.println(answer1 + answer2);
	}

	private static void recursive(int cnt, boolean odd) {
		// 현재 비숍을 놓을 수 있는 칸이 있는지 확인
		int x = -1, y = -1;
		L: for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (odd && (i + j) % 2 == 0 || !odd && (i + j) % 2 == 1 || visited[i][j] || board[i][j] != 1 || !check(i, j)) continue;
				x = i;
				y = j;
				break L;
			}
		}
		
		if (x == -1 && y == -1) {
			//display();
			if (odd) answer1 = Math.max(answer1, cnt);
			else answer2 = Math.max(answer2, cnt);
			return;
		}
		visited[x][y] = true;
		// 현재 놓을 수 있는 위치에 비숍을 놓아보고 다음 단계로 진행
		board[x][y] = 2;
		recursive(cnt + 1, odd);
		// 놓지 않고 진행
		board[x][y] = 1;
		recursive(cnt, odd);
		visited[x][y] = false;
	}

	private static boolean check(int x, int y) {
		// 대각선 4방향으로 가서 다른 비숍이 있는지 확인
		for (int d=0; d<4; d++) {
			int nx = x;
			int ny = y;
			while (true) {
				nx += dx[d];
				ny += dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
				if (board[nx][ny] == 2) return false;
			}
		}
		return true;
	}
	
	private static void display() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(visited[i][j] ? 1 + " " : 0 + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
