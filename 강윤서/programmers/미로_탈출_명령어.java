import java.util.*;
class Solution {
    static int N, M, goalR, goalC, goalDist;
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static char[] dd = {'d', 'l', 'r', 'u'};
    static String answer;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "z";
        N = n;
        M = m;
        goalR = r;
        goalC = c;
        goalDist = k;
        dfs(x, y, 0, "");
        if (answer.equals("z")) return "impossible";
        return answer;
    }
    public static void dfs(int r, int c, int dist, String path) {
        if (dist == goalDist) {
            if (r == goalR && c == goalC) {
                answer = path;
            }
            return ;
        }
        for (int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            String nextPath = path + dd[i];
            int remain = Math.abs(r-goalR) + Math.abs(c-goalC);
            if (1<=nr && nr<=N && 1<=nc && nc<=M && nextPath.compareTo(answer) < 1 && remain <= goalDist-dist && remain%2 == (goalDist-dist)%2) {
                dfs(nr, nc, dist+1, nextPath);
            }
        }
    }
}
