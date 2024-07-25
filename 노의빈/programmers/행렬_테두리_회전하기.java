import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] arr = new int[rows+1][columns+1];
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=columns; j++) {
                arr[i][j] = (i - 1) * columns + j;
            }
        }
        
        for (int i=0; i<queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            int rcnt = queries[i][2] - x + 1;
            int ccnt = queries[i][3] - y + 1;
            int cnt = (ccnt + rcnt - 2) * 2;
            int d = 0;
            int min = Integer.MAX_VALUE;
            int prev = arr[x+1][y];
            for (int j=0; j<cnt; j++) {
                if (j == ccnt - 1) d = 1;
                else if (j == ccnt + rcnt - 2) d = 2;
                else if (j == 2*ccnt + rcnt - 3) d = 3;
                int nx = x + dx[d];
                int ny = y + dy[d];
                min = Math.min(min, arr[x][y]);
                int temp = arr[x][y];
                arr[x][y] = prev;
                x = nx;
                y = ny;
                prev = temp;
            }
            answer[i] = min;
        }
        
        return answer;
    }
}
