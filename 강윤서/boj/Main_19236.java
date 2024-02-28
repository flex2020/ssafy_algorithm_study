package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_19236 {
	static int N=4, answer;
	static int[][][] board;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	static PriorityQueue<Fish> moveFish;
	static class Shark {
		int r, c, dir, sum;
		Shark (int r, int c, int dir, int sum) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.sum = sum;
		}
	}
	static class Fish {
		int r, c, number, dir;
		Fish(int r, int c, int number, int dir) {
			this.r = r;
			this.c = c;
			this.number = number;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + ", number=" + number + ", dir=" + dir + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		board = new int[N][N][2];
		moveFish = new PriorityQueue<>((p1, p2) -> p1.number - p2.number);
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				int number = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				board[i][j] = new int[] {number, dir-1};
			}
		}
		moveShark(0, 0, board, board[0][0][0], new ArrayDeque<Fish>()); // (0,0)에 있는 물고기 잡아먹고 시작
		System.out.println(answer);
	}
	public static void moveShark(int sR, int sC, int[][][] board, int result, Queue<Fish> canEat) {
		// 배열 세팅
		int[][][] map = new int[N][N][2];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				for (int k=0; k<2; k++) {
					map[i][j][k] = board[i][j][k];
				}
			}
		}
		
		// 상어 초기세팅
		Shark shark = new Shark(sR, sC, map[sR][sC][1], result);
		map[sR][sC][0] = -1;
		
		answer = Math.max(answer, result);

		// 0. 물고기 위치와 방향 파악
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j][0] > 0) {
					moveFish.offer(new Fish(i, j, map[i][j][0], map[i][j][1]));
				}
			}
		}
		
		// 1. 물고기 이동하기 (번호 오름차순으로 -> PQ에서 빼서)
		while (!moveFish.isEmpty()) {
			Fish cur = moveFish.poll();
			if (map[cur.r][cur.c][0] != cur.number) { // 자기 이동 턴 오기 전에 이미 이동돼버림
				L: for (int i=0; i<N; i++) { // 이동된 위치 찾기
					for (int j=0; j<N; j++) {
						if (map[i][j][0] == cur.number) {
							cur.r = i;
							cur.c = j;
							break L;
						}
					}
				}
			}
	
			for (int i=0; i<8; i++) { // 이동할 수 있는지 확인
				int nr = cur.r + dr[(cur.dir+i)%8];
				int nc = cur.c + dc[(cur.dir+i)%8];
				if (0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc][0] != -1) {
					int[] temp = map[nr][nc];
					map[nr][nc] = new int[] {cur.number, (cur.dir+i)%8};
					map[cur.r][cur.c] =  temp;
					break;
				}
			}
		}
		
		// 2. 상어가 먹을 수 있는 물고기 파악 -> 상어가 움직일 수 있는 방향으로 범위를 벗어날 때까지 확인
		for (int i=1; i<N; i++) {
			int nr = shark.r + dr[shark.dir] * i;
			int nc = shark.c + dc[shark.dir] * i;
			if (0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc][0] > 0) {
				canEat.offer(new Fish(nr, nc, map[nr][nc][0], map[nr][nc][1]));
			}
		}
		
		// 3. 먹을 물고기가 없으면 종료
		if (canEat.isEmpty()) {
			return ;
		}
		
		// 4. 먹을 수 있으면 냠냠하고 이동
		while (!canEat.isEmpty()) {
			Fish f = canEat.poll();
			map[shark.r][shark.c][0] = 0; // 상어 이동 전에 빈칸 만들기
			moveShark(f.r, f.c, map, result + f.number, new ArrayDeque<Fish>());
		}
	}
}
