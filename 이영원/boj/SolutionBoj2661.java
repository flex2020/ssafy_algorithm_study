import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 길이
	static String answer; // 정답이 들어갈 곳

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		// 모든 케이스 테스트
//		for (int j = 1; j < 80; j++) {
//			N=j;
//			recursive("", 0);
//			System.out.println(answer);
//		}
		
		recursive("", 0);
		System.out.println(answer);
	}
	
	// num = 더해지는 숫자, cnt는 현재까지 들어온 수 길이
	private static boolean recursive(String num, int cnt) {
		// basis part
		if(cnt==N) { // N만큼 숫자가 찼다면 answer에 담고 return true로 보낸다.
			answer=num;
			return true;
		}
		// inductive part
		for (int i = 1; i <= 3; i++) {
			if(check(num+i, cnt+1)) {
				if(recursive(num+i, cnt+1)) return true;
			}
		}
		return false;
	}
	
	// 끝에서부터 하나씩 비교해나간다. 같으면 바로 false, 맨끝숫자를 포함하지 않은 나쁜수열은 이전 재귀에서 검사되었다.
	private static boolean check(String num, int cnt) {
		int depth=1;
		for (int i = 1; i <= cnt/2; i++) {
			int leftIdx = num.length()-i;
			int leftLength = num.length()-leftIdx;
			String left = num.substring(leftIdx);
			String right = num.substring(num.length()-(leftLength*2), leftIdx);
//			System.out.println(N + " " + num + " " + cnt + " " + left + " " + right + " " + leftIdx);
			if(left.equals(right)) return false;
		}
		return true;
	}

}
