package 강윤서.boj;

import java.io.*;
public class Main_2661 {
	static int N;
	static String answer;
	static boolean flag = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dfs(1, "1");
		System.out.println(answer);
	}
	public static boolean dfs(int length, String number) {
		if (length == N) {
			answer = number;
			return true;
		}
		for (int i=1; i<=3; i++) { // i를 뒤에 붙임으로써 나쁜 순열이 되는지 확인
			boolean possible = true;
			String temp = number + Integer.toString(i); // 임의의 수 생성
			int size = 1; // 해당 사이즈로 슬라이딩하며 중복되는지 확인
			String prev = "";
			String cur = "";
			while (size < length+1) {
				for (int start = 0; start<=length-size*2+1; start++) {
					prev = temp.substring(start, start+size);
					cur = temp.substring(start+size, start+size*2);
					if (prev.equals(cur)) {
						possible = false;
					} else {
						prev = cur;
					}
				}
				
				size++;
			}
			if (possible && dfs(length+1, temp)) return true;
		}
		return false;
	}
	
}
