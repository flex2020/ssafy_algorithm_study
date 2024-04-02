package boj;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main16724 {
	private static int N, M, answer;
	private static char[][] map;
	private static int[][] marking;
	private static int[] parent;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		marking = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				marking[i][j] = M * i + j + 1;
			}
		}
		parent = new int[N*M+1];
		makeset();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (find(marking[i][j]) != M * i + j + 1) continue;
				dfs(i, j);
			}
		}
		Set<Integer> set = new HashSet<>();
		for (int i=1; i<=N*M; i++) {
			set.add(parent[i]);
		}
		answer = set.size();
		System.out.println(answer);
	}
	
	private static void dfs(int x, int y) {
		int nx = x;
		int ny = y;
		if (map[x][y] == 'U') {
			nx -= 1;
		} else if (map[x][y] == 'D') {
			nx += 1;
		} else if (map[x][y] == 'L') {
			ny -= 1;
		} else if (map[x][y] == 'R') {
			ny += 1;
		}
		// 현재위치와 다음위치가 union 되지 않았다면 union 하고 다음으로
		if (find(marking[x][y]) == find(marking[nx][ny])) return;
		union(marking[x][y], marking[nx][ny]);
		dfs(nx, ny);
	}
	
	private static void makeset() {
		for (int i=1; i<=N*M; i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int a) {
		if (parent[a] != a) {
			parent[a] = find(parent[a]);
		}
		return parent[a];
	}
	
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa < pb) parent[pb] = pa;
		else parent[pa] = pb;
	}
}
