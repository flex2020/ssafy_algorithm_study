class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int[][] map = new int[N+1][M+1];
        for (int[] cur : skill) {
            int value = cur[5] * (cur[0] == 1 ? -1 : 1);
            map[cur[1]][cur[2]] += value;
            map[cur[1]][cur[4]+1] += (-1) * value;
            map[cur[3]+1][cur[2]] += (-1) * value;
            map[cur[3]+1][cur[4]+1] += value;
        }
        for (int i=0; i<map.length; i++) {
            for (int j=1; j<map[i].length; j++) {
                map[i][j] += map[i][j-1];
            }
        }
        for (int j=0; j<map[0].length; j++) {
            for (int i=1; i<map.length; i++) {
                map[i][j] += map[i-1][j];
            }
        }
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                board[i][j] += map[i][j];
                if (board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}
