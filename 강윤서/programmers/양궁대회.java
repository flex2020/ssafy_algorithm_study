import java.util.*;
class Solution {
    // 라이언이 화살을 쏘기 전에 이길 수 있는 경우의 수를 구하고
    // 그 경우의 수를 충족하도록 쏘기
    static int[] ryan; // 1: 라이언 승 / 0: 라이언 패
    static int diff;
    static boolean possible;
    static int[] B, answer;
    public int[] solution(int n, int[] info) {
        B = new int[11];
        answer = new int[11];
        ryan = new int[11];
        
        find(0, n, info);

        if (!possible) return new int[] {-1};
        return answer;
    }
    public static void find(int depth, int n, int[] info) {
        if (depth == 11) {
            int score1 = 0; // 어피치 점수
            int score2 = 0; // 라이언 점수
            for (int i=0; i<11; i++) {
                if (ryan[i] == 0 && info[i] > 0) score1 += 10 - i;
                else if (ryan[i] == 1) score2 += 10 - i;
            }
            if (score1 < score2) { // 라이언이 이길 수 있는 경우
                if (diff > score2 - score1) return ;
                int cnt = 0; // 라이언이 화살을 쏜 횟수
                for (int i=0; i<10; i++) {
                    if (ryan[i] == 1) {
                        B[i] = info[i] + 1;
                        cnt += B[i];
                    } else {
                        B[i] = 0;
                    }
                }
                if (cnt > n) return ;
                else if (cnt < n) {
                    B[10] = n - cnt;
                } else {
                    B[10] = 0;
                }
                possible = true;
                if (diff < score2 - score1) {
                    diff = score2 - score1;
                    for (int i=0; i<11; i++) answer[i] = B[i];
                } else {
                    boolean flag = false;
                    for (int i=10; i>=0; i--) {
                        if (answer[i] == B[i]) continue;
                        if (answer[i] < B[i]) flag = true; 
                        break;
                    }
                    if (flag)
                        for (int i=0; i<11; i++) answer[i] = B[i];
                }
            }
            return ;
        }
        for (int i=1; i>=0; i--) {
            ryan[depth] = i;
            find(depth+1, n, info);
        }
    }
}
