package boj;

import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main13460 {
	private static int N, M, answer;
	private static char[][] map;
	private static Point red, blue, end;
	private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	static class Board {
		int redX, redY, blueX, blueY;

		public Board(int redX, int redY, int blueX, int blueY) {
			this.redX = redX;
			this.redY = redY;
			this.blueX = blueX;
			this.blueY = blueY;
		}

		@Override
		public String toString() {
			return "Board [redX=" + redX + ", redY=" + redY + ", blueX=" + blueX + ", blueY=" + blueY + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = -1;
		map = new char[N][M];
		for (int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j=0; j<M; j++) {
				if (map[i][j] == 'R') {
					red = new Point(i, j);
				} else if (map[i][j] == 'B') {
					blue = new Point(i, j);
				} else if (map[i][j] == 'O') {
					end = new Point(i, j);
				}
			}
		}
		bfs();
		
		System.out.println(answer);
	}
	
	private static void bfs() {
		Queue<Board> q = new ArrayDeque<>();
		q.offer(new Board(red.x, red.y, blue.x, blue.y));
		boolean[][][][] visited = new boolean[N][M][N][M];
		visited[red.x][red.y][blue.x][blue.y] = true;
		
		int count = 0;
		while (!q.isEmpty()) {
			// 10번해도 안들어갔다면 종료
			if (count == 11) return;
			int qsize = q.size();
			for (int i=0; i<qsize; i++) {
				Board b = q.poll();
				// 빨간 구슬만 도착했을때 성공
				if (b.redX == end.x && b.redY == end.y && (b.blueX != end.x || b.blueY != end.y)) {
					answer = count;
					return;
				}
				// 파란 구슬이 들어가면 진행하지 않음
				if (b.blueX == end.x && b.blueY == end.y) continue;
				
				// 보드판을 4방으로 움직여본다.
				for (int d=0; d<4; d++) {
					Point redMarble = new Point(b.redX, b.redY);
					Point blueMarble = new Point(b.blueX, b.blueY);
					moveBoard(redMarble, blueMarble, d);
					if (visited[redMarble.x][redMarble.y][blueMarble.x][blueMarble.y]) continue;
					q.offer(new Board(redMarble.x, redMarble.y, blueMarble.x, blueMarble.y));
					visited[redMarble.x][redMarble.y][blueMarble.x][blueMarble.y] = true;
				}
			}
			count += 1;
		}
	}
	
	private static void moveBoard(Point red, Point blue, int d) {
		// 해당 방향으로 최대로 갈 수 있는 좌표를 구한다.
		boolean redgoal = false;
		boolean bluegoal = false;
		int rx = red.x;
		int ry = red.y;
		while (true) {
			int nrx = rx + dx[d];
			int nry = ry + dy[d];
			if (nrx < 0 || nrx >= N || nry < 0 || nry >= M || map[nrx][nry] == '#') break;
			rx = nrx;
			ry = nry;
			if (map[nrx][nry] == 'O') {
				redgoal = true;
				break;
			}
		}
		int bx = blue.x;
		int by = blue.y;
		while (true) {
			int nbx = bx + dx[d];
			int nby = by + dy[d];
			if (nbx < 0 || nbx >= N || nby < 0 || nby >= M || map[nbx][nby] == '#') break;
			bx = nbx;
			by = nby;
			if (map[nbx][nby] == 'O') {
				bluegoal = true;
				break;
			}
		}
		if (redgoal && bluegoal) return;
		// 두 구슬의 도착점이 같다면 해당 방향에서 먼저 벽에 닿는 구슬을 기준으로 다른 구슬을 한칸 띄워서 이동시킨다.
		if (rx == bx && ry == by) {
			// 상
			if (d == 0) {
				// x좌표가 더 작은 구슬이 우선권을 가진다
				// 빨간 구슬 우선
				if (red.x < blue.x) bx += 1;
				// 파란 구슬 우선
				else rx += 1;
			}
			// 하
			else if (d == 1) {
				// x좌표가 더 큰 구슬이 우선권을 가진다 
				// 빨간 구슬 우선
				if (red.x > blue.x) bx -= 1;
				// 파란 구슬 우선
				else rx -= 1;
			}
			// 좌
			else if (d == 2) {
				// y좌표가 더 작은 구슬이 우선권을 가진다
				// 빨간 구슬 우선
				if (red.y < blue.y) by += 1;
				// 파란 구슬 우선
				else ry += 1;
			}
			// 우
			else if (d == 3) {
				// y좌표가 더 큰 구슬이 우선권을 가진다
				// 빨간 구슬 우선
				if (red.y > blue.y) by -= 1;
				// 파란 구슬 우선
				else ry -= 1;
			}
		}
		red.x = rx;
		red.y = ry;
		blue.x = bx;
		blue.y = by;
	}
}
