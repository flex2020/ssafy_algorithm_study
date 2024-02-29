import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 위, 왼위, 왼, 왼아래, 아래, 오른아래, 오른, 오른위
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1}; // 위, 왼위, 왼, 왼아래, 아래, 오른아래, 오른, 오른위
    static int answer;

    static class Fish{
        int x, y, num, dir;
        boolean isDead; // 죽었는지 살았는지 확인

        public Fish(int x, int y, int num, int dir, boolean isDead) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
            this.isDead = isDead;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", num=" + num +
                    ", dir=" + dir +
                    ", isDead=" + isDead +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] field = new int[4][4];
        Fish[] fishes = new Fish[18]; // 0에는 아무것도 없는값, 17에는 상어를 넣는다.
        int result=0;

        for (int i = 0; i < 4; i++) { // 입력받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1;
                fishes[num] = new Fish(i, j, num, dir, false);
                field[i][j] = num;
            }
        }

        result += field[0][0]; // 처음값 result에 넣기
        Fish shark = new Fish(0, 0, 17, fishes[field[0][0]].dir, false); // 상어 추적하기 위해 shark 객체 생성
        fishes[field[0][0]].isDead=true; // 처음에 들어있던 물고기 죽이기
        field[0][0] = 17; // 상어(17)로 채우기
        fishes[0] = new Fish(0, 0, 0, 0, false); // 0에는 더미값 넣어두기
        fishes[17] = shark; // 상어 넣기
        recursive(result, fishes, field, shark);

        System.out.println(answer);
    }

    private static void recursive(int result, Fish[] fishes, int[][] field, Fish shark){
//        System.out.println("result = " + result);

        // fishes, field 깊은복사 진행
        Fish[] tmpFishes = new Fish[18];
        int[][] tmpField = new int[4][4];
        for (int i = 0; i < fishes.length; i++) {
            tmpFishes[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].num, fishes[i].dir, fishes[i].isDead);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmpField[i][j] = field[i][j];
            }
        }
        // 상어도 깊은복사 진행
        Fish tmpShark = new Fish(shark.x, shark.y, shark.num, shark.dir, shark.isDead);
        // 물고기 옮기기
        fishMove(tmpFishes, tmpField);
//        System.out.println("물고기 옮기기");
//        print(tmpField);
//        printFishes(tmpFishes);

        Deque<Fish> dq = new ArrayDeque<>();
        int nx = shark.x;
        int ny = shark.y;
        // 상어가 갈 수 있는 위치들을 돌면서 갈 수 있는 곳이라면 dq에 넣는다.
        for (int i = 0; i < 4; i++) {
            nx += dx[shark.dir];
            ny += dy[shark.dir];
            if(nx>=0 && nx<4 && ny>=0 && ny<4 && tmpField[nx][ny]!=0){
                dq.offerLast(tmpFishes[tmpField[nx][ny]]);
            }
        }
        // 종료조건, dq에 아무것도 담겨있지 않다면 상어가 움직이지 못하므로 종료
        if(dq.size()==0){
            answer = Math.max(result, answer);
            return;
        }

        while(!dq.isEmpty()){ // dq가 빌때까지 반복
            Fish f = dq.pollFirst();

            // 물고기를 죽이고, 둘의 위치를 바꾸고, 상어객체와 field, fishes 배열을 업데이트해준다.
            f.isDead = true;
            swap(tmpShark.x, tmpShark.y, f.x, f.y, tmpFishes, tmpField);
            tmpField[f.x][f.y]=0;
            tmpShark.x = tmpFishes[17].x;
            tmpShark.y = tmpFishes[17].y;
            tmpShark.dir = f.dir;
            tmpFishes[17].dir = tmpShark.dir;
//            System.out.println("shark = " + tmpShark);
//            System.out.println("옮기기");
//            print(tmpField);
//            printFishes(tmpFishes);

            // 재귀 진행
            recursive(result + f.num, tmpFishes, tmpField, tmpShark);
            // 원복
            // 물고기를 살리고, 둘의 위치를 다시 바꾸고, 상어객체와 field, fishes 배열을 업데이트해준다.(여기서 굉장히 오래걸림)
            f.isDead = false;
            swap(tmpShark.x, tmpShark.y, f.x, f.y, tmpFishes, tmpField);
            tmpField[tmpShark.x][tmpShark.y]=f.num;
            tmpFishes[f.num].x = tmpShark.x;
            tmpFishes[f.num].y = tmpShark.y;
            tmpFishes[f.num].dir = tmpShark.dir;
            tmpShark.x = shark.x;
            tmpShark.y = shark.y;
            tmpShark.dir = shark.dir;
            tmpFishes[17].dir = tmpShark.dir;
//            System.out.println("원복");
//            print(tmpField);
//            printFishes(tmpFishes);
        }




    }

    private static void fishMove(Fish[] fishes, int[][] field){
        for (int i = 1; i < fishes.length-1; i++) {
            if(!fishes[i].isDead){ // 죽은건 냅두고 죽지않은것만 이동시키기
                for (int j = 0; j < 8; j++) { // 팔방탐색(반시계)
                    int nx = fishes[i].x + dx[(fishes[i].dir+j)%8];
                    int ny = fishes[i].y + dy[(fishes[i].dir+j)%8];
                    if(nx>=0 && nx<4 && ny>=0 && ny<4 && field[nx][ny]>=0 && field[nx][ny]<=16){ // 범위 내이고 빈공간(0)이거나 물고기인 경우 이동
                        fishes[i].dir = (fishes[i].dir+j)%8; //  반시계로 돌린만큼 해당 물고기 방향 업데이트
                        swap(fishes[i].x, fishes[i].y, nx, ny, fishes, field); // 둘의 위치 업데이트
//                        print(field);
                        break;
                    }
                }
            }
        }
    }

    // fishes(물고기배열), field(물고기, 상어 숫자를 표시한 2차원 배열) 업데이트(swap)
    private static void swap(int x1, int y1, int x2, int y2, Fish[] fishes, int[][] field){
        int a = field[x1][y1];
        int b = field[x2][y2];

        fishes[a].x = x2;
        fishes[a].y = y2;
        fishes[b].x = x1;
        fishes[b].y = y1;

        field[x1][y1]=b;
        field[x2][y2]=a;

    }

    private static void print(int[][] field){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printFishes(Fish[] fishes){
        for (int i = 0; i < fishes.length; i++) {
            System.out.println(fishes[i] + " ");
        }
    }
}
