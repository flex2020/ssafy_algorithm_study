package boj;

import java.io.*;
import java.util.*;

public class Main1339 {
	private static int N;
	private static String[] word;
	private static Alphabet[] sums;
	private static int[] val;
	
	static class Alphabet implements Comparable<Alphabet> {
		char c;
		int sum;
		
		@Override
		public int compareTo(Alphabet o) {
			return o.sum - sum;
		}

		@Override
		public String toString() {
			return "Alphabet [c=" + c + ", sum=" + sum + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		word = new String[N];
		val = new int[26];
		sums = new Alphabet[26];
		for (int i=0; i<26; i++) {
			sums[i] = new Alphabet();
			sums[i].c = (char)('A' + i);
		}
		for (int i=0; i<N; i++) {
			word[i] = br.readLine();
		}
		for (String s : word) {
			int pow = s.length() - 1;
			for (int i=0; i<s.length(); i++) {
				char c = s.charAt(i);
				sums[c - 'A'].sum += Math.pow(10, pow--);
			}
		}
		Arrays.sort(sums);
		int number = 9;
		for (int i=0; i<26; i++) {
			val[sums[i].c - 'A'] = number--;
			if (number == 0) break;
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<word[i].length(); j++) {
				char c = word[i].charAt(j);
				// 이미 숫자로 치환됐다면 패스
				if (c >= '0' && c <= '9') continue;
				word[i] = word[i].replaceAll(c + "", val[c - 'A'] + "");
			}
		}
		int answer = 0;
		for (int i=0; i<N; i++) {
			answer += Integer.parseInt(word[i]);
		}
		System.out.println(answer);
	}

}
