import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] dx = {-1,-1,0,1,1,1,0,-1}; // 델타 기법
        int[] dy = {0,1,1,1,0,-1,-1,-1};
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int answer=0;
            int result = 0;
            int N = Integer.parseInt(br.readLine());
            char[][] map = new char[N+2][N+2]; // 지원님 외곽 채우기 전략 써보기
            
            Arrays.fill(map[0], 'G');
            Arrays.fill(map[map.length-1], 'G');
            for(int i=1;i<map.length-1;i++){
                map[i][0]='G';
                map[i][map.length-1]='G';
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<map.length-1;j++){
                    map[i][j]=st.nextToken().charAt(0);
                }
            }
            
            for(int i=1;i<map.length-1;i++){
                for(int j=1;j<map.length-1;j++){
                    result=0;
                   	for(int mv=0;mv<dx.length;mv++){ // 돌려가면서 최대값 찾기
                        if(map[i+dx[mv]][j+dy[mv]]=='W') result++;
                    }
                    answer = Math.max(answer, result);
                }
            }
            
            
            System.out.println("#" + test_case + " " + answer);

		}
	}
}
