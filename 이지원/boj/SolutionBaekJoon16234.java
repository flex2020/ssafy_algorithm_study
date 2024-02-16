import java.util.*;
import java.io.*;

class Position {
	int x;
	int y;
	
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class SolutionBaekJoon16234 {
	
	static int L, R;
	static int[][] map;
	static int[][] unions;
	static int number;
	static boolean isExistedUnion;
	static int moveDayCount;
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 지도 정보 입력받기
		map = new int[N + 2][N + 2];
		for (int i = 1; i < map.length - 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < map[0].length - 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 연합 국가 인구 이동하기
		moveDayCount = 0;
		checkUnions();
		
		System.out.println(moveDayCount);
	}
	
	public static void checkUnions() {
		while (true) {
			// 연합 국가 확인하기
			unions = new int[map.length][map[0].length];
			number = 1;
			isExistedUnion = false;
			for (int i = 1; i < map.length - 1; i++) {
				for (int j = 1; j < map[0].length - 1; j++) {
					if (unions[i][j] == 0) {
						checkUnion(new Position(i, j));
						number++;
					}
				}
			}
			
			if (!isExistedUnion) {  // 연합 국가가 존재하지 않는 경우
				return;
			}
			
			// 연합 국가 인구 수 계산 후 적용하기
			calculateUnionPeopleCount();
			
			moveDayCount++;
		}
	}
	
	// 연합 국가 확인하기
	public static void checkUnion(Position position) {
		Queue<Position> queue = new ArrayDeque<>();
		
		// 시작점
		unions[position.x][position.y] = number;
		queue.offer(position);
		
		while (!queue.isEmpty()) {
			Position current = queue.poll();
			
			for (int index = 0; index < dx.length; index++) {
				int nextX = current.x + dx[index];
				int nextY = current.y + dy[index];
				
				int difference = Math.abs(map[current.x][current.y] - map[nextX][nextY]);
				if (map[nextX][nextY] != 0 && unions[nextX][nextY] == 0 && difference >= L && difference <= R) {
					isExistedUnion = true;
					unions[nextX][nextY] = number;
					queue.offer(new Position(nextX, nextY));
				}
			}
		}
	}
	
	// 연합 국가 인구 수 계산 후 적용하기
	public static void calculateUnionPeopleCount() {
		int[] nationCounts = new int[number];
		int[] peopleCounts = new int[number];
		// 연합 국가별 국가 수 및 총 인구 수 계산하기
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[0].length - 1; j++) {
				nationCounts[unions[i][j]]++;
				peopleCounts[unions[i][j]] += map[i][j];
			}
		}
		
		// 연합 국가별 인구 수 계산하기
		for (int i = 1; i < nationCounts.length; i++) {
			peopleCounts[i] /= nationCounts[i];
		}
		
		// 연합 국가별 인구 수 적용하기
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[0].length - 1; j++) {
				map[i][j] = peopleCounts[unions[i][j]];
			}
		}
	}

}
