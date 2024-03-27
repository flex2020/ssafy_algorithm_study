package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> PQ = new PriorityQueue<>((p1, p2) -> p2[1] == p1[1] ? p1[0] - p2[0] : p2[1] - p1[1]);
		PriorityQueue<Integer> bag = new PriorityQueue<>((b1, b2) -> b1 - b2);
		PriorityQueue<int[]> jewelry = new PriorityQueue<>((j1, j2) -> j1[0] - j2[0]);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewelry.offer(new int[] { w, v });
		}
		for (int i = 0; i < K; i++) {
			bag.offer(Integer.parseInt(br.readLine()));
		}
		long answer = 0;
		while (!bag.isEmpty()) {
			// 가방에 대해서 넣을 수 있는 보석 검사
			int curSize = bag.poll();
			while (!jewelry.isEmpty()) {
				int[] cur = jewelry.poll();
				if (cur[0] <= curSize) {
					PQ.offer(cur);
				} else {
					jewelry.offer(cur);
					break;
				}
			}
			if (!PQ.isEmpty()) {
				int[] cur = PQ.poll();
				answer += cur[1];
			}
		}
		System.out.println(answer);
	}

}
