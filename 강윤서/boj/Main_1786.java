package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_1786 {
	static List<Integer> answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray();
		char[] p = br.readLine().toCharArray();
		answer = new ArrayList<>();
		KMP(s, p);
		System.out.println(answer.size());
		for (int a : answer) {
			System.out.println(a);
		}
	}

	public static int[] getPi(char[] p) {
		// pi[i] = 0부터 i까지 접두사와 접미사가 일치하는 최대 길이
		int[] pi = new int[p.length];
		int j = 0;
		for (int i = 1; i < p.length; i++) {
			while (j > 0 && p[i] != p[j]) {
				j = pi[j - 1];
			}
			if (p[i] == p[j]) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

	public static void KMP(char[] s, char[] p) {
		int[] table = getPi(p);
		int j = 0;
		for (int i = 0; i < s.length; i++) {
			while (j > 0 && s[i] != p[j]) {
				j = table[j - 1];
			}
			if (s[i] == p[j]) {
				if (j == p.length - 1) { // 패턴과 일치하는 거 찾음
					answer.add(i - p.length + 2); // 인덱스 1부터 시작
					j = table[j];
				} else {
					j++;
				}
			}
		}
	}
}
