import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int N, M;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    static int[][] map;
    static int[][] memo;
    static int answer;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        answer = 0;

        map = new int[N][M];
        memo = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                if(c=='H') map[i][j] = -1;
                else map[i][j] = c-'0';
            }
        }

        dfs(0, 0, 0);

        System.out.println(answer);
    }

    private static boolean dfs(int x, int y, int cnt){

        answer = Math.max(answer, cnt+1);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i]*map[x][y];
            int ny = y + dy[i]*map[x][y];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny]==-1 || memo[nx][ny] > cnt) continue;
            memo[nx][ny]=cnt+1;

            if(visited[nx][ny]) { // 방문한 경우 answer를 -1로 만들고 리턴
                answer=-1;
                return true;
            }
            
            visited[nx][ny] = true;
            if(dfs(nx, ny, cnt+1)) return true;
            visited[nx][ny] = false;
        }
        return false;
    }
}
