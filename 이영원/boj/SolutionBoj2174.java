import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int A, B, N, M;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static String answer;

    static class Robot{
        int x, y, dir; // 위치, 방향

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Robot{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    '}';
        }
    }

    static class Command{
        int robotNum, cnt; // 로봇숫자, 커맨드 반복횟수
        String type; // 커맨드 타입

        public Command(int robotNum, String type, int cnt) {
            this.robotNum = robotNum;
            this.type = type;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Command{" +
                    "robotNum=" + robotNum +
                    ", type=" + type +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken()); // 행
        B = Integer.parseInt(st.nextToken()); // 열
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 로봇수
        M = Integer.parseInt(st.nextToken()); // 명령수

        Robot[] robots = new Robot[N+1];
        Command[] commands = new Command[M];
        answer = "OK";

        for (int i = 1; i <= N; i++) { // 로봇 입력
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = 0;
            String d = st.nextToken();
            switch(d){
                case "N":
                    dir = 0;
                    break;
                case "E":
                    dir = 1;
                    break;
                case "S":
                    dir = 2;
                    break;
                case "W":
                    dir = 3;
                    break;
            }
            robots[i] = new Robot(x-1, y-1, dir);
        }

        for (int i = 0; i < M; i++) { // 커맨드 입력
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            String type = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            commands[i] = new Command(num, type, cnt);
        }

        L: for (int i = 0; i < M; i++) {
            switch(commands[i].type){
                case "L":
                    // 돌리기
                    for (int j = 0; j < commands[i].cnt; j++) {
                        robots[commands[i].robotNum].dir=(robots[commands[i].robotNum].dir==0) ? 3 : robots[commands[i].robotNum].dir-1;
                    }
                    break;
                case "R":
                    // 돌리기
                    robots[commands[i].robotNum].dir=(robots[commands[i].robotNum].dir+commands[i].cnt)%4;
                    break;
                case "F":
                    for (int t = 0; t < commands[i].cnt; t++) {
                        int nx = robots[commands[i].robotNum].x + dx[robots[commands[i].robotNum].dir];
                        int ny = robots[commands[i].robotNum].y + dy[robots[commands[i].robotNum].dir];
                        if(nx >= 0 && nx < A && ny >= 0 && ny < B){
                            // 다른 로봇이랑 충돌했는지 확인
                            for (int j = 1; j < robots.length; j++) {
                                if(j != commands[i].robotNum){
                                    if(robots[j].x == nx && robots[j].y == ny){
                                        answer = "Robot " + commands[i].robotNum + " crashes into robot " + j;
                                        break L;
                                    }
                                }
                            }
//                            System.out.println(nx + " " + ny);
                            // 충돌 안했다면 업데이트
                            robots[commands[i].robotNum].x = nx;
                            robots[commands[i].robotNum].y = ny;
                        }else{ // 벽에 부딪혔다면 answer 변경 후 탈출
                            answer = "Robot " + commands[i].robotNum + " crashes into the wall";
                            break L;
                        }
                    }
                    break;
            }
        }
        System.out.println(answer);
    }
}
