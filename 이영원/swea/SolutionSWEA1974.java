import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] sudoku = new int[9][9];
        boolean flag=true;

        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            boolean[][][] check = new boolean[3][9][9]; // 조건타입, 몇번째 조건인지, 몇번을 담당하는지
            flag=true;
            for(int i=0;i<9;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<9;j++){
                    sudoku[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            
            Loop:
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    if(check[0][i][sudoku[i][j]-1] || check[1][j][sudoku[i][j]-1] || check[2][(i/3*3)+(j/3)][sudoku[i][j]-1]){
                        System.out.println("#" + test_case + " " + 0);
                        flag=false;
                        break Loop;
                    }
                    check[0][i][sudoku[i][j]-1]=true; // 가로조건
                    check[1][j][sudoku[i][j]-1]=true; // 세로조건
                    check[2][(i/3*3)+(j/3)][sudoku[i][j]-1]=true; // 3*3 조건
                }
            } // 여기까지 입력
            if(flag){
                System.out.println("#" + test_case + " " + 1);
            }
        }
    }
}
