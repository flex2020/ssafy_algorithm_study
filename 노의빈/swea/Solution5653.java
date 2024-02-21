package swea;

import java.util.*;
import java.io.*;
import java.awt.Point;

public class Solution5653 {
	private static int N, M, K, answer, startX, startY;
	private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	private static Cell[][] map;
	private static List<Point> divisionList;
	private static final int SIZE = 550;
	
	static class Cell {
		int status; // 0: 임시, 1: 비활성, 2: 활성, 3: 죽음
		int hp; // 생명력
		int val;
		public Cell(int status, int hp, int val) {
			super();
			this.status = status;
			this.hp = hp;
			this.val = val;
		}
		@Override
		public String toString() {
			return "[status=" + status + ", hp=" + hp + ", val=" + val + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new Cell[SIZE][SIZE];
			answer = 0;
			startX = (SIZE-N)/2;
			startY = (SIZE-M)/2;
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					int hp = Integer.parseInt(st.nextToken());
					if (hp != 0) map[startX+i][startY+j] = new Cell(1, hp, hp);
				}
			}
			simulate();
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	private static void simulate() {
		while (K != 0) {
			K -= 1;
			//System.out.println(K);
			// 생명력 1씩 줄이기
			reduceHp();
			
			// 세포 번식하기
			divisionCell();
		}
		reduceHp();
		for (int i=0; i<SIZE; i++) {
			for (int j=0; j<SIZE; j++) {
				if (map[i][j] != null && (map[i][j].status == 1 || map[i][j].status == 2)) {
					answer += 1;
				}
			}
		}
	}
	
	private static void reduceHp() {
		for (int i=0; i<SIZE; i++) {
			for (int j=0; j<SIZE; j++) {
				if (map[i][j] != null && map[i][j].status < 3 && map[i][j].hp > 0) {
					map[i][j].hp -= 1;
					
					// 만약 hp가 0이 되면 다음 상태로 변경
					if (map[i][j].hp == 0) {
						map[i][j].status += 1;
						map[i][j].hp = map[i][j].val + 1;
					}
				}
			}
		}
		//System.out.println("죽은 수 = " + death);
	}
	
	private static void divisionCell() {
		divisionList = new ArrayList<>();
		for (int i=0; i<SIZE; i++) {
			for (int j=0; j<SIZE; j++) {
				// 활성화 상태이고, 첫 1초가 지났다면 번식리스트에 삽입
				if (map[i][j] != null && map[i][j].status == 2 && map[i][j].val == map[i][j].hp) {
					divisionList.add(new Point(i, j));
				}
			}
		}
		division();
	}
	
	private static void division() {
		Set<Point> pset = new HashSet<>();
		for (int i=0; i<divisionList.size(); i++) {
			Point p = divisionList.get(i);
			Cell c = map[p.x][p.y];

			for (int d=0; d<4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				Cell nc = map[nx][ny];
				// 이미 그 위치에 줄기세포가 자리했고 임시 줄기세포가 아니라면 패쓰
				if (nc != null && nc.status != 0) {
					continue;
				}
				// 자리가 비었다면 줄기세포 위치시키기
				if (nc == null) {
					map[nx][ny] = new Cell(0, c.val, c.val);
				}
				// 임시 줄기세포라면, 현재꺼와 val이 더 큰 것으로 설정하기
				else {
					map[nx][ny] = map[nx][ny].val > c.val ? map[nx][ny] : new Cell(0, c.val, c.val);
				}
				
				pset.add(new Point(nx, ny)); // 퍼져나갈 곳 추가
			}
		}
		//System.out.println("분열 수 = " + pset.size());
		// 퍼져나간 곳 임시 -> 비활성화 상태로 변경
		for (Point p : pset) {
			map[p.x][p.y].status = 1;
		}
	}
}
