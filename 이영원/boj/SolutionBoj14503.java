import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dy = {0, 1, 0, -1}; // 상 우 하 좌

    static class Point{
        int x;
        int y;
        int dir;

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    '}';
        }
    }


    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // 청소기 시작좌표, 방향
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int startDir = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M]; // 방문체크배열

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer =1; // 처음위치는 청소할거니까 1
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(startX, startY, startDir)); // 처음 좌표값 넣어주기
        visited[startX][startY] = true;
        L: while(!dq.isEmpty()){ // dq가 빌때까지 진행
            Point p = dq.pollFirst();
//            System.out.println(p.toString());
            for (int i = 0; i < 4; i++) {
                // 사방탐색하면서 청소할 곳이 있는지 탐색
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                // 청소할 곳이 있다면 현재 방향 기준 반시계 방향으로 돌림
                if(map[nx][ny]==0 && !visited[nx][ny]){
                    int pdir = (p.dir-1<0) ? 3 : p.dir-1;
                    int px = p.x + dx[pdir];
                    int py = p.y + dy[pdir];
                    // 돌렸을 떄 그 앞의 값이 방문하지 않은 0인지 확인하고 맞다면 그 값을 큐에 넣고 answer를 올려준다.
                    if(map[px][py]==0 && !visited[px][py]) {
                        visited[px][py] = true;
                        dq.offerLast(new Point(px, py, pdir));
//                        System.out.println(px + " " + py + " " + pdir);
                        answer++;
                        break; // 더이상의 시행은 무의미하므로 사방탐색 반복문 탈출
                    } else{ // 돌렸을 때 방문하지 않은 0이 아니라면 방향만 바꿔주고 큐에 넣어준다.
                        dq.offerLast(new Point(p.x, p.y, pdir));
                        break; // 더이상의 시행은 무의미하므로 사방탐색 반복문 탈출
                    }
                }
                if(i==3){ // 사방탐색을 다 했는데도 없다면 현재방향기준 후방이 벽인지 확인
                    int px = p.x + dx[(p.dir+2)%4];
                    int py = p.y + dy[(p.dir+2)%4];
                    // 벽이 아니라면 그 방향으로 후진하여 Point를 큐에 넣는다.(방문되어있어도 상관없음)
                    if(map[px][py]==0) dq.offerLast(new Point(px, py, p.dir));
                    else break L; // 벽이라면 시행을 완전히 끝내기 위해 반복문 탈출
                }
            }
        }

        System.out.println(answer);



    }

}
