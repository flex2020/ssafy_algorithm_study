package codepractice;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

class Miro{
	int x;
	int y;
	int cnt;
	public Miro(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class Main {
	
    static int[][] arr;
    static int count=Integer.MAX_VALUE;
    static boolean[][] visited; // 방문배열

    // 하우상좌
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        visited[0][0]=true;

        for (int i = 0; i < arr.length; i++) {
            String input = br.readLine();
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j]=Integer.parseInt(input.charAt(j)+"");
            }
        }

//        dfs(0, 0, 1);
        bfs();
        System.out.println(count);

    }

    private static void dfs(int i, int j, int cnt){
        if(i==arr.length-1 && j==arr[0].length-1){
            count = Math.min(cnt, count);
            return;
        }
        for(int t=0;t<dx.length;t++){
            int nx = i + dx[t];
            int ny = j + dy[t];
            if(nx>=0 && nx<arr.length && ny>=0 && ny<arr[0].length && !visited[nx][ny] && arr[nx][ny]==1){
                visited[nx][ny]=true;
                dfs(nx,ny, cnt+1);
                visited[nx][ny]=false;
            }
        }
    }

    private static void bfs(){
        Deque<Miro> dq = new ArrayDeque<>();
        dq.offerLast(new Miro(0,0,1));
        while(!dq.isEmpty()) {
        	Miro m = dq.pollFirst();
        	if(m.x==arr.length-1 && m.y==arr[0].length-1) {
        		count=m.cnt;
        		return;
        	}
        	for(int i=0;i<4;i++) {
                int nx = m.x + dx[i];
                int ny =m.y + dy[i];
                if(nx>=0 && nx<arr.length && ny>=0 && ny<arr[0].length && !visited[nx][ny] && arr[nx][ny]==1){
                	visited[nx][ny]=true;
                    dq.offerLast(new Miro(nx, ny, m.cnt+1));
                }
        	}
        }
    }

}
