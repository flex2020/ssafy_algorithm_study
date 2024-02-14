import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] classes = new int[N][2];
		for (int i = 0; i < classes.length; i++) {
			st = new StringTokenizer(br.readLine());
			classes[i][0] = Integer.parseInt(st.nextToken());
			classes[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int idx=1;
		
		Arrays.sort(classes, (a,b) -> { // 시작시간 기준 정렬
			return (a[0]!=b[0]) ? a[0]-b[0] : a[1]-b[1];
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(classes[0][1]);
		
		while(idx!=N) {
			if(pq.peek() <= classes[idx][0]) {
				pq.poll();
			}
			pq.add(classes[idx][1]);
			idx++;
		}
		System.out.println(pq.size());
	}
}
