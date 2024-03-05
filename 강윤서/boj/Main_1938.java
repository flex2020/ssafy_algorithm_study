package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_1938 {
    static int N, answer;
    static char[][] board;
    static boolean[][][] visited;
    static List<int[]> startCheck, endCheck;
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1}, dc = {0, 0, -1, 1, -1, 1, -1, 1};
    static Log endLog;
    static Queue<Log> Q;
    static class Log {
        int centerR, centerC, dir, cnt; // dir - 0: 가로, 1: 세로
        Log (int centerR, int centerC, int dir, int cnt) {
            this.centerR = centerR;
            this.centerC = centerC;
            this.dir = dir;
            this.cnt = cnt;
        }
        Log (int centerR, int centerC, int dir) {
            this.centerR = centerR;
            this.centerC = centerC;
            this.dir = dir;
        }
        @Override
        public String toString() {
            return "centerR: " + centerR + " centerC: " + centerC + " dir: " + dir + " cnt: " + cnt; 
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        visited = new boolean[N][N][2];
        startCheck = new ArrayList<>();
        endCheck = new ArrayList<>();
        Q = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<N; j++) {
                board[i][j] = input[j];
                if (board[i][j] == 'B') {
                    startCheck.add(new int[] {i, j});
                } else if (board[i][j] == 'E') {
                    endCheck.add(new int[] {i, j});
                }
            }
        }
        if (startCheck.get(0)[0] == startCheck.get(1)[0]) { // 가로
            Q.offer(new Log(startCheck.get(1)[0], startCheck.get(1)[1], 0, 0));
        } else { // 세로
            Q.offer(new Log(startCheck.get(1)[0], startCheck.get(1)[1], 1, 0));
        }
        if (endCheck.get(0)[0] == endCheck.get(1)[0]) { // 가로
            endLog = new Log(endCheck.get(1)[0], endCheck.get(1)[1], 0);
        } else { // 세로
            endLog = new Log(endCheck.get(1)[0], endCheck.get(1)[1], 1);
        }
        visited[Q.peek().centerR][Q.peek().centerC][Q.peek().dir] = true;
        while (!Q.isEmpty()) {
            Log cur = Q.poll();
            // 도착점 도달
            if (cur.centerR == endLog.centerR && cur.centerC == endLog.centerC && cur.dir == endLog.dir) {
                answer = cur.cnt;
                break;
            }
            for (int i=0; i<5; i++) {
                if (i==4) { // 회전
                    if (rotateCheck(cur.centerR, cur.centerC, cur.dir)) {
                        Q.offer(new Log(cur.centerR, cur.centerC, (cur.dir+1)%2, cur.cnt+1));
                        visited[cur.centerR][cur.centerC][(cur.dir+1)%2] = true;
                    }
                } else {
                    int nr = cur.centerR + dr[i];
                    int nc = cur.centerC + dc[i];
                    if (check(nr, nc, cur.dir)) {
                        Q.offer(new Log(nr, nc, cur.dir, cur.cnt+1));
                        visited[nr][nc][cur.dir] = true;
                    }
                }
            }
        }
        System.out.println(answer);
    }
    public static boolean rotateCheck(int r, int c, int dir) {
        if (visited[r][c][(dir+1)%2]) return false; // 이미 해당 방향으로 방문
        for (int i=0; i<8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr<0 || nr>=N || nc<0 || nc>=N || board[nr][nc] == '1') return false;
        }
        return true;
    }
    public static boolean check(int r, int c, int dir) {
        /*
         * [ 옮길 수 있는 조건 ]
         * 1. 양 끝이 맵을 벗어나지 않음
         * 2. 중심점이 해당 방향으로 탐색하지 않음
         * 3. 이동하고자 하는 점들 모두가 '1'이 아니어야 한다.
         */
        if (dir == 0) { // 가로
            if (r<0 || r>=N || c<=0 || c>=N-1 || visited[r][c][dir] || board[r][c-1] == '1' || board[r][c] == '1' || board[r][c+1] == '1') return false;
            return true;
        } else { // 세로
            if (r<=0 || r>=N-1 || c<0 || c>=N || visited[r][c][dir] || board[r-1][c] == '1' || board[r][c] == '1' || board[r+1][c] == '1') return false;
            return true;
        }
    }
}
