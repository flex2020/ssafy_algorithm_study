import java.util.*;
import java.io.*;

public class 나무박멸 {
	
	static class Ground {
		int count;  // -2: 제초제, -1: 벽, 0: 빈칸
		int addCount;  // 확산된 나무 그루 수
		int time;  // 제초제 뿌린 후 시간
		
		Ground(int count) {
			this.count = count;
		}
		
		void add() {
			count += addCount;
			addCount = 0;
		}
	}
	
	static class Position {
		int x;
		int y;
		int removeCount;  // 박멸되는 나무 그루 수
		
		Position(int x, int y, int removeCount) {
			this.x = x;
			this.y = y;
			this.removeCount = removeCount;
		}
	}

	static int M, K, C;
	static Ground[][] map;
	static Position maxPosition;
	static int result;
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	// 위 왼쪽/오른쪽 대각선, 아래 왼쪽/오른쪽 대각선
	static int[] crossDx = {-1, -1, 1, 1};
	static int[] crossDy = {-1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 지도 정보 입력받기
		map = new Ground[N + 2][N + 2];
		Arrays.fill(map[0], new Ground(-1));
		Arrays.fill(map[map.length - 1], new Ground(-1));
		for (int i = 1; i < map.length - 1; i++) {
			map[i][0] = new Ground(-1);
			map[i][map[0].length - 1] = new Ground(-1);
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < map[0].length - 1; j++) {
				map[i][j] = new Ground(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 박멸한 나무의 그루 수 구하기
		simulation();
		
		System.out.println(result);
	}
	
	public static void simulation() {
		result = 0;
		
		while (M > 0) {
			// 나무 성장하기
			growTree();
			
			// 나무 확산하기
			spreadTree();
			
			// 제초제 뿌릴 장소 선정하기
			choosePosition();
			
			// 제초제 뿌리기
			if (maxPosition.x != 0 || maxPosition.y != 0) {
				spray();
			}
			
			// 시간 줄이기
			M--;
		}
	}
	
	public static void growTree() {
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[0].length - 1; j++) {
				// 나무가 있는 경우
				if (map[i][j].count > 0) {
					// 나무 성장하기
					int adjacentCount = 0;
					int blankCount = 0;
					for (int direction = 0; direction < dx.length; direction++) {
						int nextX = i + dx[direction];
						int nextY = j + dy[direction];
						
						if (map[nextX][nextY].count > 0) {
							adjacentCount++;
						} else if (map[nextX][nextY].count == 0) {
							blankCount++;
						}
					}
					map[i][j].count += adjacentCount;
					
					// 나무 확산할 그루 수 계산하기
					if (blankCount > 0) {
						int spreadedTreeCount = map[i][j].count / blankCount;
						for (int direction = 0; direction < dx.length; direction++) {
							int nextX = i + dx[direction];
							int nextY = j + dy[direction];
							
							if (map[nextX][nextY].count == 0) {
								map[nextX][nextY].addCount += spreadedTreeCount;
							}
						}
					}
				}
			}
		}
	}
	
	public static void spreadTree() {
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[0].length - 1; j++) {
				if (map[i][j].addCount > 0) {
					map[i][j].add();
				}
			}
		}
	}
	
	public static void choosePosition() {
		maxPosition = new Position(0, 0, Integer.MIN_VALUE);
		
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[0].length - 1; j++) {
				if (map[i][j].count > 0) {  // 나무가 있는 경우
					// 박멸할 수 있는 나무 그루 수 계산하기
					int removeCount = map[i][j].count;
					
					for (int direction = 0; direction < crossDx.length; direction++) {
						int nextX = i;
						int nextY = j;
						for (int k = 0; k < K; k++) {
							nextX += crossDx[direction];
							nextY += crossDy[direction];
							
							if (map[nextX][nextY].count > 0) {
								removeCount += map[nextX][nextY].count;
							} else {
								break;
							}
						}
					}
					
					// 최대로 나무를 박멸할 수 있는 위치 갱신하기
					if (maxPosition.removeCount < removeCount) {
						maxPosition.x = i;
						maxPosition.y = j;
						maxPosition.removeCount = removeCount;
					}
				} else if (map[i][j].count == -2) {  // 이미 제초제를 뿌린 경우
					map[i][j].time++;
					
					// 제초제 남아있는 시간을 지난 경우
					if (map[i][j].time >= C) {
						map[i][j].count = 0;
						map[i][j].time = 0;
					}
				}
			}
		}
	}
	
	public static void spray() {
		// 박멸한 나무 그루 수 더하기
		result += maxPosition.removeCount;
		
		// 제초제 뿌릴 기준점
		map[maxPosition.x][maxPosition.y].count = -2;
		
		// 제초제 뿌릴 대각선
		for (int direction = 0; direction < crossDx.length; direction++) {
			int nextX = maxPosition.x;
			int nextY = maxPosition.y;
			
			for (int k = 0; k < K; k++) {
				nextX += crossDx[direction];
				nextY += crossDy[direction];
				
				if (map[nextX][nextY].count > 0) {  // 나무가 있는 경우
					map[nextX][nextY].count = -2;
				} else if (map[nextX][nextY].count == 0) {  // 빈칸인 경우
					map[nextX][nextY].count = -2;
					break;
				} else if (map[nextX][nextY].count == -2) {  // 이미 제초제가 뿌려져 있는 경우
					map[nextX][nextY].time = 0;
					break;
				} else {
					break;
				}
			}
		}
	}

}
