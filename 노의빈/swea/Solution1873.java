import java.util.*;
import java.io.*;

public class Solution {
	private static int H, W, N;
	private static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	private static char[] tanks = {'<', '>', '^', 'v'};
	private static char[][] map;
	private static char[] ops;
	private static Tank tank;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			for (int i=0; i<H; i++) {
				String input = br.readLine();
				for (int j=0; j<W; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == '<') {
						tank = new Tank(i, j, 0);
					} else if (map[i][j] == '>') {
						tank = new Tank(i, j, 1);
					} else if (map[i][j] == '^') {
						tank = new Tank(i, j, 2);
					} else if (map[i][j] == 'v') {
						tank = new Tank(i, j, 3);
					}
				}
			}
			N = Integer.parseInt(br.readLine());
			ops = br.readLine().toCharArray();
			solve();
			System.out.print("#" + tc + " ");
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	private static void solve() {
		for (int i=0; i<N; i++) {
			char op = ops[i];
			if (op == 'U') {
				move(2);
			} else if (op == 'D') {
				move(3);
			} else if (op == 'L') {
				move(0);
			} else if (op == 'R') {
				move(1);
			} else if (op == 'S') {
				shoot();
			}
		}
	}
	private static void shoot() {
		int nx = tank.x, ny = tank.y; // 포탄의 좌표
		while (true) {
			nx += dx[tank.d];
			ny += dy[tank.d];
			// 포탄이 맵밖으로 나간 경우
			if (!(0 <= nx && nx < H && 0 <= ny && ny < W)) {
				return;
			}
			// 포탄이 벽돌에 부딪힌 경우
			if (map[nx][ny] == '*') {
				map[nx][ny] = '.';
				return;
			}
			// 포탄이 강철벽에 부딪힌 경우
			if (map[nx][ny] == '#') {
				return;
			}
		}
	}
	private static void move(int dir) {
		tank.d = dir;
		int nx = tank.x + dx[dir];
		int ny = tank.y + dy[dir];
		// 다음에 이동할 곳이 범위 안이라면
		if (0 <= nx && nx < H && 0 <= ny && ny < W) {
			// 평지라면 탱크 이동
			if (map[nx][ny] == '.') {
				map[tank.x][tank.y] = '.'; // 이전 좌표 평지로
				// 탱크 좌표 변경
				tank.x = nx;
				tank.y = ny;
			}
		}
		map[tank.x][tank.y] = tanks[dir]; // 전차의 방향 변경
	}
}

class Tank {
	int x, y, d;
	Tank(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}