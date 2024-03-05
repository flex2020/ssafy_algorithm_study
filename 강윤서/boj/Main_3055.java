package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_3055 {
	static int R, C, curTime;
	static boolean flag;
	static char[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static Queue<Position> curQ, curWaterQ;
	static class Position {
		int r, c;
		Position (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visited = new boolean[R][C];
		curQ = new ArrayDeque<>();
		curWaterQ = new ArrayDeque<>();
		for (int i=0; i<R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j=0; j<C; j++) {
				board[i][j] = input[j];
				if (board[i][j] == 'S') { // 고슴도치 위치
					curQ.offer(new Position(i, j));
				} else if (board[i][j] == '*') { // 물
					curWaterQ.offer(new Position(i, j));
				}
			}
		}
		while (true) {
			moveWater();
			if (move()) {
				break;
			}
			if (curQ.size() == 0) {
				flag = true;
				break;
			}
			curTime++;
		}
		if (flag) System.out.println("KAKTUS");
		else System.out.println(curTime);
		
	}
	public static void moveWater() {
		int size = curWaterQ.size();
		for (int s=0; s<size; s++) {
			Position cur = curWaterQ.poll();
			for (int i=0; i<4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (0<=nr && nr<R && 0<=nc && nc<C && board[nr][nc] == '.' && !visited[nr][nc]) {
					board[nr][nc] = '*';
					visited[nr][nc] = true;
					curWaterQ.offer(new Position(nr, nc));
				}
			}
		}
	}
	public static boolean move() {
		int size = curQ.size();
		for (int s=0; s<size; s++) {
			Position cur = curQ.poll();
			if (board[cur.r][cur.c] == 'D') {
				return true;
			}
			for (int i=0; i<4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (0<=nr && nr<R && 0<=nc && nc<C && board[nr][nc] != '*' && board[nr][nc] != 'X' && !visited[nr][nc]) {
					curQ.offer(new Position(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		return false;
	}
}
