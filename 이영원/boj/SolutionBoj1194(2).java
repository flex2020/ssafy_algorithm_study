import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


public class Main {

    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static char[][] map;
    static boolean[][][] visited;

    static class Point{
        int x;
        int y;
        int cnt;
        int key;

        public Point(int x, int y, int cnt, int key) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }
    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer=0;
        int startX=0;
        int startY=0;

        map = new char[N+2][M+2];
        // key a, b, c, d, e, f를 위한 여섯자리 방문배열 마련 -> 000000
        visited = new boolean[N+2][M+2][1<<6];

        Arrays.fill(map[0], '#');
        Arrays.fill(map[N+1], '#');
        for (int i = 1; i < N+1; i++) {
            String input = br.readLine();
            for (int j = 1; j < M+1; j++) {
                map[i][j] = input.charAt(j-1);
                if(map[i][j]=='0'){
                    startX=i;
                    startY=j;
                }
            }
            map[i][0]='#';
            map[i][M+1] = '#';
        }

        answer = bfs(startX, startY);

        System.out.println(answer);
    }

    private static int bfs(int x, int y){
        int cnt=0;
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(x, y, 0, 0));
        visited[x][y][0]=true;
        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            if(map[p.x][p.y] == '1'){
                return p.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(map[nx][ny] != '#' && !visited[nx][ny][p.key]){
                    //  000 000
                    //| 000 001
                    //  000 001
                    if(map[nx][ny] >= 'a' && map[nx][ny]<='f'){ // 열쇠인 경우 p,key를 업데이트 해준다.(or 연산)
                        visited[nx][ny][p.key | (1 << map[nx][ny]-'a')] = true;
                        dq.offerLast(new Point(nx, ny, p.cnt+1, p.key | (1 << map[nx][ny]-'a')));
                        // 문이라면
                        // 있다면 통과 없다면 pass
                        //  101 001
                        //& 000 001
                        //  000 001 > 0
                    }else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F' && !visited[nx][ny][p.key]){ // 문인 경우
                        if((p.key & (1 << map[nx][ny]-'A')) != 0){ // 있으면 1 이상이 나온다.
                            visited[nx][ny][p.key]=true;
                            dq.offerLast(new Point(nx, ny, p.cnt+1, p.key));
                        }
                    }else if(!visited[nx][ny][p.key]){ // 0 . 1 인 경우, 그냥 방문체크만 하고 넣어준다.
                        visited[nx][ny][p.key]=true;
                        dq.offerLast(new Point(nx, ny, p.cnt+1, p.key));
                    }
                }
            }
        }
        return -1;
    }

}
