package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_2493 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<int[]> stack = new Stack<>();
		int N = Integer.parseInt(br.readLine());
		int[] answer = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			int[] cur = {i, Integer.parseInt(st.nextToken())}; // 인덱스랑 값 입력받기
			while (!stack.isEmpty()) {
				if (stack.peek()[1] < cur[1]) { // 현재 값보다 스택에 더 큰 값이 있을 때까지 뽑기
					stack.pop();
				} else {
					break;
				}
			}
			if (!stack.isEmpty())
				answer[i] = stack.peek()[0]; // 스택의 탑 인덱스 저장
			else
				answer[i] = 0; // 없으면 0
			stack.push(cur); // 현재 값 스택에 넣기
		}
		for (int i=1; i<=N; i++) {
			System.out.print(answer[i] + " ");
		}
	}
}
