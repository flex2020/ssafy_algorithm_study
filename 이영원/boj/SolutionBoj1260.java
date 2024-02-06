package com.ssafy.hw.step2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {
	
	static List<List<Integer>> list;
	static StringBuilder sb;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		visited= new boolean[N+1];
		for(int i=0;i<N;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=1;i<=M;i++) { // 입력
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.get(start-1).add(end);
			list.get(end-1).add(start);
		}
		
		for(int i=0;i<N;i++) {
			Collections.sort(list.get(i));
		}
		
		sb = new StringBuilder();
		
		dfs(K);
		sb.append("\n");
		visited = new boolean[N+1];
		bfs(K);
		
		System.out.println(sb);
	}
	
	private static void bfs(int K) {
		Deque<Integer> dq = new ArrayDeque<>();
		dq.offerLast(K);
		visited[K]=true;
		while(!dq.isEmpty()) {
			int start = dq.pollFirst();
			sb.append(start).append(" ");
			List<Integer> li = list.get(start-1);
			for(Integer i : li) {
				if(!visited[i]) {
					visited[i]=true;
					dq.offerLast(i);
				}
			}
		}
	}
	
	private static void dfs(int K) {
		if(!visited[K]) {
			sb.append(K).append(" ");
			visited[K]=true;
			for(int i=0;i<list.get(K-1).size();i++) {
				dfs(list.get(K-1).get(i));
			}

		}
	}

}
