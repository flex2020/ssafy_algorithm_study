package boj;

import java.io.*;
import java.util.*;

public class Main11559 {
	private static int N = 12, M = 6, answer;
	private static char[][] field;
	private static boolean[][] visited;
	private static List<Puyo> marking, tempMarking;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	
	static class Puyo {
		int x, y;
		char color;

		public Puyo(int x, int y, char color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}

		@Override
		public String toString() {
			return "Puyo [x=" + x + ", y=" + y + ", color=" + color + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		field = new char[N][M];
		for (int i=0; i<N; i++) {
			field[i] = br.readLine().toCharArray();
		}
		
		simulate();
		System.out.println(answer);
	}
	
	private static void simulate() {
		while (true) {
			// 현재 터질 수 있는 것 마킹
			mark();
			// 마킹된게 없다면 중단
			if (marking.isEmpty()) break;
			// 마킹된 것을 터트림
			boom();
			
			// 빈공간을 채움
			down();
			answer += 1; // 연쇄 증가
		}
	}
	
	private static void boom() {
		// marking 된 좌표들을 터트린다.
		for (Puyo p : marking) {
			field[p.x][p.y] = '.';
		}
	}
	
	private static void mark() {
		marking = new ArrayList<>();
		visited = new boolean[N][M];
		// 모든 뿌요를 탐색하며 터질 수 있다면 marking에 넣는다
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (field[i][j] != '.') {
					tempMarking = new ArrayList<>();
					dfs(i, j, field[i][j]);
					if (tempMarking.size() < 4) continue;
					marking.addAll(tempMarking);
				}
			}
		}
	}
	
	private static void dfs(int x, int y, char color) {
		visited[x][y] = true;
		tempMarking.add(new Puyo(x, y, color));
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || field[nx][ny] != color) continue;
			dfs(nx, ny, color);
		}
	}
	
	private static void down() {
		for (int j=0; j<M; j++) {
			List<Character> list = new ArrayList<>();
			for (int i=0; i<N; i++) {
				if (field[i][j] == '.') list.add(field[i][j]);
			}
			for (int i=0; i<N; i++) {
				if (field[i][j] != '.') list.add(field[i][j]);
			}
			for (int i=0; i<N; i++) {
				char c = list.get(i);
				field[i][j] = c;
			}
		}
	}
}
