package 강윤서.swea;
import java.io.*;
import java.util.*;

public class Solution_1263 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int INF = 1000000;
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] board = new int[N][N];
			int[][] dist = new int[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = INF;
				}
			}
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (i == j) dist[i][j] = 0;
					else if (board[i][j] == 1) {
						dist[i][j] = 1;
					}
				}
			}
			for (int k=0; k<N; k++) {
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
					}
				}
			}
			int minDist = INF;
			for (int i=0; i<N; i++) {
				int temp = 0;
				for (int j=0; j<N; j++) {
					temp += dist[i][j];
				}
				if (temp < minDist) {
					minDist = temp;
				}
			}
			System.out.println("#" + tc + " " + minDist);
		}
	}

}
