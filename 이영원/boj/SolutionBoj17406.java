import java.io.BufferedReader;
import java.awt.Point;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][][] ans; // 정답을 담을 배열
    static int[][] original; // 초기화를 위한 초기 배열
    static int answer; // 정답이 들어간 배열의 인덱스
    static int score; // 배열의 값을 저장할 변수
    static int N;
    static int M;
    static int K;
    static int[][] rcs; // r, c, s 값을 저장할 변수
    static boolean[] selected; // 중복체크용 배열(순열)
    static int[] kOrder; // rcs값의 순서를 저장한다.(순열)

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        answer=0; // 처음엔 0에 저장
        score=Integer.MAX_VALUE; // score의 최소값을 찾는것이 문제기 때문에 Integer.MAX_VALUE로 초기화

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 회전 연산의 수

//        arr = new int[N][M];
        ans = new int[2][N][M];
        original = new int[N][M];
        rcs = new int[K][3]; // rcs
        selected = new boolean[K];
        kOrder = new int[K];

        for(int i=0;i<N;i++) { // 초기 배열 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                ans[answer][i][j]=Integer.parseInt(st.nextToken());
                original[i][j]=ans[answer][i][j];
            }
        }

        for (int i = 0; i < K; i++) { // rcs 입력
            st = new StringTokenizer(br.readLine());
            rcs[i][0]=Integer.parseInt(st.nextToken());
            rcs[i][1]=Integer.parseInt(st.nextToken());
            rcs[i][2]=Integer.parseInt(st.nextToken());
        }

        perm(0); // 순열

        System.out.println(score);


    }

    // rcs 순열을 돌리기 위한 재귀 메소드, cnt는 몇번 골랐는지를 확인하기 위함이다.
    private static void perm(int cnt){
        if(cnt==K){ // K개만큼 다 골랐으면 ans[answer] 배열을 초기화하고 result를 얻는다.
            reset();
            getResult();
            return;
        }
        for (int i = 0; i < kOrder.length; i++) {
            if(!selected[i]){ // 선택되지 않았다면 selected, kOrder를 설정하고 재귀, 나오면 돌려준다.
                selected[i]=true;
                kOrder[i]=cnt;
                perm(cnt+1);
                selected[i]=false;
                kOrder[i]=-1;
            }
        }
    }

    // ans[answer] 배열을 original 배열을 이용하여 초기화하는 메소드
    private static void reset(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans[answer][i][j]=original[i][j];
            }
        }
    }

    // 실제 값을 계산하는 메소드
    private static void getResult(){
        for (int i = 0; i < K; i++) {
            // 배열 내에서 반시계 사이클이 몇번 돌아가야하는지 구하기
            int repeat = rcs[kOrder[i]][2]+1;
            int n = rcs[kOrder[i]][2]*2+1;
            for(int j=0;j<repeat;j++) { // 각 사각형마다 돌리는 메소드, 이 경우 시계방향으로 한칸씩만 옮기면 된다.
                turn(n-2*j, n-2*j, ((n-(2*j)+n-(2*j)-2)*2), 1, j, rcs[kOrder[i]][0]-rcs[kOrder[i]][2]-1, rcs[kOrder[i]][1]-rcs[kOrder[i]][2]-1);
            }
            // 돌리지 않은 부분에 대해서는 그 전 값을 그대로 옮겨서 채워준다.
            fill(N, M, rcs[kOrder[i]][0]-rcs[kOrder[i]][2]-1, rcs[kOrder[i]][1]-rcs[kOrder[i]][2]-1, rcs[kOrder[i]][0]+rcs[kOrder[i]][2]-1, rcs[kOrder[i]][1]+rcs[kOrder[i]][2]-1);
            answer=(answer+1)%2; // answer를 뒤집어서 다음 영역에 다른값을 저장한다.
        }
        // score 최솟값을 구한다.
        score = Math.min(score, scoring());
    }

    // 배열값을 구하는 메소드
    private static int scoring(){
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < ans[answer].length; i++) {
            int sum=0;
            for (int j = 0; j < ans[answer][i].length; j++) {
                sum+=ans[answer][i][j];
            }
            result = Math.min(result, sum);
        }
        return result;
    }

    // 회전하지 않은 나머지 부분을 채워주는 메소드
    private static void fill(int N, int M, int rFirst, int cFirst, int rLast, int cLast){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(i>=rFirst && i<=rLast && j>=cFirst && j<=cLast) continue;
                ans[(answer+1)%2][i][j]=ans[answer][i][j];
            }
        }
    }

    // N=돌리는 부분의 가로길이, M=돌리는 부분의 세로길이, cycle=몇번 돌려야 제자리로 돌아오는지, R=돌리는 횟수
    // i=안쪽 사각형에 회전을 위해 인덱스에 추가할 값, r=돌리는 부분의 실제 행 인덱스값(왼쪽 위), c=돌리는 부분의 실제 열 인덱스값(왼쪽 위)
    private static void turn(int N, int M, int cycle, int R, int i, int r, int c) { // 돌리는 함수
        Point startP; // 시작점
        Point targetP; // 타겟점
        if(cycle==0) { // 1*1 사각형이란 뜻이므로 그 값만 그대로 answer에 올려주고 return
            ans[(answer+1)%2][r+i][c+i]=ans[answer][r+i][c+i];
            return;
        }
        int target;
        for(int start=0;start<cycle;start++) {
            target=(start==0) ? cycle-1 : start-1; //시작점을 어디로 옮겨야하는지 결정
            startP = find(start, N, M, r, c); // 시작점 좌표
            targetP = find(target, N, M, r, c); // 목표점 좌표
            ans[(answer+1)%2][targetP.x+i][targetP.y+i]=ans[answer][startP.x+i][startP.y+i]; // 대입
        }
    }

    //	T< N이라면 x만 그만큼 증가
//	N<=T<=(N-1)+(M-1) 이라면 x = N-1, y 는 T-(N-1) 만큼 증가
//	(N-1)+(M-1)<T<2*(N-1)+M-1이라면 x는 2(N+M-2)-T-(M-1), y = (M-1)
//	이외에는 x는 0, y는 2(N+M-2)-T
    private static Point find(int t, int N, int M, int r, int c) {
        int x;
        int y;
        if(t<N) {
            x=t;
            y=0;
        }else if(t<=(N-1)+(M-1)) {
            x=N-1;
            y=t-(N-1);
        }else if(t<2*(N-1)+M-1) {
            x = 2*(N+M-2)-t-(M-1);
            y = M-1;
        }else {
            x=0;
            y=2*(N+M-2)-t;
        }
        return new Point(x+r,y+c);
    }
}
