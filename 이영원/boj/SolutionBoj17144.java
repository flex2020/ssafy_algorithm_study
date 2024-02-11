import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] room;
    static int R;
    static int C;
    static int purifier;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int answer=0;


        room = new int[R][C];

//        Arrays.fill(room[0], -1);
//        Arrays.fill(room[R + 1], -1);
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j]==-1) purifier=i;
            }
//            room[i][0] = -1;
//            room[i][C + 1] = -1;
        }
//        print();

        for(int i=1;i<=T;i++){
            room=spread();
//            print();
            purify();
//            print();
        }

        answer = count();

        System.out.println(answer);


    }

    private static void print(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(room[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] spread(){
        int[][] arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(room[i][j]==-1){
                    arr[i][j]=-1;
                    continue;
                }
                int cnt=0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if(nx>=0 && nx<R && ny>=0 && ny<C && room[nx][ny]!=-1){
                        cnt++;
                        arr[nx][ny]+=room[i][j]/5;
                    }
                }
                arr[i][j]+=room[i][j]-room[i][j]/5*cnt;
            }
        }
        return arr;
    }

    private static void purify(){
        counterClockwiseTurn(purifier-1);
        clockwiseTurn(purifier);
    }

    private static void counterClockwiseTurn(int purifier){
        int x=purifier-1;
        int y=0;
        int d=3;
        int cycle = purifier*2+(C-1)*2-2;
        for (int i = 0; i < cycle; i++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx<0 || nx>=purifier+1 || ny<0 || ny>=C) {
                d=(d+1)%4;
                i--;
                continue;
            }
            room[x][y]=room[nx][ny];
            x=nx;
            y=ny;
        }
        room[purifier][1]=0;
    }

    private static void clockwiseTurn(int purifier){
        int x=purifier+1;
        int y=0;
        int d=1;
        int cycle = (R-purifier-1)*2+(C-1)*2-2;
        for (int i = 0; i < cycle; i++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx<purifier || nx>=R || ny<0 || ny>=C) {
                d= (d==0) ? 3 : d-1;
                i--;
                continue;
            }
            room[x][y]=room[nx][ny];
            x=nx;
            y=ny;
        }
        room[purifier][1]=0;
    }

    private static int count(){
        int result=0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(room[i][j]!=-1) result+=room[i][j];
            }
        }
        return result;
    }
}
