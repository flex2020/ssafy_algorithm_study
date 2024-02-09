import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{

    static int [] turnDir;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int K = Integer.parseInt(br.readLine());
            int[][] magnets = new int[4][8];
            int[][] move = new int[K][2];
            turnDir = new int[4];
            int answer = 0;

            for(int i=0;i<4;i++){ // 자석 정보 입력
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<8;j++){
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0;i<K;i++){ // 자석 회전 시행 입력
                st = new StringTokenizer(br.readLine());
                move[i][0]=Integer.parseInt(st.nextToken());
                move[i][1]=Integer.parseInt(st.nextToken());
            }

            for(int i=0;i<K;i++){
                turn(magnets, move[i][0], move[i][1]);
            }

            answer = scoring(magnets);

            System.out.println("#" + test_case + " " + answer);

        }
    }

    // 자석 돌리는 메소드
    private static void turn(int[][] magnets, int num, int dir){
        turnDir[num-1]=(dir==1) ? 1 : -1; // 처음 회전 방향 설정
        for(int i=num-1, j=0;i<3;i++, j++){ // 처음 회전 자석 기준 오른쪽 자석들의 회전 방향 설정
            if(turnDir[i]==-1 && magnets[i][2]!=magnets[i+1][6]) turnDir[i+1]=1;
            else if(turnDir[i]==1 && magnets[i][2]!=magnets[i+1][6]) turnDir[i+1]=-1;
        }
        for(int i=num-1, j=0;i>0;i--, j++){ // 처음 회전 자석 기준 왼쪽 자석들의 회전 방향 설정
            if(turnDir[i]==-1 && magnets[i][6]!=magnets[i-1][2]) turnDir[i-1]=1;
            else if(turnDir[i]==1 && magnets[i][6]!=magnets[i-1][2]) turnDir[i-1]=-1;
        }

        for(int i=0;i<4;i++){ // 설정된 방향을 토대로 자석 돌리기
            if(turnDir[i]==1) clockWiseTurn(magnets, i);
            else if(turnDir[i]==-1) counterClockWiseTurn(magnets, i);
            turnDir[i]=0; // 회전 방향 초기화
        }
    }

    // 시계 방향 회전
    private static void clockWiseTurn(int[][] magnets, int num){
        int tmp = magnets[num][7];
        for(int i=7;i>0;i--){
            magnets[num][i]=magnets[num][i-1];
        }
        magnets[num][0]=tmp;
    }

    // 반시계 방향 회전
    private static void counterClockWiseTurn(int[][] magnets, int num){
        int tmp = magnets[num][0];
        for(int i=0;i<7;i++){
            magnets[num][i]=magnets[num][i+1];
        }
        magnets[num][7]=tmp;
    }

    // 점수 세기
    private static int scoring(int[][] magnets){
        int result = 0;
        int num=1;
        for(int i=0;i<4;i++){
            if(magnets[i][0]==1) result+=num;
            num*=2;
        }
        return result;
    }
}
