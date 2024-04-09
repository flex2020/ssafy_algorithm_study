package boj;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main16946 {
	private static int N, M, size;
	private static int[][] map, group, answer;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	private static int[] countArr;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		answer = new int[N][M];
		group = new int[N][M];
		countArr = new int[N*M+2];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		// 벽이 아닌 공간의 크기 설정
		setEmptyArea();
		setAnswerMap();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				sb.append(answer[i][j] % 10);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void setEmptyArea() {
		int groupId = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 1 || group[i][j] != 0) continue;
				bfs(i, j, groupId);
				countArr[groupId++] = size;
			}
		}
	}
	private static void bfs(int sx, int sy, int groupId) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(sx, sy));
		group[sx][sy] = groupId;
		size = 0;
		while (!q.isEmpty()) {
			Point p = q.poll();
			size += 1;
			for (int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 1 || group[nx][ny] != 0) continue;
				q.offer(new Point(nx, ny));
				group[nx][ny] = groupId;
			}
		}
	}
	
	private static void setAnswerMap() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 0) continue;
				answer[i][j] = 1;
				Set<Integer> check = new HashSet<>();
				for (int d=0; d<4; d++) {
					int ni = i + dx[d];
					int nj = j + dy[d];
					if (ni < 0 || ni >= N || nj < 0 || nj >= M || check.contains(group[ni][nj])) continue;
					answer[i][j] += countArr[group[ni][nj]];
					check.add(group[ni][nj]);
				}
			}
		}
	}
}
