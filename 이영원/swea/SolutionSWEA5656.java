import java.io.*;
import java.util.*;
import java.awt.Point;

class Solution
{
    
    static int N, W, H;
    static int[][] map;
    static int[] sel;
    static int answer;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            sel = new int[N];
            answer=Integer.MAX_VALUE;
            map = new int[H][W];
            for(int i=0;i<H;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<W;j++){
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            perm(0);
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
    private static void perm(int cnt){
        // basis part
        if(cnt == sel.length){
            play();
            return;
        }
        // inductive part
        for(int i=0;i<W;i++){
            sel[cnt]=i;
            if(answer==0) return;
            perm(cnt+1);
            if(answer==0) return;
        }
    }
    
    private static void play(){
        int[][] tmpMap = new int[H][W];
        // 깊은복사
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                tmpMap[i][j] = map[i][j];
            }
        }
        
//        System.out.println(Arrays.toString(sel));
        
//        if(sel[0]==2 && sel[1]==2 && sel[2]==6) {
//        	int a=3;
//        	
//        }
        
        for(int i=0;i<sel.length;i++){
            Point p = null;
            for(int j=0;j<H;j++){
                if(tmpMap[j][sel[i]]!=0){
                    p = new Point(j, sel[i]);
                    break;
                }
            }
            if(p==null) continue;
            tmpMap=crash(tmpMap, p); // 부수기
            clean(tmpMap); // 치우기
            move(tmpMap); // 옮기기
//            print(tmpMap);
        }

        
        answer = Math.min(answer, count(tmpMap)); // 세고 넣기
//        System.out.println(answer);
    }
    
    private static int[][] crash(int[][] map, Point start){
        int[][] tmpMap = new int[H][W];
        boolean[][] visited = new boolean[H][W];
        // 깊은복사
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                tmpMap[i][j] = map[i][j];
            }
        }
        
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(start);
        
        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            int num = map[p.x][p.y];
            tmpMap[p.x][p.y]=0;
            for(int i=0;i<4;i++){
            	int nx=p.x;
            	int ny=p.y;
                for(int j=0;j<map[p.x][p.y]-1;j++){
                    nx += dx[i];
                	ny += dy[i];
                    if(nx<0 || nx>=H || ny<0 || ny>=W || visited[nx][ny]) continue;
                    visited[nx][ny]=true;
                    dq.offerLast(new Point(nx, ny));
                    tmpMap[nx][ny]=0;
                }
            }
        }
        return tmpMap;
    }
    
    private static void clean(int[][] map){
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
               	if(map[i][j]<0) map[i][j]=0;
            }
        }
    }
    
    private static void move(int[][] map){
		for(int i=0;i<W;i++){
            Deque<Integer> dq = new ArrayDeque<>();
            for(int j=0;j<H;j++){
                if(map[j][i]!=0) {
                    dq.offerLast(map[j][i]);
                    map[j][i]=0;
                }
            }
            int idx=H-1;
            while(!dq.isEmpty()){
                if(map[idx][i]==0){
                    map[idx][i]=dq.pollLast();
                }
                idx--;
            }
        }
    }
    
    private static void print(int[][] map){
        System.out.println();
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private static int count(int[][] map){
        int result=0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j]>0) result++;
            }
        }
        return result;
    }
                          
}
