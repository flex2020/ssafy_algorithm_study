package boj;

import java.io.*;
import java.util.*;

public class Main2661 {
	private static int N;
	private static String answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = "";
		recursive("");
		System.out.println(answer);
	}
	private static void recursive(String s) {
		if (s.length() == N) {
			answer = s;
			return;
		}
		for (int i=1; i<=3; i++) {
			if (!check(s + i)) continue;
			recursive(s + i);
			if (!answer.equals("")) return;
		}
	}
	
	private static boolean check(String s) {
		// 부분수열이 있는지 확인
		// 길이가 1인 부분 수열 ~ 길이가 n/2인 부분 수열까지 확인
		for (int i=1; i<=s.length()/2; i++) {
			// substring 시작지점
			for (int start=0; start<s.length()-i*2+1; start++) {
				String s1 = s.substring(start, start + i);
				String s2 = s.substring(start + i, start + 2*i);
				if (s1.equals(s2)) return false;
			}
		}
		return true;
	}
}
