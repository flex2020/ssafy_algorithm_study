import java.io.*;
import java.util.*;

class Solution
{
    
    static int N, X;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static boolean[][] check; // 겹치는지 체크하기 위한거
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int answer=0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            check = new boolean[N][N];
            
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
					map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 가로체크
            for(int i=0;i<N;i++){
                int prev = map[i][0];
                for(int j=1;j<N;j++){
                    if(prev < map[i][j]){
                        if(map[i][j]-prev!=1) break;
                        if(!leftCheck(i, j, 2)) break;// 왼쪽체크
                    }else if(prev > map[i][j]){
                        if(prev-map[i][j]!=1) break;
                        if(!rightCheck(i, j, 0)) break; // 오른쪽체크
                    }
                    if(j==N-1) {
//                    	System.out.println(Arrays.toString(map[i]));
                    	answer++;
                    }
                    prev = map[i][j];
                }
            }
            
            check = new boolean[N][N];
            
            // 세로체크
            for(int i=0;i<N;i++){
                int prev = map[0][i];
                for(int j=1;j<N;j++){
                    if(prev < map[j][i]){
                        if(map[j][i]-prev!=1) break;
                        if(!leftCheck(j, i, 3)) break; // 위체크
                    }else if(prev > map[j][i]){
                        if(prev-map[j][i]!=1) break;
                        if(!rightCheck(j, i, 1)) break; // 아래체크
                    }
                    if(j==N-1) {
//                    	System.out.println(i);
                    	answer++;
                    }
                    prev = map[j][i];
                }
            }

			System.out.println("#" + test_case + " " + answer);
		}
	}
    
    private static boolean leftCheck(int x, int y, int dir){
		int nx = x + dx[dir];
        int ny = y + dy[dir];
        int cnt=1;
        int original = map[nx][ny];
        check[nx][ny]=true;
        for(int i=0;i<X;i++){
            nx += dx[dir];
            ny += dy[dir];
            if(nx<0 || nx>=N || ny<0 || ny>=N || check[nx][ny]) break;
            if(map[nx][ny]==original) {
            	cnt++;
            	check[nx][ny]=true;
            }
            else{
                return false;
            }
            if(cnt==X) return true;
        }
        return false;
    }
    
    private static boolean rightCheck(int x, int y, int dir){
        int nx = x;
        int ny = y;
        int cnt=1;
        int original = map[nx][ny];
        check[nx][ny]=true;
        for(int i=0;i<X;i++){
            nx += dx[dir];
            ny += dy[dir];
            if(nx<0 || nx>=N || ny<0 || ny>=N || check[nx][ny]) break;
            if(map[nx][ny]==original) {
            	cnt++;
            	check[nx][ny]=true;
            }
            else{
                return false;
            }
            if(cnt==X) return true;
        }
        return false;
    }
}
