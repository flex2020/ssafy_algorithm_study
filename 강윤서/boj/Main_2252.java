package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_2252 {
	static int N, M, a, b;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		List<List<Integer>> L = new ArrayList<>();
		int[] indegree = new int[N+1]; // 진입차수
		for (int i=0; i<=N; i++) {
			L.add(new ArrayList<>());
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			L.get(a).add(b);
			indegree[b] += 1; // 진입차수 증가
		}
		Queue<Integer> Q = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			if (indegree[i] == 0) {
				Q.offer(i);
			}
		}

		while (!Q.isEmpty()) {
			int cur = Q.poll();
			sb.append(cur + " ");
			for (int i=0; i<L.get(cur).size(); i++) {
				int nextIndex = L.get(cur).get(i);
				indegree[nextIndex] -= 1;
				if (indegree[nextIndex] == 0) {
					Q.offer(nextIndex);
				}
			}
		}
		System.out.println(sb);
	}
}