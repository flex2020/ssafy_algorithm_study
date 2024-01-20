package 강윤서.swea;

import java.util.*;
public class Solution_2805 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc=1; tc<=T; tc++) {
            int answer = 0;
            int N = sc.nextInt();
            int[][] map = new int[N][N];
            for (int i=0; i<N; i++) {
                String[] str = sc.next().split("");
                for (int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(str[j]);
                }
            }
            int start = N/2;
            int end = N/2;
            for (int i=0; i<N; i++) {
                for (int j=start; j<end+1; j++) {
                    answer += map[i][j];
                }
                if (i<N/2) {
                    start -= 1;
                    end += 1;
                } else {
                    start += 1;
                    end -= 1;
                }
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}
