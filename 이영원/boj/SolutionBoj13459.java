import java.util.*;
import java.io.*;
import java.awt.Point;

// 진짜 못풀었다...
public class Main {

    static char[][] map;
    static Point red, blue, hole; // 빨강, 파랑, 구멍
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static int N, M, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j]=='R') red = new Point(i, j);
                else if(map[i][j]=='B') blue = new Point(i, j);
                else if(map[i][j]=='O') hole = new Point(i, j);
            }
        }

        dfs(0, -5, null, null);
        if(answer==Integer.MAX_VALUE) {
            answer = 0;
        }else answer=1;

        System.out.println(answer);

    }

    private static void dfs(int cnt, int prev, Point p1, Point p2) {
//        print(map);
        // basis part
        if(red.equals(blue)) { // 동시에 구멍에 떨어진 경우
            red.x=p1.x;
            red.y=p1.y;
            blue.x=p2.x;
            blue.y=p2.y;
            return;
        }else if(blue.equals(hole)) { // 파란공만 구멍에 떨어진 경우
            blue.x=p2.x;
            blue.y=p2.y;
            return;
        }else if(red.equals(hole)){ // 빨간공만 구멍에 떨어진경우(굳)
//        	System.out.println(cnt);
            red.x=p1.x;
            red.y=p1.y;
            answer = Math.min(answer, cnt);
            return;
        }else if(cnt>=10 || cnt >= answer || ((p1!=null && p1.equals(red)) && (p2!=null && p2.equals(blue)))){
            // 최대횟수 초과, 현재 답보다 높아지거나, 이동했는데 같은 곳인 경우 리턴
            return;
        }

        // 맵을 깊은복사할 필요는 없고, red, blue 업데이트해서 그것만 위치값만 바꿔주기
        Point redTmp = new Point(red.x, red.y); // 과거 위치
        Point blueTmp = new Point(blue.x, blue.y); // 과거 위치
//        Point redTmp = null;
//        Point blueTmp = null;
//        if(p1==null && p2==null) {
//        	redTmp = new Point(red.x, red.y);
//        	blueTmp = new Point(blue.x, blue.y);
//        }else {
//            redTmp = new Point(p1.x, p1.y);
//            blueTmp = new Point(p2.x, p2.y);
//        }
        // inductive part
        for (int i = 0; i < dx.length; i++) {
            if(findFirst(i)) { // 누가 앞이냐에 따라서 움직이는 순서 결정 후 움직이기
                if(prev==i || (prev+2)%4==i) continue; // 과거랑 같은 행동이나 원복되는거(반대방향) 방지
                if(!move(red, i)) rotate(red, redTmp);
                if(!move(blue, i)) rotate(blue, blueTmp);
//                System.out.println("red = " + red + " redTmp = " + redTmp + " blue = " + blue + " blueTmp = " + blueTmp + " cnt = " + cnt);
                dfs(cnt+1, i, redTmp, blueTmp);
                // 원복
                map[red.x][red.y]='.';
                map[blue.x][blue.y]='.';
                map[redTmp.x][redTmp.y]='R';
                map[blueTmp.x][blueTmp.y]='B';
                red.x = redTmp.x;
                red.y = redTmp.y;
                blue.x = blueTmp.x;
                blue.y = blueTmp.y;


//                rotate(red, redTmp);
//                rotate(blue, blueTmp);
//                System.out.println("red = " + red + " redTmp = " + redTmp + " blue = " + blue + " blueTmp = " + blueTmp + " cnt = " + cnt);
            }else {
                if(prev==i || (prev+2)%4==i) continue;
                if(!move(blue, i)) rotate(blue, blueTmp);
                if(!move(red, i)) rotate(red, redTmp);
//                System.out.println("red = " + red + " redTmp = " + redTmp + " blue = " + blue + " blueTmp = " + blueTmp + " cnt = " + cnt);
                dfs(cnt+1, i, redTmp, blueTmp);
                // 원복
                map[red.x][red.y]='.';
                map[blue.x][blue.y]='.';
                map[redTmp.x][redTmp.y]='R';
                map[blueTmp.x][blueTmp.y]='B';
                red.x = redTmp.x;
                red.y = redTmp.y;
                blue.x = blueTmp.x;
                blue.y = blueTmp.y;
//                rotate(blue, blueTmp);
//                rotate(red, redTmp);
//                System.out.println("red = " + red + " redTmp = " + redTmp + " blue = " + blue + " blueTmp = " + blueTmp + " cnt = " + cnt);
            }
        }
    }

    // 두 점을 바꾸고 맵에서 업데이트
    private static void rotate(Point p1, Point p2){
        char tmp = map[p1.x][p1.y];
        int x = p1.x;
        int y = p1.y;
        if(map[p1.x][p1.y]!='O'){
            map[p1.x][p1.y]=map[p2.x][p2.y];
            map[p2.x][p2.y] = tmp;
        }else{
            map[p2.x][p2.y]='.';
        }
    }

    // 각 포인트, 방향으로 구슬 옮기기(벽을 만날경우, 다른 구슬을 만날경우, 구멍을 만날경우 고려)
    private static boolean move(Point p, int dir) {
        switch(dir){
            case 0:
                for(int i=p.y+1;i<M;i++) {
                    if(map[p.x][i]=='#') {
                        p.y=i-1;
                        return false;
                    }else if(map[p.x][i]=='O') { // 구멍에 들어가면 해당 구슬 지우고 좌표 구멍으로 옮기기(나중에 원복함)
                        map[p.x][p.y]='.';
                        p.y=i;
                        return true;
                    }else if(map[p.x][i]=='R' || map[p.x][i]=='B') {
                        p.y=i-1;
                        return false;
                    }
                }
                break;
            case 1:
                for(int i=p.x+1;i<N;i++) {
                    if(map[i][p.y]=='#') {
                        p.x=i-1;
                        return false;
                    }else if(map[i][p.y]=='O') {// 구멍에 들어가면 해당 구슬 지우고 좌표 구멍으로 옮기기(나중에 원복함)
                        map[p.x][p.y]='.';
                        p.x=i;
                        return true;
                    }else if(map[i][p.y]=='R' || map[i][p.y]=='B') {
                        p.x=i-1;
                        return false;
                    }
                }
                break;
            case 2:
                for(int i=p.y-1;i>=0;i--) {
                    if(map[p.x][i]=='#') {
                        p.y=i+1;
                        return false;
                    }else if(map[p.x][i]=='O') {// 구멍에 들어가면 해당 구슬 지우고 좌표 구멍으로 옮기기(나중에 원복함)
                        map[p.x][p.y]='.';
                        p.y=i;
                        return true;
                    }else if(map[p.x][i]=='R' || map[p.x][i]=='B') {
                        p.y=i+1;
                        return false;
                    }
                }
                break;
            case 3:
                for(int i=p.x-1;i>=0;i--) {
                    if(map[i][p.y]=='#') {
                        p.x=i+1;
                        return false;
                    }else if(map[i][p.y]=='O') {// 구멍에 들어가면 해당 구슬 지우고 좌표 구멍으로 옮기기(나중에 원복함)
                        map[p.x][p.y]='.';
                        p.x=i;
                        return true;
                    }else if(map[i][p.y]=='R' || map[i][p.y]=='B') {
                        p.x=i+1;
                        return false;
                    }
                }
//                p.x=1;
//                p.y=1;
                break;
        }
        return false;
    }

    // 반대 방향에서 어느 구슬을 먼저 만나는지 탐색
    private static boolean findFirst(int dir) {
        dir = (dir+2)%4; // 방향 뒤집기
        switch(dir){
            case 0:
                for (int i = 1; i < N-1; i++) {
                    for (int j = 1; j < M-1; j++) {
                        if(map[i][j]=='R') return true;
                        else if(map[i][j]=='B') return false;
                    }
                }
                break;
            case 1:
                for (int i = 1; i < M-1; i++) {
                    for (int j = 1; j < N-1; j++) {
                        if(map[j][i]=='R') return true;
                        else if(map[j][i]=='B') return false;
                    }
                }
                break;
            case 2:
                for (int i = 1; i < N-1; i++) {
                    for (int j = M-1; j >= 1; j--) {
                        if(map[i][j]=='R') return true;
                        else if(map[i][j]=='B') return false;
                    }
                }
                break;
            case 3:
                for (int i = 1; i < M-1; i++) {
                    for (int j = N-1; j >= 1; j--) {
                        if(map[j][i]=='R') return true;
                        else if(map[j][i]=='B') return false;
                    }
                }
                break;
        }
        return false;
    }

    private static void print(char[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
