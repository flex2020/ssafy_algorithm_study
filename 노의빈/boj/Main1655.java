package boj;

import java.util.*;
import java.io.*;

public class Main1655 {
	private static int N, mid;
	private static PriorityQueue<Integer> pq1, pq2;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq1 = new PriorityQueue<>((a, b) -> b - a);
		pq2 = new PriorityQueue<>();
		StringBuilder answer = new StringBuilder();
		for (int i=1; i<=N; i++) {
			int input = Integer.parseInt(br.readLine());
			// i가 홀수
			if (i % 2 == 1) {
				if (pq1.isEmpty() && pq2.isEmpty()) {
					mid = input;
				}
				else if (pq1.size() > pq2.size()) {
					if (mid > input) {
						pq2.offer(mid);
						pq1.offer(input);
						mid = pq1.poll();
					}
					else {
						pq2.offer(input);
					}
				}
				else {
					if (mid > input) {
						pq1.offer(input);
					}
					else {
						pq1.offer(mid);
						pq2.offer(input);
						mid = pq2.poll();
					}
				}
			}
			// i가 짝수
			else {
				if (pq1.isEmpty() && pq2.isEmpty()) {
					if (mid < input) {
						pq2.offer(input);
					} else {
						pq2.offer(mid);
						mid = input;
					}
				}
				else if (mid > input) {
					pq2.offer(mid);
					pq1.offer(input);
					mid = pq1.poll();
				}
				else {
					pq2.offer(input);
				}
			}
			answer.append(mid + "\n");
		}
		System.out.println(answer.toString());
	}
}
