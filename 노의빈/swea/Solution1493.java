import java.io.*;

public class Solution1493 {
	private static int p, q, answer;
	private static int[][] graph = new int[301][301];
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		setGraph();
		for (int tc=1; tc<=T; tc++) {
			String[] input = br.readLine().split(" ");
			p = Integer.parseInt(input[0]);
			q = Integer.parseInt(input[1]);
			star();
			System.out.println("#" + tc + " " + answer);
		}
	}
	private static void setGraph() {
		int a = 1;
		for (int i=1; i<=300; i++) {
			a = 1 + i * (i - 1) / 2;
			graph[i][1] = a;
			for (int j=2; j<=300; j++) {
				graph[i][j] = a + j * (j - 1) / 2 + (j - 1) * i ;
			}
		}
	}
	
	private static int getNumber(int x, int y) {
		return graph[x][y];
	}
	
	private static int[] getAddress(int v) {
		for (int i=1; i<=300; i++) {
			for (int j=1; j<=300; j++) {
				if (v == graph[i][j]) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
	
	private static void star() {
		int[] p1 = getAddress(p);
		int[] p2 = getAddress(q);
		answer = getNumber(p1[0] + p2[0], p1[1] + p2[1]);
	}
}