import java.io.*;
import java.util.*;



public class Main {

    static char[][] map;
    static int R, C;
    static int Mx, My, Zx, Zy;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R+1][C+1];

        for (int i = 1; i <= R; i++) {
            String input = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = input.charAt(j-1);
                if(map[i][j]=='M'){
                    Mx = i;
                    My = j;
                }else if(map[i][j]=='Z'){
                    Zx = i;
                    Zy = j;
                }
            }
        }

        go(Mx, My);
        System.out.println(sb);

    }

    // 출발
    private static void go(int x, int y){
        int dir = startCheck(x, y); // 어디로 나가야할지 방향 설정
        int nx = x;
        int ny = y;
        while(map[nx][ny]!='.'){ // 비어있는 부분이 나와있을 때까지 진행
            nx += dx[dir];
            ny += dy[dir];
            switch(map[nx][ny]){
                case '|':
                case '-':
                case '+':
                    break;
                case '1':
                    if(dir==2) dir=1;
                    else if(dir==3) dir=0;
                    break;
                case '2':
                    if(dir==1) dir=0;
                    else if(dir==2) dir=3;
                    break;
                case '3':
                    if(dir==0) dir=3;
                    else if(dir==1) dir=2;
                    break;
                case '4':
                    if(dir==3) dir=2;
                    else if(dir==0) dir=1;
                    break;
                default:
                    break;
            }
        }
        sb.append(nx).append(" ").append(ny).append(" ");
        boolean[] arr= new boolean[4];

        for (int i = 0; i < 4; i++) { // 비어있는 부분 기준으로 어디에서 어디로 들어올 수 있는지 확인
            int px = nx + dx[i];
            int py = ny + dy[i];
            if(px < 1 || px > R || py < 1 || py > C) continue;
            switch(map[px][py]){
                case '|':
                    if(i==1 || i==3) arr[i]=true;
                    break;
                case '-':
                    if(i==0 || i==2) arr[i]=true;
                    break;
                case '+':
                    arr[i]=true;
                    break;
                case '1':
                    if(i==2 || i==3) arr[i]=true;
                    break;
                case '2':
                    if(i==1 || i==2) arr[i]=true;
                    break;
                case '3':
                    if(i==0 || i==1) arr[i]=true;
                    break;
                case '4':
                    if(i==3 || i==0) arr[i]=true;
                    break;
            }
        }

//        System.out.println(Arrays.toString(arr));

        // 넣기
        if(arr[0] && arr[1] && arr[2] && arr[3]) sb.append("+");
        else if(arr[0] && arr[2]) sb.append("-");
        else if(arr[1] && arr[3]) sb.append("|");
        else if(arr[0] && arr[1]) sb.append("1");
        else if(arr[0] && arr[3]) sb.append("2");
        else if(arr[2] && arr[3]) sb.append("3");
        else if(arr[1] && arr[2]) sb.append("4");ㄱ

    }

    // 어디로 출발할지 사방탐색하면서 확인
    private static int startCheck(int x, int y){
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 1 || nx > R || ny < 1 || ny > C) continue;
            switch(map[nx][ny]){
                case '|':
                    if(i==1 || i==3) return i;
                    break;
                case '-':
                    if(i==0 || i==2) return i;
                    break;
                case '+':
                    return i;
                case '1':
                    if(i==2 || i==3) return i;
                    break;
                case '2':
                    if(i==1 || i==2) return i;
                    break;
                case '3':
                    if(i==0 || i==1) return i;
                    break;
                case '4':
                    if(i==3 || i==0) return i;
                    break;
            }
        }
        return -1;
    }

    private static void print(char[][] map){
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
