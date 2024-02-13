package swea;

import java.util.*;
import java.io.*;

public class Solution5658 {
	private static Map<Character, Integer> map = new HashMap<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 초기 16진수-10진수 map 설정
		for (int i=0; i<10; i++) {
			map.put((char)(i+'0'), i);
		}
		map.put('A', 10);
		map.put('B', 11);
		map.put('C', 12);
		map.put('D', 13);
		map.put('E', 14);
		map.put('F', 15);
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long answer = 0;
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int M = N/4; // 각 16진수의 자릿수
			StringBuilder sb = new StringBuilder(br.readLine());
			Set<String> hexSet = new HashSet<>();
			List<Long> decList = new ArrayList<>();
			
			// M번 반복하면 0번째랑 같아져서 M번만 반복
			for (int i=0; i<M; i++) {
				// M개씩 잘라서 set에 넣는다 
				for (int j=0; j<4; j++) {
					hexSet.add(sb.substring(j*M, j*M+M).toString());
				}
				// 맨 마지막 문자를 맨앞에 넣어줌
				sb.insert(0, sb.charAt(N-1));
			}

			// 16진수 -> 10진수로 변환 후 list에 넣음
			hexSet.forEach(item -> decList.add(getDecimal(item, M)));
			Collections.sort(decList); // 오름차순 정렬
			answer = decList.get(decList.size() - K); // 뒤에서 M개를 선택
			System.out.println("#" + tc + " " + answer);
		}
	}
	// 16 -> 10진수 변환
	private static long getDecimal(String hex, int M) {
		long result = 0;
		for (int i=0; i<M; i++) {
			int exp = M-i-1; // 16의 지수
			result += Math.pow(16, exp) * map.get(hex.charAt(i));
		}
		return result;
	}

}
