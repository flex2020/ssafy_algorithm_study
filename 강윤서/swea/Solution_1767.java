package 강윤서.boj;

import java.util.*;
import java.io.*;
public class Solution_1767 {
	static int T, N;
	static int[][] board;
	static List<Position> Processor;
	static PriorityQueue<Result> answer;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static class Position {
		int r, c;
		Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class Result {
		int cnt, dist; // cnt: 연결된 코어 개수, dist: 전선 길이의 합
		Result (int cnt, int dist) {
			this.cnt = cnt;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			Processor = new ArrayList<>();
			answer = new PriorityQueue<>((a1, a2) -> a1.cnt == a2.cnt ? a1.dist - a2.dist : a2.cnt - a1.cnt);
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 1 && (0<i && i<N-1 && 0<j && j<N-1)) { // 가장자리에 있지 않은 프로세서
						Processor.add(new Position(i, j));
					}
				}
			}
			dfs(0, 0, 0, board);
			System.out.println("#" + tc + " " + answer.peek().dist);
		}
	}
	// cnt: 연결된 core 개수, idx: 현재 검사하고 있는 core, dist: 연결한 전선 수
	public static void dfs(int cnt, int idx, int dist, int[][] board) {
		if (idx == Processor.size()) {
			answer.offer(new Result(cnt, dist));
			return ;
		}
		if (!answer.isEmpty() && answer.peek().cnt > Processor.size() - idx + cnt) {
			return ;
		}
		// 배열 복사
		int[][] map = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = board[i][j];
			}
		}
		
		// 해당 인덱스의 프로세서 가져오기
		Position cur = Processor.get(idx);
		
		// 가장자리까지 이동할 수 있는지 확인
		for (int i=0; i<4; i++) {
			boolean connected = false;
			for (int d=1; d<=N; d++) {
				int nr = cur.r + dr[i]*d;
				int nc = cur.c + dc[i]*d;
				if (nr<0 || nr>=N || nc<0 || nc>=N) { // 가장자리까지 전선 연결
					connected = true;
					break;
				}
				if (0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc] != 0) { // 더 나갈 수 없음
					break;
				}
			}
			if (connected) {
				// 전선 연결
				int d = 1;
				for (d=1; d<=N; d++) {
					int nr = cur.r + dr[i]*d;
					int nc = cur.c + dc[i]*d;
					if (nr<0 || nr>=N || nc<0 || nc>=N) { // 가장자리까지 전선 연결
						break;
					}
					map[nr][nc] = 2;
				}
				dfs(cnt+1, idx+1, dist+d-1, map);
				// 연결한 전선 해제
				for (d=1; d<=N; d++) {
					int nr = cur.r + dr[i]*d;
					int nc = cur.c + dc[i]*d;
					if (nr<0 || nr>=N || nc<0 || nc>=N) {
						break;
					}
					map[nr][nc] = 0;
				}
			} else {
				dfs(cnt, idx+1, dist, map);
			}
		}
	}
}
