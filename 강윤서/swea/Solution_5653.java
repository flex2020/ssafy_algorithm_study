package 강윤서.swea;

import java.io.*;
import java.util.*;

class Cell {
	int r, c, activateTime, life;
	// activateTime : 활성화 되기까지 남은 시간 (음수: 활성화 되고 지난 시간)
	// life : 생명력
	Cell (int r, int c, int activateTime, int life) {
		this.r = r;
		this.c = c;
		this.activateTime = activateTime;
		this.life = life;
	}
}
public class Solution_5653 {
	static int T, N, M, K, curTime;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static PriorityQueue<Cell> PQ;
    static Queue<Cell> Q; // PQ에 들어가기를 기다리는 저장소
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			curTime = 0; // 시간 초기화
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[N][M];
			visited = new boolean[N+2*K][M+2*K];
			PQ = new PriorityQueue<>((p1, p2) -> p2.life - p1.life); // 생명력 기준 내림차순 정렬 (초기번식이 항상 생명력 높음 = 덮어질 일 없음)
            Q = new ArrayDeque<>();
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] > 0) {
                        visited[K+i][K+j] = true;
						PQ.offer(new Cell(K+i, K+j, board[i][j], board[i][j]));
					}
				}
			}
			while (curTime++ < K) {
                while (!PQ.isEmpty()) {
                    Cell cur = PQ.poll();
                    cur.activateTime--;
                    if (cur.activateTime < 0) { // 활성화 될 차례
                        for (int i=0; i<4; i++) {
                            int nr = cur.r + dr[i];
                            int nc = cur.c + dc[i];
                            if (!visited[nr][nc]) {
                                visited[nr][nc] = true;
                                Q.offer(new Cell(nr, nc, cur.life, cur.life));
                            }
                        }
                    }
                    if (cur.activateTime + cur.life == 0) { // 활성화 기간 끝 -> 세포 죽이기
                        continue;
                    }
                    Q.offer(cur);
                }
                while (!Q.isEmpty()) PQ.offer(Q.poll()); // PQ에 옮겨 담기
			}
			System.out.printf("#%d %d\n", tc, PQ.size());
		}
	}
}