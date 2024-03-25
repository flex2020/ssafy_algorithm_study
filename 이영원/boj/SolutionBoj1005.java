import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수 T

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물의 개수 N
            int K = Integer.parseInt(st.nextToken()); // 건설순서 규칙 수 K

            int[] buildingTime = new int[N+1]; // 각 건물별 건설시간 배열
            int[] dp = new int[N+1]; // dp?
            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {
                buildingTime[i] = Integer.parseInt(st.nextToken());
            }

            adjList = new ArrayList[N+1]; // 인접리스트

            for (int i = 0; i < N+1; i++) { // 초기화
                adjList[i] = new ArrayList<>();
            }
            
            int[] weesang = new int[N+1]; // 위상(2시간만에 깨달았네..)

            for (int i = 0; i < K; i++) { // 인접리스트, 위상배열 만들기
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                weesang[end]++;
                adjList[start].add(end);
            }

            Deque<Integer> dq = new ArrayDeque<>(); // 덱

            for (int i = 1; i <= N; i++) { // 진입차수 0인거 넣기
                if(weesang[i]==0) {
                    dq.offerLast(i);
                    dp[i]=buildingTime[i];
                }
            }

            int win = Integer.parseInt(br.readLine()); // 승리하기 위한 건물 숫자

            while(!dq.isEmpty()){
                int num = dq.pollFirst();
                if(num==win) { // 정답이 진입차수가 0인 경우 탈출
                    break;
                }
                int size = adjList[num].size(); // 인접리스트만큼 확인
                for (int i = 0; i < size; i++) { // 인접한거 진입차수 하나씩 깎기
                    int n = adjList[num].get(i);
                    weesang[n]--;
                    dp[n] = Math.max(dp[n], dp[num] + buildingTime[n]); // 가장 높은거 찾아서 넣어두기
                    if(weesang[n]==0) dq.offerLast(n);
                }
//                System.out.println(Arrays.toString(dp));
            }
            sb.append(dp[win]).append("\n"); // 정답 입력
        }

        System.out.println(sb);
    }
}
