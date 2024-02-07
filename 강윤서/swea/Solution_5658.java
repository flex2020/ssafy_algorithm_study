package 강윤서.swea;

import java.util.*;
import java.io.*;

public class Solution_5658 {
	static int T, N, K, size;
	static List<String> result;
	static List<Integer> answer;
	static Deque<Character> DQ;
	static String pw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			char[] input = br.readLine().toCharArray();
			DQ = new ArrayDeque<>();
			result = new ArrayList<>();
			answer = new ArrayList<>();
			for (int i=0; i<N; i++) {
				DQ.offer(input[i]); 
			}
			size = N/4; // 한 변의 길이
			
			while (N-- >= 0) {
				pw = "";
				select(); // pw 만들고 DQ update
				if (check()) { // 중복된 값이 없는 것
					result.add(pw);
					answer.add(Integer.decode("0x" + pw));
				}
			}
			answer.sort((r1, r2) -> r2 - r1); // 내림차순 정렬
			System.out.printf("#%d %d\n", tc, answer.get(K-1));
		}
	}
	public static void select() {
		for (int i=0; i<size; i++) {
			Character c = DQ.pollFirst();
			pw += c;
			DQ.offer(c);
		}
		for (int i=0; i<size+1; i++) {
			DQ.offerFirst(DQ.pollLast());
		}
	}
	public static boolean check() { // true: 중복된 값 없음, false: 중복된 값 있음
		for (int i=0; i<answer.size(); i++) {
			if (answer.get(i).equals(Integer.decode("0x" + pw))) {
				return false;
			}
		}
		return true;
	}

}

