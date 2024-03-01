import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {

    static int R, C;
    static char[][] map;
    static boolean[][][][] visited; // r, c, mem(숫자), dir(방향)
    static boolean flag; // @가 아예 없는지 확인하기 위한 것
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());


        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            flag=false;
            int mem = 0;

            map = new char[R][C];
            visited = new boolean[R][C][16][4]; // mem은 0~15, 방향은 4방
            for (int i = 0; i < R; i++) {
                String input = br.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = input.charAt(j);
                    if (map[i][j] == '@') flag = true;
                }
            }
            if (flag) { // 맵에 @가 있다면 dfs 진행
                System.out.println("#" + t + " " + (dfs(0, 0, 3, mem)?"YES":"NO"));
            } else { // 맵에 @가 아예 없다면 그냥 NO
                System.out.println("#" + t + " NO");
            }
        }
    }

    // r, c, dir, mem
    private static boolean dfs(int r, int c, int dir, int mem) {
        // r이나 c가 범위를 벗어났다면 반대쪽으로 돌려서 가져오기
        if(r<0) {
            r=R-1;
        }else if(r>=R) {
            r=0;
        }else if(c<0) {
            c=C-1;
        }else if(c>=C) {
            c=0;
        }

        // 해당 좌표에 같은 mem값, 같은 dir값을 갖고 도착했다면 루프에 걸렸다는 뜻이므로 false 리턴
        if (visited[r][c][mem][dir]) return false; // 순환
        // 아니라면 visited 체크 후 switch문 진행
        visited[r][c][mem][dir] = true;

        switch(map[r][c]) {
            case '<':
                dir=2;
                break;
            case '>':
                dir=3;
                break;
            case '^':
                dir=0;
                break;
            case 'v':
                dir=1;
                break;
            case '_':
                if(mem==0) dir=3;
                else dir=2;
                break;
            case '|':
                if(mem==0) dir=1;
                else dir=0;
                break;
            case '?': // 물음표라면 각 방향으로 dfs 진행해보기
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(dfs(nr, nc, i, mem)) return true;
                }
                return false;
            case '.':
                break;
            case '@':
                return true;
            case '+':
                mem= (mem+1)%16;
                break;
            case '-':
                mem=(mem==0)?15:mem-1;
                break;
            case '0':
                mem = 0;
                break;
            case '1':
                mem = 1;
                break;
            case '2':
                mem = 2;
                break;
            case '3':
                mem = 3;
                break;
            case '4':
                mem = 4;
                break;
            case '5':
                mem = 5;
                break;
            case '6':
                mem = 6;
                break;
            case '7':
                mem = 7;
                break;
            case '8':
                mem = 8;
                break;
            case '9':
                mem = 9;
                break;

        }
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        // 바뀐 값으로 다시 dfs 진행(@만나거나 루프 만날때까지)
        if(dfs(nr, nc, dir, mem)) return true;
        return false;
    }
}
