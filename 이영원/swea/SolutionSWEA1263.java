import java.util.*;
import java.io.*;

class Solution
{
    
    static final int INF = 600000;
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int answer =INF;
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] adjMatrix = new int[N][N];
            int[][] D = new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            for(int i=0;i<N-1;i++){ // 초기화
                for(int j=i+1;j<N;j++){
                    if(i==j) D[i][j]=0;
                    else if(adjMatrix[i][j]==1){
                        D[i][j]=1;
                        D[j][i]=1;
                    }else{
                        D[i][j]=INF;
                        D[j][i]=INF;
                    }
                }
            }
            
            
            for(int k=0;k<N;k++){
                for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                        D[i][j] = Math.min(D[i][k]+D[k][j], D[i][j]);
                    }
                }
            }
            
            for(int i=0;i<N;i++){
                int result =0;
                for(int j=0;j<N;j++){
                    if(D[i][j]>=INF) continue;
                    result+=D[i][j];
                }
                answer = Math.min(result, answer);
            }
			System.out.println("#" + test_case + " " + answer); 
		}
	}
}
