package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_14888 {
    static int N, maxAnswer = Integer.MIN_VALUE, minAnswer = Integer.MAX_VALUE;
    static Deque<Integer> DQ;
    static int[] A, op;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        DQ = new ArrayDeque<>();
        A = new int[N];
        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            // Q.offer(Integer.parseInt(st.nextToken()));
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, A[0]);
        System.out.println(maxAnswer);
        System.out.println(minAnswer);
    }
    public static void dfs(int idx, int result) {
        if (idx == N-1) {
            maxAnswer = Math.max(maxAnswer, result);
            minAnswer = Math.min(minAnswer, result);
            return ;
        }
        for (int o=0; o<4; o++) {
            if (op[o] > 0) {
                int num1 = result;
                int num2 = A[idx+1];
                if (o == 0) {
                    op[o]--;
                    dfs(idx+1, num1 + num2);
                    op[o]++;
                } else if (o == 1) {
                    op[o]--;
                    dfs(idx+1, num1 - num2);
                    op[o]++;
                } else if (o == 2) {
                    op[o]--;
                    dfs(idx+1, num1 * num2);
                    op[o]++;
                } else {
                    op[o]--;
                    dfs(idx+1, num1 / num2);
                    op[o]++;
                }
            }
        }
    }
}
