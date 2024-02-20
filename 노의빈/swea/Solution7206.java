package swea;

import java.io.*;
import java.util.*;

public class Solution7206 {
	private static Map<Integer, List<List<Integer>>> map;
	private static int[] dp = new int[100000];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<>();
		for (int i=0; i<5; i++) {
			map.put(i, new ArrayList<>());
		}
		powerset(1, 0, new boolean[5]);
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			String number = br.readLine();
			recursive(number);
			System.out.println("#" + tc + " " + dp[Integer.parseInt(number)]);
		}
		
	}
	
	// 재귀 + 메모이제이션 dp
	private static int recursive(String number) {
		// 문자열 길이가 1이면 0리턴
		if (number.length() == 1) {
			return 0;
		}
		// 현재 숫자에 저장된 값이 있으면 저장된 값을 리턴
		if (dp[Integer.parseInt(number)] != 0) {
			return dp[Integer.parseInt(number)];
		}
		
		int max = 0;
		// powerset을 통해 나온 결과로 재귀 돌려서 가장 큰 값 찾음
		for (int i=1; i<=number.length()-1; i++) {
			List<List<Integer>> ps = map.get(i); // 원소가 i개인 부분집합을 가져온다
			for (int j=0; j<ps.size(); j++) {
				List<Integer> sub = ps.get(j); // 원소가 i개인 부분집합 중 1개
				// 불가능한 부분집합 건너뛰기
				boolean flag = false;
				for (int k=0; k<i; k++) {
					if (sub.get(k) >= number.length()) flag = true;
				}
				if (flag) continue;
				int result = 1;
				result *= Integer.parseInt(number.substring(0, sub.get(0)));
				for (int k=1; k<i; k++) {
					result *= Integer.parseInt(number.substring(sub.get(k-1), sub.get(k)));
				}
				result *= Integer.parseInt(number.substring(sub.get(i-1), number.length()));
				// 만약 저장된 값이 있다면
				dp[result] = Math.max(dp[result], recursive(result + ""));
				max = Math.max(max, dp[result]);
			}
		}
		// dp에 가장 큰 값을 저장 해주고 1만큼 더해줌
		dp[Integer.parseInt(number)] = max + 1;
		return dp[Integer.parseInt(number)];
	}
	
	// 1, 2, 3, 4로 powerset 미리 만들기
	private static void powerset(int idx, int cnt, boolean[] sel) {
		if (idx == sel.length) {
			
			List<List<Integer>> ll = map.get(cnt);
			List<Integer> l = new ArrayList<>();
			for (int i=0; i<sel.length; i++) {
				if (sel[i]) l.add(i);
			}
			ll.add(l);
			map.put(cnt, ll);
			return;
		}
		
		sel[idx] = true;
		powerset(idx + 1, cnt + 1, sel);
		
		sel[idx] = false;
		powerset(idx + 1, cnt, sel);
	}
}
