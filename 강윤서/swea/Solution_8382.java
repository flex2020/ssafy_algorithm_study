package 강윤서.swea;

import java.util.*;
public class Solution_8382 {
    static int T, x1, y1, x2, y2;
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int tc=1; tc<=T; tc++) {
            int answer = 0;
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            
            int dist1 = Math.abs(x1-x2);
            int dist2 = Math.abs(y1-y2);
            if (dist1 % 2 == dist2 % 2) 
                answer = Math.max(dist1, dist2) * 2;
            else
                answer = Math.max(dist1, dist2) * 2 - 1;
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}
