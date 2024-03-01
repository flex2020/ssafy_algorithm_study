import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static int R, C, answer;
    static char[][] map;
    static boolean[][] visited;
    static Deque<Point> hedgehog;
    static Deque<Point> water;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int time=0;
        water = new ArrayDeque<>();
        hedgehog = new ArrayDeque<>();
        visited = new boolean[R+2][C+2];
        map = new char[R+2][C+2]; // 테두리 채우기
        boolean flag=false;

        Arrays.fill(map[0], 'X');
        Arrays.fill(map[R+1], 'X');
        for (int i = 1; i < R+1; i++) {
            String input = br.readLine();
            for (int j = 1; j < C+1; j++) {
                map[i][j] = input.charAt(j-1);
                if(map[i][j]=='*') water.offerLast(new Point(i, j));
                else if(map[i][j]=='S') hedgehog.offerLast(new Point(i, j));
            }
            map[i][0] = 'X';
            map[i][C+1] = 'X';
        }

        W: while(true){
            time++;
            // 물 이동
            int size = water.size();
            for (int i = 0; i < size; i++) {
                Point p = water.pollFirst();
                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];
                    if(!visited[nx][ny] && map[nx][ny]=='.'){
                        water.offerLast(new Point(nx, ny));
                        visited[nx][ny]=true;
                        map[nx][ny]='*';
                    }
                }
            }

            // 고슴도치 이동(다음 예상지역에는 불가능)
            size = hedgehog.size();
            if(size==0) {
                flag=true;
                break;
            }
            for (int i = 0; i < size; i++) {
                Point p = hedgehog.pollFirst();
                if(map[p.x][p.y]=='D') break W;
                L: for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];
                    if(!visited[nx][ny] && map[nx][ny]=='.'){
                        for (int k = 0; k < 4; k++) {
                            int px = nx + dx[k];
                            int py = ny + dy[k];
                            if(map[nx][ny] == '*'){
                                break L;
                            }
                            if(k==3){
                                hedgehog.offerLast(new Point(nx, ny));
                                visited[nx][ny]=true;
                            }
                        }
                    }else if(map[nx][ny]=='D'){
                        visited[nx][ny]=true;
                        hedgehog.offerLast(new Point(nx, ny));
                    }
                }
            }
        }

        if(flag){
            System.out.println("KAKTUS");
        }else{
            System.out.println(time-1);
        }
    }
}
