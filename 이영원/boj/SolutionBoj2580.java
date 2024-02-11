import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] sudoku;
    static boolean[][][] check;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sudoku = new int[9][9];
        check = new boolean[3][9][9]; // 조건타입, 행, 열

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j]=Integer.parseInt(st.nextToken());
                if(sudoku[i][j]==0) continue;
                check[0][i][sudoku[i][j]-1]=true; // 가로조건
                check[1][j][sudoku[i][j]-1]=true; // 세로조건
                check[2][j/3+i/3*3][sudoku[i][j]-1]=true; // 3*3 조건
            }
        }

//        recursive(0, 0);

        L: for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudoku[i][j]==0) {
                    recursive(i, j);
                    break L;
                }
            }
        }

        print();


    }

    private static boolean recursive(int x, int y){
        // inductive part
        for(int i=x;i<9;i++){
            for(int j=(i==x) ? y : 0;j<9;j++){
                if(sudoku[i][j]==0){
                    for (int k = 0; k < 9; k++) {
                        if(!check[0][i][k] && !check[1][j][k] && !check[2][j/3+i/3*3][k]){ // 조건을 하나도 만족하지 않는곳(넣을곳)을 찾으면 넣고 재귀
                            check[0][i][k]=true;
                            check[1][j][k]=true;
                            check[2][j/3+i/3*3][k]=true;
                            sudoku[i][j]=k+1;
                            if(recursive(i, j)) return true;
                            // false면 원복하고 다시 반복
                            check[0][i][k]=false;
                            check[1][j][k]=false;
                            check[2][j/3+i/3*3][k]=false;
                            sudoku[i][j]=0;
                        }
                        // 모든 숫자를 돌려봤는데 없으면 그건 실패니까 false 리턴
                        if(k==8) return false;
                    }
                }
            }
        }
        return true;
    }

    // 출력해보는 메소드
    private static void print(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
