import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int M, N;
    static int[][] map; // 필드
    static int[][] dp; // dp
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 행
        N = Integer.parseInt(st.nextToken()); // 열

        map = new int[M][N];
        dp = new int[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
//        print();
        // 아예 실패한 경우 -1이 될 수 있으므로 0으로 초기화
        System.out.println(dp[0][0] < 0 ? 0 : dp[0][0]);
    }

    private static boolean dfs(int x, int y) {
        boolean flag = false; // flag인지 확인
//        print();
        // 목적지에 처음으로 도착한 경우 목적지 업데이트, visited 업데이트 후 true 리턴
        if ((x == M - 1 && y == N - 1)) {
            dp[M - 1][N - 1] = 1;
            visited[x][y] = true;
            return true;
        }

        for (int i = 0; i < 4; i++) { // 네가지 방향에 대해서 진행
            int nx = x + dx[i];
            int ny = y + dy[i];

            // map 범위 바깥이거나, 높이가 높거나 같은경우, 혹은 이전에 글로 가봤는데 답이 없는(-1) 경우 continue
            if (nx < 0 || nx >= M || ny < 0 || ny >= N || map[nx][ny] >= map[x][y] || dp[nx][ny] == -1)
                continue;
            // 갈 수는 있는데 이미 방문이 진행된 경우, 거기 있는 dp를 기반으로 현재좌표 dp 업데이트(flag는 true)
            if (visited[nx][ny]) {
                dp[x][y] += dp[nx][ny];
                visited[x][y] = true;
                flag = true;
                continue;
            }
            // 방문이 한번도 안된 경우 dfs로 재귀 진행
            if (dfs(nx, ny)) {
                visited[x][y] = true;
                dp[x][y] += dp[nx][ny];
                flag = true;
            }
        }
        // flag가 true인 경우 가능한 곳이라는 뜻이므로 return true
        if (flag) {
            return true;
        } else { // false인 경우 여긴 답이 없으니까 dp를 -1로 업데이트
            dp[x][y] = -1;
        }
        // 전부다 시행했는데 안된 경우이므로 return false
        return false;
    }

    private static void print() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
