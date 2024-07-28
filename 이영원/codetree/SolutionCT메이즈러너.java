import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int N, M, K, escapeX, escapeY;
    static int[][] maze;

    static class Player{
        int x, y, dis; // x, y 좌표, 이동거리
        boolean escape; // 탈출했는지 아닌지

        Player(int x, int y){
            this.x = x;
            this.y = y;
            this.escape = false;
            this.dis=0;
        }

        @Override
        public String toString(){
            return x + " " + y + " " + escape + " " + dis;
        }
    }

    static int escapeCnt; // 총 탈출 수
    static Player[] players;

    static int[] dx = {0, 0, -1, 1}; // 좌 우 상 하
    static int[] dy = {-1, 1, 0, 0}; // 좌 우 상 하

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 미로 크기
        M = Integer.parseInt(st.nextToken()); // 참가자 수
        K = Integer.parseInt(st.nextToken()); // 게임 시간
        escapeCnt = 0;
        int answer = 0;

        maze = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        players = new Player[M];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            players[i] = new Player(x-1, y-1);
        }

        st = new StringTokenizer(br.readLine());
        escapeX = Integer.parseInt(st.nextToken())-1;
        escapeY = Integer.parseInt(st.nextToken())-1;
        maze[escapeX][escapeY] = -1;

        for(int i=0;i<K;i++){
            for(int j=0;j<M;j++){
                if(players[j].escape) continue;
                playerMove(j);
            }

            //  print();

            if(escapeCnt==M) break;

            mazeMove();

            //  print();



        }

        // 플레이어 이동횟수의 합
        for(int i=0;i<M;i++){
            answer += players[i].dis;
        }

        // 출력
        System.out.println(answer);
        System.out.println((escapeX+1) + " " + (escapeY+1));
    }

    // 플레이어가 움직이는 메소드
    private static void playerMove(int idx){
        int minDis= Math.abs(players[idx].x-escapeX) + Math.abs(players[idx].y-escapeY); // 현재 위치에서의 최소 거리
        int minIdx = -1;
        for(int i=0;i<4;i++){
            int nx = players[idx].x + dx[i];
            int ny = players[idx].y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N || maze[nx][ny]>0) continue;

            int dis = Math.abs(nx-escapeX) + Math.abs(ny-escapeY);

            if(dis<=minDis){ // 움직인 곳의 거리가 최소 거리보다 작거나 같으면(상하좌우 고려를 위함) 바꿔주기
                minIdx=i;
                minDis = dis;
            }
        }

        if(minIdx>=0){ // 바뀐 내용이 있다면 업데이트
            players[idx].x += dx[minIdx];
            players[idx].y += dy[minIdx];
            players[idx].dis++;
            if(players[idx].x==escapeX && players[idx].y==escapeY) {
                players[idx].escape = true;
                escapeCnt++;
            }
        }
    }

    // 미로 움직임
    private static void mazeMove(){
        int dis = 0;
        int x = 0;
        int y = 0;
        int minDis = 11;
        for(int i=0;i<M;i++){
            if(players[i].escape) continue; // 이미 탈출한 플레이어는 고려하지 않음
            int height = Math.abs(players[i].x - escapeX)+1;
            int width = Math.abs(players[i].y - escapeY)+1;
            dis = Math.max(height, width); // 더 긴 길이를 한 변으로 삼음
            int nx=0;
            int ny=0;
            // 2차원 배열 다 돌려보면서 원하는 플레이어랑 탈출구가 정사각형 내에 들어오는 것을 확인
            L: for(int j=0;j<N;j++){
                nx = j;
                for(int k=0;k<N;k++){
                    ny = k;
                    if(players[i].x >= nx && players[i].x < nx+dis && players[i].y >= ny && players[i].y < ny+dis
                            && escapeX >= nx && escapeX < nx+dis && escapeY >= ny && escapeY < ny+dis){
                        break L;
                    }
                }
            }

            // 여러개 있다면 조건에 맞게 선택
            if(dis < minDis){
                x = nx;
                y = ny;
                minDis = dis;
            }else if(dis == minDis){
                if(nx < x){
                    x = nx;
                    y = ny;
                    minDis = dis;
                }else if(nx == x){
                    if(ny < y){
                        x = nx;
                        y = ny;
                        minDis = dis;
                    }
                }
            }
        }

        //  System.out.println(x + " " + y + " " + minDis);

        // 몇번 사이클 돌아야하는지 연산
        int cycle = minDis%2==0?minDis/2:minDis/2+1;

        // 복사하기 위한 미로
        int[][] copyMaze= new int[minDis][minDis];

        for(int i=0;i<cycle;i++){
            rotate(x+i, y+i, minDis-(i*2), copyMaze, i);
        }

        // copyMaze를 원래 maze에 옮겨주기, 탈출구 좌표도 업데이트 해주기
        for(int i=0;i<minDis;i++){
            for(int j=0;j<minDis;j++){
                maze[i+x][j+y] = copyMaze[i][j];
                if(copyMaze[i][j]==-1){
                    escapeX=i+x;
                    escapeY=j+y;
                }
            }
        }

        // 옮긴 위치의 벽들 내구도 내려주기
        minus(x, y, minDis);
    }

    private static void minus(int x, int y, int dis){
        for(int i=x;i<x+dis;i++){
            for(int j=y;j<y+dis;j++){
                if(maze[i][j] > 0) maze[i][j]--;
            }
        }
    }

    // 배열 돌려주는 메소드
    private static void rotate(int x, int y, int dis, int[][] copyMaze, int plus){
        int cnt = dis*4-4; // 총 몇개를 옮겨야하는지
        boolean[] check = new boolean[M]; // 이미 옮긴 플레이어를 두변 옮기지 않기 위한 체크배열
        for(int i=0;i<cnt;i++){
            // 시작점과 타겟점을 잡고 copyMaze에 해당 값 넣기
            int target = (i+dis-1)%cnt;
            Point startP = find(i, dis);
            Point targetP = find(target, dis);
            copyMaze[targetP.x+plus][targetP.y+plus] = maze[startP.x+x][startP.y+y];
            for(int j=0;j<M;j++){ // 해당 포인트가 플레이어 좌표라면 플레이어 위치 옮겨주기
                if(players[j].x == startP.x+x && players[j].y == startP.y+y && !check[j]){
                    players[j].x = plus+targetP.x+x;
                    players[j].y = plus+targetP.y+y;
                    check[j]=true;
                }
            }
        }

        if(cnt==0) copyMaze[plus][plus] = maze[x][y]; // 아무것도 안움직이는 경우(가운데 점) 그거 그냥 copyMaze에 복사

    }

    //	i < dis라면 y만 그만큼 증가
    //	dis <= i <= dis*2-2 이라면 x = i-(dis-1), y 는 dis-1 만큼 증가
    //	(dis-1)+(dis-1)< i < 2*(dis-1)+dis-1이라면 x는 (dis-1), y = 2(dis+dis-2)-i-(dis-1)
    //	이외에는 x는 2(dis+dis-2)-i, y는 0
    private static Point find(int i, int dis){
        int x;
        int y;
        if(i<dis) {
            x=0;
            y=i;
        }else if(i<=dis*2-2) {
            x=i-(dis-1);
            y=dis-1;
        }else if(i<2*(dis-1)+dis-1) {
            x = dis-1;
            y = 2*(dis+dis-2)-i-(dis-1);
        }else {
            x=2*(dis+dis-2)-i;
            y=0;
        }
        return new Point(x,y);
    }

    private static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        for(int i=0;i<M;i++){
            System.out.println(players[i].toString());
        }

        System.out.println();
    }
}
