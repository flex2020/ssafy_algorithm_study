package swea;

import java.io.*;
import java.util.*;

public class Solution1218 {
	private static int N, answer;
	private static Stack<Character> s;
	private static char[] openBrackets = {'(', '{', '[', '<'};
	private static char[] closeBrackets = {')', '}', ']', '>'};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc=1; tc<=10; tc++) {
			s = new Stack<>();
			answer = 1;
			N = Integer.parseInt(br.readLine());
			char[] data = br.readLine().toCharArray();
			for (int i=0; i<N; i++) {
				// 여는 괄호면 스택에 삽입
				if (!isCloseBracket(data[i])) {
					s.push(data[i]);
				}
				// 닫는 괄호면 스택이 비어있지 않을때 짝지어주기
				else if (!s.empty()) {
					char c = s.pop();
					if (!isSameBracket(c, data[i])) {
						answer = 0;
						break;
					}
				}
				// 스택이 비어있다면 실패
				else {
					answer = 0;
					break;
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
	private static boolean isCloseBracket(char c) {
		for (int i=0; i<4; i++) {
			if (c == closeBrackets[i]) {
				return true;
			}
		}
		return false;
	}
	private static boolean isSameBracket(char c1, char c2) {
		int i1 = 0;
		int i2 = 0;
		for (int i=0; i<4; i++) {
			if (openBrackets[i] == c1) {
				i1 = i;
				break;
			}
		}
		for (int i=0; i<4; i++) {
			if (closeBrackets[i] == c2) {
				i2 = i;
				break;
			}
		}
		return i1 == i2;
	}
}
