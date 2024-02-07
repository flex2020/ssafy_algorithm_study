import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{

    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;



    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = Integer.parseInt(br.readLine());
            int answer=0;
            int result =0;
            arr = new int[N][N];
            visited = new boolean[N][N];
            for(int i=0;i<N;i++){ // 입력
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    visited[i][j]=true;
                    int cand = dfs(i, j, 1);
                    visited[i][j]=false;
                    if(result==cand){
                        answer=Math.min(answer, arr[i][j]);
                    }
                    else if(result<cand){
                        answer=arr[i][j];
                        result=cand;
                    }
                }
            }
            System.out.println("#" + test_case + " " + answer + " " + result);

        }
    }

    private static int dfs(int i, int j, int cnt){
        int result=cnt;
        for(int d=0;d<4;d++){
            int nx = i+dx[d];
            int ny = j+dy[d];
//
            if(nx>=0 && nx<arr.length && ny>=0 && ny<arr.length && arr[nx][ny]-arr[i][j]==1 && !visited[nx][ny]){
//                System.out.println(arr[nx][ny]);
//                System.out.println(arr[i][j]);
//                System.out.println(Math.abs(arr[nx][ny]-arr[i][j]));
                visited[nx][ny]=true;
                result=Math.max(dfs(nx, ny, cnt+1), result);
                visited[nx][ny]=false;
            }
        }
        return result;
    }

}
