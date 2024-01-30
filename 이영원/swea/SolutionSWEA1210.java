// 반복문
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int T = Integer.parseInt(br.readLine());
            int[][] map = new int[100][100];
            int answer=0;
           	int row=99;
            int col = 99;
            int dir = 1; // 1:위, 2:좌, 3:우
            
            for(int i=0;i<map.length;i++){ // 배열 입력받기
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<map.length;j++){
                    map[i][j]=Integer.parseInt(st.nextToken());
                    if(map[i][j]==2){
                        row=i;
                        col=j;
                    }
                }
            }
            
            while(row!=0){
                if((dir==1 || dir==2) && col-1>=0 && map[row][col-1]==1){
                    dir=2;
                    col--;
                }else if((dir==1 || dir==3) && col+1<100 && map[row][col+1]==1){
                    dir=3;
                    col++;
                }else{
                    dir=1;
                    row--;
                }
            }
            
            System.out.println("#" + T + " " + col);
		}
	}
}

// 재귀
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int row=99;
    static int col=99;
    static boolean[][] visited = new boolean[100][100];
    static int[][] map = new int[100][100];
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int T = Integer.parseInt(br.readLine());
            visited = new boolean[100][100];

            int answer=0;

            int dir = 1; // 1:위, 2:좌, 3:우
            
            for(int i=0;i<map.length;i++){ // 배열 입력받기
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<map.length;j++){
                    map[i][j]=Integer.parseInt(st.nextToken());
                    if(map[i][j]==2){
                        row=i;
                        col=j;
                    }
                }
            }
            
            recursive(row, col);
            
            System.out.println("#" + T + " " + col);
		}
	}
    
    private static void recursive(int x, int y){
        if(x==0){
            col=y;
            return;
        }
        if(y-1>=0 && !visited[x][y-1] && map[x][y-1]==1){
            visited[x][y-1]=true;
            recursive(x, y-1);
        }else if(y+1<100 && !visited[x][y+1] && map[x][y+1]==1){
            visited[x][y+1]=true;
            recursive(x, y+1);
        }else if(!visited[x-1][y] && map[x-1][y]==1){
            visited[x-1][y]=true;
            recursive(x-1, y);
        }
    }
}
