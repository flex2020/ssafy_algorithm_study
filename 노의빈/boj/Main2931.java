package boj;

import java.io.*;
import java.util.*;

public class Main2931 {
	private static int R, C, answerX, answerY, startX, startY, endX, endY;
	private static char answer;
	private static char[][] map;
	private static boolean[][] visited;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R+1][C+1];
		for (int i=1; i<=R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j=1; j<=C; j++) {
				map[i][j] = input[j-1];
				if (map[i][j] == 'M') {
					startX = i;
					startY = j;
				} else if (map[i][j] == 'Z') {
					endX = i;
					endY = j;
				}
			}
		}
		visited = new boolean[R+1][C+1];
		dfs(startX, startY);
		List<Integer> temp = new ArrayList<>();
		for (int i=0; i<4; i++) {
			int nx = answerX + dx[i];
			int ny = answerY + dy[i];
			if (nx <= 0 || nx > R || ny <= 0 || ny > C || map[nx][ny] == '.') continue;
			// 아래
			if (i == 0 && (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '2' || map[nx][ny] == '3')) temp.add(i);
			// 오른쪽
			else if (i == 1 && (map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '3' || map[nx][ny] == '4')) temp.add(i);
			// 위
			else if (i == 2 && (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '4')) temp.add(i);
			// 왼쪽
			else if (i == 3 && (map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '2')) temp.add(i);
		}
		if (temp.size() == 4) answer = '+';
		else {
			int a = temp.get(0);
			int b = temp.get(1);
			if (a == 0 && b == 2) answer = '|';
			else if (a == 1 && b == 3) answer = '-';
			else if (a == 0 && b == 1) answer = '1';
			else if (a == 1 && b == 2) answer = '2';
			else if (a == 2 && b == 3) answer = '3';
			else if (a == 0 && b == 3) answer = '4';
		}
		
		
		System.out.println(answerX + " " + answerY + " " + answer);
	}
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		if (map[x][y] == '.') {
			answerX = x;
			answerY = y;
			return;
		}
		
		if (x == startX && y == startY) {
			for (int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx <= 0 || nx > R || ny <= 0 || ny > C ) continue;
				// 아래
				if (i == 0 && (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '2' || map[nx][ny] == '3')) dfs(nx, ny);
				// 오른쪽
				if (i == 1 && (map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '3' || map[nx][ny] == '4')) dfs(nx, ny);
				// 위
				if (i == 2 && (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '4')) dfs(nx, ny);
				// 왼쪽
				if (i == 3 && (map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '2')) dfs(nx, ny);
			}
			return;
		}
		if (map[x][y] == '|') {
			if (!visited[x-1][y]) dfs(x-1, y);
			else dfs(x+1, y);
		} else if (map[x][y] == '-') {
			if (!visited[x][y-1]) dfs(x, y-1);
			else dfs(x, y+1);
		} else if (map[x][y] == '+') {
			if (!visited[x-1][y]) dfs(x-1, y);
			else if (!visited[x+1][y]) dfs(x+1, y);
			else if (!visited[x][y-1]) dfs(x, y-1);
			else dfs(x, y+1);
		} else if (map[x][y] == '1') {
			if (!visited[x+1][y]) dfs(x+1, y);
			else dfs(x, y+1);
		} else if (map[x][y] == '2') {
			if (!visited[x-1][y]) dfs(x-1, y);
			else dfs(x, y+1);
		} else if (map[x][y] == '3') {
			if (!visited[x-1][y]) dfs(x-1, y);
			else dfs(x, y-1);
		} else if (map[x][y] == '4') {
			if (!visited[x+1][y]) dfs(x+1, y);
			else dfs(x, y-1);
		}
	}
}
