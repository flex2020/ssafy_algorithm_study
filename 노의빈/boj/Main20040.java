package boj;

import java.io.*;
import java.util.*;

public class Main20040 {
	private static int N, M, answer;
	private static int[] parent;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		makeSet();
		answer = 0;
		for (int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (find(a) != find(b)) {
				union(a, b);
			} else if (answer == 0) {
				answer = i;
				break;
			}
		}
		System.out.println(answer);
	}
	
	private static void makeSet() {
		parent = new int[N];
		for (int i=0; i<N; i++) parent[i] = i;
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
