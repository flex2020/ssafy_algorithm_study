package 강윤서.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_9229_1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Integer[] A = new Integer[N];

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A, Collections.reverseOrder()); // 내림차순 정렬
            int answer = 0;
            for (int i=0; i<N-1; i++) {
                for (int j=i+1; j<N; j++) {
                    if (A[i] + A[j] <= M) {
                        answer = Math.max(answer, A[i]+A[j]);
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}
