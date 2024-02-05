import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int cnt;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			cnt = 0;
			int result = isPalindrome(br.readLine());
			System.out.println(result + " " + cnt);
		}
	}
	
	private static int isPalindrome(String s) {
		return recursion(s, 0, s.length()-1);
	}
	
	private static int recursion(String s, int l, int r) {
		cnt++;
	    if(l >= r) return 1;
	    else if(s.charAt(l) != s.charAt(r)) return 0;
	    else return recursion(s, l+1, r-1);
	}
	
}
