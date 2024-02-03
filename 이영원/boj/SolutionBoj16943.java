import java.util.*;
import java.io.*;

public class Main {
	private static String a;
	private static int answer = -1, b;
	private static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = st.nextToken();
		b = Integer.parseInt(st.nextToken());
		visited = new boolean[a.length()];
		p(0, "");
		System.out.println(answer);
	}
	private static void p(int count, String snum) {
		if (count == a.length()) {
			int num = Integer.parseInt(snum);
			String temp = num + "";
			if (temp.length() != a.length()) return;
			if (num < b) {
				answer = Math.max(answer, num);
			}
			return;
		}
		
		for (int i=0; i<a.length(); i++) {
			if (visited[i]) continue;
			visited[i] = true;
			p(count + 1, snum + a.charAt(i));
			visited[i] = false;
		}
	}
}
