import java.util.*;
import java.io.*;

public class SolutionBaekJoon16236 {
	
	static class Position implements Comparable<Position> {
		int x;
		int y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Position o) {
			return this.x == o.x ? Integer.compare(this.y, o.y) : Integer.compare(this.x,  o.x);
		}
	}
	
	static int[][] map;
	static Position shark;
	static int sharkSize;
	static int eatCount;
	static int totalTime;
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 지도 정보 입력받기
		map = new int [N + 2][N + 2];
		Arrays.fill(map[0], -1);
		Arrays.fill(map[map.length - 1], -1);
		StringTokenizer st;
		for (int i = 1; i < map.length - 1; i++) {
			map[i][0] = -1;
			map[i][map[0].length - 1] = -1;
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < map[0].length - 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 9) {
					shark = new Position(i, j);
				}
			}
		}
		
		// 물고기 먹기
		sharkSize = 2;
		eatCount = 0;
		totalTime = 0;
		while (doEat());
		
		System.out.println(totalTime);
	}
	
	public static boolean doEat() {
		PriorityQueue<Position> priorityQueue = new PriorityQueue<>();
		
		Queue<Position> queue = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[map.length][map[0].length];
		// 상어
		isVisited[shark.x][shark.y] = true;
		queue.offer(new Position(shark.x, shark.y));
		
		// 물고기 찾기
		int time = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Position current = queue.poll();
				
				for (int direction = 0; direction < dx.length; direction++) {
					int nextX = current.x + dx[direction];
					int nextY = current.y + dy[direction];
					
					// 벽이거나 방문했던 곳인 경우
					if (map[nextX][nextY] == -1 || isVisited[nextX][nextY]) {
						continue;
					}
					
					if (map[nextX][nextY] == 0 || map[nextX][nextY] == sharkSize) {  // 물고기가 없거나 물고기 = 상어인 경우
						isVisited[nextX][nextY] = true;
						queue.offer(new Position(nextX, nextY));
					} else if (map[nextX][nextY] < sharkSize) {  // 물고기 < 상어인 경우
						// 먹을 물고기 후보에 넣기
						priorityQueue.offer(new Position(nextX, nextY));
					}
				}
			}
			
			// 먹을 수 있는 물고기가 있는 경우
			if (!priorityQueue.isEmpty()) {
				Position next = priorityQueue.poll();
				
				// 물고기 먹기
				map[next.x][next.y] = 0;
				eatCount++;
				
				// 상어 이동하기
				map[shark.x][shark.y] = 0;
				shark.x = next.x;
				shark.y = next.y;
				map[shark.x][shark.y] = 9;
				
				// 상어 사이즈 == 먹은 물고기 개수인 경우
				if (sharkSize == eatCount) {
					sharkSize++;
					eatCount = 0;
				}
				
				totalTime += time;
				return true;
			}
			
			time++;  // 시간 증가하기
		}
		
		return false;
	}

}
