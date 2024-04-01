import java.util.*;
import java.io.*;

public class SolutionBaekJoon16724 {
	
	static class Position {
		int x;
		int y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int M;
	static char[][] map;
	static int[][] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 지도 입력받기
		map = new char[N][M];
		for (int i = 0; i < map.length; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		makeSet();
		checkMap();
		int result = calculateResult();
		
		System.out.println(result);
	}
	
	public static void makeSet() {
		parents = new int[map.length][map[0].length];
		
		for (int i = 0; i < parents.length; i++) {
			for (int j = 0; j < parents[0].length; j++) {
				parents[i][j] = i * M + j;
			}
		}
	}
	
	public static void union(Position a, Position b) {
		int parentA = findParent(a);
		int parentB = findParent(b);
		
		if (parentA < parentB) {
			parents[parentB / M][parentB % M] = parentA;
		} else {
			parents[parentA / M][parentA % M] = parentB;
		}
	}
	
	public static int findParent(Position a) {
		if (parents[a.x][a.y] == a.x * M + a.y) {
			return parents[a.x][a.y];
		} else {
			Position parent = new Position(parents[a.x][a.y] / M, parents[a.x][a.y] % M);
			return parents[a.x][a.y] = findParent(parent);
		}
	}
	
	public static void checkMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				// 자신과 이어진 곳 찾기
				Position next = null;
				switch (map[i][j]) {
					case 'U':
						next = new Position(i - 1, j);
						break;
					case 'D':
						next = new Position(i + 1, j);
						break;
					case 'L':
						next = new Position(i, j - 1);
						break;
					case 'R':
						next = new Position(i, j + 1);
						break;
				}
				
				// 자신과 이어진 곳 잇기
				union(new Position(i, j), next);
			}
		}
	}
	
	public static int calculateResult() {
		Set<Integer> tmp = new HashSet<>();
		
		for (int i = 0; i < parents.length; i++) {
			for (int j = 0; j < parents[0].length; j++) {
				tmp.add(parents[i][j]);
			}
		}
		
		Set<Integer> set = new HashSet<>();
		for (int parent : tmp) {
			set.add(findParent(new Position(parent / M, parent % M)));
		}
		
		return set.size();
	}

}
