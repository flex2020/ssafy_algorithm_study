import java.io.*;
import java.util.*;
public class Solution_7465 {
	static int N, M;
	static int[][] board;
	static int[] set;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			set = new int[N+1];
			for (int i=1; i<=N; i++) {
				set[i] = i;
			}
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
			}
			Set<Integer> S = new HashSet<>();
			for (int i=1; i<=N; i++) {
				S.add(set[i]);
			}
			System.out.printf("#%d %d\n", tc, S.size());
		}

	}
	public static void union(int one, int two) {
		int oneOwner = find(one); // 대표 찾기
		int twoOwner = find(two); 
		
		if (oneOwner != twoOwner) {
			set[twoOwner] = oneOwner;
			for (int i=1; i<=N; i++) {
				if (set[i] == twoOwner) {
					set[i] = oneOwner;
				}
			}
		}
	}
	public static int find(int org) {
		if (set[org] == org) return set[org];
		// path compression
		else return set[org] = find(set[org]);
		
	}

}
