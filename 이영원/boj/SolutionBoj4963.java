import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.awt.Point;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] visited;
    static int[][] graph;
    // 팔방탐색
    static int[] dx = {0,1,1,1,0,-1,-1,-1};
    static int[] dy = {1,1,0,-1,-1,-1,0,1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); // 열
            int h = Integer.parseInt(st.nextToken()); // 행
            int answer=0;

            if(w==0 && h==0) break; // 둘다 0이면 멈추기

            // 주변을 -1로 채워넣어서 테두리 조건 간편화
            graph = new int[h+2][w+2];
            visited = new boolean[h+2][w+2];

            Arrays.fill(graph[0], -1);
            Arrays.fill(graph[h+1], -1);
            for (int i = 1; i < h+1; i++) { // 섬 정보 입력
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < w+1; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
                graph[i][0] = -1;
                graph[i][w+1]=-1;
            }

            for (int i = 1; i < h+1; i++) {
                for (int j = 1; j < w+1; j++) {
                    if(graph[i][j]==1 && !visited[i][j]){ // 아직 방문하지 않은 1이라면 answer를 올리고 bfs 진행
                        visited[i][j]=true;
                        bfs(i, j);
                        answer++;
                    }
                }
            }

            System.out.println(answer);

        }

    }

    private static void bfs(int x, int y){
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(x, y));
        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            for (int i = 0; i < 8; i++) {
                int nx = p.x+dx[i];
                int ny = p.y+dy[i];
                // 테두리가 아니고, 1이라면 방문
                if(graph[nx][ny]!=-1 && graph[nx][ny]!=0 && !visited[nx][ny]){
                    visited[nx][ny]=true;
                    dq.offerLast(new Point(nx, ny));
                }
            }
        }

    }

}
