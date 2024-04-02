import java.util.*;
import java.io.*;

public class Main {

    static int N;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int[][] cables = new int[N][2];

        for (int i = 0; i < cables.length; i++) {
            st = new StringTokenizer(br.readLine());
            cables[i][0] = Integer.parseInt(st.nextToken());
            cables[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1]; // 해당 포인트에서 최대 몇개가 최장증가수열인지 dp
        int[] record = new int[N+1]; // 끝점으로 업데이트되는 record 배열
        boolean[] check = new boolean[N+1]; // 실제로 최장증가수열에 포함되는 수인지 연산하기 위한 배열

        Arrays.sort(cables, (a,b) -> { // 시작점 기준 오름차순 정렬
            return a[0]-b[0];
        });

        int len = 0;
        int n=0;
        // 이진탐색으로 최장증가수열(LIS) 적용
        for (int i = 0; i < cables.length; i++) {
            int num = binarySearch(cables[i][1], 0, len, record);
            if(num>len) { // 가장 오른쪽에 들어갈 수인 경우 len 늘려주기
                len++;
            }
            record[num] = cables[i][1]; // record에 현재 포인트에서의 최장증가수열 길이를 기록
            dp[i+1]=num;
            n = Math.max(n, num); // 길이를 구하려고 넣은건가 싶은데 이건 왜넣었을까?

//			System.out.println(Arrays.toString(dp));
//			System.out.println(Arrays.toString(record));
//			System.out.println();
        }
        System.out.println(N-len); // 빼야하는 개수
        StringBuilder sb = new StringBuilder();
        int idx= N;
        int num = n;
        // 오름차순으로 넣기 위해 실제로 들어가는 포인트는 true, 나머지는 false로 유지
        for (int i = N; i >= 1; i--) {
            if(dp[idx]==num) {
                check[idx]=true;
                idx--;
                num--;
                continue;
            }
            idx--;
        }

//    	System.out.println(Arrays.toString(check));
        // 한번 탐색하면서 false인 것들을 sb에 추가
        for (int i = 1; i < check.length; i++) {
            if(!check[i]) {
                sb.append(cables[i-1][0]).append("\n");
            }
        }
        // 출력
        System.out.println(sb);
    }

    // 이진탐색(만들어놓고 왜 되는지 모르겠음. 더 공부 필요)
    private static int binarySearch(int key, int low, int high, int[] dp) {
        int mid=0;

        while(low<=high) {
            mid = (low+high)/2;

            if(key < dp[mid]) {
                high=mid-1;
            }else {
                low = mid+1;
            }
        }
        return high+1;
    }
}
