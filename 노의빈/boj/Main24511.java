package boj;

import java.util.*;
import java.io.*;

public class Main24511 {
	private static int N, M, C;
	private static Deque<Integer> dq = new ArrayDeque<>();
	private static StringBuilder sb = new StringBuilder();
	private static boolean[] isQueue;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		isQueue = new boolean[N];
		for (int i=0; i<N; i++) {
			isQueue[i] = Integer.parseInt(st.nextToken()) == 0;
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			if (isQueue[i]) dq.offerFirst(Integer.parseInt(st.nextToken()));
			else st.nextToken();
		}
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int qsize = dq.size();
		// 아직 M만큼 안모였으면 덱에 삽입
		if (M > qsize) {
			for (int i=0; i<M-qsize; i++) {
				dq.offerLast(Integer.parseInt(st.nextToken()));
			}
		}
		// M만큼 모였으니 덱에서 빼서 답에추가
		for (int i=0; i<M; i++) {
			sb.append(dq.poll() + " ");
		}
		
		System.out.println(sb.toString());
	}
}
