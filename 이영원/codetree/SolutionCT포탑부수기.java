import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int N, M, K, liveCnt;

    static class Cannon{
        int attack, turn, hit; // 공격력, 공격한 턴, 맞은 턴

        public Cannon(int attack, int turn, int hit){
            this.attack = attack;
            this.turn = turn;
            this.hit = hit;
        }
    }

    static class Position {
        int x, y;
        Position prev; // 이전 좌표를 저장하기 위한 필드

        public Position(int x, int y, Position prev) {
            this.x = x;
            this.y = y;
            this.prev = prev;
        }
    }

    static int[] dx = {0, 1, 0, -1, -1, 1, 1, -1}; // 우 하 좌 상 대각
    static int[] dy = {1, 0, -1, 0, 1, 1, -1, -1}; // 우 하 좌 상 대각

    static Cannon[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 턴 수

        map = new Cannon[N][M];
        liveCnt = N*M;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = new Cannon(Integer.parseInt(st.nextToken()), 0, 0);
                if(map[i][j].attack==0) liveCnt--;
            }
        }

        for(int i=1;i<=K;i++){ // K번 반복
            Point attacker = selectAttacker(i);
            Point victim = selectVictim(attacker);

//            System.out.println(i + " " + liveCnt);
//            System.out.println(attacker);
//            System.out.println(victim);
//            print();

            attack(new Position(attacker.x, attacker.y, null), new Position(victim.x, victim.y, null), i);

//             print();

            if(liveCnt<=1) break;

            restore(i);

//             print();
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                answer = Math.max(map[i][j].attack, answer);
            }
        }

        System.out.println(answer);
    }

    private static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j].attack + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void restore(int turn){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j].attack <= 0 || map[i][j].turn==turn || map[i][j].hit==turn) continue;
                map[i][j].attack++;
            }
        }
    }

    private static Point selectAttacker(int turn){
        Cannon result = null;  // 초기값을 null로 설정
        int x = -1, y = -1;  // 유효하지 않은 좌표로 초기화

        // 첫 번째 부서지지 않은 포탑을 찾음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].attack > 0) {  // 부서지지 않은 포탑을 찾으면
                    result = map[i][j];
                    x = i;
                    y = j;
                    break;  // 첫 번째 포탑을 찾으면 루프를 종료
                }
            }
            if (result != null) break;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j].attack <= 0) continue;
                if(result.attack==0){
                    result = map[i][j];
                    x=i;
                    y=j;
                    continue;
                }
                if(result.attack > map[i][j].attack){
                    result = map[i][j];
                    x=i;
                    y=j;
                }else if(result.attack == map[i][j].attack){
                    if(result.turn < map[i][j].turn){
                        result = map[i][j];
                        x=i;
                        y=j;
                    } else if(result.turn == map[i][j].turn){
                        if(x+y < i+j){
                            result = map[i][j];
                            x=i;
                            y=j;
                        } else if(x+y == i+j){
                            if(y < j){
                                result = map[i][j];
                                x=i;
                                y=j;
                            }
                        }
                    }
                }
            }
        }

        map[x][y].attack += N+M;
        map[x][y].turn = turn;

        return new Point(x, y);
    }

    private static Point selectVictim(Point attacker){
        Cannon result = null;  // 초기값을 null로 설정
        int x = -1, y = -1;  // 유효하지 않은 좌표로 초기화

        // 첫 번째 부서지지 않은 포탑을 찾음 (공격자를 제외하고)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (attacker.x == i && attacker.y == j) continue;  // 공격자 제외
                if (map[i][j].attack > 0) {  // 부서지지 않은 포탑을 찾으면
                    result = map[i][j];
                    x = i;
                    y = j;
                    break;  // 첫 번째 포탑을 찾으면 루프 종료
                }
            }
            if (result != null) break;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(attacker.x == i && attacker.y == j) continue;
                if(map[i][j].attack <= 0) continue;
                if(result.attack==0){
                    result = map[i][j];
                    x=i;
                    y=j;
                    continue;
                }

                if(result.attack < map[i][j].attack){
                    result = map[i][j];
                    x=i;
                    y=j;
                }else if(result.attack == map[i][j].attack){
                    if(result.turn > map[i][j].turn){
                        result = map[i][j];
                        x=i;
                        y=j;
                    }else if(result.turn == map[i][j].turn){
                        if(x+y > i+j){
                            result = map[i][j];
                            x=i;
                            y=j;
                        } else if(x+y == i+j){
                            if(y > j){
                                result = map[i][j];
                                x=i;
                                y=j;
                            }
                        }
                    }
                }
            }
        }

        return new Point(x, y);
    }

    private static boolean laserAttack(Position attacker, Position victim, int x, int y, int turn) {
        boolean[][] visited = new boolean[N][M];
        Deque<Position> dq = new ArrayDeque<>();
        dq.offerLast(new Position(x, y, null)); // 시작점 추가
        visited[x][y] = true;

        // BFS 탐색
        while (!dq.isEmpty()) {
            Position current = dq.pollFirst();

            // 피해자의 위치에 도달하면 경로 추적 후 피해를 적용
            if (current.x == victim.x && current.y == victim.y) {
                List<Position> path = new ArrayList<>();
                Position temp = current;
                while (temp != null) {
                    path.add(temp);
                    temp = temp.prev; // 경로 추적
                }
                applyDamage(attacker, victim, path, turn); // 경로 상의 포탑들에게 피해 적용
                return true;
            }

            // 4방향 탐색 (우-하-좌-상 순서로)
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 경계 밖으로 나가면 반대편으로 이동
                if (nx < 0) nx = N - 1;
                else if (nx >= N) nx = 0;
                if (ny < 0) ny = M - 1;
                else if (ny >= M) ny = 0;

                // 부서진 포탑은 통과 불가능
                if (map[nx][ny].attack <= 0 || visited[nx][ny]) continue;

                // 큐에 추가하면서 경로 추적을 위해 이전 좌표 저장
                dq.offerLast(new Position(nx, ny, current));
                visited[nx][ny] = true;
            }
        }

        return false; // 경로가 없으면 false 반환
    }

    private static void applyDamage(Position attacker, Position victim, List<Position> path, int turn) {
        // 경로 상의 모든 포탑들에게 피해를 적용 (마지막 포탑은 전체 피해, 중간 포탑들은 절반 피해)
        for (int i = 0; i < path.size(); i++) {
            Position p = path.get(i);
            if (victim.x == p.x && victim.y == p.y) {  // 마지막 포탑은 피해자
                map[p.x][p.y].attack -= map[attacker.x][attacker.y].attack;
            } else if (attacker.x == p.x && attacker.y == p.y) continue;
            else {  // 중간에 있는 포탑들은 절반의 피해를 받음
                map[p.x][p.y].attack -= map[attacker.x][attacker.y].attack / 2;
            }

            // 포탑이 부서졌다면 liveCnt 감소
            if (map[p.x][p.y].attack <= 0) {
                map[p.x][p.y].attack = 0;  // 공격력이 0 이하로 떨어지면 0으로 고정
                liveCnt--;  // 부서진 포탑 개수 줄임
            }

            map[p.x][p.y].hit = turn;  // 이번 턴에 피해를 입었음을 표시
        }
    }

    private static void attack(Position attacker, Position victim, int turn) {
        // 레이저 공격을 시도하고 실패하면 포탄 공격
        if (!laserAttack(attacker, victim, attacker.x, attacker.y, turn)) {
            cannonAttack(attacker, victim, turn);
        }
    }


    private static void cannonAttack(Position attacker, Position victim, int turn) {
        // 피해자에게 피해 적용
        map[victim.x][victim.y].attack -= map[attacker.x][attacker.y].attack;
        map[victim.x][victim.y].hit = turn;

        // 피해자가 부서졌다면 liveCnt 감소
        if (map[victim.x][victim.y].attack <= 0) {
            map[victim.x][victim.y].attack = 0;
            liveCnt--;  // 부서진 포탑 개수 줄임
        }

        // 피해자 주변에 있는 포탑들에게 절반의 피해를 적용
        for (int i = 0; i < 8; i++) {
            int nx = victim.x + dx[i];
            int ny = victim.y + dy[i];

            // 경계 밖으로 나가면 반대편으로 이동
            if (nx < 0) nx = N - 1;
            else if (nx >= N) nx = 0;

            if (ny < 0) ny = M - 1;
            else if (ny >= M) ny = 0;

            if (map[nx][ny].attack <= 0) continue;  // 부서진 포탑은 무시
            if (nx == attacker.x && ny == attacker.y) continue;  // 공격자는 피해를 받지 않음

            map[nx][ny].attack -= map[attacker.x][attacker.y].attack / 2;

            // 포탑이 부서졌다면 liveCnt 감소
            if (map[nx][ny].attack <= 0) {
                map[nx][ny].attack = 0;
                liveCnt--;  // 부서진 포탑 개수 줄임
            }

            map[nx][ny].hit = turn;
        }
    }

}
