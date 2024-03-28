package 강윤서.boj;
import java.util.*;
import java.io.*;

public class Main_9205 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			List<int[]> list = new ArrayList<>();
			int[][] dist = new int[N + 2][N + 2];
			for (int i = 0; i < N + 2; i++)
				Arrays.fill(dist[i], 5000000);

			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			list.add(new int[] { startX, startY });
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.add(new int[] { x, y });
			}
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			list.add(new int[] { endX, endY });
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (i == j)
						dist[i][j] = 0;
					else {
						int d = Math.abs(list.get(i)[0] - list.get(j)[0]) + Math.abs(list.get(i)[1] - list.get(j)[1]);
						dist[i][j] = d;
					}
				}
			}
			for (int k = 1; k <= N; k++) {
				for (int i = 0; i < N + 2; i++) { // 시작 점
					for (int j = 0; j < N + 2; j++) {
						if (dist[i][k] <= 1000) dist[i][k] = 0;
						if (dist[k][j] <= 1000) dist[k][j] = 0;
						dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
					}
				}
//				print(dist);
			}
			if (dist[0][list.size()-1] > 1000) System.out.println("sad");
			else System.out.println("happy");
//			System.out.println(dist[0][list.size() - 1]);
		}
	}

	public static void print(int[][] d) {
		System.out.println("======================");
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d[0].length; j++) {
				System.out.print(d[i][j] + " ");
			}
			System.out.println();
		}
	}
}
