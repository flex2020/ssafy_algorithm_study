import java.util.*;
import java.io.*;

public class SolutionBaekJoon20057 {
	
	static class Position {
		int x;
		int y;
		int direction;
		
		Position(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}
	
	static int[][] map;
	static int movedSand;
	static int result;
	static Position tornado;
	static int length;
	// 좌하우상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 지도 입력받기
		map = new int[N + 4][N + 4];
		Arrays.fill(map[0], -1);
		Arrays.fill(map[1], -1);
		Arrays.fill(map[map.length - 1], -1);
		Arrays.fill(map[map.length - 2], -1);
		StringTokenizer st;
		for (int i = 2; i < map.length - 2; i++) {
			map[i][0] = -1;
			map[i][1] = -1;
			map[i][map[0].length - 1] = -1;
			map[i][map[0].length - 2] = -1;
			
			st = new StringTokenizer(br.readLine());
			for (int j = 2; j < map[0].length - 2; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 토네이도 처음 위치 구하기
		int mid = (N + 1) / 2 + 1;
		tornado = new Position(mid, mid, 0);
		length = 1;
		
		result = 0;
		simulation();
		
		System.out.println(result);
	}
	
	public static void simulation() {
		while (true) {
			// 토네이도 이동하기
			for (int l = 0; l < length; l++) {
				tornado.x += dx[tornado.direction];
				tornado.y += dy[tornado.direction];
				
				// 모레 이동하기
				movedSand = 0;
				moveSand();
				
				// 도착지에 도달한 경우
				if (tornado.x == 2 && tornado.y == 2) {
					return;
				}
			}
			
			// 토네이도 방향 전환하기
			if (tornado.direction % 2 != 0) {
				length++;
			}
			tornado.direction = (tornado.direction + 1) % 4;
		}
	}
	
	public static void moveSand() {
		// 토네이도 진행 방향의 90도 회전 방향
		int direction = ((tornado.direction - 1) + 4) % 4;
		Position next = new Position(tornado.x + dx[direction], tornado.y + dy[direction], direction);
		moveSand90(next);
		
		direction = ((tornado.direction + 1) + 4) % 4;
		next = new Position(tornado.x + dx[direction], tornado.y + dy[direction], direction);
		moveSand90(next);
		
		// 토네이도 진행 방향
		// 5%
		int nextX = tornado.x + dx[tornado.direction];
		int nextY = tornado.y + dy[tornado.direction];
		double sand = map[tornado.x][tornado.y] * 0.01;
		if (map[nextX][nextY] != -1) {
			int preX = nextX + dx[tornado.direction];
			int preY = nextY + dy[tornado.direction];
			if (map[preX][preY] != -1) {
				map[preX][preY] += (int)(sand * 5);
			} else {
				result += (int)(sand * 5);
			}
			movedSand += (int)(sand * 5);
			
			// a
			map[nextX][nextY] += map[tornado.x][tornado.y] - movedSand;
		} else {
			result += map[tornado.x][tornado.y] - movedSand;
		}
		
		// y
		map[tornado.x][tornado.y] = 0;
	}
	
	public static void moveSand90(Position cur) {
		double sand = map[tornado.x][tornado.y] * 0.01;
		
		// 7%
		if (map[cur.x][cur.y] != -1) {
			map[cur.x][cur.y] += (int)(sand * 7);
		} else {
			result += (int)(sand * 7);
		}
		movedSand += (int)(sand * 7);
		
		// 같은 방향으로 진행(2%)
		int nextX = cur.x + dx[cur.direction];
		int nextY = cur.y + dy[cur.direction];
		if (map[nextX][nextY] != -1) {
			map[nextX][nextY] += (int)(sand * 2);
		} else {
			result += (int)(sand * 2);
		}
		movedSand += (int)(sand * 2);
		
		// 토네이도 반대 방향으로 진행(1%)
		nextX = cur.x - dx[tornado.direction];
		nextY = cur.y - dy[tornado.direction];
		if (map[nextX][nextY] != -1) {
			map[nextX][nextY] += (int)(sand);
		} else {
			result += (int)(sand);
		}
		movedSand += (int)(sand);
		
		// 토네이도 방향으로 진행(10%)
		nextX = cur.x + dx[tornado.direction];
		nextY = cur.y + dy[tornado.direction];
		if (map[nextX][nextY] != -1) {
			map[nextX][nextY] += (int)(sand * 10);
		} else {
			result += (int)(sand * 10);
		}
		movedSand += (int)(sand * 10);
	}

}
