import java.util.*;
import java.io.*;

public class Solution {
	private static int[] gyu;
	private static int[] in;
	private static boolean[] v;
	private static int[] sel;
	private static int win, lose, sumg, sumi;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			gyu = new int[9];
			in = new int[9];
			v = new boolean[18];
			win = 0;
			lose = 0;
			sumg = 0;
			sumi = 0;
			for (int i=0; i<9; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());
				v[gyu[i] - 1] = true; 
			}
			int idx = 0;
			for (int i=0; i<18; i++) {
				if (!v[i]) {
					in[idx++] = i+1;
				}
			}
			sel = new int[9];
			v = new boolean[9];
			p(0);
			System.out.println("#" + tc + " " + win + " " + lose);
		}
	}
	private static void p(int idx) {
		if (idx == 9) {
//			System.out.println(Arrays.toString(gyu));
//			System.out.println(Arrays.toString(sel));
			for (int i=0; i<9; i++) {
				if (gyu[i] > sel[i]) {
					sumg += gyu[i] + sel[i];
				} else {
					sumi += gyu[i] + sel[i];
				}
			}
			if (sumg > sumi) {
				win += 1;
			} else if (sumg < sumi) {
				lose += 1;
			}
			sumg = 0;
			sumi = 0;
			return;
		}
		
		for (int i=0; i<9; i++) {
			if (v[i]) continue;
			v[i] = true;
			sel[idx] = in[i];
			p(idx+1);
			v[i] = false;
		}
	}
}
