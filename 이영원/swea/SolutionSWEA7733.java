import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;
import java.awt.Point;

class Solution
{
    
    static int[][] map;
    static int N;
    static boolean[][] visited; // 방문배열
    static int[] dx = {0,1,0,-1}; // 우 하 좌 상
    static int[] dy = {1,0,-1,0}; // 우 하 좌 상
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = Integer.parseInt(br.readLine());
            map = new int[N+2][N+2]; // 테두리 조건 완화
            visited = new boolean[N+2][N+2]; // 테두리 조건 완화
            boolean[] nums = new boolean[101]; // 어떤 수가 있는지 기록한 배열
            int answer=1;
            int max=0; // 가장 큰 수
            
            Arrays.fill(map[0], -1);
            Arrays.fill(map[N+1], -1);
            Arrays.fill(visited[0], true);
            Arrays.fill(visited[N+1], true);
            for(int i=1;i<N+1;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<N+1;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(!nums[map[i][j]]) nums[map[i][j]]=true;
                    if(max < map[i][j]) max=map[i][j];
                }
                map[i][0] = -1;
                map[i][N+1] = -1;
                visited[i][0] = true;
                visited[i][N+1] = true;
            }

            for(int i=1;i<max;i++){
                if(nums[i]){ // 있는 수만 진행
                    eat(i); // 요정이 해당 치즈들을 먹는다.
                    answer = Math.max(answer, findParts()); // 덩어리 개수를 구한다.
                }
            }
            
            System.out.println("#" + test_case + " " + answer);

		}
	}
    
    private static int findParts(){
        int cnt=0;
        boolean[][] v = new boolean[N+2][N+2]; // 덩어리가 몇개인지 확인할 용도의 배열
        for(int i=1;i<N+1;i++){
            for(int j=1;j<N+1;j++){
                if(!v[i][j] && !visited[i][j]){
                    v[i][j]=true;
                   	//System.out.println(i + " " + j);
                    bfs(i, j, v);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    // 덩어리는 v를 true로 해줌으로써 확인하는 bfs 메소드
    private static void bfs(int x, int y, boolean[][] v){
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(x, y));
        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            for(int i=0;i<4;i++){
                int nx = p.x+dx[i];
                int ny = p.y+dy[i];
                if(map[nx][ny]!=-1 && !visited[nx][ny] && !v[nx][ny]){
                    v[nx][ny]=true;
                    dq.offerLast(new Point(nx, ny));
                }
            }
        }
    }
    
    // 숫자가 같으면 먹는 메소드 map을 -1로 하고, visited를 true로 만든다.
    private static void eat(int num){
        for(int i=1;i<N+1;i++){
            for(int j=1;j<N+1;j++){
                if(map[i][j]==num) {
                    map[i][j]=-1;
                    visited[i][j]=true;
                }
            }
        }
    }
    
}
