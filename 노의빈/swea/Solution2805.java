import java.io.*;
import java.util.*;

public class Solution2805 {
	private static int N, answer;
	private static int[][] graph;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			answer = 0;
			graph = new int[N][N];
			for (int i=0; i<N; i++) {
				String[] input = br.readLine().split("");
				for (int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(input[j]);
				}
			}
			int mid = N / 2;
			for (int i=0; i<N; i++) {
				int start = 0;
				int end = 0;
				// TODO: 각 행에 맞는 start, end 설정
				if (i < mid) {
					start = mid - i;
					end = mid + i + 1;
				} else if (i == mid) {
					start = 0;
					end = N;
				} else {
					start = mid - (N-i) + 1;
					end = mid + (N-i);
				}
				for (int j=start; j<end; j++) {
					answer += graph[i][j];
				}
			}

			System.out.println("#" + tc + " " + answer);
		}
	}

}