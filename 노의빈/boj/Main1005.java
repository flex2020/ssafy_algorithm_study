package boj;

import java.io.*;
import java.util.*;

public class Main1005 {
	private static int N, K, answer, target;
	private static int[] time, indegree, total;
	private static List<List<Integer>> graph;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = 0;
			st = new StringTokenizer(br.readLine());
			time = new int[N+1];
			total = new int[N+1];
			indegree = new int[N+1];
			for (int i=1; i<=N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			graph = new ArrayList<>();
			for (int i=0; i<=N; i++) graph.add(new ArrayList<>());
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				indegree[b] += 1;
			}
			target = Integer.parseInt(br.readLine());
			topologySort();
			System.out.println(answer);
		}
	}
	private static void topologySort() {
		// 진입차수가 0인 건물 큐에 넣기
		Queue<Integer> q = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
				total[i] = time[i];
			}
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (indegree[target] == 0) {
				answer = total[target];
				return;
			}
			for (int next : graph.get(cur)) {
				total[next] = Math.max(total[next], time[next] + total[cur]);
				indegree[next] -= 1;
				if (indegree[next] != 0) continue;
				q.offer(next);
			}
		}
	}
}
