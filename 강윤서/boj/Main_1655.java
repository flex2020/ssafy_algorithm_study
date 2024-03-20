package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_1655 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> left = new PriorityQueue<>((p1, p2) -> p2 - p1);
		PriorityQueue<Integer> right = new PriorityQueue<>((p1, p2) -> p1 - p2);
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i=1; i<=N; i++) {
			int value = Integer.parseInt(br.readLine());
            // 왼쪽부터 넣어보기
            if (left.size() == right.size()) {
                left.offer(value);
            } else {
                right.offer(value);
            }
            // left의 크기는 항상 right의 크기보다 크거나 같다
            // right가 비어있지 않고 두 pq의 peek 값을 비교했을 때 left의 top이 더 크다면 둘이 스왑
			if (!right.isEmpty() && right.peek() < left.peek()) {
                int v1 = left.poll();
                int v2 = right.poll();
                left.offer(v2);
                right.offer(v1);
            }
            sb.append(left.peek() + "\n");
		}
		System.out.println(sb);
	}
}