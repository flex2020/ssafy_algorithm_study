import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 현재 활성화된 앱 수
        M = Integer.parseInt(st.nextToken()); // 확보해야하는 메모리 크기

        int[] memories = new int[N];
        int[] costs = new int[N];
        int[] dp = new int[100*N+100]; // 인덱스 = 비용합, 안에 들어있는 값 = 메모리합
        // 메모리합의 최대를 구해서 넣어준다.

        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자는 인덱스
        // 1
        // 1, 2, 1+2
        // 1, 2, 1+2, 3, 1+3, 2+3, 1+2+3
        // 이런식으로 이전 값 요소들에 새로운 값을 더해주면서 모든 부분집합 탐색(난 왜 이걸 몰랐지? 이 멍청한 놈)
        // 이렇게 하나씩 dp에 기록해두고 0이 아닌걸 기준으로 뒤에서부터 내려오면서 최대 메모리합을 해당 인덱스에 저장
        for (int i = 0; i < N; i++) {
            for (int j = dp.length-1; j >= costs[i] ; j--) { // 앞에서부터 오면 했던거 또 더함
                if(costs[i]==j || dp[j-costs[i]]!=0) dp[j] = Math.max(dp[j-costs[i]] + memories[i], dp[j]);
            }
//            System.out.println(Arrays.toString(dp));
        }

        // M을 넘는것중 가장 빠른것을 찾아서 출력 후 탈출
        for (int i = 0; i < dp.length-1; i++) {
            if(dp[i]>=M){
                System.out.println(i);
                break;
            }
        }
    }
}
