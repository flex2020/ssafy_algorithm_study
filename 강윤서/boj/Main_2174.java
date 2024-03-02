package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_2174 {
	static int A, B, N, M;
	static int[][] board;
	static Robot[] robot;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1}; // 상 좌 하 우 (왼쪽을 회전)
	static boolean flag = true;
	static class Robot {
		int r, c, number, dir;
		Robot (int number, int r, int c, int dir) {
			this.number = number;
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[B][A];
		robot = new Robot[N+1];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			char dir = st.nextToken().charAt(0);
			board[B-y-1][x] = i+1;
			
			if (dir == 'N') {
				robot[i+1] = new Robot(i+1, B-y-1, x, 0);
			}
			else if (dir == 'W') {
				robot[i+1] = new Robot(i+1, B-y-1, x, 1);
			}
			else if (dir == 'S') {
				robot[i+1] = new Robot(i+1, B-y-1, x, 2);
			}
			else if (dir == 'E') {
				robot[i+1] = new Robot(i+1, B-y-1, x, 3);
			}
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			char cmd = st.nextToken().charAt(0);
			int repeat = Integer.parseInt(st.nextToken());
			while (repeat-- > 0) {
				Robot cur = robot[number];
				if (cmd == 'L') {
					cur.dir = (cur.dir+1) % 4;
				} else if (cmd == 'R') {
					cur.dir = (cur.dir+3) % 4;
				} else {
					int nr = cur.r + dr[cur.dir];
					int nc = cur.c + dc[cur.dir];
					if (flag && (nr<0 || nr>=B || nc<0 || nc>=A)) {
						sb.append("Robot " + cur.number + " crashes into the wall");
						flag = false;
						break;
					}
					if (flag && board[nr][nc] != 0) {
						sb.append("Robot " + cur.number + " crashes into robot " + board[nr][nc]);
						flag = false;
						break;
					}
					if (flag && 0<=nr && nr<B && 0<=nc && nc<A && board[nr][nc] == 0) {
						board[cur.r][cur.c] = 0;
						board[nr][nc] = cur.number;
						robot[number] = new Robot(cur.number, nr, nc, cur.dir);
					}
				}
			}
			
		}
		if (flag) {
			System.out.println("OK");
		} else {
			System.out.println(sb);
		}
	}

}
