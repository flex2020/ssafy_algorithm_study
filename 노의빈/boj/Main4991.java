package boj;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main4991 {
	private static int w, h, startX, startY, answer, tempStartX, tempStartY;
	private static List<Point> endPoints;
	private static List<Integer> order;
	private static boolean[] sel;
	private static char[][] map;
	private static int[][] memo;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if ((w | h) == 0) break;
			answer = Integer.MAX_VALUE;
			endPoints = new ArrayList<>();
			order = new ArrayList<>();
			map = new char[h][w];
			sel = new boolean[10];
			memo = new int[11][11];
			for (int i=0; i<h; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j=0; j<w; j++) {
					map[i][j] = input[j];
					if (map[i][j] == 'o') {
						startX = i;
						startY = j;
					} else if (map[i][j] == '*') {
						endPoints.add(new Point(i, j));
					}
				}
			}
			permutation(0);
			System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		}
	}

	private static void permutation(int idx) {
		if (idx == endPoints.size()) {
			int result = simulate();
			if (result == -1) return;
			answer = Math.min(answer, result);
			return;
		}
		
		for (int i=0; i<endPoints.size(); i++) {
			if (sel[i]) continue;
			sel[i] = true;
			order.add(i);
			permutation(idx + 1);
			order.remove(idx);
			sel[i] = false;
		}
	}
	
	private static int simulate() {
		int sum = 0;
		int cur = 0;
		tempStartX = startX;
		tempStartY = startY;
		for (int i : order) {
			Point p = endPoints.get(i);
			int result = 0;
			if (memo[cur][i+1] != 0) {
				result = memo[cur][i+1];
			}
			else {
				result = bfs(p.x, p.y);
				memo[cur][i+1] = result;
			}
			if (result == -1) return -1;
			tempStartX = p.x;
			tempStartY = p.y;
			sum += result;
			cur = i + 1;
		}
		return sum;
	}
	
	private static int bfs(int endX, int endY) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(tempStartX, tempStartY));
		boolean[][] visited = new boolean[h][w];
		visited[tempStartX][tempStartY] = true;
		int count = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();
			for (int i=0; i<qsize; i++) {
				Point p = q.poll();
				if (p.x == endX && p.y == endY) {
					return count;
				}
				
				for (int d=0; d<4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					
					if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == 'x' || visited[nx][ny]) continue;
					q.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
			count += 1;
		}
		return -1;
	}
}
