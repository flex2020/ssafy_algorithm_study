package 강윤서.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1869 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] person = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++) {
                person[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(person); // 도착시간 순대로 정렬
            boolean flag = true;
            int time = M, cnt = K, idx = 0; // 현재 시간, 만들어져있는 붕어빵 개수, person을 탐색할 idx
            while (flag && idx < N) {
                while (person[idx] > time) {
                    // 그 다음 손님의 도착시간까지 시간을 흐르게 함
                    time += M;
                    cnt += K;
                }
                if (person[idx] < time && cnt-K < 1) {
                    flag = false;
                }
                else if (cnt == 0) {
                    // 손님에게 줄 붕어빵이 없음
                    flag = false;
                } else {
                    cnt -= 1;
                    idx += 1;
                }
            }
            if (flag) {
                System.out.println("#"+tc+" Possible");
            } else {
                System.out.println("#"+tc+" Impossible");
            }
        }
    }
}
