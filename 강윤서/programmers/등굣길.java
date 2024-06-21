class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] board = new int[n+1][m+1];
        boolean[][] cantMove = new boolean[n+1][m+1];
        for (int[] puddle : puddles) {
            cantMove[puddle[1]][puddle[0]] = true;
        }
        for (int i=1; i<n+1; i++) {
            for (int j=1; j<m+1; j++) {
                if (i == 1 && j == 1) {
                    board[i][j] = 1;
                    continue;
                }
                if (!cantMove[i-1][j] && !cantMove[i][j-1]) {
                    board[i][j] = (board[i-1][j] + board[i][j-1]) % 1000000007;
                } else if (cantMove[i-1][j] && !cantMove[i][j-1]) {
                    board[i][j] = board[i][j-1] % 1000000007;
                } else if (cantMove[i][j-1] && !cantMove[i-1][j]) {
                    board[i][j] = board[i-1][j] % 1000000007;
                }
            }
        }
        return board[n][m] % 1000000007;
    }
}
