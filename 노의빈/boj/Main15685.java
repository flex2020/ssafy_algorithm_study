package boj;

import java.io.*;
import java.util.*;

public class Main15685 {
	private static int N, answer;
	private static DragonCurve[] dc;
	private static boolean[][] map;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
	
	static class DragonCurve {
		int x, y, d, g;

		public DragonCurve(int x, int y, int d, int g) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		dc = new DragonCurve[N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			dc[i] = new DragonCurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		marking();
		
		//display();
		counting();
		System.out.println(answer);
	}
	
	private static void marking() {
		for (DragonCurve c : dc) {
			// 방향들을 담는 리스트
			List<Integer> dir = new ArrayList<>();
			dir.add(c.d);
			// g세대만큼 반복
			for (int i=0; i<c.g; i++) {
				int lsize = dir.size();
				// 현재 세대 그리기
				for (int j=lsize-1; j>=0; j--) {
					dir.add((dir.get(j) + 1) % 4);
				}
			}
			int nx = c.x;
			int ny = c.y;
			map[nx][ny] = true;
			// 담은 방향들로 이동시키면서 마킹하기
			for (int i=0; i<dir.size(); i++) {
				nx += dx[dir.get(i)];
				ny += dy[dir.get(i)];
				map[nx][ny] = true;
			}
		}
	}
	
	
	private static void counting() {
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) answer += 1;
			}
		}
	}
	
	private static void display() {
		for (int j=0; j<30; j++) {
			for (int i=0; i<30; i++) {
				System.out.print(map[i][j] ? 1 + " " : 0 + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
