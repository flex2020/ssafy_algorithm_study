import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int answer=0;
            int N = sc.nextInt();
            int[][] farm = new int[N][N];

            for (int i = 0; i < N; i++) { // farm 넣기
                String input = sc.next();
                for (int j = 0; j < N; j++) {
                    farm[i][j]=Integer.parseInt(input.charAt(j)+"");
                }
            }

            int center = N/2; // 중심 인덱스


            for(int i=0;i<center;i++){ // 늘어나는 부분 덧셈
                for(int j=center-i;j<=center+i;j++){
                    answer+=farm[i][j];
                }
            }

            int minus=N/2; // 줄어드는 부분을 위한 minus 지표

            for(int i=N/2;i<N;i++, minus--){ // 줄어드는 부분 덧셈
                for(int j=center-minus;j<=center+minus;j++){
                    answer+=farm[i][j];
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}