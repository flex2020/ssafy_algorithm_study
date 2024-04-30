package boj;

import java.io.*;
import java.util.*;

public class Main2234 {
	private static int N, M, count, answer1, answer2, answer3;
	private static int[][] map, numMap;
	private static boolean[][] visited;
	private static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
	private static List<Integer> sizes;
	private static List<Set<Integer>> graph;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 11 = 1011(2), 0~3까지 서,북,동,남 순서
		map = new int[M][N];
		numMap = new int[M][N];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[M][N];
		sizes = new ArrayList<>();
		graph = new ArrayList<>();
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				if (visited[i][j]) continue;
				graph.add(new HashSet<>());
				answer1 += 1;
				count = 0;
				dfs(i, j);
				sizes.add(count);
			}
		}
		// 방 확장공사
		for (int i=0; i<answer1; i++)
			for (int next : graph.get(i))
				answer3 = Math.max(answer3, sizes.get(i) + sizes.get(next-1));
		
		System.out.println(answer1);
		System.out.println(answer2);
		System.out.println(answer3);
	}
	private static void dfs(int x, int y) {
		count += 1;
		visited[x][y] = true;
		numMap[x][y] = answer1;
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 해당 방향에 벽이 있다면 패스
			if ((map[x][y] & (1 << i)) != 0) {
				// 해당 방향으로 갔을 때 다른 방이 나온다면 인접리스트에 추가
				if (nx >= 0 && nx < M && ny >= 0 && ny < N && numMap[nx][ny] != 0 && numMap[nx][ny] != answer1) {
					graph.get(answer1 - 1).add(numMap[nx][ny]);
				}
				continue;
			}
			if (visited[nx][ny]) continue;
			dfs(nx, ny);
		}
		answer2 = Math.max(answer2, count);
	}
}
