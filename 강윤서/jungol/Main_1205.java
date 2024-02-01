package 강윤서.jungol;
import java.io.*;
import java.util.*;

public class Main_1205 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int zeroCnt = 0; // 0의 개수
		int answer = 0; 
		int[] dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
			if (dp[i] == 0) zeroCnt++;
		}

		Arrays.sort(dp);
        for (int i=N-1; i>=zeroCnt+1; i--) { // 끝에서부터 보기
            int length = 1;
            int zero = zeroCnt;
            int cur = dp[i];
            for (int j=i-1; j>=zeroCnt; j--) { // 그 앞에 있는 값을 탐색하며 zero 개수로 커버할 수 없을 때까지 탐색
                if (cur - 1 == dp[j]) {
                    length += 1;
                    cur = dp[j];
                } else if (cur == dp[j]) {
                    continue;
                } else {
                    int needZero = cur - dp[j] - 1; // 필요한 0의 개수
                    if (needZero <= zero) { // 가지고 있는 0의 개수안일 때
                        length += needZero + 1; // 사용한 0의 개수랑 이전 값 더해주기
                        cur = dp[j];
                        zero -= needZero;
                    } else {
                        break;
                    }
                }
            }
            length += zero; // 남아있는 0의 개수 더해주기
            answer = Math.max(answer, length);
        }
        if (zeroCnt == N) answer = zeroCnt;
		
		System.out.println(answer);
	}
}


