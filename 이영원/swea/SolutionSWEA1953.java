import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.awt.Point;

class Solution
{

    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    // dx, dy 인덱스값으로 pipes 만들어두기
    static int[][] pipes = {{}, {0,1,2,3}, {1,3}, {0,2}, {0,3}, {0,1}, {1,2}, {2,3}};

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 지도 세로길이
            int M = Integer.parseInt(st.nextToken()); // 지도 가로길이
            int R = Integer.parseInt(st.nextToken()); // 맨홀 세로위치
            int C = Integer.parseInt(st.nextToken()); // 맨홀 가로위치
            int L = Integer.parseInt(st.nextToken()); // 소요시간

            int[][] map = new int[N+2][M+2]; // 테두리 채우기
            boolean[][] visited = new boolean[N+2][M+2]; // 방문체크배열
            int time=1;
            long answer=1;

            for(int i=1;i<N+1;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<M+1;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Deque<Point> dq = new ArrayDeque<>();

            dq.offerLast(new Point(R+1, C+1));
            visited[R+1][C+1]=true;



            while(!dq.isEmpty()){
                int size = dq.size();
                for(int k = 0 ; k<size;k++){
                    Point p = dq.pollFirst();

                    for(int i=0;i<pipes[map[p.x][p.y]].length;i++){
                        int nx = p.x + dx[pipes[map[p.x][p.y]][i]];
                        int ny = p.y + dy[pipes[map[p.x][p.y]][i]];
                        if(map[nx][ny]!=0 && !visited[nx][ny]){
                            boolean flag = false;
                            for(int j=0;j<pipes[map[nx][ny]].length;j++){
                                // 현재 가고자 하는 방향이 이어져있는지 반복문을 돌려가면서 확인(있다면 flag를 true로 만들어주기)
                                // 반대 방향이어야 이어진 것이므로 +2 %4를 해준다.
                                if((dx[(pipes[map[p.x][p.y]][i] +2)%4 ] == dx[pipes[map[nx][ny]][j]]) && (dy[(pipes[map[p.x][p.y]][i] +2)%4 ] == dy[pipes[map[nx][ny]][j]])) {
                                    flag = true;
                                }
                            }
                            if(flag){ // 있다면 방문체크하고 dq에 넣기
                                answer++;
                                visited[nx][ny]=true;
                                dq.offerLast(new Point(nx, ny));
                            }

                            //System.out.println(answer + " " + nx + " " + ny);
                        }
                    }
                }

                time++; // 시간 올려주기
                if(time==L) break;
            }

            if(L==1) answer=1; // 이것때문에 10분 추가..

            System.out.println("#" + test_case + " " + answer);
        }
    }
}
