import java.util.*;
import java.io.*;

public class SolutionBaekJoon9252 {
	
	static char[] str1;
	static char[] str2;
	static int[][] memo;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 문자열 입력받기
		str1 = (" " + br.readLine()).toCharArray();
		str2 = (" " + br.readLine()).toCharArray();
		
		checkMemo();
		calculateResult();
		
		System.out.println(sb);
	}
	
	public static void checkMemo() {
		memo = new int[str1.length][str2.length];
		
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				if (str1[i] == str2[j]) {
					memo[i][j] = memo[i - 1][j - 1] + 1;
				} else {
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
				}
			}
		}
	}
	
	public static void calculateResult() {
		sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		int x = str1.length - 1;
		int y = str2.length - 1;
		while (x > 0 && y > 0) {
			if (memo[x - 1][y] == memo[x][y]) {
				x--;
			} else if (memo[x][y - 1] == memo[x][y]) {
				y--;
			} else {
				stack.push(str1[x]);
				
				x--;
				y--;
			}
		}
		
		sb.append(stack.size() + "\n");
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
	}

}
