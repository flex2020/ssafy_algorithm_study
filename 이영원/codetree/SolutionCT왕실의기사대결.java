import java.util.*;
import java.io.*;

public class Main {

    static int L, N, Q;
    static int[][] map;
    static int[][] knightMap;
    static Knight[] knights;

    static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dy = {0, 1, 0, -1}; // 상 우 하 좌

    static class Knight{
//      sr, sc는 왼쪽 위 위치, er, ec는 오른 아래 위치
        int sr, sc, er, ec, h, w, k, remain;
        boolean isDead = false;

        Knight(int r, int c, int h, int w, int k){
            this.sr = r;
            this.sc = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.remain = k;
            this.er = sr+h-1;
            this.ec = sc+w-1;
        }

        @Override
        public String toString(){
            return "sr = " + sr
                    + " sc = " + sc
                    + " h = " + h
                    + " w = " + w
                    + " k = " + k
                    + " remain = " + remain
                    + " er = " + er
                    + " ec = " + ec;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        knights = new Knight[N+1]; // 기사들 정보를 담을 배열

        map = new int[L+1][L+1]; // 맵
        knightMap = new int[L+1][L+1]; // 기사 위치 맵

        for(int i=1;i<L+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<L+1;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k2 = Integer.parseInt(st.nextToken());
            knights[i] = new Knight(r, c, h, w, k2);
            // System.out.println(knights[i]);
            for(int j=knights[i].sr;j<=knights[i].er;j++){
                for(int k=knights[i].sc;k<=knights[i].ec;k++){
                    knightMap[j][k]=i;
                }
            }
        }

        // print(map);

        // [0]은 몇번 기사한테 내리는 명령인지, [1]은 방향 d
        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            // 실행 가능한 명령인지 확인하고 진행
            if(check(target, dir) && !knights[target].isDead) {
                // System.out.println("gogo");
                move(target, dir, 0);
            }
            // print(knightMap);
        }

        // 스코어링
        for(int i=1;i<N+1;i++){
            if(!knights[i].isDead){
                // System.out.println(i + " " + knights[i]);
                answer+=knights[i].k-knights[i].remain;
            }
        }

        System.out.println(answer);


    }

    private static void print(int[][] map){
        for(int i=1;i<L+1;i++){
            for(int j=1;j<L+1;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 실행 가능한 명령인지 확인하는 메소드
    private static boolean check(int target, int dir){

        int startX = 0;
        int startY = 0;
        boolean[] visited = new boolean[N+1];

        switch(dir){
            case 0: // 상
                // 이동하는 위치의 끄트머리를 이동시켜보고 범위를 벗어나는지 확인
                startX = knights[target].sr + dx[dir];
                startY = knights[target].sc + dy[dir];
                if(startX<1 || startX > L || startY<1 || startY > L) return false;
                for(int i=knights[target].sc;i<=knights[target].ec;i++){
                    // 벽이 있으면 이동 불가능하므로 false 리턴
                    if(map[startX][i]==2) return false;
                    // 다른 기사가 있고, 해당 기사가 방문하지 않은 경우, 재귀로 check 진행
                    if(knightMap[startX][i]!=0 && !visited[knightMap[startX][i]]){
                        if(!check(knightMap[startX][i], dir)) return false;
                        visited[knightMap[startX][i]]=true; // 방문체크
                    }
                }
                break;
            case 1: // 우
                // 이동하는 위치의 끄트머리를 이동시켜보고 범위를 벗어나는지 확인
                startX = knights[target].er + dx[dir];
                startY = knights[target].ec + dy[dir];
                if(startX<1 || startX > L || startY<1 || startY > L) return false;
                for(int i=knights[target].sr;i<=knights[target].er;i++){
                    // 벽이 있으면 이동 불가능하므로 false 리턴
                    if(map[i][startY]==2) return false;
                    // 다른 기사가 있고, 해당 기사가 방문하지 않은 경우, 재귀로 check 진행
                    if(knightMap[i][startY]!=0 && !visited[knightMap[i][startY]]){
                        if(!check(knightMap[i][startY], dir)) return false;
                        visited[knightMap[i][startY]]=true; // 방문체크
                    }
                }
                break;
            case 2: // 하
                // 이동하는 위치의 끄트머리를 이동시켜보고 범위를 벗어나는지 확인
                startX = knights[target].er + dx[dir];
                startY = knights[target].ec + dy[dir];
                if(startX<1 || startX > L || startY<1 || startY > L) return false;
                for(int i=knights[target].sc;i<=knights[target].ec;i++){
                    // 벽이 있으면 이동 불가능하므로 false 리턴
                    if(map[startX][i]==2) return false;
                    // 다른 기사가 있고, 해당 기사가 방문하지 않은 경우, 재귀로 check 진행
                    if(knightMap[startX][i]!=0 && !visited[knightMap[startX][i]]){
                        if(!check(knightMap[startX][i], dir)) return false;
                        visited[knightMap[startX][i]]=true; // 방문체크
                    }
                }
                break;
            case 3: // 좌
                // 이동하는 위치의 끄트머리를 이동시켜보고 범위를 벗어나는지 확인
                startX = knights[target].sr + dx[dir];
                startY = knights[target].sc + dy[dir];
                if(startX<1 || startX > L || startY<1 || startY > L) return false;
                for(int i=knights[target].sr;i<=knights[target].er;i++){
                    // 벽이 있으면 이동 불가능하므로 false 리턴
                    if(map[i][startY]==2) return false;
                    // 다른 기사가 있고, 해당 기사가 방문하지 않은 경우, 재귀로 check 진행
                    if(knightMap[i][startY]!=0 && !visited[knightMap[i][startY]]){
                        if(!check(knightMap[i][startY], dir)) return false;
                        visited[knightMap[i][startY]]=true; // 방문체크
                    }
                }
                break;
        }
        // System.out.println(target + " " + dir + " true");
        // 모든 경우를 통과했다면 true 리턴
        return true;
    }

    // 실제로 이동시키는 메소드
    private static void move(int target, int dir, int depth){

        int startX = 0;
        int startY = 0;
        boolean[] visited = new boolean[N+1];
        // System.out.println(target + " " + dir + " " + depth);

        switch(dir){
            case 0: // 상
                // 끄트머리가 범위를 벗어나는지 확인하고 아니라면 반복을 돌면서 만나는 다른 기사들을 재귀로 들어가서 이동시킨다.
                startX = knights[target].sr + dx[dir];
                startY = knights[target].sc + dy[dir];
                if(startX<1 || startX > L || startY<1 || startY > L) return;
                for(int i=knights[target].sc;i<=knights[target].ec;i++){
                    if(knightMap[startX][i]!=0 && !visited[knightMap[startX][i]]){
                        move(knightMap[startX][i], dir, depth+1);
                        visited[knightMap[startX][i]]=true;
                    }
                }
                // 명령을 받은 기사(depth==0)를 제외한 기사들은 이동위치에 함정이 있는경우, 이동시키고 체력을 깎는다.
                for(int i=knights[target].sr;i<=knights[target].er;i++){
                    for(int j=knights[target].sc;j<=knights[target].ec;j++){
                        knightMap[i][j]=0;
                        knightMap[i+dx[dir]][j+dy[dir]]=target;
                        if(map[i+dx[dir]][j+dy[dir]]==1 && depth>0){
                            knights[target].remain--;
                        }
                    }
                }
                // knights 배열 업데이트
                knights[target].sr+=dx[dir];
                knights[target].sc+=dy[dir];
                knights[target].er+=dx[dir];
                knights[target].ec+=dy[dir];
                // 남은 체력이 없는 경우 죽인다.
                if(knights[target].remain<=0) {
                    knights[target].isDead=true;
                    for(int i=knights[target].sr;i<=knights[target].er;i++){
                        for(int j=knights[target].sc;j<=knights[target].ec;j++){
                            knightMap[i][j]=0;
                        }
                    }
                }
                break;
            case 1: // 우
                // 끄트머리가 범위를 벗어나는지 확인하고 아니라면 반복을 돌면서 만나는 다른 기사들을 재귀로 들어가서 이동시킨다.
                startX = knights[target].er + dx[dir];
                startY = knights[target].ec + dy[dir];
                if(startX<1 || startX > L || startY<1 || startY > L) return;
                for(int i=knights[target].sr;i<=knights[target].er;i++){
                    if(knightMap[i][startY]!=0 && !visited[knightMap[i][startY]]){
                        move(knightMap[i][startY], dir, depth+1);
                        visited[knightMap[i][startY]]=true;
                    }
                }
                // 명령을 받은 기사(depth==0)를 제외한 기사들은 이동위치에 함정이 있는경우, 이동시키고 체력을 깎는다.
                for(int i=knights[target].er;i>=knights[target].sr;i--){
                    for(int j=knights[target].ec;j>=knights[target].sc;j--){
                        knightMap[i][j]=0;
                        knightMap[i+dx[dir]][j+dy[dir]]=target;
                        if(map[i+dx[dir]][j+dy[dir]]==1 && depth>0){
                            // print(map);
                            // System.out.print(i+dx[dir]);
                            // System.out.println(j+dy[dir]);
                            knights[target].remain--;
                        }
                    }
                }
                // knights 배열 업데이트
                knights[target].sr+=dx[dir];
                knights[target].sc+=dy[dir];
                knights[target].er+=dx[dir];
                knights[target].ec+=dy[dir];
                // System.out.println(target + " " + knights[target]);
                // 남은 체력이 없는 경우 죽인다.
                if(knights[target].remain<=0) {
                    knights[target].isDead=true;
                    for(int i=knights[target].sr;i<=knights[target].er;i++){
                        for(int j=knights[target].sc;j<=knights[target].ec;j++){
                            knightMap[i][j]=0;
                        }
                    }
                }
                break;
            case 2: // 하
                // 끄트머리가 범위를 벗어나는지 확인하고 아니라면 반복을 돌면서 만나는 다른 기사들을 재귀로 들어가서 이동시킨다.
                startX = knights[target].er + dx[dir];
                startY = knights[target].ec + dy[dir];
                if(startX<1 || startX > L || startY<1 || startY > L) return;
                for(int i=knights[target].sc;i<=knights[target].ec;i++){
                    if(knightMap[startX][i]!=0 && !visited[knightMap[startX][i]]){
                        move(knightMap[startX][i], dir, depth+1);
                        visited[knightMap[startX][i]]=true;
                    }
                }
                // 명령을 받은 기사(depth==0)를 제외한 기사들은 이동위치에 함정이 있는경우, 이동시키고 체력을 깎는다.
                for(int i=knights[target].er;i>=knights[target].sr;i--){
                    for(int j=knights[target].ec;j>=knights[target].sc;j--){
                        knightMap[i][j]=0;
                        knightMap[i+dx[dir]][j+dy[dir]]=target;
                        if(map[i+dx[dir]][j+dy[dir]]==1 && depth>0){
                            knights[target].remain--;
                        }
                    }
                }
                // knights 배열 업데이트
                knights[target].sr+=dx[dir];
                knights[target].sc+=dy[dir];
                knights[target].er+=dx[dir];
                knights[target].ec+=dy[dir];
                // 남은 체력이 없는 경우 죽인다.
                if(knights[target].remain<=0) {
                    knights[target].isDead=true;
                    for(int i=knights[target].sr;i<=knights[target].er;i++){
                        for(int j=knights[target].sc;j<=knights[target].ec;j++){
                            knightMap[i][j]=0;
                        }
                    }
                }
                break;
            case 3: // 좌
                // 끄트머리가 범위를 벗어나는지 확인하고 아니라면 반복을 돌면서 만나는 다른 기사들을 재귀로 들어가서 이동시킨다.
                startX = knights[target].sr + dx[dir];
                startY = knights[target].sc + dy[dir];
                if(startX<1 || startX > L || startY<1 || startY > L) return;
                for(int i=knights[target].sr;i<=knights[target].er;i++){
                    if(knightMap[i][startY]!=0 && !visited[knightMap[i][startY]]){
                        move(knightMap[i][startY], dir, depth+1);
                        visited[knightMap[i][startY]]=true;
                    }
                }
                // 명령을 받은 기사(depth==0)를 제외한 기사들은 이동위치에 함정이 있는경우, 이동시키고 체력을 깎는다.
                for(int i=knights[target].sr;i<=knights[target].er;i++){
                    for(int j=knights[target].sc;j<=knights[target].ec;j++){
                        knightMap[i][j]=0;
                        knightMap[i+dx[dir]][j+dy[dir]]=target;
                        if(map[i+dx[dir]][j+dy[dir]]==1 && depth>0){
                            knights[target].remain--;
                        }
                    }
                }
                // knights 배열 업데이트
                knights[target].sr+=dx[dir];
                knights[target].sc+=dy[dir];
                knights[target].er+=dx[dir];
                knights[target].ec+=dy[dir];
                // 남은 체력이 없는 경우 죽인다.
                if(knights[target].remain<=0) {
                    knights[target].isDead=true;
                    for(int i=knights[target].sr;i<=knights[target].er;i++){
                        for(int j=knights[target].sc;j<=knights[target].ec;j++){
                            knightMap[i][j]=0;
                        }
                    }
                }
                break;
        }
    }
}
