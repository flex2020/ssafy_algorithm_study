package 강윤서.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7964 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] A = new int[N];
            int cnt = 0; // 연속된 0의 개수
            int answer =0; // 지어야 하는 차원관문의 최소 개수
            for (int i=0; i<N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                if (A[i] == 0) {
                    cnt += 1;
                } else {
                    cnt = 0;
                }
                if (cnt >= D) {
                    answer += 1;
                    cnt = 0;
                }
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
    
}
