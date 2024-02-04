package boj;

import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main2493 {
	private static int N;
	private static int[] tops;
	private static int[] answers;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tops = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			tops[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Point> s = new Stack<>();
		answers = new int[N];
		s.push(new Point(tops[N-1], N-1));
		for (int i=N-2; i>=0; i--) {
			if (s.peek().x > tops[i]) {
				s.push(new Point(tops[i], i));
				continue;
			}
			while (!s.isEmpty()) {
				if (s.peek().x > tops[i]) {
					break;
				}
				Point p = s.pop();
				answers[p.y] = i+1;
			}
			s.push(new Point(tops[i], i));

		}
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(answers[i] + " ");
		}
		System.out.println(sb.toString());
	}

}
