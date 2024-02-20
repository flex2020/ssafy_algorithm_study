package boj;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main2252 {
	private static int N, M;
	private static int[] inDegree;
	private static StringBuilder answer;
	private static List<List<Integer>> graph;
	private static Queue<Integer> q;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inDegree = new int[N + 1];
		answer = new StringBuilder();
		graph = new ArrayList<>();
		for (int i=0; i<=N; i++) graph.add(new ArrayList<>());
		q = new ArrayDeque<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			inDegree[end] += 1; // 진입차수 증가
			graph.get(start).add(end); // 그래프에 추가
		}
		
		for (int i=1; i<=N; i++) {
			if (inDegree[i] == 0) q.offer(i); // 진입차수가 0이면 큐에 삽입
		}
		
		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			int node = q.poll();
			answer.append(node).append(" "); // 큐에서 뺀 것을 정답에 추가
			
			for (int i=0; i<graph.get(node).size(); i++) {
				inDegree[graph.get(node).get(i)] -= 1; // 큐에서 뺀 것과 연결된 것의 진입차수 1 빼기
				if (inDegree[graph.get(node).get(i)] == 0) q.offer(graph.get(node).get(i)); // 만약 진입차수가 0이 되면 큐에 삽입
			}
		}
		
		System.out.println(answer.toString());
	}

}
