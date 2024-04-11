import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
	
	static int N, answer;
	static int[][] map;
	
	static int[][] dx = { // 좌 하 우 상
			{-1, 1, -2, 2, -1, 1, -1, 1, 0, 0},
			{-1, -1, 0, 0, 0, 0, 1, 1, 2, 1},
			{-1, 1, -2, 2, -1, 1, -1, 1, 0, 0},
			{1, 1, 0, 0, 0, 0, -1, -1, -2, -1}
			};
	
	static int[][] dy = { // 좌 하 우 상
			{1, 1, 0, 0, 0, 0, -1, -1, -2, -1},
			{-1, 1, -2, 2, -1, 1, -1, 1, 0, 0},
			{-1, -1, 0, 0, 0, 0, 1, 1, 2, 1},
			{-1, 1, -2, 2, -1, 1, -1, 1, 0, 0},
			};
	
	static double[] calc = {0.01, 0.01, 0.02, 0.02, 0.07, 0.07, 0.1, 0.1, 0.05};
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	
    	map = new int[N][N];
    	answer=0;
    	
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
    	
    	int cnt = N*2-1;
    	int dir = 3;
    	int move=0;
    	int nx = N/2;
    	int ny = N/2;
    	
    	for (int i = 0; i < N*2-1; i++) {
			if(i%2==0) move++; 
			dir = (dir+1)%4;
			
			if(i==N*2-2) move--;
			
			for (int j = 0; j < move; j++) {
				nx+=dx[dir][dx[0].length-1];
				ny+=dy[dir][dy[0].length-1];
				calculate(nx, ny, dir);
//				print();
			}
		}
    	
    	System.out.println(answer);
    }
    
    private static void calculate(int x, int y, int dir) {
    	int nx = x;
    	int ny = y;
    	
    	int num = map[nx][ny];
    	for (int i = 0; i < dx[0].length-1; i++) {
			nx = x + dx[dir][i];
			ny = y + dy[dir][i];
			int calcNum = (int)(map[x][y]*calc[i]);
			if(calcNum==0) continue;
			if(nx<0 || nx>=N || ny<0 || ny>=N) {
				answer+=calcNum;
			}else {
				map[nx][ny]+=calcNum;
			}
			num-=calcNum;
			
		}
    	
    	map[x][y]=0;
    	nx = x + dx[dir][dx[0].length-1];
    	ny = y + dy[dir][dy[0].length-1];
		if(nx<0 || nx>=N || ny<0 || ny>=N) {
			answer+=num;
		}else {
			map[nx][ny]+=num;
		}
    }
    
    private static void print() {
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
    	System.out.println();
    }
    
}
