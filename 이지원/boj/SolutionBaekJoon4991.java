import java.util.*;
import java.io.*;

public class SolutionBaekJoon4991 {
	
	static class Position {
		int x;
		int y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static char[][] map;
	static Position robot;
	static List<Position> dirtyPositions;
	static int dirtyCount;
	static int minResult;
	static int[][] lengths;  // i: from, j: to
	static boolean[] isChoosed;
	static int[] choosedDirty;
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			if (W == 0 && H == 0) {
				break;
			}
			
			// 지도 정보 입력받기
			map = new char[H + 2][W + 2];
			dirtyPositions = new ArrayList<>();
			dirtyCount = 0;
			char[] tmp;
			for (int i = 1; i < map.length - 1; i++) {
				tmp = br.readLine().toCharArray();
				for (int j = 1; j < map[0].length - 1; j++) {
					map[i][j] = tmp[j - 1];
					
					if (map[i][j] == 'o') {
						robot = new Position(i, j);
					} else if (map[i][j] == '*') {
						dirtyCount++;
						dirtyPositions.add(new Position(i, j));
					}
				}
			}
			
			// 각 지점(로봇, 더러운 곳)끼리 거리 구하기
			lengths = new int[dirtyCount + 1][dirtyCount + 1];
			for (int i = 0; i < lengths.length; i++) {
				Arrays.fill(lengths[i], Integer.MAX_VALUE);
			}
			calculateLength();
			
			// 모두 깨끗한 칸으로 바꾸는 이동 횟수의 최솟값 구하기
			minResult = Integer.MAX_VALUE;
			isChoosed = new boolean[dirtyCount];
			choosedDirty = new int[dirtyCount];
			calculateMinResult(0, 0);
			
			if (minResult == Integer.MAX_VALUE) {
				sb.append("-1");
			} else {
				sb.append(minResult);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	// dirtyCount: 로봇 번호
	public static void calculateLength() {
		aa: for (int i = 0; i < dirtyCount; i++) {
			Position dirty = dirtyPositions.get(i);
			
			boolean[][] isVisited = new boolean[map.length][map[0].length];
			Queue<Position> queue = new ArrayDeque<>();
			// 기준이 되는 더러운 곳
			isVisited[dirty.x][dirty.y] = true;
			queue.offer(new Position(dirty.x, dirty.y));
			
			int length = 0;
			int count = 0;
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int c = 0; c < size; c++) {
					Position current = queue.poll();
					
					// 더러운 곳인 경우
					if (map[current.x][current.y] == '*') {  // 더러운 곳인 경우
						// 더러운 곳 인덱스 찾기
						int number = findDirtyNumber(current);
						lengths[i][number] = length;
						
						// 찾은 목적지 개수 늘리기
						count++;
					} else if (map[current.x][current.y] == 'o') {  // 로봇인 경우
						lengths[dirtyCount][i] = length;
						lengths[i][dirtyCount] = length;

						// 찾은 목적지 개수 늘리기
						count++;
					}
					
					// 목적지를 다 찾은 경우
					if (count == dirtyCount + 1) {
						continue aa;
					}
					
					// 사방 둘러보기
					for (int direction = 0; direction < dx.length; direction++) {
						int nextX = current.x + dx[direction];
						int nextY = current.y + dy[direction];
						
						// 지도 밖이거나 벽인 경우
						if (map[nextX][nextY] == 0 || map[nextX][nextY] == 'x') {
							continue;
						}
						
						if (!isVisited[nextX][nextY]) {
							isVisited[nextX][nextY] = true;
							queue.offer(new Position(nextX, nextY));
						}
					}
				}
				
				length++;
			}
		}
	}
	
	public static int findDirtyNumber(Position position) {
		for (int i = 0; i < dirtyCount; i++) {
			Position d = dirtyPositions.get(i);
			if (d.x == position.x && d.y == position.y) {
				return i;
			}
		}
		
		return -1;
	}
	
	// 방문할 더러운 곳 순서 정하기
	public static void calculateMinResult(int count, int index) {
		// 순서를 다 정한 경우
		if (count == dirtyCount) {
			// 더러운 곳을 다 방문하기
			int result = calculateResult();
			minResult = Math.min(minResult, result);
			return;
		}
		
		for (int i = 0; i < dirtyCount; i++) {
			if (!isChoosed[i]) {
				isChoosed[i] = true;
				choosedDirty[index] = i;
				
				calculateMinResult(count + 1, index + 1);
				
				isChoosed[i] = false;
			}
		}
	}
	
	public static int calculateResult() {
		int result = 0;
		
		// 시작점: 로봇
		int to = dirtyCount;
		
		for (int i = 0; i < dirtyCount; i++) {
			int from = choosedDirty[i];
			
			// 해당 지역으로 갈 수 없는 경우
			if (lengths[to][from] == Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			
			result += lengths[to][from];
			to = from;
		}
		
		return result;
	}

}
