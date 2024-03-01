import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 위부터 시계방향 8방
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1}; // 위부터 시계방향 8방

    static int N, M, K;
    static PriorityQueue<Tree> pq; // 우선순위 큐
    static Deque<Tree> dead; // 죽은거 모을 deque
    static int[][] A; // 양분상황 2차원 배열

    static class Tree{
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "x=" + x +
                    ", y=" + y +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] original = new int[N][N]; // 겨울에 양분을 채워주기 위한 2차원 배열
        A = new int[N+2][N+2]; // 테두리 채우기
        int time=0;
        pq = new PriorityQueue<>((a,b) -> {
            return a.age - b.age;
        });
        dead = new ArrayDeque<>();

        Arrays.fill(A[0], -1);
        Arrays.fill(A[N+1], -1);
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                A[i][j] = 5;
                original[i-1][j-1] = Integer.parseInt(st.nextToken());
            }
            A[i][0] = -1;
            A[i][N+1] = -1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            pq.add(new Tree(x, y, z));
        }

        while(time!=K){
            spring();
            summer();
            autumn();
            winter(original);
//            System.out.println(pq);
            time++;
        }

        System.out.println(pq.size());
    }

    // 나이가 적은 순으로 양분을 먹고 성장하고 다시 pq에 넣어준다.
    private static void spring(){
        Deque<Tree> dq = new ArrayDeque<>();
        int size = pq.size();
        for (int i = 0; i < size; i++) {
            Tree t = pq.poll();
            if(A[t.x][t.y] >= t.age){
                A[t.x][t.y]-=t.age;
                dq.offerLast(t);
            }else{
                dead.offerLast(t);
            }
        }

        while(!dq.isEmpty()){
            Tree t = dq.pollFirst();
            t.age++;
            pq.offer(t);
        }
//        System.out.println("봄");
//        print();
    }

    // 죽은 것들의 자리에서 양분을 추가해준다.
    private static void summer(){
        while(!dead.isEmpty()){
            Tree t = dead.pollFirst();
            A[t.x][t.y]+=t.age/2;
        }
//        System.out.println("여름");
//        print();
    }

    // 나무의 나이가 5의 배수라면 증식, 이후 다시 pq에 넣는다.
    private static void autumn(){
        Deque<Tree> dq = new ArrayDeque<>();
        while(!pq.isEmpty()){
            Tree t = pq.poll();
            if(t.age%5==0){
                for (int i = 0; i < 8; i++) {
                    int nx = t.x + dx[i];
                    int ny = t.y + dy[i];
                    if(A[nx][ny]!=-1){
                        dq.offerLast(new Tree(nx, ny, 1));
                    }
                }
            }
            dq.offerLast(t);
        }

        while(!dq.isEmpty()){
            pq.offer(dq.pollFirst());
        }

//        System.out.println("가을");
//        print();
    }

    // original의 값을 양분에 추가
    private static void winter(int[][] original){
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                A[i][j]+= original[i-1][j-1];
            }
        }

//        System.out.println("겨울");
//        print();
    }

    private static void print(){
        System.out.println(pq);
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
