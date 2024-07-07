class Solution {
    static boolean[][][] board;
    static int[] dr = {-1, 0, 1, 0}; // U L D R
    static int[] dc = {0, -1, 0, 1};
    static int curR, curC;
    public int solution(String dirs) {
        int answer = 0;
        board = new boolean[11][11][4];
        curR = 5;
        curC = 5;
        char[] dirArray = dirs.toCharArray();
        for (char d : dirArray) {
            int nr = curR;
            int nc = curC;
            int direction = -1;
            
            if (d == 'U') direction = 0;
            else if (d == 'D') direction = 2;
            else if (d == 'R') direction = 3;
            else if (d == 'L') direction = 1;
            
            nr += dr[direction];
            nc += dc[direction];
            
            if (nr<0 || nr>=11 || nc<0 || nc>=11) continue;
            if (!board[nr][nc][direction] && !board[curR][curC][(direction+2)%4]) {
                answer++;
                board[nr][nc][direction] = true;
                board[curR][curC][(direction+2)%4] = true;
            }
            curR = nr;
            curC = nc;
        }
        return answer;
    }
}
