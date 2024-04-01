package boj;

import java.io.*;
import java.util.*;

public class Main1202 {
	private static int N, K;
	private static long answer;
	private static PriorityQueue<Jewel> jewels;
	private static int[] bag;
	
	static class Jewel implements Comparable<Jewel> {
		int w, v;
		
		public Jewel(int w, int v) {
			this.w = w;
			this.v = v;
		}

		@Override
		public int compareTo(Jewel o) {
			if (w == o.w) return o.v - v;
			return w - o.w;
		}

		@Override
		public String toString() {
			return "Jewel [w=" + w + ", v=" + v + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		answer = 0;
		jewels = new PriorityQueue<>();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			jewels.offer(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		bag = new int[K];
		for (int i=0; i<K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bag);

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		for (int i=0; i<K; i++) {
			while (!jewels.isEmpty()) {
				// 만약 보석이 가방한도보다 더 무거워지면 패스
				if (jewels.peek().w > bag[i]) break;
				Jewel j = jewels.poll(); // 현재 가장 가볍고 가치가 높은 보석을 꺼낸다
				pq.offer(j.v);
			}
			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}

		System.out.println(answer);
	}
	

}
