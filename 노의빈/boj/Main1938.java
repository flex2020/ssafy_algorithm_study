package boj;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main1938 {
	private static int N, answer;
	private static char[][] map;
	private static Tree startTree, endTree;
	
	static class Tree {
		int x, y, type; // 중심의 x좌표, y좌표, 가로세로여부

		public Tree(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Tree [x=" + x + ", y=" + y + ", type=" + type + "]";
		}
		
		public boolean move(int d) {
			// 위
			if (d == 0) {
				// 가로로 놓인 경우
				if (type == 0) {
					if (x - 1 < 0) return false; 
					if (map[x-1][y-1] != '0' || map[x-1][y] != '0' || map[x-1][y+1] != '0') return false;
					x -= 1;
				}
				// 세로로 놓인 경우
				else {
					if (x - 2 < 0) return false; 
					if (map[x-2][y] != '0' || map[x-1][y] != '0' || map[x][y] != '0') return false;
					x -= 1;
				}
			}
			// 아래
			else if (d == 1) {
				// 가로로 놓인 경우
				if (type == 0) {
					if (x + 1 >= N) return false; 
					if (map[x+1][y-1] != '0' || map[x+1][y] != '0' || map[x+1][y+1] != '0') return false;
					x += 1;
				}
				// 세로로 놓인 경우
				else {
					if (x + 2 >= N) return false; 
					if (map[x+2][y] != '0' || map[x+1][y] != '0' || map[x][y] != '0') return false;
					x += 1;
				}
			}
			// 왼쪽
			else if (d == 2) {
				// 가로로 놓인 경우
				if (type == 0) {
					if (y - 2 < 0) return false; 
					if (map[x][y-2] != '0' || map[x][y-1] != '0' || map[x][y] != '0') return false;
					y -= 1;
				}
				// 세로로 놓인 경우
				else {
					if (y - 1 < 0) return false; 
					if (map[x-1][y-1] != '0' || map[x][y-1] != '0' || map[x+1][y-1] != '0') return false;
					y -= 1;
				}
			}
			// 오른쪽
			else if (d == 3) {
				// 가로로 놓인 경우
				if (type == 0) {
					if (y + 2 >= N) return false; 
					if (map[x][y+2] != '0' || map[x][y+1] != '0' || map[x][y] != '0') return false;
					y += 1;
				}
				// 세로로 놓인 경우
				else {
					if (y + 1 >= N) return false; 
					if (map[x-1][y+1] != '0' || map[x][y+1] != '0' || map[x+1][y+1] != '0') return false;
					y += 1;
				}
			}
			// 회전
			else if (d == 4) {
				// 가로로 놓인 경우
				if (type == 0) {
					if (x - 1 < 0 || x + 1 >= N) return false;
					if (map[x-1][y-1] != '0' || map[x-1][y] != '0' || map[x-1][y+1] != '0' || 
						map[x+1][y-1] != '0' || map[x+1][y] != '0' || map[x+1][y+1] != '0') return false;
					type = 1;
						
				}
				// 세로로 놓인 경우
				else {
					if (y - 1 < 0 || y + 1 >= N) return false;
					if (map[x-1][y-1] != '0' || map[x][y-1] != '0' || map[x+1][y-1] != '0' || 
						map[x-1][y+1] != '0' || map[x][y+1] != '0' || map[x+1][y+1] != '0') return false;
					type = 0;
				}
			}
			return true;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		List<Point> list = new ArrayList<>();
		int idx1 = 0;
		int idx2 = 0;
		for (int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j=0; j<N; j++) {
				if (map[i][j] == 'B') {
					if (idx1 == 0) {
						idx1 += 1;
					} else if (idx1 == 1) {
						int type = 0; // 가로로 있다고 가정하고
						// 세로로 있는지 확인
						if (i-1 >= 0 && map[i-1][j] == 'B') type = 1;
						startTree = new Tree(i, j, type);
						idx1 += 1;
					}
					list.add(new Point(i, j));
				} else if (map[i][j] == 'E') {
					if (idx2 == 0) {
						idx2 += 1;
					} else if (idx2 == 1) {
						int type = 0; // 가로로 있다고 가정하고
						// 세로로 있는지 확인
						if (i-1 >= 0 && map[i-1][j] == 'E') type = 1;
						endTree = new Tree(i, j, type);
						idx2 += 1;
					}
					list.add(new Point(i, j));
				}
			}
		}
		for (Point p : list) map[p.x][p.y] = '0';
		
		bfs();
		
		System.out.println(answer);
	}
	
	private static void bfs() {
		Queue<Tree> q = new ArrayDeque<>();
		q.offer(startTree);
		boolean[][][] visited = new boolean[N][N][2];
		visited[startTree.x][startTree.y][startTree.type] = true;
		int count = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();
			for (int i=0; i<qsize; i++) {
				Tree t = q.poll();
				if (t.x == endTree.x && t.y == endTree.y && t.type == endTree.type) {
					answer = count;
					return;
				}
				
				for (int d=0; d<5; d++) {
					Tree temp = new Tree(t.x, t.y, t.type);
					if (!temp.move(d) || visited[temp.x][temp.y][temp.type]) continue;
					q.offer(new Tree(temp.x, temp.y, temp.type));
					visited[temp.x][temp.y][temp.type] = true;
				}
			}
			count += 1;
		}
		
	
	}
	
}
