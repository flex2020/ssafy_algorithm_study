class Solution {
    public int[] solution(int n) {
        int size = n * (n + 1) / 2;
        int[] answer = new int[size];
        int[][] temp = new int[n][n];
        int[] dx = {1, 0, -1}, dy = {0, 1, -1};
        int target = n;
        int d = 0;
        int x = 0, y = 0;
        int diff = n-1;
        for (int i=1; i<=size; i++) {
            temp[x][y] = i;
            if (i == target) {
                target += diff;
                diff -= 1;
                d = (d + 1) % 3;
            }
            x += dx[d];
            y += dy[d];
        }
        int idx = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (temp[i][j] != 0) answer[idx++] = temp[i][j];
            }
        }
        
        return answer;
    }
}
