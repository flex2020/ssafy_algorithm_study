import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int M, T;  // M: 몬스터의 수, T: 턴 수
    static int answer;  // 최종 남은 몬스터의 수
    static int[][] map;  // 각 좌표에 있는 몬스터의 수를 저장하는 배열

    static int[][][] monsters;  // 몬스터들의 방향 정보를 저장하는 배열
    static int[][][] eggs;  // 복제된 몬스터 정보를 저장하는 배열
    static Point pacman;  // 팩맨의 현재 위치
    static int[][] seeche;  // 시체가 있는 좌표를 저장 (2턴 후에 사라짐)

    // 몬스터와 팩맨의 움직임을 위한 8방향 배열 (상, 좌상, 좌, 좌하, 하, 우하, 우, 우상)
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static int maxEat;  // 팩맨이 먹을 수 있는 최대 몬스터 수
    static Point[] bestPath;  // 팩맨의 가장 좋은 이동 경로를 저장하는 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());  // 몬스터 수 입력
        T = Integer.parseInt(st.nextToken());  // 턴 수 입력

        map = new int[4][4];  // 4x4 맵 초기화
        seeche = new int[4][4];  // 시체 정보 초기화
        monsters = new int[4][4][8];  // 각 좌표에 있는 몬스터의 방향 정보 초기화 (8방향)
        eggs = new int[4][4][8];  // 복제된 몬스터 정보 초기화

        // 팩맨 초기 위치 입력
        st = new StringTokenizer(br.readLine());
        pacman = new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

        // 몬스터들의 초기 위치와 방향 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            monsters[x-1][y-1][d-1]++;  // 몬스터 위치 및 방향 저장
            map[x-1][y-1]++;  // 해당 좌표에 몬스터 수 증가
        }

        // T번의 턴 동안 게임 진행
        for (int t = 0; t < T; t++) {
            // 1. 몬스터 복제 시도
            monsterReplicate();
            // 2. 몬스터 이동
            monsterMove();
            // 3. 팩맨 이동
            pacmanMove();
            // 4. 시체 소멸 처리
            deleteSeeche();
            // 5. 몬스터 복제 완료
            completeReplicate();
        }

        // 마지막에 살아남은 몬스터의 수 계산
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }

    // 몬스터 복제 시도 (복제된 몬스터는 바로 움직이지 않음)
    private static void monsterReplicate(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 8; k++) {
                    eggs[i][j][k] = monsters[i][j][k];  // 기존 몬스터 정보를 복제 배열로 저장
                }
            }
        }
    }

    // 몬스터 이동 (갈 수 있는 곳이 없으면 제자리에)
    private static void monsterMove(){
        int[][][] newMonsters = new int[4][4][8];  // 새로운 몬스터 배열
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 8; k++) {
                    if(monsters[i][j][k] > 0){  // 몬스터가 존재할 경우
                        int dir = k;  // 현재 몬스터 방향
                        for (int d = 0; d < 8; d++) {  // 8방향 모두 확인
                            int nx = i + dx[dir];
                            int ny = j + dy[dir];
                            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || seeche[nx][ny] > 0 || (nx == pacman.x && ny == pacman.y)){
                                dir = (dir + 1) % 8;  // 반시계 방향으로 회전
                                if(d == 7) newMonsters[i][j][k] += monsters[i][j][k];  // 이동할 곳이 없으면 제자리에
                                continue;
                            }
                            newMonsters[nx][ny][dir] += monsters[i][j][k];  // 새로운 위치로 이동
                            map[nx][ny] += monsters[i][j][k];  // 맵에 몬스터 수 반영
                            map[i][j] -= monsters[i][j][k];  // 기존 위치에서 몬스터 수 감소
                            break;
                        }
                    }
                }
            }
        }
        monsters = newMonsters;  // 업데이트된 몬스터 배열로 변경
    }

    // 팩맨 이동 (최대 몬스터를 먹을 수 있는 경로로 이동)
    private static void pacmanMove(){
        boolean[][] visited = new boolean[4][4];  // 방문 체크 배열
        maxEat = -1;
        bestPath = new Point[3];  // 팩맨이 이동할 3칸의 경로 저장
        for (int i = 0; i < 3; i++) {
            bestPath[i] = new Point();
        }
        List<Point> candList = new ArrayList<>();  // 경로 후보 리스트
        dfs(visited, 0, 0, candList, pacman.x, pacman.y);  // DFS로 최적 경로 탐색

        // 팩맨이 몬스터를 먹고 시체 남김
        for (int i = 0; i < 3; i++) {
            map[bestPath[i].x][bestPath[i].y] = 0;  // 해당 좌표의 몬스터 수 0으로 변경
            for (int j = 0; j < 8; j++) {
                if(monsters[bestPath[i].x][bestPath[i].y][j] > 0){
                    monsters[bestPath[i].x][bestPath[i].y][j] = 0;  // 해당 좌표의 몬스터 제거
                    seeche[bestPath[i].x][bestPath[i].y] = 3;  // 시체는 3턴 동안 유지
                }
            }
        }

        // 팩맨의 위치 업데이트
        pacman.x = bestPath[2].x;
        pacman.y = bestPath[2].y;
    }

    // DFS를 통해 팩맨이 이동할 최적의 경로 탐색
    private static void dfs(boolean[][] visited, int depth, int eat, List<Point> candList, int x, int y){
        if(depth == 3){  // 3칸 이동한 경우
            if(maxEat < eat){  // 최대 먹을 수 있는 몬스터 수 갱신
                maxEat = eat;
                for (int i = 0; i < 3; i++) {
                    bestPath[i].x = candList.get(i).x;
                    bestPath[i].y = candList.get(i).y;
                }
            }
            return;
        }

        // 4방향(상, 좌, 하, 우) 탐색
        for (int i = 0; i < 8; i+=2) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
            candList.add(new Point(nx, ny));  // 현재 좌표 추가
            if(!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(visited, depth + 1, eat + map[nx][ny], candList, nx, ny);
                visited[nx][ny] = false;
            } else {
                dfs(visited, depth + 1, eat, candList, nx, ny);
            }
            candList.remove(depth);
        }
    }

    // 시체 소멸 (2턴 후에 시체가 사라짐)
    private static void deleteSeeche(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(seeche[i][j] > 0) seeche[i][j]--;
            }
        }
    }

    // 몬스터 복제 완료
    private static void completeReplicate(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 8; k++) {
                    monsters[i][j][k] += eggs[i][j][k];  // 복제된 몬스터 추가
                    map[i][j] += eggs[i][j][k];  // 맵에 몬스터 수 반영
                }
            }
        }
        eggs = new int[4][4][8];  // 복제된 몬스터 배열 초기화
    }

    // 맵 출력 함수 (디버깅용)
    private static void print(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
