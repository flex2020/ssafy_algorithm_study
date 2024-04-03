package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_1781 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		// PQ -> 0: 데드라인, 1: 받을 수 있는 컵라면 개수
		PriorityQueue<int[]> PQ = new PriorityQueue<>((p1, p2) -> p2[0] == p1[0] ? p2[1] - p1[1] : p2[0] - p1[0]);
		PriorityQueue<int[]> curDay = new PriorityQueue<>((p1, p2) -> p2[1] - p1[1]);

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			PQ.offer(new int[] {a, b});
		}
		int answer = 0;
		for (int day=N; day>0; day--) { // 현재 날
			while (!PQ.isEmpty()) {
				int[] cur = PQ.poll();
				if (cur[0] < day) {
					PQ.offer(cur);
					break;
				}
				curDay.offer(cur);
			}
			
			if (!curDay.isEmpty()) {
				int[] cur = curDay.poll();
				
				if (cur[0] < day) continue;
				answer += cur[1];
			}
		}
		System.out.println(answer);

	}

}
