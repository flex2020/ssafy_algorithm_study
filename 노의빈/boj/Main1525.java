package boj;

import java.io.*;
import java.util.*;

public class Main1525 {
	private static int answer, startX, startY;
	private static String board;
	private static Map<String, Boolean> visited;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	
	
	static class Puzzle {
		int x, y;
		String board;
		public Puzzle(int x, int y, String board) {
			super();
			this.x = x;
			this.y = y;
			this.board = board;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = -1;
		board = "";
		visited = new TreeMap<>();
		for (int i=0; i<3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) {
				String t = st.nextToken();
				if (t.equals("0")) {
					startX = i;
					startY = j;
				}
				board += t;
			}
		}
		bfs();
		System.out.println(answer);
		
	}
	
	private static void bfs() {
		Queue<Puzzle> q = new ArrayDeque<>();
		q.offer(new Puzzle(startX, startY, board));
		visited.put(board, true);
		
		int count = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();
			
			for (int i=0; i<qsize; i++) {
				Puzzle p = q.poll();
				
				if (p.board.equals("123456780")) {
					answer = count;
					return;
				}
				
				for (int d=0; d<4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					
					if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) continue;
					String nboard = swap(p.board, p.x, p.y, nx, ny);
					if (visited.get(nboard) == null) {
						q.offer(new Puzzle(nx, ny, nboard));
						visited.put(nboard, true);
					}
				}
			}
			count += 1;
		}
	}
	
	private static String swap(String board, int x1, int y1, int x2, int y2) {
		int index1 = x1*3 + y1;
		int index2 = x2*3 + y2;
		if (index1 > index2) {
			int temp = index1;
			index1 = index2;
			index2 = temp;
		}
		
		return board.substring(0, index1) + board.charAt(index2) 
			+ board.substring(index1 + 1, index2) + board.charAt(index1)
			+ board.substring(index2 + 1, 9);
	}
}
