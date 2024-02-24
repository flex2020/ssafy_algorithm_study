import java.util.*;
import java.io.*;

class Position {
	int x;
	int y;
	int direction;
	
	Position(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}

public class SolutionBaekJoon17070 {
	
	static int N;
	static int[][] map;
	static boolean[][] isVisited;
	// 오른쪽(가로), 아래(세로), 오른쪽 아래 대각선
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 0, 1};
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 지도 정보 입력받기
		map = new int[N + 2][N + 2];
		Arrays.fill(map[0], 1);
		Arrays.fill(map[map.length - 1], 1);
		StringTokenizer st;
		for (int i = 1; i < map.length - 1; i++) {
			map[i][0] = 1;
			map[i][map[0].length - 1] = 1;
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < map[0].length - 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 파이프 옮기는 경우의 수 구하기
		count = 0;
		isVisited = new boolean[map.length][map[0].length];
		calculateCount();
		
		System.out.println(count);
	}
	
	public static void calculateCount() {
		Queue<Position> queue = new ArrayDeque<>();
		// 시작점
		isVisited[1][2] = true;
		queue.offer(new Position(1, 2, 0));
		
		while (!queue.isEmpty()) {
			Position current = queue.poll();
			
			boolean isRightDown = true;
			for (int index = 0; index < dx.length; index++) {				
				int nextX = current.x + dx[index];
				int nextY = current.y + dy[index];
				
				switch (current.direction) {
				case 0:
					if (map[nextX][nextY] == 1) {
						isRightDown = false;
					} else {
						if (index == 0 || (index == 2 && isRightDown)) {
							if (nextX == N && nextY == N) {
								count++;
							} else {
								queue.offer(new Position(nextX, nextY, index));
							}
						}
					}
					break;
				case 1:
					if (map[nextX][nextY] == 1) {
						isRightDown = false;
					} else {
						if (index == 1 || (index == 2 && isRightDown)) {
							if (nextX == N && nextY == N) {
								count++;
							} else {
								queue.offer(new Position(nextX, nextY, index));
							}
						}
					}
					break;
				case 2:
					if (map[nextX][nextY] == 1) {
						isRightDown = false;
					} else {
						if (index < 2 || (index == 2 && isRightDown)) {
							if (nextX == N && nextY == N) {
								count++;
							} else {
								queue.offer(new Position(nextX, nextY, index));
							}
						}
					}
					break;
				}
			}
		}
	}

}
