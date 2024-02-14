import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    static StringBuilder sb;
    static char[][] mtx;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        mtx = new char[N][N];

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                mtx[i][j] = input[j];
            }
        }

        char c = check(0, 0, N);
        if(c!='?'){
            sb.append(c);
        }else{
            sb.append("(");
            quad(N, 0, 0);
            sb.append(")");
        }

        System.out.println(sb);
    }

    // 각 4가지 파트에 대해서 분할정복 진행, 하나라면 그것만 append하고 리턴
    private static void quad(int N, int x, int y){
        char c;
        // basis part
        if(N==1){
            sb.append(mtx[x][y]);
//            System.out.println(sb);
            return;
        }
        // inductive part
        // 왼위
        c = check(x, y, N/2);
        if(c!='?'){
            sb.append(c);
        }else{
            sb.append("(");
            quad(N/2, x, y);
            sb.append(")");
        }
        // 오른위
        c = check(x, y+N/2, N/2);
        if(c!='?'){
            sb.append(c);
        }else{
            sb.append("(");
            quad(N/2, x, y+N/2);
            sb.append(")");
        }
        // 왼아래
        c = check(x+N/2, y, N/2);
        if(c != '?'){
            sb.append(c);
        }else{
            sb.append("(");
            quad(N/2, x+N/2, y);
            sb.append(")");
        }
        // 오른아래
        c = check(x+N/2, y+N/2, N/2);
        if(c != '?'){
            sb.append(c);
        }else{
            sb.append("(");
            quad(N/2, x+N/2, y+N/2);
            sb.append(")");
        }
    }

    // check 메소드, 1만 있는지 0만 있는지 파악하고 하나만 있으면 그걸 반환, 아니면 ? 반환
    private static char check(int x, int y, int size){
        char check = mtx[x][y];
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if(mtx[i][j]!=check) return '?';
            }
        }
        return check;
    }
}
