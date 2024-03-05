import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {

    static class Point{
        int x, y, type;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", type=" + type +
                    '}';
        }
    }

    static Point[] log;
    static Point[] goal;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 맵 크기
        map = new char[N][N];
        log = new Point[3];
        goal = new Point[3];
        int logIdx = 0;
        int goalIdx = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j]=='B'){ // 통나무 입력
                    log[logIdx++] = new Point(i, j);
                }else if(map[i][j]=='E'){ // 도착지 입력
                    goal[goalIdx++] = new Point(i, j);
                }
            }
        }

        if(log[0].x == log[1].x){
            log[0].type=0;
            log[1].type=0;
            log[2].type=0;
        }else{
            log[0].type=1;
            log[1].type=1;
            log[2].type=1;
        }

        if(goal[0].x == goal[1].x){
            goal[0].type=0;
            goal[1].type=0;
            goal[2].type=0;
        }else{
            goal[0].type=1;
            goal[1].type=1;
            goal[2].type=1;
        }

        int answer = bfs();
//        System.out.println(goal[1].x + " " + goal[1].y + " " + goal[1].type);
        System.out.println(answer);
    }

    private static int bfs(){
        int cnt=0;
        boolean flag=false;
        Deque<Point> dq = new ArrayDeque<>();
        boolean[][][] visited= new boolean[N][N][2];
        visited[log[1].x][log[1].y][log[1].type]=true; // visited 넣기
        dq.offerLast(new Point(log[1].x, log[1].y, log[1].type));
        L: while(!dq.isEmpty()){
            int size = dq.size(); // 시행횟수만큼 dq 돌리기
            for (int i = 0; i < size; i++) {
                Point p = dq.pollFirst();
                if(p.x==goal[1].x && p.y==goal[1].y && p.type == goal[1].type) {
                    flag = true;
                    break L;
                }
                for (int dir = 0; dir < 4; dir++) {
                    int nx = p.x + dx[dir];
                    int ny = p.y + dy[dir];
                    if(checkMove(nx, ny, p.type) && !visited[nx][ny][p.type]){ // 이동 가능한지랑 visited인지 확인
                        visited[nx][ny][p.type]=true;
                        dq.offerLast(new Point(nx, ny, p.type));
                    }
                }
                if(checkTurn(p.x, p.y) && !visited[p.x][p.y][(p.type+1)%2]){ // 회전 가능한지랑 visited인지 확인
                    visited[p.x][p.y][(p.type+1)%2]=true;
                    dq.offerLast(new Point(p.x, p.y, (p.type+1)%2));
                }
            }
//            System.out.println(dq);
            cnt++;
        }
        if(!flag) cnt=0; // 찾지 못하면 cnt==0
        return cnt;
    }

    private static boolean checkMove(int x, int y, int type){ // 갈 수 있는지 확인
        if(x<0 || x>=N || y<0 || y>=N || map[x][y]=='1'){
            return false;
        }

        if(type==0){
            if(y-1<0 || y+1>=N || map[x][y-1]=='1' || map[x][y+1]=='1'){
                return false;
            }
        }else{
            if(x-1<0 || x+1>=N || map[x-1][y]=='1' || map[x+1][y]=='1'){
                return false;
            }
        }
        return true;
    }

    private static boolean checkTurn(int x, int y){ // 돌 수 있는지 확인
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if(i<0 || i>=N || j<0 || j>=N || map[i][j]=='1'){
                    return false;
                }
            }
        }
        return true;
    }

}
