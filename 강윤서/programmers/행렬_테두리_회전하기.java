class Solution {
    static int[][] board;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int[] solution(int rows, int columns, int[][] queries) {
        board = new int[rows+1][columns+1];
        int number = 1;
        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=columns; j++) {
                board[i][j] = number++;
            }
        }
        int[] answer = new int[queries.length];
        for (int i=0; i<queries.length; i++) {
            answer[i] = rotate(queries[i]);
        }
        
        return answer;
    }
    public static void print() {
        System.out.println("===");
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int rotate(int[] query) {
        int cr = query[0];
        int cc = query[1];
        int first = board[cr][cc];
        int nr = cr;
        int nc = cc;
        int i = 0; // dr, dc 인덱스
        int minValue = first;
        while (true) {
            nr += dr[i];
            nc += dc[i];
            if (query[0]<=nr && nr<=query[2] && query[1]<=nc && nc<=query[3]) {
                board[cr][cc] = board[nr][nc];
                minValue = Math.min(minValue, board[nr][nc]);
                cr = nr;
                cc = nc;
            } else {
                nr -= dr[i];
                nc -= dc[i];
                if (i == 3) break;
                i++;
            }
        }
        board[query[0]][query[1] + 1] = first;
        return minValue;
    }
}
