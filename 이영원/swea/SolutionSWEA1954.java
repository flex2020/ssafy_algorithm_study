package com.ssafy.recursive;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Hw1_0131 {

	// 델타기법
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());                                

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            
            int nx = 0;
            int ny = 0;
            int dir = 0;
            
            for(int i=1;i<=N*N;i++){
                map[nx][ny]=i;
                // 벽에 부딪히거나, 값이 들어가있는 경우 방향전환
                if(nx+dx[dir]<0 || nx+dx[dir]>=N || ny+dy[dir]<0 || ny+dy[dir]>=N || map[nx+dx[dir]][ny+dy[dir]]!=0) dir=(dir+1)%4;
                nx+=dx[dir];
                ny+=dy[dir];
            }
            
            for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

}
