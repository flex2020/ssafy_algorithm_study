import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.awt.Point;

public class Main {

    static int N, M;
    static char[][] map;
    static int[][] visited;
    static int num;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new int[N][M];
        num=0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j]!=0) continue;
                paint(i, j, ++num); // 칠하기
//                print(visited);
            }
        }

        System.out.println(num);
    }

    private static void paint(int x, int y, int num){
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(x, y));
        visited[x][y]=num;
        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            Point moveP = movePoint(p.x, p.y, map[p.x][p.y]); // 해당방향에 있는곳 칠하고 dq에 넣기
            if(visited[moveP.x][moveP.y]==0){
                visited[moveP.x][moveP.y] = num;
                dq.offerLast(new Point(moveP.x, moveP.y));
            }

            // 사방탐색하면서 현재 포인트로 들어오는 점이 있는지를 확인 후 있으면 칠하고 dq에 넣기
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny]!=0) continue;
                moveP = movePoint(nx, ny, map[nx][ny]);
                if(moveP.x==p.x && moveP.y==p.y){
                    visited[nx][ny] = num;
                    dq.offerLast(new Point(nx, ny));
                }
            }
        }
    }

    // 점 이동하는 메소드
    private static Point movePoint(int x, int y, char c){
        int nx=-1;
        int ny=-1;
        switch(c){ // 해당 방향은 100% 포함이므로 포함시킨다.
            case 'U':
                nx = x + dx[3];
                ny = y + dy[3];
                break;
            case 'D':
                nx = x + dx[1];
                ny = y + dy[1];
                break;
            case 'L':
                nx = x + dx[2];
                ny = y + dy[2];
                break;
            case 'R':
                nx = x + dx[0];
                ny = y + dy[0];
                break;
        }
        if(nx<0 || nx>=N || ny<0 || ny>=M){
            nx=x;
            ny=y;
        }
        return new Point(nx, ny);
    }

    private static void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
