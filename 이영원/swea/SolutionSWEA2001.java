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
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int answer=0;
            int result = 0;
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            int[][] map = new int[N][N];
            
            for(int i=0;i<N;i++){ // map 입력
                st = new StringTokenizer(br.readLine());
             	for(int j=0;j<N;j++){
                	map[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            
            for(int i=0;i<=N-M;i++){
             	for(int j=0;j<=N-M;j++){
                    result = 0;
                 	for(int k=i;k<i+M;k++){
                     	for(int l=j;l<j+M;l++){
                         	   result += map[k][l];
                        }
                    }
                    answer = Math.max(answer, result);
                }
            }
            
            System.out.println("#" + test_case + " " + answer);

		}
	}
}
