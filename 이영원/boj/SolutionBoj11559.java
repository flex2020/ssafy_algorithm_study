import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.Point;

public class Main {

    static char[][] map;
    static int answer, N;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        int combo = 0;

        map = new char[12][6];
        for (int i = 0; i < map.length; i++) {
            String input = br.readLine();
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        while(true){
            boolean flag = false;
            boolean[][] visited = new boolean[12][6];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if(map[i][j]!='.'){
                        if(bfs(i, j, visited)){ // 색깔을 찾았다면 bfs 진행
                            flag = true;
                        }
//                        print(map);
                    }
                }
            }
            if(!flag) break;
            else{ // 지워진게 있다면 콤보 올리고 내려
                combo++;
                down();
            }
        }
        System.out.println(combo);
    }

    private static boolean bfs(int x, int y, boolean[][] visited){
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(x, y));
        visited[x][y] = true;

        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(x, y));

        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && map[nx][ny]==map[x][y] && !visited[nx][ny]){
                    visited[nx][ny]=true;
                    dq.offerLast(new Point(nx, ny));
                    pointList.add(new Point(nx, ny));
                }
            }
        }

        if(pointList.size()>=4){ // point가 4개 이상이면 없앤다.
            for(Point p : pointList){
                map[p.x][p.y] = '.';
            }
            return true;
        }
        return false;
    }

    private static void down(){
        int startX = 11;
        int startY = 0;
        int plusX = -1;
        int plusY = 1;

        int edge = 11;

        for (int i = startY; i < 6; i += plusY) {
            edge = 11;
            L: while (edge > 0) {
                int nx = edge;
                int ny = i;
                if(map[edge][i] != '.'){
                    edge--;
                    continue;
                }
                for (int j = edge; j > 0; j += plusX) {
                    nx += dx[3];
                    ny += dy[3];
                    if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && map[nx][ny] != '.') {
                        map[edge--][i] = map[nx][ny];
                        map[nx][ny] = '.';
                    }
                    if(j==1) break L;
                }
            }
        }
    }

    private static void print(char[][] map){
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
