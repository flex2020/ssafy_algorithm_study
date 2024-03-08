import java.util.*;
import java.io.*;

public class SolutionBaekJoon1520 {

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int M, N;
    static int[][] map;
    static int[][] memo;
    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // 지도 정보 입력받기
        map = new int[M + 2][N + 2];
        for (int i = 1; i < map.length - 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < map[0].length - 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // memoization
        memo = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        memo[M][N] = 1;
        calculateResult(1, 1);

        System.out.println(memo[1][1]);
    }

    public static int calculateResult(int x, int y) {
        // 이미 경로 수가 있는 경우
        if (memo[x][y] > -1) {
            return memo[x][y];
        }

        // 갈 수 있는 곳으로 이동하기
        boolean canGo = false;
        for (int direction = 0; direction < dx.length; direction++) {
            int nextX = x + dx[direction];
            int nextY = y + dy[direction];

            if (map[nextX][nextY] > 0 && map[nextX][nextY] < map[x][y]) {
                if (!canGo) {
                    canGo = true;
                    memo[x][y] = 0;
                }
                memo[x][y] += calculateResult(nextX, nextY);
            }
        }

        // 갈 수 있는 곳이 없는 경우
        if (!canGo) {
            memo[x][y] = 0;
        }

        return memo[x][y];
    }

}
