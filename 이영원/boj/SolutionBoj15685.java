import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {1, 0, -1, 0}; // 우 상 좌 하
    static int[] dy = {0, -1, 0, 1}; // 우 상 좌 하

    private static class Dragon{
        int x, y, d;

        public Dragon(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static boolean[][] map;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 드래곤 커브 개수

        map = new boolean[105][105];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // x
            int y = Integer.parseInt(st.nextToken()); // y
            int d = Integer.parseInt(st.nextToken()); // 방향
            int g = Integer.parseInt(st.nextToken()); // 세대
            makeCurve(x, y, d, g);
//            display();
        }

        counting();

        System.out.println(answer);

    }

    private static void counting() {
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) answer += 1;
            }
        }
    }

    private static void display() {
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                System.out.print(map[i][j] ? 1 : 0);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void makeCurve(int x, int y, int d, int g){
        List<Dragon> list = new ArrayList<>();
        int nx = x;
        int ny = y;
        list.add(new Dragon(nx, ny, d)); // 첫점 넣기
        map[nx][ny]=true;
        nx+=dx[d];
        ny+=dy[d];
        list.add(new Dragon(nx, ny, (d+1)%4)); // 둘점 넣기
        map[nx][ny]=true;
        for (int i = 1; i <= g; i++) {
            int size = list.size();
            for (int j = size-1; j > 0; j--) { // 돌리기(끝에서부터 첫점을 제외하고 방향으로 이동 후 방향 반시계로 돌려주면 된다.
                Dragon dragon = list.get(j);
                nx+=dx[dragon.d];
                ny+=dy[dragon.d];
                map[nx][ny]=true;
                list.add(new Dragon(nx, ny, (dragon.d+1)%4));
            }
        }
    }
}
