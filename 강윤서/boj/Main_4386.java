package 강윤서.boj;
import java.io.*;
import java.util.*;
public class Main_4386 {
	static class Star {
		int number;
		double dist;
		Star (int number, double dist) {
			this.number = number;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		double[] dist = new double[N];
		boolean[] visited = new boolean[N];
		Arrays.fill(dist, Float.MAX_VALUE);
		List<double[]> list = new ArrayList<>();
		PriorityQueue<Star> PQ = new PriorityQueue<>((p1, p2) -> Double.compare(p1.dist, p2.dist));
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			list.add(new double[] {x, y});
		}
		
		PQ.offer(new Star(0, 0));
		dist[0] = 0;
		double answer = 0;
		while (!PQ.isEmpty()) {
			Star cur = PQ.poll();
			if (visited[cur.number]) continue;
			visited[cur.number] = true;
			answer += cur.dist;
			for (int i=0; i<N; i++) {
				if (i == cur.number || visited[i]) continue;
				double d = Math.pow(Math.abs(list.get(cur.number)[0] - list.get(i)[0]), 2) + Math.pow(Math.abs(list.get(cur.number)[1] - list.get(i)[1]), 2);
				d = Math.sqrt(d);
				PQ.offer(new Star(i, d));
			}
		}
		System.out.println(Math.round(answer * 100) / 100.0);

	}

}
