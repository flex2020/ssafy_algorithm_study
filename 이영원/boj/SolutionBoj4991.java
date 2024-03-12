import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.Point;

public class Main {

    static int w, h, startX, startY, targetCnt;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static List<Point> pointList;

    static class Dust{
        int x, y, num; // 좌표값 + 비트마스킹값

        public Dust(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Dust{" +
                    "x=" + x +
                    ", y=" + y +
                    ", num=" + num +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            int answer=0;
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); // 가로
            h = Integer.parseInt(st.nextToken()); // 세로
            if(w==0 && h==0) break; // 끝
            map = new char[h+2][w+2];

            targetCnt=0;
            startX=0;
            startY=0;

            pointList = new ArrayList<>(); // 먼지 좌표를 저장할 리스트

            // 배열 테두리 채우기
            Arrays.fill(map[0], 'x');
            Arrays.fill(map[h+1], 'x');
            for (int i = 1; i < h+1; i++) {
                String input = br.readLine();
                for (int j = 1; j < w+1; j++) {
                    map[i][j] = input.charAt(j-1);
                    if(map[i][j]=='*') {
                        pointList.add(new Point(i, j));
                    }
                    else if(map[i][j]=='o'){
                        startX=i;
                        startY=j;
                    }
                }
                map[i][0]='x';
                map[i][w+1]='x';
            }

            targetCnt=pointList.size(); // 먼지 개수를 저장
            answer = bfs();
            System.out.println(answer);

        }
    }

    private static int bfs(){
        // 먼지를 청소했는지 아닌지를 visited에 저장, 000100이면 4번째 먼지만 청소한 상태에서 해당 좌표에 도달했다는 뜻
        boolean[][][] visited = new boolean[h+2][w+2][1<<targetCnt];
        int cnt=0;
        Deque<Dust> dq = new ArrayDeque<>();
        dq.offerLast(new Dust(startX, startY, 0));
        while(!dq.isEmpty()){
            int size = dq.size(); // depth별로 bfs 진행
//            System.out.println(dq);
            for (int i = 0; i < size; i++) {
                Dust p = dq.pollFirst();
//                System.out.println(p);
                if(visited[p.x][p.y][(1<<targetCnt)-1]) return cnt;
                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if(map[nx][ny]=='x') continue; // 가구가 있는경우 못가니까 continue
                    if(map[nx][ny]=='.' && visited[nx][ny][p.num]) continue; // 아무것도 없지만 이미 방문한 경우 continue
                    else if(map[nx][ny]=='*'){ // 먼지가 있는곳인 경우
                        int dustIdx = pointList.indexOf(new Point(nx, ny)); // 현재 위치의 먼지의 인덱스를 구한다.
                        if((p.num & 1<<dustIdx) != 0 && !visited[nx][ny][p.num]){ // 청소한 장소지만 비트마스킹값이 바뀐 이후에 처음 도달하는 경우
                            visited[nx][ny][p.num]=true;
                            dq.offerLast(new Dust(nx, ny, p.num));
                        }else if((p.num & 1<<dustIdx) == 0){ // 청소를 한번도 하지않은 경우
                            int n = p.num | 1<<dustIdx;
                            visited[nx][ny][n]=true;
                            dq.offerLast(new Dust(nx, ny, n));
                        }// 청소한 장소지만 같은 비트마스킹값을 가지고 방문한 경우 아무것도 하지 않는다.
                    }else{ // 아무것도 없는 곳이고 아직 지나가지 않은 경우(큐에 넣기)
                        visited[nx][ny][p.num]=true;
                        dq.offerLast(new Dust(nx, ny, p.num));
                    }
                }
            }
            cnt++;
        }
        return -1;
    }
}
