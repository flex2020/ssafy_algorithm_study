import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int s = (int) Math.pow(2,N);
		dc(N, r, c, s, 0);
		System.out.println(answer);
	}
	
	// 숫자, r, c, s(사이즈), start(시작값)
	private static void dc(int N, int r, int c, int s, int start) {
		if(N == 1) {
			answer = start + (r*2) + c;
			return;
		}
		
		int num = (s/2)*(s/2);
		
		if(r<s/2 && c<s/2) { // 왼위
//			System.out.println(1);
//			System.out.println(N + " " + r + " " + c + " " + s + " " + start);
			dc(N-1, r, c, s/2, start+num*0);
		}else if(r<s/2 && c>=s/2) { // 오른위
//			System.out.println(2);
//			System.out.println(N + " " + r + " " + c + " " + s + " " + start);
			dc(N-1, r, c-s/2, s/2, start+num*1);
		}else if(r>=s/2 && c<s/2) { // 왼아래
//			System.out.println(3);
//			System.out.println(N + " " + r + " " + c + " " + s + " " + start);
			dc(N-1, r-s/2, c, s/2, start+num*2);
		}else { // 오른아래
//			System.out.println(4);
//			System.out.println(N + " " + r + " " + c + " " + s + " " + start);
			dc(N-1, r-s/2, c-s/2, s/2, start+num*3);
		}
		
	}
}
