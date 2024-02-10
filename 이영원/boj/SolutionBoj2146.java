import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
import java.awt.Point;

public class Main {

    static int[][] map; // 입력받은 맵
    static int[][] realMap; // 넘버링한 맵
    static boolean[][] visited; // 넘버링 맵 만들 때 사용한 방문배열
    static int num; // 넘버링한 숫자
    static int N;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N+2][N+2];
        realMap = new int[N+2][N+2];
        visited = new boolean[N+2][N+2];
        num = 2;
        int answer=Integer.MAX_VALUE;

        // 주변을 -1로 채워서 테두리 조건 완화
        Arrays.fill(map[0], -1);
        Arrays.fill(map[N+1], -1);
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                realMap[i][j]=map[i][j];
            }
            map[i][0]=-1;
            map[i][N+1]=-1;
        }
        // bfs를 통해서 넘버링을 위한 realMap 만들기, 각 섬은 1이 아니라 2,3,4 넘버링이 될 것이다.
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if(realMap[i][j]==1 && !visited[i][j]){
                    visited[i][j]=true;
                    realMap[i][j]=num;
                    bfs(i, j);
                    num++;
                }
            }
        }

        // 넘버링된 섬들의 정보를 기반으로 각 외곽포인트마다 다른 섬에 연결할 수 있는
        // 가장 최소거리의 다리를 구하고 그것들의 최솟값을 answer에 저장한다.
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(map[nx][ny]!=-1 && realMap[i][j]!=0 && realMap[i][j]!=realMap[nx][ny]){
                        answer = Math.min(answer, findRoute(i, j, realMap[i][j]));
                    }
                }
            }
        }

        System.out.println(answer);
    }

    // 해당 포인트에서 가장 최소거리의 다리를 구하는 bfs 메소드
    private static int findRoute(int x, int y, int num){
        int cnt=-1;
        boolean[][] v = new boolean[N+2][N+2];
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(x, y));
        v[x][y]=true;
        while(!dq.isEmpty()){
            int size = dq.size();
            cnt++;
            for(int s = 0; s<size; s++){
                Point p = dq.pollFirst();
                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if(map[nx][ny]!=-1 && realMap[nx][ny]!=num && !v[nx][ny]){
                        if(realMap[nx][ny]!=0) {
//                            System.out.println("nx = " + nx + " ny = " + ny + " realMap[nx][ny] = " + realMap[nx][ny] + " cnt = " + cnt);
                            return cnt;
                        }
                        dq.offerLast(new Point(nx, ny));
                        v[nx][ny]=true;
                    }
                }
            }
        }
        return cnt;
    }

    // 넘버링 배열인 realMap을 만들기 위한 bfs 메소드
    private static void bfs(int x, int y){
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(x, y));
        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(map[nx][ny]==1 && realMap[nx][ny]==1 && !visited[nx][ny]){
                    dq.offerLast(new Point(nx, ny));
                    visited[nx][ny]=true;
                    realMap[nx][ny]=num;
                }
            }
        }
    }


}
