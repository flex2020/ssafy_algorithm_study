import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, k;
    static int[][] field; // 누구의 냄새가 묻어있는지 표시한 필드
    static int[][] smellField; // 냄새 유통기한 표시 필드
    static int answer;
    static int[] dx = {0, -1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, 0, -1, 1}; // 상 하 좌 우
    static Shark[] sharks; // 상어 배열
    static int sharkCnt; // 상어 수

    static class Shark{
        int x;
        int y;
        int[][] dir; // 0 : 위 1: 아래 2 : 왼쪽 3: 오른쪽
        int curDir; // 현재 방향
        boolean out; // 쫓겨났는지 여부

        public Shark(int x, int y, int[][] dir, int curDir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.curDir = curDir;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + Arrays.toString(dir) +
                    ", curDir=" + curDir +
                    ", out=" + out +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 필드 길이
        M = Integer.parseInt(st.nextToken()); // 상어 수
        k = Integer.parseInt(st.nextToken()); // 냄새 유지시간

        sharks = new Shark[M+1];
        sharkCnt = M;
        int time=0;

        field = new int[N+2][N+2];
        smellField = new int[N+2][N+2];
        Arrays.fill(field[0], -1);
        Arrays.fill(field[N+1], -1);
        Arrays.fill(smellField[0], -1);
        Arrays.fill(smellField[N+1], -1);
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                smellField[i][j] = field[i][j];
                if(field[i][j]!=0) {
                    sharks[field[i][j]] = new Shark(i, j, new int[4][4], 0);
                    smellField[i][j]=k; // 냄새로 바꿔주기
                }
            }
            field[i][0] = -1;
            field[i][N+1] = -1;
            smellField[i][0] = -1;
            smellField[i][N+1] = -1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= sharkCnt; i++) { // 현재 방향 입력받기
            sharks[i].curDir = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) { // 상어 방향 입력받기
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    sharks[i].dir[j][k]=Integer.parseInt(st.nextToken());
                }
            }
        }

        while(true){
            time++; // 시간 올려주기
            for (int i = 1; i <= M; i++) { // 상어 움직이기
                moveShark(i);
            }
            updateSmell(); // 냄새 업데이트
            checkOut(); // 같은 장소의 상어 지우기( + 상어가 있는 자리 냄새 업데이트)
//            Thread.sleep(1000);
//            print();

            if(sharkCnt==1){ // shark가 하나만 남았으면 탈출
                answer=time;
                break;
            }

            if(time==1000){ // 1000초때까지 하나 이상이 남았으면 -1로 하고 탈출
                answer=-1;
                break;
            }
        }

        System.out.println(answer);
    }

    // 같은 자리에 있는 상어가 있는지 확인하기 쫓아내기
    private static void checkOut(){
        for (int i = 1; i <= M; i++) {
            if(sharks[i].out) continue;
            if(field[sharks[i].x][sharks[i].y] != i) { // 같은 자리에 상어가 없으면 쫓아내고 sharkCnt 줄여주기
                sharks[i].out=true;
                sharkCnt--;
            }
            // 상어가 있는자리 냄새 업데이트
            smellField[sharks[i].x][sharks[i].y]=k;
        }
    }

    // 상어를 움직인다.
    private static void moveShark(int i){
        if(sharks[i].out) return; // 이미 아웃된건 넘긴다.
        // 먼저 냄새가 없는 방향이 있는지 우선순위 기반해서 탐색
        for (int j = 0; j < 4; j++) {
            int nx = sharks[i].x + dx[sharks[i].dir[sharks[i].curDir-1][j]];
            int ny = sharks[i].y + dy[sharks[i].dir[sharks[i].curDir-1][j]];
            if(smellField[nx][ny]==0){
                sharks[i].x = nx;
                sharks[i].y = ny;
                sharks[i].curDir = sharks[i].dir[sharks[i].curDir-1][j];
                // field가 이미 새로운 상어에 의해 채워진 상태라면 더 작은 번호를 가진 상어의 번호로 채워준다.
                if(field[nx][ny]!=0) field[nx][ny] = Math.min(i, field[nx][ny]);
                else field[nx][ny] = i; // 아니라면 그냥 상어 번호를 해준다.
                return;
            }
        }
        // 자신의 냄새가 있는 방향이 있는지 우선수위 기반해서 재탐색
        for (int j = 0; j < 4; j++) {
            int nx = sharks[i].x + dx[sharks[i].dir[sharks[i].curDir-1][j]];
            int ny = sharks[i].y + dy[sharks[i].dir[sharks[i].curDir-1][j]];
            if(field[nx][ny]==i){
                sharks[i].x = nx;
                sharks[i].y = ny;
                sharks[i].curDir = sharks[i].dir[sharks[i].curDir-1][j];
                smellField[nx][ny]=k;
                return;
            }
        }
    }

    // 필드의 냄새를 업데이트한다.
    private static void updateSmell(){
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if(smellField[i][j]!=0) {
                    smellField[i][j]--;
                    // 냄새가 지워졌다면 field도 0으로 해준다.
                    if(smellField[i][j]==0) field[i][j]=0;
                }
            }
        }
    }

    private static void print(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
