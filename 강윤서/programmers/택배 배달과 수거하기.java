package 강윤서.programmers;
import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int boxCnt = 0;
        int emptyCnt = 0;
        int cnt = 0;
        for (int i=n-1; i>=0; i--) {
            cnt = 0;
            boxCnt += deliveries[i];
            emptyCnt += pickups[i];
            while (boxCnt > 0 || emptyCnt > 0) {
                boxCnt -= cap;
                emptyCnt -= cap;
                cnt++;
            }
            answer += (i+1) * 2 * cnt;
            System.out.println(cnt + " " + answer + " " + boxCnt + " " + emptyCnt);
        }
        return answer;
    }
}
