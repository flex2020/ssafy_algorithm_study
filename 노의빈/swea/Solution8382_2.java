import java.util.*;

public class Solution8382_2 {
	private static int x1, x2, y1, y2, answer;
	private static final int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	private static final int INF = 1000000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc=1; tc<=T; tc++) {
			answer = INF;
			x1 = sc.nextInt();
			y1 = sc.nextInt();
			x2 = sc.nextInt();
			y2 = sc.nextInt();
			
			changePoints();
			bfs();
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, -1, 0));
		boolean[][][] visited = new boolean[201][201][2];
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			if (p.x == x2 && p.y == y2) {
				answer = Math.min(answer, p.dist);
				return;
			}
			
			for (int i=0; i<4; i++) {
				// 이전이 가로이동이었다면(0) 현재 가로이동 불가능
				// 이전이 세로이동이었다면(1) 현재 세로이동 불가능
				if (p.prev == 0 && i >= 2 || p.prev == 1 && i < 2) continue;
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				int prev = i < 2 ? 1 : 0; // 현재 이동에 따른 prev 값 설정
				if (0 <= nx && nx < 201 && 0 <= ny && ny < 201) {
					if (!visited[nx][ny][prev]) {
						q.add(new Point(nx, ny, prev, p.dist + 1));
						visited[nx][ny][prev] = true;
					}
					
				}
			}
		}
	}
	// 좌표평면 기준 변경
	private static void changePoints() {
		int dx = Math.abs(x1 - x2);
		int dy = Math.abs(y1 - y2);
		x1 = 0;
		y1 = 0;
		x2 = dx;
		y2 = dy;
	}
}

class Point {
	int x, y, prev, dist; // prev == -1 -> 첫이동 / prev == 0 -> 이전 가로(세로이동가능) / prev == 1 -> 이전 세로(가로이동가능)
	public Point(int x, int y, int prev, int dist) {
		this.x = x;
		this.y = y;
		this.prev = prev;
		this.dist = dist;
	}
}