package swea;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Solution1953 {
	private static int N, M, R, C, L, answer;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			answer = 0;
			map = new int[N][M];
			visited = new boolean[N][M];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	private static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(R, C));
		visited[R][C] = true;
		int time = 1;
		while (!q.isEmpty()) {
			int qsize = q.size();
			// 시간 지나면 루프 탈출
			if (time == L) break;
			// 레벨 별로 뽑아내기
			for (int abc=0; abc<qsize; abc++) {
				Point p = q.poll();
				// 현재 파이프와 다음 파이프가 연결되어 있을 때 이동할 수 있다.
				for (int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 0 || !connected(map[p.x][p.y], map[nx][ny], i)) continue;
					q.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
				
			}
			time += 1;
		}
		// 방문할 수 있는 곳 모두 더하기
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (visited[i][j]) answer += 1;
			}
		}
	}

	private static boolean connected(int pipe1, int pipe2, int dir) {
		// dir: 오른쪽 아래 왼쪽 위 순서
		// 오른쪽과 연결되려면 아래 조건 만족하기
		if (dir == 0) {
			if (pipe1 == 1 || pipe1 == 3 || pipe1 == 4 || pipe1 == 5) {
				return pipe2 == 1 || pipe2 == 3 || pipe2 == 6 || pipe2 == 7;
			}
		}
		// 아래와 연결되려면 아래 조건 만족하기
		else if (dir == 1) {
			if (pipe1 == 1 || pipe1 == 2 || pipe1 == 5 || pipe1 == 6) {
				return pipe2 == 1 || pipe2 == 2 || pipe2 == 4 || pipe2 == 7;
			}
		}
		// 왼쪽과 연결되려면 아래 조건 만족하기
		else if (dir == 2) {
			if (pipe1 == 1 || pipe1 == 3 || pipe1 == 6 || pipe1 == 7) {
				return pipe2 == 1 || pipe2 == 3 || pipe2 == 4 || pipe2 == 5;
			}
		}
		// 위쪽과 연결되려면 아래 조건 만족하기
		else if (dir == 3) {
			if (pipe1 == 1 || pipe1 == 2 || pipe1 == 4 || pipe1 == 7) {
				return pipe2 == 1 || pipe2 == 2 || pipe2 == 5 || pipe2 == 6;
			}
		}
		return false;
	}
}
