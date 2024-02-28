import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int answer, N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			answer=0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			long numL = 1;
			long numR = 1;
			int cnt = Math.min(N, M-N);
			for (int i = 1; i <= cnt; i++) {
				numL*=M--;
				numR*=i;
			}
			System.out.println(numL/numR);
		}
	}
}
