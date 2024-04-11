import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // 우 우상 상 좌상 좌 좌하 하 우하
    static int[] dy = {0, 1, 1, 0, -1, -1, -1, 0, 1}; // 우 우상 상 좌상 좌 좌하 하 우하
    static int[][] map;
    static int[][] move;
    static Deque<Point> dq;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 격자크기
        m = Integer.parseInt(st.nextToken()); // 몇년
        int answer=0;

        map = new int[n][n];
        move = new int[m][2];
        dq = new ArrayDeque<>();

        for(int i=0;i<n;i++){ // 격자입력
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<m;i++){ // 이동입력
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken());
            move[i][1] = Integer.parseInt(st.nextToken());
        }

        dq.offerLast(new Point(n-1, 0));
        dq.offerLast(new Point(n-1, 1));
        dq.offerLast(new Point(n-2, 0));
        dq.offerLast(new Point(n-2, 1));

        for(int i=0;i<m;i++){
            move(move[i][0], move[i][1]);
            grow();
            make();
            System.out.println(dq.toString());
        }

        for(int i=0;i<n;i++){ // 더하기
            for(int j=0;j<n;j++){
                answer+=map[i][j];
            }
        }

        System.out.println(answer);
    }

    private static void move(int dir, int cnt){
        int size = dq.size();
        for(int t=0;t<size;t++){
            Point p = dq.pollFirst();
            int nx = p.x;
            int ny = p.y;
            for(int i=0;i<cnt;i++){
                nx+=dx[dir];
                ny+=dy[dir];
                if(nx<0){
                    nx=n-1;
                }else if(nx>=n){
                    nx=0;
                }else if(ny<0){
                    ny=n-1;
                }else if(ny>=n){
                    ny=0;
                }
            }
            dq.offerLast(new Point(nx, ny));
        }

    }

    private static void grow(){
        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            map[p.x][p.y]++;
            for(int i=2;i<=8;i+=2){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny]<1) continue;
                map[p.x][p.y]++;
            }
        }
    }

    private static void make(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]>=2){
                    dq.offerLast(new Point(i, j));
                    map[i][j]-=2;
                }
            }
        }
    }
}
