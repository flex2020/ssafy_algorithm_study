package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_17144 {
	static int R, C, T, answer;
	static Queue<int[]> Q;
	static int[][] board, copy;
	static List<Integer> row;
	static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		row = new ArrayList<>(); // 공기청정기 행의 값
		Q = new ArrayDeque<>();
		for (int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == -1) {
					row.add(i);
				}
			}
		}
		for (int time=0; time<T; time++) {
			spread();
			wind();
		}
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (board[i][j] > 0) answer += board[i][j];
			}
		}
		System.out.println(answer);
	}
	
	public static void spread() {
		copy = new int[R][C];
		makeQueue();
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			int cnt = 0; // 확산할 수 있는 방향의 수
			for (int i=0; i<4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (0<=nr && nr<R && 0<=nc && nc<C && board[nr][nc] >= 0) {
					cnt++; // 해당 방향으로 확산 가능
					copy[nr][nc] += Math.floorDiv(board[cur[0]][cur[1]], 5);
				}
			}
			board[cur[0]][cur[1]] -= Math.floorDiv(board[cur[0]][cur[1]], 5) * cnt;
		}
		makeBoard();
	}
	
	public static void makeQueue() {
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (board[i][j] > 0) Q.offer(new int[] {i, j});
			}
		}
	}
	public static void makeBoard() {
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				board[i][j] += copy[i][j];
			}
		}
	}
	
	public static void wind() {
		int first1 = board[0][0];
		int first2 = board[row.get(1)][C-1];
		int first3 = board[R-1][0];
		// 왼쪽
		for (int i=0; i<C-1; i++) {
			board[0][i] = board[0][i+1]; 
			board[R-1][i] = board[R-1][i+1];
		}
		
		// 위쪽
		for (int i=0; i<row.get(0); i++) {
			board[i][C-1] = board[i+1][C-1];
		}
		for (int i=row.get(1); i<R-1; i++) {
			if (board[i][0] == -1) continue;
			board[i][0] = board[i+1][0];
		}
		// 오른쪽
		for (int i=C-1; i>1; i--) {
			board[row.get(0)][i] = board[row.get(0)][i-1];
			board[row.get(1)][i] = board[row.get(1)][i-1];
		}
		// 새로 나온 바람
		board[row.get(0)][1] = 0;
		board[row.get(1)][1] = 0;
		// 아래로
		for (int i=row.get(0); i>0; i--) { 
			if (board[i][0] == -1) continue;
			board[i][0] = board[i-1][0];
		}
		for (int i=R-1; i>row.get(1); i--) {
			board[i][C-1] = board[i-1][C-1];
		}
		board[1][0] = first1;
		board[row.get(1)+1][C-1] = first2;
		board[R-2][0] = first3;
	}

}
