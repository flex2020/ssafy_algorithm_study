package 강윤서.swea;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_9229_2 {
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
            Arrays.sort(A);

            int answer = -1;
            int start = 0, end = N-1;
            while (start < end) {
                if (A[start] + A[end] <= M) {
                    answer = Math.max(answer, A[start]+A[end]);
                    start += 1;
                } else {
                    end -= 1;
                }
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}
