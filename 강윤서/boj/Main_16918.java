package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_16918 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		char[][] board = new char[R+2][C+2];
		int[][] time = new int[R+2][C+2];

		for (int i=1; i<=R; i++) {
			Arrays.fill(board[i], '#'); // 가두기
			Arrays.fill(time[i], -1);
			String input = " " + br.readLine();
			for (int j=1; j<=C; j++) {
				board[i][j] = input.charAt(j);
				if (board[i][j] == 'O') {
					time[i][j] = 3;
				} else if (N>=2){
					board[i][j] = 'O';
					time[i][j] = 5;
				}
					
			}
		}

		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		for (int t=3; t<=N; t++) {
			boolean flag = true; // 해당 초에 폭탄이 터지는지 여부(true: 안터짐, false: 터짐)
			for (int r=1; r<=R; r++) {
				for (int c=1; c<=C; c++) {
					if (time[r][c] == t) {
						flag = false;
						// 폭탄 빵!
						board[r][c] = '.';
						time[r][c] = -1;
						for (int d=0; d<4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (board[nr][nc] == '#') continue;
							if (time[nr][nc] == t) continue;
							board[nr][nc] = '.';
							time[nr][nc] = -1;
						}
					}
				}
			}
			if (flag) { // 폭탄 안 터짐 -> 폭탄 채우자~
				for (int r=1; r<=R; r++) {
					for (int c=1; c<=C; c++) {
						if (board[r][c] == '.') {
							board[r][c] = 'O';
							time[r][c] = t+3;
						}
					}
				}
			}
		}
		for (int i=1; i<=R; i++) {
			for (int j=1; j<=C; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

}