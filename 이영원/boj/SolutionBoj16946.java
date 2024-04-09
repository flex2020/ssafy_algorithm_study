import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int N, M;

    static int[][] map; // 입력 맵
    static int[][] answer; // 정답 맵
    static boolean[][] visited; // 방문배열

    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        map = new int[N][M];
        answer = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.charAt(j)+"");
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==1) answer[i][j]++; // 그 자리에 있으면 +1
                else if(!visited[i][j]) { // 방문안한 빈공간일 경우 bfs 진행
                    bfs(i, j);
                }
            }
        }

        print(answer);

    }

    private static void bfs(int x, int y){
        Deque<Point> dq = new ArrayDeque<>();
        Deque<Point> wall = new ArrayDeque<>();
        dq.offerLast(new Point(x, y));
        visited[x][y]=true;

        int num=1;
        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny]) continue;
                if(map[nx][ny]==1 && !visited[nx][ny]){ // bfs에서 칠했을 때 인접하는 벽 넣기
                    visited[nx][ny]=true;
                    wall.offerLast(new Point(nx, ny));
                    continue;
                }
                visited[nx][ny]=true;
                num++;
                dq.offerLast(new Point(nx, ny));
            }
        }

        while(!wall.isEmpty()){ // 벽에 인접 카운트 더해주기
            Point p = wall.pollFirst();
            visited[p.x][p.y]=false; // 다른 bfs에서 칠해질수도 있으니 false로 바꿔주기
            answer[p.x][p.y]+=num;
        }
    }

    // 출력
    private static void print(int[][] map){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]/10>=1?map[i][j]%10:map[i][j]); // 나머지 연산
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
