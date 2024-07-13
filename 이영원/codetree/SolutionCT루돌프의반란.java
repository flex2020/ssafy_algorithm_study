import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {

    static int N, M, P, C, D;
    static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1}; // 4 뒤에는 안씀(상우하좌)
    static int[] dy = {0, 1, 0, -1, 1, 1, -1, -1}; // 4 뒤에는 안씀(상우하좌)
    static int[][] map;
    static Santa[] santas;
    static int deadCnt;
    static int rx, ry;

    static class Santa{
        // x, y, 점수, 기절
        int x, y, score, stun;
        // 사망여부
        boolean dead;

        Santa(int x, int y){
            this.x = x;
            this.y = y;
            this.score = 0;
            this.dead = false;
            this.stun = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 게임판 크기
        M = Integer.parseInt(st.nextToken()); // 게임 턴 수
        P = Integer.parseInt(st.nextToken()); // 산타 수
        C = Integer.parseInt(st.nextToken()); // 루돌프 힘
        D = Integer.parseInt(st.nextToken()); // 산타 힘

        StringBuilder sb = new StringBuilder();

        map = new int[N+1][N+1]; 
        santas = new Santa[P+1];

        st = new StringTokenizer(br.readLine());
        rx = Integer.parseInt(st.nextToken());
        ry = Integer.parseInt(st.nextToken());
        map[rx][ry]=-1; // 루돌프

        for(int i=1;i<=P;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            santas[num] = new Santa(sx, sy);
            map[sx][sy] = num;
        }

//        print();

        for(int i=0;i<M;i++){
            rudolphMove(i);
//            print();
            for(int j=1;j<=P;j++){
                if(santas[j].stun > i) continue;
                if(!santas[j].dead){
                    santaMove(i, j);
//                    print();
                }

            }
            for(int j=1;j<=P;j++){
                if(!santas[j].dead){
                    santas[j].score++;
                }
            }

            if(deadCnt==P) break;
        }


        for(int i=1;i<P+1;i++){
            sb.append(santas[i].score).append(" ");
        }

        System.out.println(sb.toString());
    }

    // 루돌프 이동
    private static void rudolphMove(int turn){
        int nx, ny;
        int dis=0;
        int minDis=Integer.MAX_VALUE;
        int minIdx=0;
        for(int i=1;i<=P;i++){ // 각 산타 보면서 최소거리, 최소인덱스 구하기
            if(santas[i].dead) continue;
            dis = (int)Math.pow(santas[i].x-rx, 2) + (int)Math.pow(santas[i].y-ry, 2);
            if(minDis>dis){
                minDis=dis;
                minIdx=i;
            }else if(minDis==dis){ // 거리가 같다면 x가 큰거 찾기
                if(santas[minIdx].x < santas[i].x){
                    minDis = dis;
                    minIdx=i;
                }else if(santas[minIdx].x==santas[i].x){ // x도 같다면 y가 큰거 찾기
                    if(santas[minIdx].y < santas[i].y){
                        minDis = dis;
                        minIdx = i;
                    }
                }
            }
        }

        nx = santas[minIdx].x-rx;
        ny = santas[minIdx].y-ry;

        if((nx >= -1 && nx <= 1) && (ny >= -1 && ny <= 1)){ // 범위 내에 있다면 거리 1만 움직이는거
            if(map[rx+nx][ry+ny]>0){ // 이동한 곳에 산타가 있다면
                santas[map[rx+nx][ry+ny]].score += C; // 점수 올려주고
                santas[map[rx+nx][ry+ny]].stun = turn+2; // 2턴뒤에 풀리도록 스턴 걸어주고
                crash(rx+nx, ry+ny, nx, ny, map[rx+nx][ry+ny], C); // 충돌
            }
            // map이랑 루돌프 위치 업데이트
            map[rx][ry]=0;
            rx+=nx;
            ry+=ny;
            map[rx][ry]=-1;
        }else{ // 거리 1 이상 이동해야하는 경우
            map[rx][ry]=0; // map에 루돌프 위치 업데이트
            // 해당 대각 방향으로 1 움직이기
            if(nx<0){
                rx += nx/nx*(-1);
            }else if(nx>0){
                rx += nx/nx;
            }

            // 해당 대각 방향으로 1 움직이기
            if(ny<0){
                ry += ny/ny*(-1);
            }else if(ny>0){
                ry += ny/ny;
            }

            map[rx][ry]=-1; // map에 루돌프 위치 업데이트
        }

        return;
    }

    private static void santaMove(int turn, int num){
        int minDis=Integer.MAX_VALUE;
        int minIdx=0;
        int dis=0;
        int curDis = (int)Math.pow(rx-santas[num].x, 2) + (int)Math.pow(ry-santas[num].y, 2);
        // 산타가 움직일 수 있는 상우하좌로 움직여보면서 가장 작은곳을 찾기
        for(int i=0;i<4;i++){
            int nx = santas[num].x + dx[i];
            int ny = santas[num].y + dy[i];
            if(nx<1 || nx>=N+1 || ny<1 || ny>=N+1 || map[nx][ny]>0) continue;
            dis = (int)Math.pow(rx-nx, 2) + (int)Math.pow(ry-ny, 2);
            if(dis<minDis){
                minDis = dis;
                minIdx = i;
            }
        }

        // 현재위치에서 작은 경우만 업데이트
        if(minDis < curDis){
            int nx = santas[num].x + dx[minIdx];
            int ny = santas[num].y + dy[minIdx];
            if(nx==rx && ny==ry){ // 움직인 곳에 루돌프가 있는 경우
                map[santas[num].x][santas[num].y]=0; // map 업데이트
                santas[num].stun = turn+2; // 2턴 뒤에 움직이도록 스턴 걸어주고
                crash(rx, ry, dx[minIdx]*-1, dy[minIdx]*-1, num, D); // 충돌
                santas[num].score += D; // 점수 업데이트
            }else{ // 움직인 곳에 루돌프가 없는 경우
                map[santas[num].x][santas[num].y]=0;
                santas[num].x = nx;
                santas[num].y = ny;
                map[santas[num].x][santas[num].y]=num;
            }
        }
    }

    // 충돌메소드, 현재위치, 이동할 방향(nx, ny), 움직이는 산타 번호, 힘
    private static void crash(int x, int y, int dx, int dy, int num, int power){
        int nx = x;
        int ny = y;

        // 힘만큼 움직이기
        nx = nx + (dx * power);
        ny = ny + (dy * power);
        if(nx<1 || nx>=N+1 || ny<1 || ny>=N+1){ // 바깥으로 나간경우 죽이고 deadCnt 올려주기
            santas[num].dead=true;
            deadCnt++;
            return;
        }

        if(map[nx][ny]==0){ // 이동한 곳에 아무것도 없으면 업데이트하고 리턴
            map[nx][ny]=num;
            santas[num].x=nx;
            santas[num].y=ny;
            return;
        }

        if(map[nx][ny]>0){ // 이동한 곳에 또다른 산타가 있다면 재귀로 power=1로 해주고 업데이트
            int n = map[nx][ny];
            map[nx][ny]=num;
            santas[num].x=nx;
            santas[num].y=ny;
            crash(nx, ny, dx, dy, n, 1);
        }
    }

    private static void print(){
        for(int i=1;i<N+1;i++){
            for(int j=1;j<N+1;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
