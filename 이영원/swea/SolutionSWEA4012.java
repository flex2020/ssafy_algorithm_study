import java.util.*;
import java.io.*;

public class Solution {
	private static int N, answer;
	private static int[][] s;
	private static boolean[] sel;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			s = new int[N][N];
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sel = new boolean[N];
			c(0, 0);
			System.out.println("#" + tc + " " + answer);
		}
		
	}
	private static void c(int idx, int cnt) {
		if (cnt == N/2) {
			int[] a = new int[N/2]; // a index
			int[] b = new int[N/2]; // b index
			int ai = 0;
			int bi = 0;
			for (int i=0; i<N; i++) {
				if (sel[i]) a[ai++] = i;
				else b[bi++] = i;
			}
			int suma = 0;
			int sumb = 0;
			ai = 0;
			bi = 0;
			for (int i=0; i<N/2; i++) {
				for (int j=0; j<N/2; j++) {
					if (a[i] == a[j]) continue;
					suma += s[a[i]][a[j]];
				}
			}
			for (int i=0; i<N/2; i++) {
				for (int j=0; j<N/2; j++) {
					if (b[i] == b[j]) continue;
					sumb += s[b[i]][b[j]];
				}
			}
			
			answer = Math.min(answer, Math.abs(suma-sumb));
			return;
		}
		if (idx == N) {
			return;
		}
		sel[idx] = true;
		c(idx+1, cnt+1);
		sel[idx] = false;
		c(idx+1, cnt);
	}
}
