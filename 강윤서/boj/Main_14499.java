package 강윤서.boj;
import java.io.*;
import java.util.*;
public class Main_14499 {
	static int N, M, x, y, K;
	static int[][] board;
	static int[] dx = {0, 0, 0, -1 ,1}, dy = {0, 1, -1, 0, 0};
	static int[] dice;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		dice = new int[6];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dice[0] = board[x][y];

		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx<0 || nx>=N || ny<0 || ny>=M) continue;
			if (dir == 1) { // 동
				int tmp = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[3];
				dice[3] = tmp;
			} else if (dir == 2) { // 서
				int tmp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[5];
				dice[5] = dice[1];
				dice[1] = tmp;
			} else if (dir == 3) { // 븍
				int tmp = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[5];
				dice[5] = dice[4];
				dice[4] = tmp;
			} else { // 남
				int tmp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[5];
				dice[5] = dice[2];
				dice[2] = tmp;
				
			}
			if (board[nx][ny] != 0) {
				dice[5] = board[nx][ny];
				board[nx][ny] = 0;
			} else {
				board[nx][ny] = dice[5];
			}
			x = nx;
			y = ny;
			sb.append(dice[0] + "\n");
		}
		System.out.println(sb);
		
	}

}
