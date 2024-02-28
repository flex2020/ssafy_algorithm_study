package boj;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main19237 {
	private static int N, M, K, answer;
	private static int[][] map, smellMap;
	private static Shark[] sharks;
	private static boolean[] isAlive;
	private static int[][][] pd; // x번째 상어가 y방향일때 갖는 우선순위
	private static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, -1, 1};
	private static List<Point> nextPoints;
	private static final int SMELL_ZERO = 10000;
	
	// 1: 위 2: 아래 3: 왼쪽 4: 오른쪽
	static class Shark {
		int x, y, d;
		
		public Shark(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M, K 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 지도 및 상어 입력
		sharks = new Shark[M+1];
		isAlive = new boolean[M+1];
		Arrays.fill(isAlive, true);
		map = new int[N][N];
		smellMap = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) continue;
				sharks[map[i][j]] = new Shark(i, j, 0);
			}
		}
		// 상어의 첫 방향 입력
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=M; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken());
		}
		// 상어의 방향 우선순위 입력
		pd = new int[M+1][5][5];
		for (int i=1; i<=M; i++) {
			for (int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k=1; k<=4; k++) {
					pd[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		simulate();
		System.out.println(answer);
	}
	
	private static void simulate() {
		// 처음 자신의 냄새를 뿌린다. (냄새 상어id * 10000 + K)
		spreadSmell();
		while (answer != 1001) {
			// 1번 상어만 남았는지 확인
			if (check()) break;
			// 상어의 이동방향 결정
			markingToMove();
			
			// 상어 이동
			move();
		
			// 상어가 이동할때마다 냄새 -1
			decreaseSmell();
			answer += 1;
		}
		if (answer == 1001) answer = -1;
	}
	
	private static boolean check() {
		for (int i=2; i<=M; i++) {
			if (isAlive[i]) return false;
		}
		return true;
	}
	
	private static void spreadSmell() {
		for (int i=1; i<=M; i++) {
			int x = sharks[i].x;
			int y = sharks[i].y;
			smellMap[x][y] = i * SMELL_ZERO + K;
		}
	}
	
	private static void decreaseSmell() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 냄새가 아니라면 건너뛴다
				if (smellMap[i][j] == 0) continue;
				smellMap[i][j] -= 1;
				if (smellMap[i][j] % SMELL_ZERO == 0) smellMap[i][j] = 0;
			}
		}
	}
	
	private static void markingToMove() {
		nextPoints = new ArrayList<>();
		nextPoints.add(new Point(0, 0)); // 0번 인덱스 더미값
		for (int i=1; i<=M; i++) {
			// 상어가 죽었다면 더미값 넣어주고 다음으로
			if (!isAlive[i]) {
				nextPoints.add(new Point(0, 0));
				continue;
			}
			Shark s = sharks[i];
			Point nextPoint = null;
			// 1. 인접한 칸 중 아무 냄새가 없는 칸의 방향
			for (int idx=1; idx<=4; idx++) {
				int d = pd[i][s.d][idx]; // i번째 상어가 바라보는 방향의 idx방향
				int nx = s.x + dx[d];
				int ny = s.y + dy[d];
				// 지도 밖인지, 아무 냄새가 없는지 확인
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || smellMap[nx][ny] != 0) continue;
				s.d = d; // 상어의 방향 설정
				nextPoint = new Point(nx, ny); // 다음 장소 설정 후 루프 탈출
				break;
			}
			// 다음 방향이 설정되지 않았다면 2번 진행
			if (nextPoint == null) {
				// 2. 다음 방향이 설정되지 않았다면 자신의 냄새가 있는 칸으로 이동한다.
				for (int idx=1; idx<=4; idx++) {
					int d = pd[i][s.d][idx]; // i번째 상어가 바라보는 방향의 idx방향
					int nx = s.x + dx[d];
					int ny = s.y + dy[d];
					// 지도 밖인지, 자신의 냄새가 맞는지
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || smellMap[nx][ny] / SMELL_ZERO != i) continue;
					s.d = d; // 상어의 방향 설정
					nextPoint = new Point(nx, ny); // 다음 장소 설정 후 루프 탈출
					break;
				}
			}
			// 이동하지 못한다면 그대로 있는다
			if (nextPoint == null) nextPoint = new Point(s.x, s.y); 
			// 3. 상어를 이동리스트에 담는다.
			nextPoints.add(nextPoint);
		}
	}
	
	private static void move() {
		for (int i=1; i<=M; i++) {
			// 상어가 죽었다면 다음으로
			if (!isAlive[i]) continue;
			Shark s = sharks[i];
			Point p = nextPoints.get(i);
			// 자신보다 더 작은 상어가 있다면 이동하지 않고 맵에서 이탈한다
			if (map[p.x][p.y] != 0 && map[s.x][s.y] > map[p.x][p.y]) {
				map[s.x][s.y] = 0;
				isAlive[i] = false;
				continue;
			}
			// 갈 수 있다면 상어 이동 후 냄새 설정
			map[s.x][s.y] = 0;
			s.x = p.x;
			s.y = p.y;
			map[s.x][s.y] = i;
			smellMap[s.x][s.y] = i * SMELL_ZERO + K + 1;
		}
	}
}
