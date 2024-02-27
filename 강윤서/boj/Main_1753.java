package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_1753 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		List<List<int[]>> L = new ArrayList<>();
		for (int i=0; i<V+1; i++) {
			L.add(new ArrayList<>());
		}
		int[] distance = new int[V+1];
		int K = Integer.parseInt(br.readLine());
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[K] = 0;
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			L.get(from).add(new int[] {to, weight});
		}
		boolean[] visited = new boolean[V+1];
		PriorityQueue<int[]> PQ = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
		PQ.offer(new int[] {K, 0});
		while (!PQ.isEmpty()) {
			int[] cur = PQ.poll();
			visited[cur[0]] = true;
			for (int i=0; i<L.get(cur[0]).size(); i++) {
				int[] next = L.get(cur[0]).get(i);
				if (!visited[next[0]] && distance[cur[0]] + next[1] < distance[next[0]]) {
					distance[next[0]] = distance[cur[0]] + next[1];
					PQ.offer(new int[] {next[0], distance[next[0]]});
				}
			}
		}
		for (int i=1; i<=V; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				sb.append("INF\n");
			} else {
				sb.append(distance[i] + "\n");
			}
		}
		System.out.println(sb);
	}
}