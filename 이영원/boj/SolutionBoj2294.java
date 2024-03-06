import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 동전 수
		int K = Integer.parseInt(st.nextToken()); // 가치의 합(목표)
		HashSet<Integer> money = new HashSet<>(); // 중복제거
		int[] dp = new int[1000001]; // dp
		
		for (int i = 0; i < N; i++) {
			money.add(Integer.parseInt(br.readLine()));
		}
		
		int[] moneyArr = new int[money.size()];
		int idx=0;
		
		for(int m : money) { // 중복제거한거 배열에 넣기
			moneyArr[idx++] = m;
			dp[m]=1;
		}
		
		Arrays.sort(moneyArr); // 오름차순 정렬
		
		idx=moneyArr[0]+1; // 가장 작은거에서 하나 키우기
		while(idx!=K+1) {
			for (int i = 0; i < moneyArr.length; i++) {
				if(idx <= moneyArr[i]) break; // 그것보다 큰 경우 break
				// 동전으로 반복문 돌려보면서 확인
				if(dp[idx-moneyArr[i]]!=0 && (dp[idx] > dp[idx-moneyArr[i]]+1 || dp[idx]==0)) {
					dp[idx] = dp[idx-moneyArr[i]]+1;
				}
			}
			idx++;
		}
		
//		System.out.println(Arrays.toString(dp));
		
		if(dp[K]==0) answer=-1; // 0이라면 -1
		else answer = dp[K];
		
		System.out.println(answer);
		
		
		

	}

}
