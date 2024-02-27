package boj;

import java.io.*;
import java.util.*;

public class Main16637 {
	private static int N, M;
	private static long answer;
	private static String exp, tempExp;
	private static boolean[] sel;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = N/2;
		exp = br.readLine();
		sel = new boolean[M];
		answer = Long.MIN_VALUE;
		powerset(0);
		System.out.println(answer);
	}
	
	private static void powerset(int idx) {
		if (idx == M) {
			// 현재 고른 것으로 계산해본다
			tempExp = exp;
			calculate();
			return;
		}
		// 이전 것이 선택되지 않았다면 현재 것을 선택할 수 있다
		if (idx == 0 || !sel[idx-1]) {
			sel[idx] = true;
			powerset(idx + 1);
		}
		
		// 선택하지 않는 경우도 진행한다
		sel[idx] = false;
		powerset(idx + 1);
		
	}
	
	private static void calculate() {
		// 괄호 친 것 먼저 계산하기
		for (int i=0; i<M; i++) {
			if (!sel[i]) continue;
			int subret = 0;
			int aIdx = 2 * i;
			int bIdx = 2 * (i + 1);
			int a = tempExp.charAt(aIdx) - '0';
			int b = tempExp.charAt(bIdx) - '0';
			char op = tempExp.charAt(aIdx+1);
			if (op == '+') {
				subret = a + b;
			} else if (op == '-' ) {
				subret = a - b;
			} else {
				subret = a * b;
			}
			
			String sub = subret + "";
			// 항의 계산 결과가 음수인 경우
			if (subret < 0) {
				sub = "~" + (-subret);
			}
			while (sub.length() != 3) {
				sub += "#"; // 길이가 3이 될때까지 더미 문자열 추가
			}
			tempExp = tempExp.substring(0, aIdx) + sub + tempExp.substring(bIdx+1, tempExp.length());
		}
		tempExp = tempExp.replaceAll("#", "");
		// 새로운 식을 숫자와 연산자로 분리한다
		List<Long> number = new ArrayList<>();
		List<Character> op = new ArrayList<>();
		String n = "";
		for (int i=0; i<tempExp.length(); i++) {
			char c = tempExp.charAt(i);
			if (c == '+' || c == '-' || c == '*') {
				if (n.charAt(0) == '~') number.add(-Long.parseLong(n.substring(1, n.length())));
				else number.add(Long.parseLong(n.substring(0, n.length())));
				n = "";
				op.add(c);
				continue;
			}
			n += c;
		}
		if (n.charAt(0) == '~') number.add(-Long.parseLong(n.substring(1, n.length())));
		else number.add(Long.parseLong(n.substring(0, n.length())));
		
		//System.out.println("number = " + number + ", op = " + op);
		
		// 분리된 것을 통해 값을 계산한다
		for (int i=0; i<number.size()-1; i++) {
			long a = number.get(i);
			long b = number.get(i+1);
			char c = op.get(i);
			if (c == '+') {
				number.set(i+1, a + b);
			} else if (c == '-') {
				number.set(i+1, a - b);
			} else {
				number.set(i+1, a * b);
			}
		}
		
		answer = Math.max(answer, number.get(number.size() - 1));
	}
	
}
