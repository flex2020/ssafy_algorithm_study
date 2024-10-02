import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K; // 격자의 크기 N, 팀의 개수 M, 라운드 수 K
    static int[][] map; // 격자 맵을 나타내는 2차원 배열
    static int score; // 점수를 저장할 변수

    // 방향 배열 (우, 상, 좌, 하)
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    // 사람(Person) 클래스: 머리사람, 꼬리사람, 나머지 사람들 모두를 표현
    static class Person{
        int x, y; // 현재 위치 좌표
        Person next; // 다음 사람
        Person prev; // 이전 사람

        // 머리사람과 꼬리사람 등을 연결하는 생성자
        public Person(int x, int y, Person next, Person prev){
            this.x = x;
            this.y = y;
            this.next = next;
            this.prev = prev;
        }

        // 기본 생성자
        public Person(int x, int y){
            this.x = x;
            this.y = y;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    // 그룹(Group) 클래스: 한 팀을 나타내며, 머리사람과 꼬리사람을 관리
    static class Group{
        Person head; // 팀의 머리사람
        Person tail; // 팀의 꼬리사람
        int size; // 팀의 크기

        // 머리사람과 꼬리사람을 설정하는 생성자
        public Group(Person head, Person tail){
            this.head = head;
            this.tail = tail;
            this.size = 0;
        }

        // 머리사람과 꼬리사람을 바꾸는 함수 (공을 맞았을 때)
        public void switchHeadAndTail(){
            Person next = head.next;
            Person prev = head.prev;
            Person cur = head;
            this.tail = head; // 머리사람이 꼬리사람이 됨
            while(true){
                cur.next = prev; // 다음 사람을 이전 사람으로 설정
                cur.prev = next; // 이전 사람을 다음 사람으로 설정
                if(cur.prev == null) { // 이전 사람이 없으면 머리사람으로 설정
                    this.head = cur;
                    break;
                }
                cur = cur.prev;
                next = cur.next;
                prev = cur.prev;
            }
            // 바뀐 머리사람과 꼬리사람의 위치를 맵에 업데이트
            map[this.tail.x][this.tail.y] = 3; // 꼬리사람 위치
            map[this.head.x][this.head.y] = 1; // 머리사람 위치
        }
    }

    static List<Group> group; // 모든 팀을 저장할 리스트

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 팀 개수
        K = Integer.parseInt(st.nextToken()); // 라운드 수

        map = new int[N][N]; // 격자 초기화
        score = 0; // 점수 초기화

        group = new ArrayList<>(); // 팀 그룹 리스트 초기화

        // 격자 정보 입력
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 머리사람을 찾으면 그룹을 만듦
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 1) makeGroup(i, j); // 머리사람(1)을 찾으면 그룹을 만듦
            }
        }

        // 각 라운드를 진행
        for(int round = 0; round < K; round++){
            move(); // 팀 이동

            int startX = 0, startY = 0;
            int dir = (round / N) % 4; // 공의 던지는 방향 결정 (우, 상, 좌, 하)
            switch(dir){ // 각 방향에 따라 공을 던지는 시작 지점 설정
                case 0: // 우
                    startX = round % N;
                    startY = 0;
                    break;
                case 1: // 상
                    startX = N - 1;
                    startY = round % N;
                    break;
                case 2: // 좌
                    startX = N - 1 - (round % N);
                    startY = N - 1;
                    break;
                case 3: // 하
                    startX = 0;
                    startY = N - 1 - (round % N);
                    break;
            }
            shoot(dir, startX, startY); // 공을 던짐
        }

        System.out.println(score); // 최종 점수 출력
    }

    // 새로운 팀 그룹 생성
    private static void makeGroup(int x, int y){
        boolean[][] visited = new boolean[N][N]; // 방문 배열
        Deque<Person> dq = new ArrayDeque<>(); // 큐를 이용하여 BFS 탐색
        Person head = new Person(x, y, null, null); // 머리사람 생성
        Group g = new Group(head, head); // 새로운 팀 그룹 생성
        Person cur = head;
        Person prev = head;
        dq.offerLast(head);
        g.size = 1; // 그룹 크기 설정
        while(!dq.isEmpty()){ // BFS 탐색으로 팀을 연결
            cur = dq.pollFirst();
            visited[cur.x][cur.y] = true;
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] <= 0 || map[nx][ny] >= 4) continue;
                if(map[nx][ny] == 3 && g.size <= 1) continue; // 꼬리사람인데 그룹 크기가 1인 경우는 패스
                prev = cur;
                cur = new Person(nx, ny, null, prev); // 새로운 사람 추가
                dq.offerLast(cur);
                prev.next = cur;
                g.tail = cur; // 꼬리사람 업데이트
                break;
            }
            g.size++; // 그룹 크기 증가
        }
        group.add(g); // 그룹 리스트에 추가
    }

    // 공을 던져서 점수를 획득하는 함수
    private static void shoot(int dir, int startX, int startY){
        int nx = startX;
        int ny = startY;
        while(true){
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) break; // 격자를 벗어나면 종료

            if(map[nx][ny] >= 1 && map[nx][ny] <= 3) { // 사람이 있는 경우
                scoring(nx, ny); // 점수 계산
                break;
            }

            nx += dx[dir];
            ny += dy[dir];
        }
    }

    // 점수 계산 및 머리-꼬리 변경
    private static void scoring(int x, int y){
        int cnt = 1; // 팀 내에서 몇 번째 사람인지 세는 변수
        for (int i = 0; i < group.size(); i++) {
            Group g = group.get(i);
            Person cur = g.head;
            while(cur != null){
                if(x == cur.x && y == cur.y){ // 공을 맞은 사람을 찾으면
                    score += (cnt * cnt); // 점수 계산
                    g.switchHeadAndTail(); // 머리사람과 꼬리사람 변경
                    return;
                }
                cnt++;
                cur = cur.next;
            }
            cnt = 1; // 새로운 그룹으로 넘어가면 다시 1로 초기화
        }
    }

    // 팀 이동
    private static void move(){
        for (int i = 0; i < group.size(); i++) {
            Group g = group.get(i);
            Person cur = g.head;
            Person prev = moveHead(cur); // 머리사람 이동
            prev = moveOther(prev, cur.next); // 나머지 사람 이동
            paintMap(i, prev); // 맵에 이동 결과 반영
        }
    }

    // 맵에 팀의 위치를 반영하는 함수
    private static void paintMap(int idx, Person prev){
        map[prev.x][prev.y] = 4; // 이동 선
        Group g = group.get(idx);
        map[g.head.x][g.head.y] = 1; // 머리사람 위치
        map[g.tail.x][g.tail.y] = 3; // 꼬리사람 위치
        Person cur = g.head.next;
        while(cur.next != null){
            map[cur.x][cur.y] = 2; // 나머지 사람 위치
            cur = cur.next;
        }
    }

    // 나머지 사람들 이동
    private static Person moveOther(Person prev, Person cur){
        while(cur != null){
            int x = cur.x;
            int y = cur.y;
            cur.x = prev.x;
            cur.y = prev.y;
            prev.x = x;
            prev.y = y;
            cur = cur.next;
        }
        return new Person(prev.x, prev.y);
    }

    // 머리사람 이동
    private static Person moveHead(Person head){
        Person prevHead = new Person(head.x, head.y);
        for (int i = 0; i < 4; i++) {
            int nx = head.x + dx[i];
            int ny = head.y + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] <= 2) continue; // 이동 가능한지 확인
            head.x = nx;
            head.y = ny;
            break;
        }
        return prevHead;
    }

    // 디버깅용: 그룹 정보 출력
    private static void print(){
        for(int i = 0; i < group.size(); i++){
            Group g = group.get(i);
            Person cur = g.head;
            while(cur != null){
                System.out.print(cur + " -> ");
                cur = cur.next;
            }
            System.out.println();
        }
        System.out.println();
    }

    // 디버깅용: 맵 정보 출력
    private static void printMap(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
