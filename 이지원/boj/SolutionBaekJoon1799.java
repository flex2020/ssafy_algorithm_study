import java.util.*;
import java.io.*;

public class SolutionBaekJoon1799 {
	
	static class Position {
		int x;
		int y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] map;
	static int maxBlackResult, maxWhiteResult;
	// 대각선 위 왼쪽/오른쪽, 아래 왼쪽/오른쪽
	static int[] dx = {-1, -1, 1, 1};
	static int[] dy = {-1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 체스판 입력받기
		map = new int[N + 2][N + 2];
		Arrays.fill(map[0], Integer.MIN_VALUE);
		Arrays.fill(map[map.length - 1], Integer.MIN_VALUE);
		StringTokenizer st;
		for (int i = 1; i < map.length - 1; i++) {
			map[i][0] = Integer.MIN_VALUE;
			map[i][map[0].length - 1] = Integer.MIN_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < map[0].length - 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 비숏의 최대 개수 구하기
		calculateMaxResult();
		int maxResult = maxBlackResult + maxWhiteResult;
		
		System.out.println(maxResult);
	}
	
	public static void calculateMaxResult() {
		maxBlackResult = Integer.MIN_VALUE;
		calculateBlackMaxResult(findBlackPosition(new Position(1, 1)), 0);
		
		maxWhiteResult = Integer.MIN_VALUE;
		calculateWhiteMaxResult(findWhitePosition(new Position(1, 2)), 0);
	}
	
	public static Position findBlackPosition(Position cur) {
		Position p = new Position(cur.x, cur.y);
		
		// 체스판을 벗어나는 경우
		if (p.y > N) {
			p.x++;
			if (p.x % 2 == 0) {
				p.y = 2;
			} else {
				p.y = 1;
			}
			
			// 더 이상 이동할 칸이 없는 경우
			if (map[p.x][p.y] == Integer.MIN_VALUE) {
				p = null;
				return p;
			}
		}
		
		while (map[p.x][p.y] != 1) {
			p.y += 2;
			
			// 체스판을 벗어나는 경우
			if (p.y > N) {
				p.x++;
				if (p.x % 2 == 0) {
					p.y = 2;
				} else {
					p.y = 1;
				}
				
				// 더 이상 이동할 칸이 없는 경우
				if (map[p.x][p.y] == Integer.MIN_VALUE) {
					p = null;
					break;
				}
			}
		}
		
		return p;
	}
	
	public static Position findWhitePosition(Position cur) {
		Position p = new Position(cur.x, cur.y);
		
		// 체스판을 벗어나는 경우
		if (p.y > N) {
			p.x++;
			if (p.x % 2 == 0) {
				p.y = 1;
			} else {
				p.y = 2;
			}
			
			// 더 이상 이동할 칸이 없는 경우
			if (map[p.x][p.y] == Integer.MIN_VALUE) {
				p = null;
				return p;
			}
		}
		
		while (map[p.x][p.y] != 1) {
			p.y += 2;
			
			// 체스판을 벗어나는 경우
			if (p.y > N) {
				p.x++;
				if (p.x % 2 == 0) {
					p.y = 1;
				} else {
					p.y = 2;
				}
				
				// 더 이상 이동할 칸이 없는 경우
				if (map[p.x][p.y] == Integer.MIN_VALUE) {
					p = null;
					break;
				}
			}
		}
		
		return p;
	}
	
	public static void calculateBlackMaxResult(Position cur, int count) {
		// 더 이상 놓을 칸이 없는 경우
		if (cur == null) {
			maxBlackResult = Math.max(maxBlackResult, count);
			return;
		}
		
		// 비숍 놓지 않고 다음 넘기기
		calculateBlackMaxResult(findBlackPosition(new Position(cur.x, cur.y + 2)), count);
		
		// 해당 위치에 비숍 놓기
		map[cur.x][cur.y]--;
		int nextX, nextY;
		for (int direction = 0; direction < dx.length; direction++) {
			nextX = cur.x + dx[direction];
			nextY = cur.y + dy[direction];
			while (map[nextX][nextY] != Integer.MIN_VALUE) {
				map[nextX][nextY]--;
				
				// 다음 위치로 이동
				nextX += dx[direction];
				nextY += dy[direction];
			}
		}

		calculateBlackMaxResult(findBlackPosition(cur), count + 1);
		
		// 해당 위치에 비숍 빼기
		map[cur.x][cur.y]++;
		for (int direction = 0; direction < dx.length; direction++) {
			nextX = cur.x + dx[direction];
			nextY = cur.y + dy[direction];
			while (map[nextX][nextY] != Integer.MIN_VALUE) {
				map[nextX][nextY]++;
				
				// 다음 위치로 이동
				nextX += dx[direction];
				nextY += dy[direction];
			}
		}
	}
	
	public static void calculateWhiteMaxResult(Position cur, int count) {
		// 더 이상 놓을 칸이 없는 경우
		if (cur == null) {
			maxWhiteResult = Math.max(maxWhiteResult, count);
			return;
		}
		
		// 비숍 놓지 않고 다음 넘기기
		calculateWhiteMaxResult(findWhitePosition(new Position(cur.x, cur.y + 2)), count);
		
		// 해당 위치에 비숍 놓기
		map[cur.x][cur.y]--;
		int nextX, nextY;
		for (int direction = 0; direction < dx.length; direction++) {
			nextX = cur.x + dx[direction];
			nextY = cur.y + dy[direction];
			while (map[nextX][nextY] != Integer.MIN_VALUE) {
				map[nextX][nextY]--;
				
				// 다음 위치로 이동
				nextX += dx[direction];
				nextY += dy[direction];
			}
		}

		calculateWhiteMaxResult(findWhitePosition(cur), count + 1);
		
		// 해당 위치에 비숍 빼기
		map[cur.x][cur.y]++;
		for (int direction = 0; direction < dx.length; direction++) {
			nextX = cur.x + dx[direction];
			nextY = cur.y + dy[direction];
			while (map[nextX][nextY] != Integer.MIN_VALUE) {
				map[nextX][nextY]++;
				
				// 다음 위치로 이동
				nextX += dx[direction];
				nextY += dy[direction];
			}
		}
	}

}
