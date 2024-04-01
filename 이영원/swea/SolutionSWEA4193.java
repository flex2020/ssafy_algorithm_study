import java.io.*;
import java.util.*;
import java.awt.Point;

class Solution
{
    
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static Point start;
    static Point end;
    static boolean[][] visited;
    static int[][] map;
    static int N;
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int answer=0;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            st = new StringTokenizer(br.readLine());
            start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                                  
            visited = new boolean[N][N];
            
            answer = bfs();
            
			System.out.println("#" + test_case + " " + answer);
		}
	}
                           
    private static int bfs(){
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(start.x, start.y));
        visited[start.x][start.y]=true;
        int time=0;
        while(!dq.isEmpty()){
            int size=dq.size();
            for(int d=0;d<size;d++){
                Point p = dq.pollFirst();
                if(p.x==end.x && p.y==end.y) return time;
                for(int i=0;i<4;i++){
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny] || map[nx][ny]==1) continue;
                    if(map[nx][ny]==2 && time%3!=2){ // 소용돌이인데 지나갈 수 없는 경우 현재위치 dq에 넣는다.
                    	dq.offerLast(new Point(p.x, p.y));
                    	visited[p.x][p.y]=true;
                    }else{ // 지나갈 수 있는 경우 다음 위치를 dq에 넣는다.
                        dq.offerLast(new Point(nx, ny));
                        visited[nx][ny]=true;
                    }
                }
               
            }
//            System.out.println(dq.toString());
            time++;
        }
        return -1;
    }
}
