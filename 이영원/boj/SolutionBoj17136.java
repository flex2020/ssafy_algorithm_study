import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N = 10;
    static int[][] map;
    static int answer;
    static int[] paperCnt = {0, 5, 5, 5, 5, 5};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = Integer.MAX_VALUE;
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(0);


        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);


    }

    private static void recursive(int cnt) {
        int nx = -1;
        int ny = -1;

        // map에서 1인 곳을 찾아
        L:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    nx = i;
                    ny = j;
                    break L;
                }
            }
        }

        if (nx == -1 && ny == -1) { // 이미 다 봤으면 최소치로 만들고 리턴
            answer = Math.min(answer, cnt);
            return;
        }
//        if(nx==4 && ny==4){
//            System.out.println("a");
//        }
        int size = getPaperSize(nx, ny); // 최대 사이즈 찾기

        if (size == -1) return; // 맞는 사이즈 없으면 리턴

        for (int i = 1; i <= size; i++) { // 사이즈를 작은순으로 돌려가면서 색칠하고, 원복해준다.
            if (paperCnt[i] > 0) {
                paperCnt[i]--;
//                System.out.println(nx + " " + ny + " " + i);
                for (int r = nx; r < nx + i; r++) {
                    for (int c = ny; c < ny + i; c++) {
                        map[r][c] = 0;
                    }
                }
//                System.out.println("칠하기");
//                print();
                recursive(cnt + 1);

                paperCnt[i]++;
                for (int r = nx; r < nx + i; r++) {
                    for (int c = ny; c < ny + i; c++) {
                        map[r][c] = 1;
                    }
                }
//                System.out.println("원복");
//                print();
            }
        }

    }

    private static int getPaperSize(int x, int y) {
        L:
        for (int i = 5; i >= 1; i--) {
            for (int j = x; j < x + i; j++) {
                for (int k = y; k < y + i; k++) {
                    if (j >= N || k >= N) continue L;
                    if (map[j][k] == 0) continue L;
                    if (j == x + i - 1 && k == y + i - 1) return i;
                }
            }
        }
        return -1;
    }


    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
